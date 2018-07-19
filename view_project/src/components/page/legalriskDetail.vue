<template>
  	<div id="legalriskDetail">
		<!-- <div class="legalData">
			<p>编号：法律风险（2017）第1号</p>
			<p>送达单位：运维检修部</p>
		</div>

		<div class="legalCont">
			<h1>情况概述</h1>
			<p>电力设施建成投运后，由于其他工程新、改扩建施工中相互妨碍等因素，需要迁移或拆除电力设施的，在收到迁移或拆除申请后、正式迁移或拆除的这段期间，如发生安全事故，供电公司将存在一定的责任风险。</p>

			<h1>法律风险提示</h1>
			<p>根据《电力法》第五十五条规定：电力设施与公用工程、绿化工程和其他工程在新建、改建或扩建中相互妨碍时，有关单位应当按照国家有关规定协商。</p>

			<h1>法律建议</h1>
			<p>运行维护单位应当根据区域不同及线路性质，确定各条线路的巡视周期，指定专门人员（也可以采取业务外包的方式，但要严格管理）。</p>
		</div>

		<div class="legalInfo">
			<p><span>联系人：赵志强</span><span>电话：8807888</span></p>
			<p>签发时间：2017年8月17日</p>
		</div> -->
		<div class="legalCont" v-html = "legalriskDetail.content">

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
			legalriskDetail:{}
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
	#legalriskDetail {padding: 0 1.5rem 6.5rem 1.5rem;}
	#legalriskDetail .legalData {font-size: 1.3rem;color: #666666;line-height: 2.4rem;padding: .9rem 0;}
	#legalriskDetail .legalCont {}
	#legalriskDetail .legalCont h1 {font-size: 1.8rem;font-weight: bold;line-height: 4.5rem;}
	#legalriskDetail .legalCont p {color: #666666;line-height: 2.4rem;}
	#legalriskDetail .legalInfo {font-size: 1.3rem;line-height: 2.3rem;margin-top: 2rem;}
	#legalriskDetail .legalInfo p span {margin-right: 1.6rem;}
	#legalriskDetail .leaveWordBtn {height: 4rem;line-height: 4rem;position: fixed;bottom: 0;left: 0;width: 100%;background-color: #f4f4f4;text-align: center;color: #333333;}
	#legalriskDetail .leaveWordBtn img {width: 1.9rem;height: 1.5rem;margin-right: .5rem;}
</style>
