<template>
  	<div id="iJudge">
		<img class="iJudgeTop" src="~static/images/iJudgeTop.png" alt="">
		<h1 class="iJudgeTitle" v-text = "testInfo.stage"></h1>

		<div class="iJudgeIntro">
			<h2>活动介绍</h2>
			<p>{{ testInfo.num }}道题检验你对法律知识的了解程度，看看你的法官水平如何？</p>
		</div>

		<div class="iJudgeRule">
			<h2>活动规则</h2>
			<p>点击进入答题页面开始答题，及格以上即可参与抽奖。</p>
		</div>

		<a class="startAnswer" :style = "{'backgroundColor' : isAct ? '#429c84' : '#cccccc'}" @click = "startAnswer">开始答题</a>

  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'iJudge',
  	data () {
    	return {
			testInfo:{},
			isAct:false
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgAskController/queryCheckTheme',this.$qs.stringify({
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.testInfo = data.data;
				if (data.data.act != 1) {
					this.$toast.center("本期活动已结束，敬请期待下次活动！");
				} else {
					this.isAct = true;
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
		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/iJudge";
		let wxtitle = "我是法官| 法律知识在线答题";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "检验你对法律知识的了解程度，看看你的法官水平如何？";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		startAnswer:function(){
			if (this.isAct) {
				this.$router.push({name:'test',params:{themeId:this.testInfo.themeId,num:this.testInfo.num}})
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
	#iJudge {position: absolute;top: 0;bottom: 0;left: 0;right: 0;background: url(~static/images/judgeBg.jpg) no-repeat;background-size: cover;background-position: center center;}
	#iJudge .iJudgeTop {width: 100%;}
	#iJudge .iJudgeTitle {width: 11.2rem;height: 3.8rem;line-height: 3.8rem;text-align: center;font-size: 1.8rem;color: #ffffff;margin: 1.4rem auto 0 auto;background: url(~static/images/iJudgeTitle.png);background-size: 100% 100%;}
	#iJudge .iJudgeIntro,#iJudge .iJudgeRule {padding: 0 2.9rem;margin-top: 2rem;}
	#iJudge .iJudgeIntro h2 {font-size: 1.7rem;padding-left: 2.5rem;line-height: 3.8rem; background: url(~static/images/iJudge1.png) no-repeat;background-size: 1.8rem 1.8rem; background-position: left center;}
	#iJudge .iJudgeRule h2	{font-size: 1.7rem;padding-left: 2.5rem;line-height: 3.8rem; background: url(~static/images/iJudge2.png) no-repeat;background-size: 1.8rem 1.8rem; background-position: left center;}
	#iJudge .iJudgeIntro p,#iJudge .iJudgeRule p {font-size: 1.5rem;line-height: 2.4rem;}

	#iJudge .startAnswer {display: block;width: 31.7rem; max-width: 84.5%;height: 4.9rem;line-height: 4.9rem;color: #ffffff;font-size: 1.8rem;background-color: #429c84;border-radius: 50px;margin: 3.7rem auto 0 auto;text-align: center;}


</style>
