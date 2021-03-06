<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>新增商品属性定义</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
       商品属性定义管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/classifyAttribute/addClassifyAttr.do">
            <!-- #section:elements.form -->
			      	
			<input type="hidden" id="classifyId" name="classifyId" value="${classifyId}"/>
						      	
			      	
                  <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 名称 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="名称" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="code">编码 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" onKeyUp="value=value.replace(/[^\a-\z\A-\Z]/g,'')" class="width-100" maxlength="25" id="code" name="code" placeholder="code" value=""/>
						<i class="ace-icon fa"></i>
					</span>
					<span style="color:red;">(只能输入英文字母)</span>
                </div>
            </div> 
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="remark">说明 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="100" id="remark" name="remark" placeholder="说明,可区分相同的名称" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>             
                       
                <!--  	<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="enumeration"> 枚举定义名称 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="enumeration" name="enumeration">
							  <option value="">属性可以选择一个枚举类型</option>
					 		  <c:forEach items="${enumerationList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			            </select> 	
					</span>
                </div>
            </div>  -->
            
            		<div class="space-4"></div>
            <div class="form-group" >
                <label class="col-sm-3 control-label no-padding-right" for="valueType"> 值类型 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select  class="form-control" id="valueType" name="valueType">
							  <option value="1">输入框</option>
							  <option value="2">下拉框</option>
							  <option value="3">单选框</option>
							  <option value="4">复选框</option>
							  <option value="5">富文本</option>
							  <option value="6">文本域</option>
			            </select> 	
					</span>
                </div>
            </div>
            
            
                  		<div class="space-4"></div>
            <div class="form-group" id="isHidden" style="display:none;" >
                <label class="col-sm-3 control-label no-padding-right" for="value"> 值 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="value" name="value" placeholder="值" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sort"> 排序 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="sort" name="sort" placeholder="排序" value="0"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                     

            <div class="space-4"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
                    &nbsp; &nbsp; &nbsp;
                    <button id="back" class="btn" type="button"><i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
                </div>
            </div>

        </form>
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
</div><!-- /.row -->

<script type="text/javascript">
     var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/bootstrap-tag.min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
            $(window)
            .off('resize.chosen')
            .on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                     var $this = $(this);
                     $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');
                  
            $("#valueType").change(function(){
				var valueType=$(this).val();
				if(valueType==1||valueType==5||valueType==6){
					$("#isHidden").hide();
				}else{
					$("#isHidden").show();
				}        	
            });
            
            //关键词tags
            var tag_input = $('#value');
            try {
                tag_input.tag(
                    {
                        placeholder: tag_input.attr('placeholder')
                      
                    }
                );
                
                var $tag_obj = $('#value').data('tag');
            }
            catch (e) {
                tag_input.after('<textarea id="' + tag_input.attr('id') + '" name="' + tag_input.attr('name') + '" rows="3">' + tag_input.val() + '</textarea>').remove();
            }      
                   
            //表单验证
            $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                    name: {
                        required: true
                    },
                    mobile: {
                        required: true
                    },

                    sort: {
                        required: true,
                        range: [0, 99999999],
                        digits: true
                    },
                    code: {
                    	required:true
                    }
                },

                messages: {},

                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-success').addClass('has-error');
                },

                success: function (e) {
                    $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                    $(e).remove();
                },

                errorPlacement: function (error, element) {
                    if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                        var controls = element.closest('div[class*="col-"]');
                        if (controls.find(':checkbox,:radio').length > 1) controls.append(error);
                        else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                    }
                    else if (element.is('.select2')) {
                        error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                    }
                    else if (element.is('.chosen-select')) {
                        error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                    }
                    else error.insertAfter(element.parent());
                },

                submitHandler: function (form) {
                    postForm('model-form');
                },
                invalidHandler: function (form) {
                }
            });
        });
    })
</script>
