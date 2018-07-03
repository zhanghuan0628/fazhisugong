/**
 * 法官题目详情对话框（可用于添加和修改对话框）
 */

var sgSubjectInfoDlg = {
	formId : "subjectInfoForm", //form表单id
	table : parent.subject.table,
    infoData: {
    	id: $("#id").val(),
    	title: $("#title").val(),
    	
    },
    //验证方式
    validate: {
	    rules: {
			content:{
				required:true,
				minlength:1,
				maxlength:10000,
			},
			num:{
				required:true,
				minlength:1,
				maxlength:10,
			},
			
		},
		
    }
    
	 
	
};
function saveInfo(){
       var optn = new Array();
      
  	   $(".choose").each(function(){
  		   var title = $(this).find("input[name='title']").val();
  		   var result;
  		   if($(this).find(".rad").prop('checked')){
  			   result = 1;
  		   }else{
  			   result = 0;
  		   }
  		   var json = {"title":title,"result":result};
  		   optn.push(json);
  	   })
  	   var qstn = $("#qstn").val();
  	   var questionJson = {"qstn":qstn,"optn":optn};   	   
  	   $("#questionJson").val(questionJson);
  	   console.log( $("#questionJson").val());
}

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	var n = 0;
	 $(".del").click(function(){
  	   $(this).parent().remove();       	                 
         $(".num").each(function(){          	   
      	   n++;
      	   $(this).html(n);
         })
     }) 
      $(".rad").click(function(){
    	   $(this).siblings().removeAttr("checked");
    	   $(this).prop('checked',true);           	  
       })
	$("#add").click(function(){  
  	  var n =  $("input[name='title']").length;
  	  n = n + 1;
  	  var tab ="<div></div>";
	  tab=$("#tab") ;
  	  var div = " <div class=\"row cl choose\">"
        +"<label class=\"form-label col-xs-4 col-sm-3\"><span class=\"num\">"+n+".</span></label>"
        +"<div class=\"formControls col-xs-4 col-sm-4\">"
            +"<input type=\"text\" class=\"input-text\" name=\"title\"></div>"
        +"<input class=\"rad\" class=\"form-label col-xs-4 col-sm-3\" type=\"radio\"  style=\"width: 25px;height: 25px;\" name=\"result\" >"  
        +"<label class=\"del\" class=\"form-label col-xs-4 col-sm-3\"> <i class=\"Hui-iconfont\">&#xe60b;</i> </label></div>";
  	  tab.append(div);
  	  $(".del").click(function(){
  		  n=0;
            $(this).parent().remove();                        
            $(".num").each(function(){              
                n++;
                $(this).html(n+".");
            })
        })
        $(".rad").click(function(){
             $(this).siblings().removeAttr("checked");
             $(this).prop('checked',true);   	            
         })
     });
     $(".rad").click(function(){
  	   $(this).siblings().removeAttr("checked");
  	   $(this).prop('checked',true);           	  
     })
     
	if(sgSubjectInfoDlg.infoData.id ==null ||sgSubjectInfoDlg.infoData.id =="" ){
		/*Feng.initValidator(sgSubjectInfoDlg.formId, sgSubjectInfoDlg.validate,sgSubjectInfoDlg.table,"/sg_subject/add"); //新增
*/	}else{
		Feng.initValidator(sgSubjectInfoDlg.formId, sgSubjectInfoDlg.validate,sgSubjectInfoDlg.table,"/sg_subject/edit"); //编辑
	}
	
});