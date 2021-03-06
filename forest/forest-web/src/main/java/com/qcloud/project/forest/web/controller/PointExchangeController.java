package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.goods.service.MerchandiseImageService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.service.PointMerchandiseService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.handler.MerchandiseEvaluationHandler;
import com.qcloud.component.goods.web.vo.PointMerchandiseListVO;
import com.qcloud.component.goods.web.vo.PointMerchandiseVO;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QGrade;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicservice.ShareClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.service.RangeGradeService;

@Controller
@RequestMapping(value = PointExchangeController.DIR)
public class PointExchangeController {

    public static final String        DIR                    = "/pointExchange";

    // @Autowired
    // ExchangeMerchandiseHandler exchangeMerchandiseHandler;
    @Autowired
    CommoditycenterClient             commoditycenterClient;

    @Autowired
    MerchandiseService                merchandiseService;

    @Autowired
    FileSDKClient                     fileSDKClient;

    @Autowired
    MerchandiseSpecificationsService  merchandiseSpecificationsService;

    @Autowired
    MerchandiseImageService           merchandiseImageService;

    @Autowired
    MerchandiseEvaluationService      merchandiseEvaluationService;

    @Autowired
    MerchandiseEvaluationHandler      merchandiseEvaluationHandler;

    @Autowired
    SellercenterClient                sellercenterClient;

    @Autowired
    PersonalcenterClient              personalcenterClient;

    @Autowired
    PublicdataClient                  publicdataClient;

    @Autowired
    MyClient                          myClient;

    @Autowired
    private ShareClient               shareClient;

    final static String               XML_MESSAGE_KEY        = "commoditycenter-pointexchange-message";

    final static String               currencyexchange_range = "commoditycenter-pointexchange-range";

    @Autowired
    private ParameterClient           parameterClient;

    @Autowired
    private UnifiedMerchandiseService unifiedMerchandiseService;

    @Autowired
    private RangeGradeService         rangeGradeService;

    // @RequestMapping
    // public FrontAjaxView list(HttpServletRequest request, Double point, String keywords, PPage pPage) {
    //
    // point = point == null || point.longValue() <= 0 ? Long.MAX_VALUE : point;
    // List<MerchandiseMarketing> list = merchandiseMarketingService.list(6, keywords, point, pPage.getPageStart(), pPage.getPageSize());
    // QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
    // // 已经登录
    // if (user != null) {
    // myClient.addMySearchHistory(user.getId(), CommoditycenterClient.SEARCH_TYPE, keywords);
    // }
    // publicdataClient.addSearch(CommoditycenterClient.SEARCH_TYPE, keywords);
    // List<ExchangeMerchandiseListVO> voList = exchangeMerchandiseHandler.toVOList(list);
    // FrontAjaxView view = new FrontAjaxView();
    // view.setMessage("获取兑换商品成功.");
    // view.addObject("list", voList);
    // //
    // List<KeyValueVO> ddList = publicdataClient.listDataDictionaryByType(currencyexchange_range);
    // view.addObject("rangeList", ddList);
    // //
    // String title = "";
    // String message = "";
    // Xml xml = XmlFactory.get(XML_MESSAGE_KEY);
    // if (xml != null) {
    // List<XmlItem> messageList = xml.getItemList();
    // for (XmlItem xmlItem : messageList) {
    // if ("title".equals(xmlItem.getAttrMap().get("name"))) {
    // title = xmlItem.getText();
    // }
    // if ("message".equals(xmlItem.getAttrMap().get("name"))) {
    // message = xmlItem.getText();
    // }
    // }
    // }
    // view.addObject("title", title);
    // view.addObject("message", message);
    // return view;
    // }
    //
    // @RequestMapping
    // public FrontAjaxView get(Long unifiedMerchandiseId) {
    //
    // QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
    // AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在." + unifiedMerchandiseId);
    // AssertUtil.assertTrue(MerchandiseType.MARKETING.equals(unifiedMerchandise.getType()), "这个接口仅提供积分兑换商品数据." + unifiedMerchandiseId);
    // AssertUtil.assertTrue(unifiedMerchandise.getSence() == 6, "这个接口仅提供积分兑换商品数据." + unifiedMerchandiseId);
    //
    // merchandiseItemService.get
    //
    // MerchandiseItem merchandiseItem = merchandiseItemService.get(unifiedMerchandise.getList().get(0).getId());
    // AssertUtil.assertNotNull(merchandiseItem, "单一商品不存在." + unifiedMerchandiseId);
    // Merchandise merchandise = merchandiseService.get(merchandiseItem.getMerchandiseId());
    // AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseItem.getMerchandiseId());
    // ExchangeMerchandiseVO exchangeMerchandiseVO = new ExchangeMerchandiseVO();
    // exchangeMerchandiseVO.setDesc(merchandise.getDesc());
    // exchangeMerchandiseVO.setMerchandiseId(merchandise.getId());
    // exchangeMerchandiseVO.setMerchantId(merchandise.getMerchantId());
    // exchangeMerchandiseVO.setName(merchandiseItem.getName());
    // exchangeMerchandiseVO.setStock(unifiedMerchandise.getStock());
    // // exchangeMerchandiseVO.setSysCode(merchandise.getSysCode());
    // exchangeMerchandiseVO.setUnifiedMerchandiseId(unifiedMerchandiseId);
    // exchangeMerchandiseVO.setCurrency(unifiedMerchandise.getDiscount());
    // exchangeMerchandiseVO.setPrice(merchandiseItem.getPrice());
    // if (StringUtils.isNotEmpty(merchandise.getImage())) {
    // exchangeMerchandiseVO.setImage(fileSDKClient.getFileServerUrl() + merchandise.getImage());
    // }
    // long sum = merchandiseItem.getGoodEvaluation() + merchandiseItem.getMiddleEvaluation() + merchandiseItem.getLowEvaluation();
    // exchangeMerchandiseVO.setEvaluationNumber(sum);
    // // 规格
    // MerchandiseSpecifications merchandiseSpecifications = merchandiseSpecificationsService.get(merchandiseItem.getMerchandiseSpecificationsId());
    // if (merchandiseSpecifications != null) {
    // List<String> imageStrList = new ArrayList<String>();
    // if (merchandiseSpecifications.getAttributeId0() > 0) {
    // List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId0(), merchandiseSpecifications.getValue0());
    // for (MerchandiseImage merchandiseImage : imageList) {
    // if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
    // String[] strs = merchandiseImage.getImage().split(",");
    // for (int index = 0; index < strs.length; index++) {
    // imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
    // }
    // }
    // }
    // }
    // if (merchandiseSpecifications.getAttributeId1() > 0) {
    // List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId1(), merchandiseSpecifications.getValue1());
    // for (MerchandiseImage merchandiseImage : imageList) {
    // if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
    // String[] strs = merchandiseImage.getImage().split(",");
    // for (int index = 0; index < strs.length; index++) {
    // imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
    // }
    // }
    // }
    // }
    // if (merchandiseSpecifications.getAttributeId2() > 0) {
    // List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId2(), merchandiseSpecifications.getValue2());
    // for (MerchandiseImage merchandiseImage : imageList) {
    // if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
    // String[] strs = merchandiseImage.getImage().split(",");
    // for (int index = 0; index < strs.length; index++) {
    // imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
    // }
    // }
    // }
    // }
    // exchangeMerchandiseVO.setImageList(imageStrList);
    // }
    // // 为空则取默认图
    // if (CollectionUtils.isEmpty(exchangeMerchandiseVO.getImageList())) {
    // List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), -1L, "-1");
    // List<String> imageStrList = new ArrayList<String>();
    // for (MerchandiseImage merchandiseImage : imageList) {
    // if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
    // String[] strs = merchandiseImage.getImage().split(",");
    // for (int index = 0; index < strs.length; index++) {
    // imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
    // }
    // }
    // }
    // exchangeMerchandiseVO.setImageList(imageStrList);
    // }
    // exchangeMerchandiseVO.setShareUrl(shareClient.getShareDomain() + "/integralMerchandiseShare.html?unifiedMerchandiseId=" + unifiedMerchandiseId);
    // //
    // Page<MerchandiseEvaluation> page = merchandiseEvaluationService.page(merchandiseItem.getMerchandiseId(), StarLevelType.ALL, 0, 1);
    // MerchandiseEvaluationVO merchandiseEvaluationVO = null;
    // if (CollectionUtils.isNotEmpty(page.getData())) {
    // List<MerchandiseEvaluationVO> voList = merchandiseEvaluationHandler.toVOList(page.getData());
    // merchandiseEvaluationVO = voList.get(0);
    // }
    // MerchantMerchandiseVO merchantMerchandiseVO = new MerchantMerchandiseVO();
    // int collectNumber = myClient.countMerchandiseCollectionNumber(unifiedMerchandiseId);
    // merchantMerchandiseVO.setCollectNumber(collectNumber);
    // int merchandiseNumber = merchandiseService.countMerchantOnlineNumber(merchandiseItem.getMerchantId());
    // merchantMerchandiseVO.setMerchandiseNumber(merchandiseNumber);
    // merchantMerchandiseVO.setMerchantId(merchandiseItem.getMerchantId());
    // QMerchant merchant = sellercenterClient.getMerchant(merchandiseItem.getMerchantId());
    // merchantMerchandiseVO.setMerchantName(merchant.getName());
    // if (StringUtils.isEmpty(merchant.getProvince())) {
    // merchantMerchandiseVO.setDeliveryPlace(StringUtil.nullToEmpty(merchant.getCity()));
    // } else {
    // merchantMerchandiseVO.setDeliveryPlace(merchant.getProvince() + " " + StringUtil.nullToEmpty(merchant.getCity()));
    // }
    // FrontAjaxView view = new FrontAjaxView();
    // view.setMessage("获取兑换商品成功.");
    // view.addObject("merchandise", exchangeMerchandiseVO);
    // view.addObject("evaluation", merchandiseEvaluationVO == null ? new HashMap<String, String>() : merchandiseEvaluationVO);
    // return view;
    // }
    @PiratesApp
    @RequestMapping
    public FrontAjaxView exchangRange() {

        List<QClassify> list = publicdataClient.listClassifyForTree((long) 300);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("list", list);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listMerchandise(Long rangeId, UnifiedMerchandiseQuery query) {

        query.setQueryItemType(QueryItemType.S);
        query.setType(UnifiedMerchandiseType.Factory.get(PointMerchandiseService.unifiedMerchandise_type).getKey());
        List<RangeGrade> gradeList = rangeGradeService.listByRange(rangeId);
        List<PointMerchandiseListVO> merchandiseList = new ArrayList<PointMerchandiseListVO>();
        for (RangeGrade rangeGrade : gradeList) {
            query.setActivityId(rangeGrade.getId());
            Page<UnifiedMerchandise> page = unifiedMerchandiseService.page(query, 0, Integer.MAX_VALUE);
            PointMerchandiseListVO merchandiseListVO = new PointMerchandiseListVO();
            merchandiseListVO.setId(rangeGrade.getId());
            QGrade grade = personalcenterClient.getGrade(rangeGrade.getGradeId());
            merchandiseListVO.setName(grade.getName() + "可兑换");
            List<PointMerchandiseVO> merchandiseVO = new ArrayList<PointMerchandiseVO>();
            for (UnifiedMerchandise um : page.getData()) {
                QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(um.getId());
                PointMerchandiseVO merchandise = new PointMerchandiseVO();
                merchandise.setDesc("");
                merchandise.setDiscount(unifiedMerchandise.getDiscount());
                merchandise.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
                merchandise.setIntegral(unifiedMerchandise.getIntegral());
                merchandise.setName(unifiedMerchandise.getName());
                merchandise.setPrice(unifiedMerchandise.getPrice());
                merchandise.setUnifiedMerchandiseId(unifiedMerchandise.getId());
                merchandise.setSpecifications(unifiedMerchandise.getSpecifications());
                QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
                merchandise.setMerchantName(merchant.getName());
                merchandise.getImageList().add(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
                merchandiseVO.add(merchandise);
            }
            merchandiseListVO.setMerchandiseList(merchandiseVO);
            merchandiseList.add(merchandiseListVO);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("list", merchandiseList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getDetails(Long unifiedMerchandiseId) {

        //
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在.");
        PointMerchandiseVO merchandise = new PointMerchandiseVO();
        merchandise.setDesc("");
        merchandise.setDiscount(unifiedMerchandise.getDiscount());
        merchandise.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
        merchandise.setIntegral(unifiedMerchandise.getIntegral());
        merchandise.setName(unifiedMerchandise.getName());
        merchandise.setPrice(unifiedMerchandise.getPrice());
        merchandise.setUnifiedMerchandiseId(unifiedMerchandise.getId());
        merchandise.setSpecifications(unifiedMerchandise.getSpecifications());
        QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
        merchandise.setMerchantName(merchant.getName());
        //
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("merchandise", merchandise);
        return view;
    }
}
