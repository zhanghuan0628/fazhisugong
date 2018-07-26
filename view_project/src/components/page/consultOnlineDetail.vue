<template>
  	<div id="consultOnlineDetail" v-if = "consultOnlineDetail != undefined">
		<div class="consultQuestion">
			<div class="cq-top">
				<img :src = "consultOnlineDetail.see ? '/static/images/anonymous.png' : consultOnlineDetail.headUrl" alt="">
				<p v-text = "consultOnlineDetail.see ? '匿名用户' : consultOnlineDetail.nickName"></p>
				<span v-text = "consultOnlineDetail.createDate"></span>
			</div>
			<div class="cq-cont">
				<h1 v-text = "consultOnlineDetail.title"></h1>
				<p v-text = "consultOnlineDetail.content"></p>
			</div>
		</div>

		<div class="expertsAnswer">
			<h1 class="lineTitle"><em></em><span>专家解答</span></h1>
			<ul>
				<template v-for = "todo in consultOnlineDetail.backList">
					<li :key = "todo.id">
						<div class="ea-top">
							<img :src = "todo.headImg" alt="">
							<p v-text = "todo.userName"></p>
						</div>
						<div class="ea-cont" v-text = "todo.replyContent"></div>
						<span class="ea-date" v-text = "todo.createDate"></span>
					</li>
				</template>
				
			</ul>
		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'consultOnlineDetail',
  	data () {
    	return {
			topicId:this.$route.params.topicId,
			consultOnlineDetail:{
				backList:[]
			}
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgAskController/queryAskCommentDetail',this.$qs.stringify({
			topicId:this.topicId
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.consultOnlineDetail = data.data;
				if (data.data == undefined) {
					this.$toast.center("该条问答数据已被删除");
				}
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
		consultOnlineDetail:function(){
			this.$nextTick(() => {
				let locationUrl = location.protocol+"//"+location.host+"/consultOnlineDetail/"+this.topicId;
				let wxtitle = this.consultOnlineDetail.title;
				let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
				let wxdesc = this.consultOnlineDetail.content;
				util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
			})
		}
	},
	methods: {
		
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
	#consultOnlineDetail .consultQuestion {padding: 0 1.5rem 1.5rem 1.5rem;background-color: #ffffff;}
	#consultOnlineDetail .consultQuestion .cq-top {height: 6.8rem;position: relative;}
	#consultOnlineDetail .consultQuestion .cq-top img {width: 4rem;height: 4rem;border-radius: 50%;position: absolute;left: 0;top: 50%;transform: translateY(-50%);}
	#consultOnlineDetail .consultQuestion .cq-top p {position: absolute;top: 1.5rem;left: 4.7rem;line-height: 1.4rem;}
	#consultOnlineDetail .consultQuestion .cq-top span {position: absolute;bottom: 1.5rem;left: 4.7rem;font-size: 1.2rem;line-height: 1.2rem;color: #999999;}

	#consultOnlineDetail .consultQuestion .cq-cont h1 {font-size: 1.7rem;font-weight: bold;margin-bottom: .9rem;}
	#consultOnlineDetail .consultQuestion .cq-cont p {font-size: 1.5rem;line-height: 2.2rem;}

	#consultOnlineDetail .expertsAnswer .lineTitle {height: 3.5rem;line-height: 3.5rem;color: #429c84; background-color: #f4f4f4;}

	#consultOnlineDetail .expertsAnswer ul {background-color: #ffffff;}
	#consultOnlineDetail .expertsAnswer ul li {padding: 0 1.5rem 4.3rem 1.5rem;position: relative;border-top: 1px solid #e5e5e5;}
	#consultOnlineDetail .expertsAnswer ul li:first-child {border: none;}
	#consultOnlineDetail .expertsAnswer ul li .ea-top {height: 5.8rem;position: relative;}
	#consultOnlineDetail .expertsAnswer ul li .ea-top img {width: 2.8rem;height: 2.8rem;border-radius: 50%;position: absolute;left: 0;top: 50%;transform: translateY(-50%);}
	#consultOnlineDetail .expertsAnswer ul li .ea-top p {position: absolute;left: 3.8rem;line-height: 5.8rem;}
	#consultOnlineDetail .expertsAnswer ul li .ea-cont {font-size: 1.5rem;color: #666666;line-height: 2.2rem;}
	#consultOnlineDetail .expertsAnswer ul li .ea-date {position: absolute;line-height: 4.5rem;left: 1.5rem;bottom: 0; color: #999999;font-size: 1.2rem;}
	#consultOnlineDetail .expertsAnswer ul li .ea-praise {position: absolute;line-height: 4.5rem;right: 1.5rem;bottom: 0; color: #999999;font-size: 1.2rem;padding-left: 2.2rem;background-repeat: no-repeat;background-size: 1.65rem 1.65rem;background-position: left center;background-image: url(~static/images/praiseOff.png);}
</style>
