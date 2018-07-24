 
function getOssContent(base,content){
	var results = "";
	var imgReg = /<img.*?(?:>|\/>)/gi;
	//匹配src属性
	var srcReg = /src=[\'\"]?([^\'\"]*)[\'\"]?/i;
	var arr = content.match(imgReg);
	var x = 0;
	if (arr!=undefined && arr!=''&&arr!=null) {
		for (var i = 0; i < arr.length; i++) {
			var src = arr[i].match(srcReg);
			//获取图片地址
			if(src[1] ){
				if(src[1].indexOf("sugong.oss-cn-shanghai.aliyuncs.com") < 0){
					content = content.replace(src[1],"######"+x+"######")
					results = results+"," + src[1].split("?")[0];
					x++;
				}
			}
		}
	}
	
	if(results != ""){
		$.ajax({
			dataType : 'json',
			type : 'POST',
			url : base+"/f_infos/uploadToOss",
			async:false,
			data : {urls:results.replace(",","")},
			success : function(result) {
				if (result.code == "2000") {
					var data = result.data;
					for(var i = 0; i < data.length; i++ ){
						content = content.replace("######"+i+"######",data[i]);
					}
				} else
					alert("操作失败");
			},
		});
	}
	return content;
}