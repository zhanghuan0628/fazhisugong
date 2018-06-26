<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/jsp/common/meta.jsp"%>
        <title>21天健康打卡计划</title>
        <%@include file="/jsp/common/css.jsp"%>
        <link rel="stylesheet" type="text/css" media="screen" href="${base}/jsp/sgjk/css/global.css?v=12" />
    </head>
    <body>
        <div id="layer"></div>

        <div class="loadingCover">
            <p>计划开启中.</p>
        </div>


        <div id="cropit">
            <div class="image-editor">
                <div class="cropit-preview"></div>
                <div class="imgUpload">上传头像
                    <input type="file" name="file" id="" value="" class="cropit-image-input" />
                </div>
                
                <input type="range" class="cropit-image-zoom-input">
                
                <div class="rotateFrame">
                    <div class="rotate-ccw">逆时针旋转90度</div>
                    <div class="rotate-cw">顺时针旋转90度</div>
                </div>
                    
                <div class="export">确认</div>
                <div class="cancelUpload">取消</div>
            </div>
        </div>
        
        <div class="signIn">
            <div class="signScroll">
                <div class="signInBg1">
                    <img src="${base}/jsp/sgjk/images/signInBg1.png" alt="">
                    <img src="${base}/jsp/sgjk/images/signInBg1.png" alt="">
                </div>

                <div class="signInBg2">
                    <img src="${base}/jsp/sgjk/images/signInBg2.png" alt="">
                    <img src="${base}/jsp/sgjk/images/signInBg2.png" alt="">
                </div>

                <img class="bgImg3" src="${base}/jsp/sgjk/images/bgImg3.png" alt="" srcset="">
                <img class="bgImg4" src="${base}/jsp/sgjk/images/bgImg4.png" alt="" srcset="">
                <img class="bgImg5" src="${base}/jsp/sgjk/images/bgImg5.png" alt="" srcset="">
                
                <img class="homeTitle" src="${base}/jsp/sgjk/images/homeTitle.png" alt="" srcset="">
                <img class="rule" src="${base}/jsp/sgjk/images/rule.png?20180419" alt="">

                <div class="signInInfo">
                    <select name="department" id="">
                        <option value="">选择部门（仅限内部员工）</option>
                        <option value="">部室</option>
                        <option value="">电力调度控制中心</option>
                        <option value="">输电运检室</option>
                        <option value="">变电运维室</option>
                        <option value="">变电检修室</option>
                        <option value="">配电运检室</option>
                        <option value="">电缆运检室</option>
                        <option value="">营销部（客户服务中心）</option>
                        <option value="">经济技术研究所</option>
                        <option value="">信息通信分公司</option>
                        <option value="">物资供应中心</option>
                        <option value="">园区供电分公司</option>
                        <option value="">苏能综合服务公司</option>
                        <option value="">农电公司</option>
                    </select>
                    
                    <input name="nickName" type="text" placeholder="请填写真实姓名" />

                    <input name="cellphoe" type="text" placeholder="请输入手机号码" />

                    <div class="headUpLoad">
                        <p>上传或修改照片</p>
                        <img src="${base}/jsp/sgjk/images/headDefault.png" alt="">
                    </div>

                </div>

                <img class="hintCont" src="${base}/jsp/sgjk/images/hintCont.png" alt="">

                <a class="launchBtn">开启健康计划</a>

                <a class="checkRankList" href="${base}/jsp/sgjk/index.jsp">查看排行榜>></a>
            </div>
            
            
        </div>

        <script src="${base}/jsp/sgjk/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
        <script src="${base}/jsp/sgjk/js/aliyun-oss-sdk.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${base}/jsp/sgjk/js/jquery.cropit.js" type="text/javascript" charset="utf-8"></script>
        <script src="${base}/jsp/sgjk/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
        <script src="${base}/jsp/sgjk/js/index.js?v=12" type="text/javascript" charset="utf-8"></script>
        <script>
            function toBlob(urlData,fileType){
		        var bytes=window.atob(urlData),
		            n=bytes.length,
		            u8arr=new Uint8Array(n);
		        while(n--){
		            u8arr[n]=bytes.charCodeAt(n);
		        }
		        return new Blob([u8arr],{type:fileType});
            }

            // 判断是否注册
            function isLoginUser(openId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectLoginUser",
                    type: "post",
                    data: {openId:openId},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.code == "1"){
                            location.href="${base}/jsp/sgjk/index.jsp";
                        } else {
                            $(".loadingCover").remove();
                        }
                    }
                });
            }
            
            // 保存信息
            function saveUser(openId,nickName,wechatHeadUrl,department,qrUrl,cellphoe){
                $.ajax({
                    url: apiUrl+"/user/sava_healthy_user",
                    type: "post",
                    data: {openId:openId,nickName:nickName,wechatHeadUrl:wechatHeadUrl,department:department,qrUrl:qrUrl,cellphoe:cellphoe},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                               location.href="${base}/jsp/sgjk/index.jsp"
                                
                            }else{
                                
                            }
                        }
                    }
                });
            }

            var wechatHeadUrl = "";

            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";

            var loadingNth = 2;

            $(function() {
                hasEnd();
                
                setInterval(function(){
					if (loadingNth == 7) {
						loadingNth = 2
					}
                    
                    if (loadingNth == 2) {
                        $(".loadingCover p").text("计划开启中..")
                    } else if (loadingNth == 3) {
                        $(".loadingCover p").text("计划开启中...")
                    } else if (loadingNth == 4) {
                        $(".loadingCover p").text("计划开启中....")
                    } else if (loadingNth == 5) {
                        $(".loadingCover p").text("计划开启中.....")
                    } else if (loadingNth == 6) {
                        $(".loadingCover p").text("计划开启中......")
                    }
                    
                    loadingNth++;
				},1000)

                var oauth = "${oauth}";
				var openId = '';
				if (oauth == 'true') {
				openId = "${openId}";
				setCookie("openId",openId);
				} else {
					if (getCookie("openId") != null &&getCookie("openId") != "") {
						openId = getCookie("openId");
					} else {
						wxAuthorization("sgjk/signInPage.jsp");
					}
                }

                isLoginUser(openId);


//				裁剪框
                var editorW = $(".cropit-preview").width();
				$(".cropit-preview").css("height",editorW+"px");
				
				
				$('.image-editor').cropit({
                    onImageError(error){
                        if (error.code == 1) {
                            toast("图片太小，请重新上传");
                        } else if (error.code == 1) {
                            toast("图片加载失败，请重新上传");
                        }
                    }
                });
				
				$('.rotate-cw').click(function() {
                    $('.image-editor').cropit('rotateCW');
		        });
		        $('.rotate-ccw').click(function() {
		            $('.image-editor').cropit('rotateCCW');
		        });
		
		        $('.export').click(function() {
                    $("#layer").show();
                    $("body").append("<p class='uploadPicIng' style='position:fixed;top:50%;transform:translateY(-50%);left:0;width:100%;text-align:center;font-size:1.8rem;z-index:90;color:#FFFFFF'>图片上传中,请耐心等候...</p>")
		            var imageData = $('.image-editor').cropit('export');
                    if (imageData==undefined) {
                        toast("请上传照片");
                        return false;
                    }


                    var base64 = imageData.split(',')[1];
                    var fileType = imageData.split(';')[0].split(':')[1];
                    
                    var blob = toBlob(base64,fileType);
                    
                    var reader = new FileReader();
                    reader.readAsArrayBuffer(blob);
                    reader.onload = function (event) {
                        //				oss配置
                        var ossClient = new OSS.Wrapper({
                            region: "oss-cn-shanghai",
                            accessKeyId: "LTAICG7rs8rsGNj4",
                            accessKeySecret: "FDtacJMEQXKRwIPgK3WKYR2Cyv8xKm",
                            bucket: "ffxl"
                        });
                        
                        // 文件名
                        var date = new Date();
                        var storeAs = 'wechat/21days/'+date.getTime()+'.'+blob.type.split('/')[1];
                        
                        // arrayBuffer转Buffer
                        var buffer = new OSS.Buffer(event.target.result);
                        
                        // 上传
                        // ossClient.put(storeAs, buffer,{progress: function* (p) {
                        //     console.log(p)
                        //     // var pg = p*100;
                        //     // $("#progress p").text("正在上传.. "+pg.toFixed(2)+"%");
                        // }}).then(function(result){
                        ossClient.put(storeAs, buffer).then(function(result){
                            wechatHeadUrl = result.url;
                            $(".headUpLoad img").attr("src",wechatHeadUrl);
                            $("#cropit").css("visibility","hidden");
                            $("#layer").hide();
                            $(".uploadPicIng").remove();
                        }).catch(function(err){
                            $(".uploadPicIng").text("头像上传失败，请重新上传");
                            setTimeout(function(){
                                $(".uploadPicIng").remove();
                                $("#layer").hide();
                            }, 2000);
                            console.log(err);
                        });
                    }

		        });
		        
		        $(".cancelUpload").click(function(){
		            $("#cropit").css("visibility","hidden");
		        })
		        
		        
		        $(".headUpLoad").click(function(){
		            $("#cropit").css("visibility","visible");
		        })


                $(".launchBtn").click(function(){
                    hasEnd();
                    var department = $(".signInInfo select[name='department']").find("option:selected").text();
                    var nickName = $(".signInInfo input[name='nickName']").val();
					var cellphoe = $(".signInInfo input[name='cellphoe']").val();

                    var regPhone= /^1[3|4|5|7|8][0-9]{9}$/;
                    
                    if (department=="选择部门（仅限内部员工）"){
						toast("请选择部门");
						return false;
					}
                    if (nickName=="") {
						toast("请填写有效信息");
						return false;
					}
					if (cellphoe==""){
						toast("请填写手机号码");
						return false;
					}
					if (!regPhone.test(cellphoe)) {
						toast("手机号格式不正确")
						return false;
					} 
                    
                    if (wechatHeadUrl == "" || wechatHeadUrl == undefined) {
                        toast("请上传头像！")
                        return false;
                    }

                    var qrUrl = apiUrl + "/jsp/sgjk/personalPage.jsp?userId="+openId+"&type=QR";
					
					saveUser(openId,nickName,wechatHeadUrl,department,qrUrl,cellphoe);
					
                })


                locationUrl = apiUrl+"/jsp/sgjk/signInPage.jsp";
                wxtitle = "每天5个健康问答——健康计划已经发起！快来开启你的计划！";
                wximgUrl = apiUrl+"/jsp/sgjk/images/sgLogo.png";
                wxdesc = "我的计划？21天健康打卡计划——5980服务工程";
                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)

            })
        </script>
    </body>
</html>