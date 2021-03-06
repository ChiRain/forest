package com.qcloud.project.forest.web.controller.admin;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.exception.ForestException;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.key.TypeEnum.IntegralOrderStateType;
import com.qcloud.project.forest.model.query.IntegralOrderQuery;
import com.qcloud.project.forest.service.IntegralOrderService;
import com.qcloud.project.forest.web.handler.IntegralOrderHandler;
import com.qcloud.project.forest.web.vo.ExpressVO;
import com.qcloud.project.forest.web.vo.admin.AdminIntegralOrderVO;

@Controller
@RequestMapping(value = "/" + AdminIntegralOrderController.DIR)
public class AdminIntegralOrderController {

    public static final String   DIR             = "admin/integralOrder";

    public static final String   XML_Express_KEY = "express";

    @Autowired
    private IntegralOrderService integralOrderService;

    @Autowired
    private IntegralOrderHandler integralOrderHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, IntegralOrderQuery query) {

        Page<IntegralOrder> page = integralOrderService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminIntegralOrderVO> list = integralOrderHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/forest-IntegralOrder-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/forest-IntegralOrder-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(IntegralOrder integralOrder) {

        integralOrderService.add(integralOrder);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        IntegralOrder integralOrder = integralOrderService.get(id);
        AdminIntegralOrderVO adminIntegralOrderVO = integralOrderHandler.toVO4Admin(integralOrder);
        ModelAndView model = new ModelAndView("/admin/forest-IntegralOrder-edit");
        model.addObject("integralOrder", adminIntegralOrderVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(IntegralOrder integralOrder, String queryStr) {

        integralOrderService.update(integralOrder);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        integralOrderService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView shipOrder(HttpServletRequest request, Long orderId, String expressCode, String expressName, String expressNumber) {

        AssertUtil.assertNotEmpty(expressName, "快递公司不能为空.");
        AssertUtil.assertNotEmpty(expressNumber, "快递单号不能为空.");
        AssertUtil.greatZero(orderId, "订单id不能为空.");
        IntegralOrder order = integralOrderService.getByOrder(orderId);
        AssertUtil.assertNotNull(order, "订单数据不存在.");
        if (order.getState() == IntegralOrderStateType.TO_SHIP.getKey()) {
            order.setState(IntegralOrderStateType.TO_SIGN.getKey());
            order.setExpressCode(expressCode);
            order.setExpressName(expressName);
            order.setExpressNumber(expressNumber);
            integralOrderService.update(order);
        } else {
            throw new ForestException("已经发货了.");
        }
        AceAjaxView view = new AceAjaxView();
        view.setMessage("订单发货成功");
        view.setUrl(DIR + "/list");
        return view;
    }

    @RequestMapping
    public ModelAndView shipOrderForm(Long orderId) {

        Xml xml = XmlFactory.get(XML_Express_KEY);
        List<ExpressVO> expressVOs = new ArrayList<ExpressVO>();
        if (xml != null) {
            List<XmlItem> messageList = xml.getItemList();
            ExpressVO expressVO = null;
            for (XmlItem xmlItem : messageList) {
                expressVO = new ExpressVO();
                expressVO.setName(xmlItem.getAttrMap().get("name"));
                expressVO.setCode(xmlItem.getText());
                expressVOs.add(expressVO);
            }
        }
        ModelAndView modelAndView = new ModelAndView("/admin/forest-ShipOrder-edit");
        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("expressVOs", expressVOs);
        return modelAndView;
    }
}
