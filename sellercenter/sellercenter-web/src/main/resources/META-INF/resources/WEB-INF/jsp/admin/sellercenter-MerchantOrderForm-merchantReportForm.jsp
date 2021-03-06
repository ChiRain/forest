<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>
<title>订单管理</title>
<style>
    .select-product-dialog {
        top: 50px;
    }

    .select-product-dialog tr {
        word-break: break-all;
    }

    .select-product-dialog .modal-content {
        min-height: 200px;
        min-width: 700px;
    }

    /*.select-dialog .modal-body{*/
    /*padding: 0;*/
    /*}*/
</style>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
       报表
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            订单列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                        </div>
                    </div>
                    <div class="col-xs-6">
	                    <div class="text-right">
	                        <label>
	                            <form action="#admin/merchantOrderForm/merchantReportForm" onsubmit="listFormSearch(this); return false">
	                                <div class="dataTables_length">                                          	                            	
		                                   	 查询类型
		                                   	<select class="form-control" id="type" name="type">
	                                        	<option value="0">请选择</option>
	                                         	<option value="1">月份</option>
	                                         	<option value="2">季度</option>
	                                         	<option value="3">年份</option>
			                                </select>
			                                <select class="form-control" id="value" name="value">
	                                        	<option value="0">请选择</option>
			                                </select>
				                      		&nbsp;&nbsp;
	                                   		<button type="submit" class="btn btn-purple btn-sm">
	                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
	                                            查询
	                                        </button>                                        
	                                 </div>
	                            </form>
	                        </label>
	                    </div>
	                </div>                
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                        <th>总单号</th>
                        <th>子单号</th>
						<th>订单金额</th>
                        <th>下单用户</th>
                        <th>收货人</th>
                        <th>收货地址</th>
                        <th>收货人电话</th>
                        <th>状态</th>
                        <th>下单时间</th>
                        <th>自提门店</th>
                        <th>是否开发票</th>
                     <%--	<th>发货门店</th>
                       <th class="sorting_disabled" width="80px">操作</th>   --%>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>                                       
                                <td>${item.orderNumber}</td>
                                <td>${item.subOrderNumber}</td>
								<td>${item.sum}</td>
                                <td>${item.nickname}</td> 
                                <td>${item.consignee}</td>
                                <td>
                                	<span title="${item.address}">
                                	<c:choose>
										<c:when test="${fn:length(item.address)>10}">
											${fn:substring(item.address,0, 10)}...
										</c:when>
										<c:otherwise>
											${item.address}
										</c:otherwise>
									</c:choose>
									</span>
                                </td>
                                <td>${item.mobile}</td> 
                                <td>
                                	<span class="label
                                	<c:if test="${item.state eq 7}">
                                		label-success arrowed
                                	</c:if>
                                	<c:if test="${item.state eq 8 || item.state eq 9}">
                                		label-warning
                                	</c:if>
                                	<c:if test="${item.state eq 5}">
                                		label-danger arrowed-in
                                	</c:if>
                                	<c:if test="${item.state eq 6}">
                                		label-info arrowed-in-right arrowed
                                	</c:if>
                                	<c:if test="${item.state eq 2 || item.state eq 3}">
                                		label-inverse
                                	</c:if>">
                                	${item.stateStr}
                                	</span>
                                </td>
								<td>
									<fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss" var="time"/>
									${time}
								</td>
								<td>
									<span title="${item.pickupAddressStr}">
									<c:choose>
										<c:when test="${fn:length(item.pickupAddressStr)>10}">
											${fn:substring(item.pickupAddressStr,0, 10)}...
										</c:when>
										<c:otherwise>
											${item.pickupAddressStr}
										</c:otherwise>
									</c:choose>
									</span>
								</td>
								<td>
									<c:if test="${item.needInvoiceType eq 1}">
										<span style="color:red;">是</span>
                                	</c:if>
								</td>
								<c:if test="${confirmType ne query.state}">
									<td>
										  ${item.storeName}
                                    </td>
								</c:if>
                             <%--   <td>
	                                <div class="hidden-sm hidden-xs action-buttons">
	                                	<fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd" var="time"/>
	                                	<a title="查看" class="blue" href="#admin/subOrder/listDetail?merchantOrderFormId=${item.id}&subOrderId=${item.subOrderId}&time=${time}">
	                                        <i class="ace-icon fa fa-eye bigger-130"></i>
	                                    </a>
	                                    &nbsp;
	                                    <c:if test="${shipType eq query.state}">
		                                    <a title="发货" class="green deliverOrder-item" href="javascript:;" data-subOrderId="${item.subOrderId}" data-time="${item.time}"
		                                    api-path="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()%>/orderState/deliverOrder.do?subOrderId=${item.subOrderId}&orderDate=${time}">
		                                        <i class="ace-icon fa fa-truck bigger-130"></i>
		                                    </a>
		                                    &nbsp;
	                                    </c:if>
	                                    <c:if test="${confirmType eq query.state}">
	                                    <a title="确认订单" class="green confirmOrder-item" href="javascript:;" data-subOrderId="${item.subOrderId}" data-time="${item.time}" 
	                                    api-path="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()%>/orderState/confirmOrder.do?subOrderId=${item.subOrderId}&orderDate=${time}">
	                                        <i class="ace-icon glyphicon glyphicon-ok"></i>
	                                    </a>
	                                    </c:if>
	                                </div>
                            	</td>  --%>
                        </tr>
						</c:forEach>
                    </tbody>
                </table>    
                <div class="row" >
                    <div class="col-xs-12" style="float:right;">                  
                     		￥:  ${sum} 元
                    </div>
                </div>          
            </div>
        </div>
    </div>
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
         $('.confirmOrder-item').on('click', function () {
            var delUrl = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认订单',
                message: '确认执行此操作？',
                buttons: [{
                    id: 'btn-1',
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(delUrl, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === -599) {
                                        dialogItself.setTitle('执行失败');
                                        dialogItself.setMessage(data["message"]);
                                        //dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                        dialogItself.getButton('btn-1').remove();
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("操作成功!");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                         dialogItself.getButton('btn-1').remove();
                                        setTimeout(function () {
                                            dialogItself.close();
                                        }, 1000);
                                        setTimeout(function () {
                                            location.reload();
                                        }, 1500);
                                    }
                                });
                    }
                }, {
                    label: '取消',
                    action: function (dialogItself) {
                        dialogItself.close();
                    }
                }]
            });
        });
        
         $(".deliverOrder-item").on("click", function () {
         	var url = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认发货',
                message: $('<div></div>').load('/admin/merchantOrderForm/toAddLogistics.do'),
                cssClass: "select-product-dialog",
                buttons: [{
                	id: 'btn-2',
                    label: '保存',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(url + "&" + $("#myForm").serialize(),
	                        function (data) {
	                        	data = JSON.parse(data);
	                        	if (parseInt(data["status"]) != 200) {
                                        dialogItself.setTitle('执行失败');
                                        dialogItself.setMessage("请检查门店库存!");
                                        dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                        dialogItself.getButton('btn-2').remove();
                                        dialogItself.getButton('btn-3').remove();
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("操作成功!");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                        dialogItself.getButton('btn-2').remove();
                                        setTimeout(function () {
                                            dialogItself.close();
                                            location.reload();
                                        }, 1000);
                                        setTimeout(function () {
                                            location.reload();
                                        }, 1500);
                                    }
	                        }
                        );
                    }
                }, {
                	id: 'btn-3',
                    label: '取消',
                    action: function (dialogItself) {
                        dialogItself.close();
                    }
                }],
                onshow: function (dialog) {
                	 
                }
            });
        });
        
        $("#type").change(function(){
        	var value=$(this).val();
        	var str="";
        	if(value==0){
        		str+="<option value='0' >请选择</option>";
        	}else if(value==1){
        		for(var i=1;i<13;i++){
        			str+="<option value='"+i+"'>"+i+"月份";
        			str+="</option>";
        		}
        	}else if(value==2){
        		str+="<option value='1' >春季</option>";
    			str+="<option value='2' >夏季</option>";
    			str+="<option value='3' >秋季</option>";
    			str+="<option value='4' >冬季</option>";
        	}else if(value==3){
        		str+="<option value='1' >去年</option>";
    			str+="<option value='2' >今年</option>";
        	}
        	$("#value").html(str);
        });
    });
</script>
