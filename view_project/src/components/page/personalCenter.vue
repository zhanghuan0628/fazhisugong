<template>
  	<div id="personalCenter">

		<div class="personalTop">
			
			
			<img class="personalBg" src="~static/images/personalBg.jpg" alt="">
			
			<vue-core-image-upload crop = "local" @imageuploaded = "imageuploaded" :isXhr = "false" >
			<!-- <div class="personalHead"> -->
				<img :src = "headUrl" alt="">
			<!-- </div> -->
			</vue-core-image-upload>
			<p class="userName" v-if = "!editUsername" @click = "editUsername = true">{{ userName }}</p>
			<p class="editUserName" v-else>
				<input type="text" name="" v-model = "userName" />
				<a @click = "changeUserName"></a>
			</p>
		</div>

		<div class="personalList">
			<ul>
				<li>
					<router-link to = '/myMessages'>
						<span>我的消息</span>
						<em></em>
						<i v-if = "msgNum != ''" v-text = "msgNum"></i>
					</router-link>
				</li>
				<li>
					<router-link to = '/myConsultations'>
						<span>我的咨询</span>
						<em></em>
					</router-link>
				</li>
				<li>
					<router-link to = '/myJudges'>
						<span>我是法官</span>
						<em></em>
					</router-link>
				</li>
				<li>
					<router-link to = '/myCollections'>
						<span>我的收藏</span>
						<em></em>
					</router-link>
				</li>
				<li>
					<router-link to = '/changePassword'>
						<span>修改密码</span>
						<em></em>
					</router-link>
				</li>
			</ul>

			<a @click = "logout">退出账号</a>
		</div>
		<bottomNavi></bottomNavi>
  	</div>
</template>

<script>
import store from '@/store/store';
import bottomNavi from '@/components/common/bottomNavi';
import VueCoreImageUpload  from 'vue-core-image-upload';
import OSS from '../../../static/js/aliyun-oss-sdk.min.js';
import util from '@/assets/js/util';

export default {
	name: 'personalCenter',
	components:{
		VueCoreImageUpload,
		bottomNavi
	},
  	data () {
    	return {
			userName:localStorage.getItem("userName"),
			headUrl:localStorage.getItem("headUrl"),
			msgNum:"",
			editUsername:false,
			ossClient:null
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/queryMyInfo',this.$qs.stringify({
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.msgNum = data.data;
			} else if (data.code == "3007") {
				this.$store.commit('logout');
				this.$router.push({name:'login'})
			}
		})
		.catch(err=>{
			console.log(err);
		});
	},
	mounted () {
		this.ossClient = new OSS.Wrapper({
			region: "oss-cn-shanghai",
			accessKeyId: "LTAICG7rs8rsGNj4",
			accessKeySecret: "FDtacJMEQXKRwIPgK3WKYR2Cyv8xKm",
			bucket: "ffxl"
		});

		// 分享配置
		let locationUrl = location.protocol+"//"+location.host;
		let wxtitle = "法治苏供";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "为您提供专业的法律维权服务!";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		changeUserName:function(){
			if (this.userName.length == "" || this.userName.length >10) {
				this.userName = localStorage.getItem("userName");
				this.$toast.center('昵称不能为空或不能超过10个字符');
				return false;
			}
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/updateUserInfo',this.$qs.stringify({
				id:this.$store.state.userId,
				userName:this.userName
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.$store.commit('editUsername',this.userName);
					this.editUsername = false;
					this.$toast.center('昵称修改成功');
				} else if (data.code == "3007") {
					this.$store.commit('logout');
					this.$router.push({name:'login'})
				}
			})
			.catch(err=>{
				console.log(err);
			});
		},
		changeHeadUrl:function(headurl){
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/updateUserInfo',this.$qs.stringify({
				headUrl:headurl
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.$store.commit('editHeadurl',headurl);
					this.headUrl = headurl;
					this.$toast.center('头像修改成功');
				} else if (data.code == "3007") {
					this.$store.commit('logout');
					this.$router.push({name:'login'})
				}
			})
			.catch(err=>{
				console.log(err);
			});
		},
		imageuploaded:function(res) {
			let _this = this;
			let base64 = res.src.split(',')[1];
			let fileType = res.src.split(';')[0].split(':')[1];
			
			let blob = this.toBlob(base64,fileType);
			
			let reader = new FileReader();
			reader.readAsArrayBuffer(blob);

			reader.onload = function (event) {
				// 文件名
				let date = new Date();
				let storeAs = 'wechat/21days/'+date.getTime()+'.'+blob.type.split('/')[1];
				
				// arrayBuffer转Buffer
				let buffer = new OSS.Buffer(event.target.result);
				
				// 上传
				_this.ossClient.put(storeAs, buffer).then(function(result){
					_this.changeHeadUrl(result.url)
				}).catch(function(err){
					console.log(err);
				});
			}

			
		},
		toBlob:function(urlData,fileType){
			let bytes=window.atob(urlData),
				n=bytes.length,
				u8arr=new Uint8Array(n);
			while(n--){
				u8arr[n]=bytes.charCodeAt(n);
			}
			return new Blob([u8arr],{type:fileType});
		},
		logout:function(){
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/cancel',this.$qs.stringify({
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.$store.commit('logout');
					this.$router.push({name:'login'})
				}
			})
			.catch(err=>{
				console.log(err);
			});
			
		},
		cancelEditName:function(){
			this.userName = localStorage.getItem("userName");
			this.editUsername = false;
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
	#personalCenter {background-color: #ffffff;padding-bottom: 7rem;}
	#personalCenter .personalTop {position: relative;}
	#personalCenter .personalTop .personalBg {width: 100%;}
	#personalCenter .personalTop .g-core-image-upload-btn {position: absolute;top: 4.3rem;left: 50%;margin-left: -3.45rem;width: 6.9rem;height: 6.9rem;border: 3px solid #ffffff;border-radius: 50%;}
	#personalCenter .personalTop .g-core-image-upload-btn img { border-radius: 50%;width: 100%;height: 100%;}
	#personalCenter .personalTop .userName {position: absolute;top: 13rem;left: 50%; transform: translateX(-50%); text-align: center;color: #ffffff;font-size: 1.6rem;height: 2.4rem;line-height: 2.4rem; padding-right: 2.4rem; background: url(~static/images/editInfo.png) no-repeat;background-position: right center;background-size: 1.9rem 1.9rem;}
	#personalCenter .personalTop .editUserName {position: absolute;top: 13rem;left: 50%; transform: translateX(-50%);width: 10rem;height: 2.4rem;}
	#personalCenter .personalTop .editUserName input {width: 100%;height: 100%; text-align: center;font-size: 1.6rem;border: none;}
	#personalCenter .personalTop .editUserName a {display: block;position: absolute;right: -3rem;top: .2rem;width: 2rem;height: 2rem;background: url(~static/images/tick.png);background-size: 100% 100%;}

	#personalCenter .personalList {background-color: #ffffff;}
	#personalCenter .personalList ul li {padding: 0 1.5rem;height: 5.5rem; line-height: 5.5rem;border-bottom: 1px solid #e5e5e5;position: relative;}
	#personalCenter .personalList ul li a {display: block;}
	#personalCenter .personalList ul li span {font-size: 1.6rem;color: #333333;}
	#personalCenter .personalList ul li em {display: block;width: 1.1rem;height: 2rem;background: url(~static/images/arrow.png);background-size: 100% 100%;position: absolute;right: 1.5rem;top: 50%;transform: translateY(-50%);}
	#personalCenter .personalList ul li i {display: block;width: 1.7rem;height: 1.7rem;text-align: center;line-height: 1.7rem;background-color: #f18172;color: #ffffff;font-size: 1.1rem;border-radius: 50%; position: absolute;left: 8.7rem;top: 50%;transform: translateY(-50%);}

	#personalCenter .personalList>a {display: block;width: 11.9rem;height: 3.8rem;line-height: 3.8rem;text-align: center;background-color: #ffffff;color: #429c84;font-size: 1.5rem;border: 1px solid #429c84;border-radius: 50px;margin: 2rem auto 0 auto;}
</style>
