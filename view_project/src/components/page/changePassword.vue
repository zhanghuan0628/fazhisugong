<template>
  	<div id="changePassword">
		<input type="password" placeholder="请输入旧密码" maxlength="12" v-model = "oldPassword" @input = "oldPasswordChange">
		<input type="password" placeholder="请输入新密码" maxlength="12" v-model = "newPassword" @input = "newPasswordChange">
		<input type="password" placeholder="确认新密码" maxlength="12" v-model = "confirmPassword" @input = "confirmPasswordChange">
		<a :style = "{'background-color' : passwordFinished ? '#429c84' : '#cccccc'}" @click = "changePassword">确定修改</a>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'changePassword',
  	data () {
    	return {
			oldPassword:"",
			newPassword:"",
			confirmPassword:"",
			passwordFinished:false
    	}
	},
	created () {
		
	},
	mounted () {
		// 分享配置
		let locationUrl = location.protocol+"//"+location.host;
		let wxtitle = "法治苏供";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "为您提供专业的法律维权服务!";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		oldPasswordChange:function(){
			if (this.oldPassword != "" && this.newPassword != ""&& this.confirmPassword != "") {
				this.passwordFinished = true;
			} else {
				this.passwordFinished = false;
			}
		},
		newPasswordChange:function(){
			if (this.oldPassword != "" && this.newPassword != ""&& this.confirmPassword != "") {
				this.passwordFinished = true;
			} else {
				this.passwordFinished = false;
			}
		},
		confirmPasswordChange:function(){
			if (this.oldPassword != "" && this.newPassword != ""&& this.confirmPassword != "") {
				this.passwordFinished = true;
			} else {
				this.passwordFinished = false;
			}
		},
		changePassword:function(){
			if (this.passwordFinished) {
				if (this.oldPassword == "") {
					this.$toast.center('旧密码不能为空');
					return false;
				}
				if (this.newPassword == "") {
					this.$toast.center('新密码不能为空');
					return false;
				}
				if (this.newPassword.length < 6) {
					this.$toast.center('密码不能少于6位');
					return false;
				}
				if (this.newPassword != this.confirmPassword) {
					this.$toast.center('新密码确认不一致');
					return false;
				}
				this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/updateUserPassword',this.$qs.stringify({
					newPwd:this.newPassword,
					oldPwd:this.oldPassword
				}),{
					headers:{
						'Authorization':'Bearer ' + this.$store.state.Authorization
					}
				})
				.then(res=>{
					let data = res.data;
					if (data.code == "2000") {
						this.$toast.center("密码修改成功");
						setTimeout(() => {
							this.$router.go(-1);
						}, 1000);
					} else if (data.code == "1") {
						this.$toast.center('密码输入错误');
					} else if (data.code == "3007") {
						this.$store.commit('logout');
						this.$router.push({name:'login'})
					}
				})
				.catch(err=>{
					console.log(err);
				});
			}
			
		}
	},
	beforeRouteEnter (to, from, next) {
		if (localStorage.getItem("userId") != null && localStorage.getItem("userId") != "") {
			next();
		} else {
			next("/login");
		}
  	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#changePassword {height: 100%;padding: 1.5rem 1.5rem 0 1.5rem;box-sizing: border-box;background-color: #ffffff;}
	#changePassword input {width: 100%;height: 5rem;box-sizing: border-box;border: 2px solid #e5e5e5;text-align: center;font-size: 1.6rem;border-radius: 50px;margin-bottom: 1.5rem;}
	#changePassword a {display: block;height: 5rem;border-radius: 50px;background-color: #cccccc;color: #ffffff;font-size: 1.6rem;text-align: center;line-height: 5rem;margin-top: 1.5rem;}
</style>
