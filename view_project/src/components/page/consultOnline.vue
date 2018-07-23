<template>
  	<div id="consultOnline">
		<img class="consultFlow" src="~static/images/consultFlow.jpg" alt="">

		<div class="consultCont">
			<div class="urgency">
				<h2>紧急程度</h2>
				<p>
					<a :class = "{'consultOn' : urgency == 1}" @click = "urgency = 1">一般</a>
					<a :class = "{'consultOn' : urgency == 2}" @click = "urgency = 2">紧急</a>
				</p>
			</div>
			<div class="isOpen">
				<h2>是否公开</h2>
				<p>
					<a :class = "{'consultOn' : !isOpen}" @click = "isOpen = false">是</a>
					<a :class = "{'consultOn' : isOpen}" @click = "isOpen = true">否</a>
				</p>
			</div>
			<div class="consultTitle">
				<input type="text" name="" id="" placeholder="请输入标题（4-25字）" maxlength="25" v-model = "title" @input = "titleChange">
			</div>
			<div class="consultDescription">
				<textarea name="" id="" placeholder="请详细描述具体问题" maxlength="2000" v-model = "content" @input = "descChange"></textarea>
				<span><i v-text = "descCount"></i>/2000</span>
			</div>

			<a class="consultSubmit" @click = "consultSubmit" :style = "{'backgroundColor': contFinished ? '#429c84' : '#cfcfcf'}">提交</a>
		</div>

		<div class="consultAnswer">
			<div class="ca-top">
				<img :src = "consultAnswer.headImg" alt="">
				<p v-text = "consultAnswer.userName"></p>
				<span><i v-text = "consultAnswer.createDate"></i> 回答了</span>

				<router-link to = "/consultAnswerList">更多 ></router-link>
			</div>
			<div class="ca-cont">
				<h1 v-text = "consultAnswer.qTitle"></h1>
				<p v-text = "consultAnswer.qContent"></p>
			</div>

		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'consultOnline',
  	data () {
    	return {
			urgency:1,
			isOpen:false,
			title:"",
			content:"",
			descCount:0,
			contFinished:false,
			consultAnswer:{}
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgAskController/queryAllAskComment',{
		},{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.consultAnswer = data.data.dataList[0]
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
		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/consultOnline";
		let wxtitle = "法治苏供";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "为您提供专业的法律维权服务!";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		titleChange:function(){
			if (this.title != "" && this.content != "") {
				this.contFinished = true;
			} else {
				this.contFinished = false;
			}
		},
		descChange:function(){
			if (this.title != "" && this.content != "") {
				this.contFinished = true;
			} else {
				this.contFinished = false;
			}
			this.descCount = this.content.length;
		},
		consultSubmit:function(){
			if (this.contFinished) {
				if (this.title.length < 4) {
					this.$toast.center("标题不少于4个字");
					return false;
				}
				this.$http.post(this.$store.state.apiUrl+'/SgAskController/insertUserAsk',this.$qs.stringify({
					title:this.title,
					content:this.content,
					urgent:this.urgency,
					see:this.isOpen
				}),{
					headers:{
						'Authorization':'Bearer ' + this.$store.state.Authorization
					}
				})
				.then(res=>{
					let data = res.data;
					if (data.code == "2000") {
						this.$toast.center("问题已经成功提交，将尽快为你解答！");
						setTimeout(() => {
							this.$router.push({name:'index'});
						}, 1000);
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
	#consultOnline {}
	#consultOnline .consultFlow {width: 100%;}
	#consultOnline .consultCont {padding: 0 1.5rem 1.5rem 1.5rem;background-color: #ffffff;}
	#consultOnline .consultCont>div {height: 5.5rem;border-bottom:1px solid #eeeeee;line-height: 5.5rem;position: relative;}
	#consultOnline .consultCont>div h2 {font-size: 1.5rem;}
	#consultOnline .consultCont>div p {position: absolute;right: 0;height: 2.9rem;top: 50%;transform: translateY(-50%);}
	#consultOnline .consultCont>div p a {display: inline-block;width: 7.3rem;height: 100%;margin-left: 1rem;line-height: 2.9rem;text-align: center;color: #333333;border-radius: 50px;background-color: #f4f4f4;float: left;}
	#consultOnline .consultCont>div p .consultOn {background-color: #f18172;color: #ffffff;}
	#consultOnline .consultCont .consultTitle input {width: 100%;height: 100%;border: none;background: transparent;font-size: 1.5rem;}
	#consultOnline .consultCont .consultDescription {height: 17.5rem;padding: 1rem 0;}
	#consultOnline .consultCont .consultDescription textarea {width: 100%;height: 100%; line-height: 3.5rem;border: none;background: transparent;font-size: 1.5rem;resize: none;}
	#consultOnline .consultCont input::-webkit-input-placeholder,#consultOnline .consultCont textarea::-webkit-input-placeholder {color: #bbbbbb;}
	#consultOnline .consultCont input:-moz-placeholder,#consultOnline .consultCont textarea:-moz-placeholder {color: #bbbbbb;}
	#consultOnline .consultCont input::-moz-placeholder,#consultOnline .consultCont textarea::-moz-placeholder {color: #bbbbbb;}
	#consultOnline .consultCont input:-ms-input-placeholder,#consultOnline .consultCont textarea:-ms-input-placeholder {color: #bbbbbb;}
	#consultOnline .consultCont .consultDescription span {position: absolute;bottom: 1.4rem;right: 0;color: #bbbbbb;font-size: 1.3rem;display: block;line-height: 1.4rem;}
	#consultOnline .consultCont .consultSubmit {display: block;width: 100%;height: 4.9rem;line-height: 4.9rem;text-align: center;color: #ffffff;font-size: 1.8rem;background-color: #cfcfcf;border-radius: 50px;margin-top: 1.5rem;}

	#consultOnline .consultAnswer {padding: 0 1.5rem 1rem 1.5rem;}
	#consultOnline .consultAnswer .ca-top {height: 7rem;position: relative;}
	#consultOnline .consultAnswer .ca-top img {width: 4rem;height: 4rem;border-radius: 50%;position: absolute;left: 0;top: 50%;transform: translateY(-50%);}
	#consultOnline .consultAnswer .ca-top p {position: absolute;top: 1.5rem;left: 4.7rem;line-height: 1.4rem;}
	#consultOnline .consultAnswer .ca-top span {position: absolute;bottom: 1.5rem;left: 4.7rem;font-size: 1.2rem;line-height: 1.2rem;color: #999999;}
	#consultOnline .consultAnswer .ca-top a {position: absolute;right: 0;top: 0;line-height: 6.8rem;font-size: 1.3rem;color: #429c84;}

	#consultOnline .consultAnswer .ca-cont h1 {font-size: 1.7rem;font-weight: bold;margin-bottom: .9rem;}
	#consultOnline .consultAnswer .ca-cont p {font-size: 1.5rem;line-height: 2.2rem;overflow:hidden; text-overflow:ellipsis;display:-webkit-box; -webkit-box-orient:vertical;-webkit-line-clamp:3;}


</style>
