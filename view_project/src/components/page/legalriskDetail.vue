<template>
  	<div id="legalriskDetail">
		<div class="legalCont" v-html = "legalriskDetail.content">

		</div>
		<div class="legalLeaveword">
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
		</div>
		<router-link class="leaveWordBtn" :to = "{name:'legalLeaveword',params:{id:id}}"><img src="~static/images/leaveWord.png" alt="">留言</router-link>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'legalriskDetail',
  	data () {
    	return {
			id:this.$route.params.id,
			legalriskDetail:{},
			legalLeavewordList:[]
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgLawRiskController/queryLawRiskById',this.$qs.stringify({
			id:this.id
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.legalriskDetail = data.data;
			} else if (data.code == "3007") {
				this.$store.commit('logout');
				this.$router.push({name:'login'})
			}
		})
		.catch(err=>{
			console.log(err);
		});

		// 留言
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
		
	},
	watch:{
		legalriskDetail:function(){
			this.$nextTick(() => {
				// 分享配置
				let locationUrl = location.protocol+"//"+location.host+"/legalriskDetail/"+this.id;
				let wxtitle = this.legalriskDetail.title;
				let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
				let wxdesc = this.regTag(this.legalriskDetail.content);
				util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
			})
		}
	},
	methods: {
		regTag:function(cont){
			let reg = /<(?:.|\s)*?>/g;
			return cont.replace(reg,"")
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
	#legalriskDetail {padding-bottom:6.5rem;}
	#legalriskDetail .legalData {font-size: 1.3rem;color: #666666;line-height: 2.4rem;padding: .9rem 0;}
	#legalriskDetail .legalCont {padding: 0 1.5rem 1.5rem 1.5rem;}
	#legalriskDetail .legalCont h1 {font-size: 1.8rem;font-weight: bold;line-height: 4.5rem;}
	#legalriskDetail .legalCont p {color: #666666;line-height: 2.4rem;}
	#legalriskDetail .legalInfo {font-size: 1.3rem;line-height: 2.3rem;margin-top: 2rem;}
	#legalriskDetail .legalInfo p span {margin-right: 1.6rem;}

	#legalriskDetail .legalLeaveword .leavewordTitle {height: 3rem;line-height: 3rem;background-color: #f4f4f4;font-size: 1.3rem;color: #999999;padding-left: 1.5rem;}
	#legalriskDetail .legalLeaveword .leavewordList ul li {padding: 0 1.5rem 1.2rem 1.5rem;border-top: 1px solid #e5e5e5;}
	#legalriskDetail .legalLeaveword .leavewordList ul li:first-child {border: none;}
	#legalriskDetail .legalLeaveword .leavewordList ul li .lw-top {height: 5.8rem;position: relative;line-height: 5.8rem;}
	#legalriskDetail .legalLeaveword .leavewordList ul li .lw-top img {width: 2.8rem;height: 2.8rem;border-radius: 50%;position: absolute;left: 0;top: 50%;transform: translateY(-50%);}
	#legalriskDetail .legalLeaveword .leavewordList ul li .lw-top p {position: absolute;left: 3.8rem;top: 0; font-size: 1.5rem;}
	#legalriskDetail .legalLeaveword .leavewordList ul li .lw-top span {position: absolute;right: 0;top: 0; font-size: 1.2rem;color: #999999;}
	#legalriskDetail .legalLeaveword .leavewordList ul li .lw-cont {font-size: 1.5rem;color: #666666;line-height: 2.2rem;}

	#legalriskDetail .legalLeaveword .leavewordList ul li .lw-reply {border-left: .15rem solid #5ea48c;padding-left: 1rem;margin-top: 1rem;}
	#legalriskDetail .legalLeaveword .leavewordList ul li .lw-reply h2 {font-size: 1.5rem;font-weight: bold;margin-bottom: .7rem;}
	#legalriskDetail .legalLeaveword .leavewordList ul li .lw-reply p {font-size: 1.5rem;color: #666666;}

	#legalriskDetail .legalLeaveword .leavewordList .noLeaveword {padding-top: 6rem;text-align: center;}
	#legalriskDetail .legalLeaveword .leavewordList .noLeaveword img {width: 6rem;}
	#legalriskDetail .legalLeaveword .leavewordList .noLeaveword p {font-size: 1.5rem;color: #999999;margin-top: 1.1rem;}

	#legalriskDetail .leaveWordBtn {height: 4rem;line-height: 4rem;position: fixed;bottom: 0;left: 0;width: 100%;background-color: #f4f4f4;text-align: center;color: #333333;}
	#legalriskDetail .leaveWordBtn img {width: 1.9rem;height: 1.5rem;margin-right: .5rem;}
</style>
