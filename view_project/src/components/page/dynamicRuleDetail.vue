<template>
  	<div id="dynamicRuleDetail">
		<div class="dynamicRuleDetail">
			<h1 v-text = "dynamicRuleDetail.title"></h1>
			<div class="dynamicRuleInfo">
				<p class="author">文/{{ dynamicRuleDetail.author }}</p>
				<p class="date">{{dynamicRuleDetail.createDate | changeDate}}</p>
			</div>
			<img class="dynamicRuleImg" v-if = "dynamicRuleDetail.imgUrl != null && dynamicRuleDetail.imgUrl != ''" :src = "dynamicRuleDetail.imgUrl" alt="">
			<div class="dynamicRuleCont" v-html = "dynamicRuleDetail.content"></div>
		</div>
		<div class="collectBottom">
			<div class="collectBtn" @click = "collect">
				<img :src = "isCollect == true ? '/static/images/collect2.png' : '/static/images/collect1.png'" alt="">
				<p v-text = "isCollect == true ? '已收藏' : '收藏'"></p>
			</div>
		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'dynamicRuleDetail',
  	data () {
    	return {
			id:this.$route.params.id,
			dynamicRuleDetail:{},
			isCollect:false
    	}
	},
	filters: {
		changeDate: function(value) {
			if(value) {
				value = value.toString()
				return value = value.substring(0, 10)
			}
		}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgLawDetail',this.$qs.stringify({
			id:this.id,
			category:"law_information",
			sortnum:0
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.dynamicRuleDetail = data.data;
				if (data.data.favorite != undefined) {
					this.isCollect = true;
				}
				
			}
		})
		.catch(err=>{
			console.log(err);
		});
	},
	mounted () {
		this.$nextTick( function () {
			
    })
	},
	watch:{
		dynamicRuleDetail:function(){
			this.$nextTick(() => {
				// 分享配置
				let locationUrl = location.protocol+"//"+location.host+"/dynamicRuleDetail/"+this.id;
				let wxtitle = this.dynamicRuleDetail.title;
				let wximgUrl = this.dynamicRuleDetail.imgUrl;
				let wxdesc = this.regTag(this.dynamicRuleDetail.content);
				util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
			})
		}
	},
	
	methods: {
		collect:function(){
			if (this.$store.state.userId == "") {
				this.$router.push({name:'login'});
				return false;
			}
			this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/insertUserFavorite',this.$qs.stringify({
				sourceId:this.id,
				sourceType:"law_information"
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.isCollect = !this.isCollect;
				}
			})
			.catch(err=>{
				console.log(err);
			});
		},
		regTag:function(cont){
			let reg = /<(?:.|\s)*?>/g;
			return cont.replace(reg,"")
		}
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#dynamicRuleDetail .dynamicRuleDetail {padding: 1.5rem;}
	#dynamicRuleDetail .dynamicRuleDetail h1 {font-size: 2.1rem;line-height: 3.4rem;font-weight: bold;}
	#dynamicRuleDetail .dynamicRuleDetail .dynamicRuleInfo {font-size: 1.3rem;line-height: 1.3rem;height: 1.3rem;color: #666666;margin: 1.3rem 0 3rem 0;position: relative;}
	#dynamicRuleDetail .dynamicRuleDetail .dynamicRuleInfo .author {position: absolute;left: 0;}
	#dynamicRuleDetail .dynamicRuleDetail .dynamicRuleInfo .date {position: absolute;right: 0;}
	#dynamicRuleDetail .dynamicRuleDetail .dynamicRuleImg {width: 100%;}
	#dynamicRuleDetail .dynamicRuleDetail .dynamicRuleCont {font-size: 1.5rem;color: #666666;line-height: 2.8rem;}
	#dynamicRuleDetail .dynamicRuleDetail .dynamicRuleCont >>>  img{max-width: 100% !important;}
	#dynamicRuleDetail .dynamicRuleDetail .dynamicRuleCont >>>  section{max-width: 100% !important;}
	#dynamicRuleDetail .dynamicRuleDetail .dynamicRuleCont >>>  section{max-width: 100% !important;}

	#dynamicRuleDetail .collectBottom {height: 11rem; padding-top: 1rem;}
	#dynamicRuleDetail .collectBottom .collectBtn {width: 7rem;text-align: center;margin: 0 auto;}
	#dynamicRuleDetail .collectBottom .collectBtn img {width: 100%;}
	#dynamicRuleDetail .collectBottom .collectBtn p {font-size: 1.3rem;line-height: 1.3rem;color: #666666;}
</style>
