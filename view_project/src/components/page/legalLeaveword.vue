<template>
  	<div id="legalLeaveword">
		<h1 class="leavewordTitle">留言（{{ legalLeavewordList.length }}）</h1>
		<div class="leavewordList">
			<ul v-if = "legalLeavewordList.length > 0">
				<template v-for = "todo in legalLeavewordList">
					<li :key = "todo.id">
						<div class="lw-top">
							<img :src = "todo.headUrl" alt="">
							<p v-text = "todo.userName"></p>
							<span v-text = "todo.createDate"></span>
						</div>
						<div class="lw-cont" v-text = "todo.content"></div>
						<div class="lw-reply" v-if = "todo.replyName != undefined">
							<h2 v-text = "todo.replyName"></h2>
							<p v-text = "todo.replyContent"></p>
						</div>
					</li>
				</template>
			</ul>

			<div class="noLeaveword" v-else>
				<img src="/static/images/empty.png" alt="">
				<p>还没有人留言</p>
			</div>
		</div>

		<div class="leavewordBottom">
			<p><input type="text" placeholder="说说你的想法" v-model = "content"></p>
			<a @click = "insertComment">发布</a>
		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'legalLeaveword',
  	data () {
    	return {
			id:this.$route.params.id,
			legalLeavewordList:[],
			content:"",
			hasInsert:false
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgLawRiskController/queryLawCommentDetail',this.$qs.stringify({
			id:this.id
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.legalLeavewordList = data.data;
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
		let locationUrl = location.protocol+"//"+location.host;
		let wxtitle = "国网苏州供电公司的法治平台";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "法治苏供，为你提供法律服务";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		insertComment:function(){
			if (!this.hasInsert && this.content != "") {
				this.hasInsert = true;
				this.$http.post(this.$store.state.apiUrl+'/SgLawRiskController/insertLawComment',this.$qs.stringify({
					topicId:this.id,
					content:this.content,
				}),{
					headers:{
						'Authorization':'Bearer ' + this.$store.state.Authorization
					}
				})
				.then(res=>{
					let data = res.data;
					if (data.code == "2000") {
						this.$toast.center("留言成功");
						setTimeout(() => {
							this.$router.go(-1);
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
	#legalLeaveword {padding-bottom: 5rem;}
	#legalLeaveword .leavewordTitle {height: 3rem;line-height: 3rem;background-color: #f4f4f4;font-size: 1.3rem;color: #999999;padding-left: 1.5rem;}
	#legalLeaveword .leavewordList ul li {padding: 0 1.5rem 1.2rem 1.5rem;border-top: 1px solid #e5e5e5;}
	#legalLeaveword .leavewordList ul li:first-child {border: none;}
	#legalLeaveword .leavewordList ul li .lw-top {height: 5.8rem;position: relative;line-height: 5.8rem;}
	#legalLeaveword .leavewordList ul li .lw-top img {width: 2.8rem;height: 2.8rem;border-radius: 50%;position: absolute;left: 0;top: 50%;transform: translateY(-50%);}
	#legalLeaveword .leavewordList ul li .lw-top p {position: absolute;left: 3.8rem;top: 0; font-size: 1.5rem;}
	#legalLeaveword .leavewordList ul li .lw-top span {position: absolute;right: 0;top: 0; font-size: 1.2rem;color: #999999;}
	#legalLeaveword .leavewordList ul li .lw-cont {font-size: 1.5rem;color: #666666;line-height: 2.2rem;}

	#legalLeaveword .leavewordList ul li .lw-reply {border-left: .15rem solid #5ea48c;padding-left: 1rem;margin-top: 1rem;}
	#legalLeaveword .leavewordList ul li .lw-reply h2 {font-size: 1.5rem;font-weight: bold;margin-bottom: .7rem;}
	#legalLeaveword .leavewordList ul li .lw-reply p {font-size: 1.5rem;color: #666666;}

	#legalLeaveword .leavewordList .noLeaveword {padding-top: 16.3rem;text-align: center;}
	#legalLeaveword .leavewordList .noLeaveword img {width: 6rem;}
	#legalLeaveword .leavewordList .noLeaveword p {font-size: 1.5rem;color: #999999;margin-top: 1.1rem;}

	#legalLeaveword .leavewordBottom {position: fixed;left: 0;bottom: 0;width: 100%;height: 4.9rem;box-sizing: border-box;border: 1px solid #dcdcdc;background-color: #ffffff;}
	#legalLeaveword .leavewordBottom p {position: absolute;left: 1.5rem;right: 6.5rem;height: 3.2rem;top: 50%;transform: translateY(-50%);}
	#legalLeaveword .leavewordBottom p input {width: 100%;height: 100%;border-radius: 50px;background-color: #e5e5e5;border: none;position: absolute;left: 0;top: 0;box-sizing: border-box;padding-left: 1.2rem;}
	#legalLeaveword .leavewordBottom a {position: absolute;display: block;right: 0;width: 6.5rem;line-height: 4.9rem;top: 0;text-align: center;color: #429c84;font-weight: bold;}
</style>
