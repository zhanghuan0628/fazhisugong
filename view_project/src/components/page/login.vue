<template>
  	<div id="login">
		<div class="modifyBg" v-if = "toModify">
			<div class="modifyBox">
				<p>为了您的账户安全，<br />请去修改密码</p>
				<div>
					<router-link to = "/" class="modifyBtn1">以后再说</router-link>
					<router-link to = "/changePassword" class="modifyBtn2">好的</router-link>
				</div>
			</div>
		</div>

		<div class="forgetBox" v-if = "isForget">
			<p>请联系管理员：13812341234</p>
			<a @click = "isForget = false">知道了</a>
		</div>
		
		<img class="loginLogo" src="~static/images/loginLogo.png" alt="">

		<div class="loginBox">
			<div class="account">
				<p><input type="text" name="" v-model = "loginName" placeholder="请输入协同账号"></p>
			</div>
			<div class="password">
				<p><input type="password" name="" v-model = "password" placeholder="请输入密码"></p>
			</div>

			<a class="forget" @click = "isForget = true">忘记密码？</a>

			<a class="loginBtn" @click = "auth">登录</a>
		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'login',
  	data () {
    	return {
			loginName:"",
			password:"",
			toModify:false,
			isForget:false
    	}
	},
	created () {
		
		
	},
	mounted () {
		let windowH = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
		document.querySelector("#login").style.height = windowH+"px";
		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/login";
		let wxtitle = "法治苏供";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "为您提供专业的法律维权服务!";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		// 鉴权
		auth:function(){
			if (this.loginName == "") {
				this.$toast.center('用户名不能为空');
				return false;
			}
			if (this.password == "") {
				this.$toast.center('密码不能为空');
				return false;
			}
			this.$http.post(this.$store.state.apiUrl+'/auth',this.$qs.stringify({
				userName:this.loginName,
				password:this.password
			}))
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.$store.commit('setAuthorization',data.data);
					this.login();
				} else if (data.code == "3007" || data.code == "3008" || data.code == "3009" || data.code == "3010") {
					this.auth();
				} else {
					this.$toast.center(data.message);
				}
				
			})
			.catch(err=>{
				console.log(err);
				// this.auth();
			});
		},
		// 登录
		login:function(){
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/userLogin',this.$qs.stringify({
				loginName:this.loginName,
				password:this.password
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.$store.commit('setUserInfo',data.data);
					this.hasModified();
				} else {
					this.$toast.center(data.message);
				}
			})
			.catch(err=>{
				console.log(err);
			});
		},
		// 判断是否初始密码
		hasModified:function(){
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/queryUserModifyPwd',this.$qs.stringify({
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.$router.push({name:'index'});
				} else if (data.code == "1") {
					this.toModify = true;
				}
			})
			.catch(err=>{
				console.log(err);
			});
		}
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#login {background: url(~static/images/loginBg.jpg) no-repeat;background-size: cover;background-position: center center;position: relative;height: 100%;}
	#login .loginLogo {width: 21.2rem;position: absolute;top: 10.5rem;left: 50%;transform: translateX(-50%);}
	#login .loginBox {position: absolute;left: 4.7rem;right: 4.7rem;top: 24rem;}
	#login .loginBox div {padding-left: 3rem;height: 4.2rem;background-repeat: no-repeat;background-size: 1.9rem 2.1rem;background-position: left center;}
	#login .loginBox .account {background-image: url(~static/images/loginName.png);margin-bottom: 1.8rem;} 
	#login .loginBox .password {background-image: url(~static/images/loginPassword.png)}
	#login .loginBox div p {border-bottom: 1px solid rgba(255,255,255,0.5);height: 100%;}
	#login .loginBox div p input {width: 100%;height: 100%;border: none;background: transparent;color: #ffffff;font-size: 1.6rem;}

	#login .loginBox div p input::-webkit-input-placeholder {color: rgba(255,255,255,0.5);}
	#login .loginBox div p input:-moz-placeholder {color: rgba(255,255,255,0.5);}
	#login .loginBox div p input::-moz-placeholder {color: rgba(255,255,255,0.5);}
	#login .loginBox div p input:-ms-input-placeholder {color: rgba(255,255,255,0.5);}

	#login .loginBox .forget {display: inline-block; margin: 2rem 0 0 3rem;color: #ffffff;}
	#login .loginBox .loginBtn {display: block;height: 4.9rem;line-height: 4.9rem;color: #ffffff;text-align: center;font-size: 1.8rem;background-color: #429c84;border-radius: 50px;margin-top: 3.2rem;}

	#login .modifyBg {position: fixed;left: 0;right: 0;top: 0;bottom: 0;background-color: #878787;z-index: 50;}
	#login .modifyBg .modifyBox {width: 23.5rem;background-color: #ffffff;border-radius: 10px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);overflow: hidden;}
	#login .modifyBg .modifyBox p {font-size: 1.8rem;padding: 2.5rem 0;text-align: center;}
	#login .modifyBg .modifyBox div {height: 5rem;display: flex;display: -webkit-flex;border-top: 1px solid #dcdcdc;}
	#login .modifyBg .modifyBox div a {text-align: center;line-height: 5rem;flex: 1;font-size: 1.8rem;}
	#login .modifyBg .modifyBox div .modifyBtn1 {color: #666666;border-right: 1px solid #dcdcdc;}
	#login .modifyBg .modifyBox div .modifyBtn2 {color: #429c84;}

	#login .forgetBox {position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%); width: 28.5rem;background-color: #ffffff;border-radius: 10px;z-index: 40}
	#login .forgetBox p {line-height: 10rem;font-size: 1.8rem;text-align: center;}
	#login .forgetBox a {display: block;border-top: 1px solid #dcdcdc;font-size: 1.8rem;line-height: 5rem;text-align: center; color: #429c84;}
</style>
