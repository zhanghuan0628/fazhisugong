<template>
  	<div id="testReview">
		<div class="testRate">
			<div class="testNum">
				<span class="which" v-text = "curNum"></span><i>/</i><span class="total" v-text = "qNum"></span>
			</div>
			<div class="progressBar">
				<em :style= "{'width': curNum*100/qNum + '%'}"></em>
			</div>
		</div>
		
		<div id="questionDetail" v-if = "qList.length != 0">
			<div :data-question = "qList[curInd].id" class="testDescription" v-text = "qList[curInd].qstn"></div>

			<div class="testChoice">
				<ul>
					<template v-for = "(todo,index) in qList[curInd].list">
						<li :key = "index" :class = "{'tc_right' : todo.result == '1','tc_wrong' :todo.num == '1' && todo.result == '0'}">
							{{ todo.title }}
							<img v-if = "todo.result == '1'" src = "/static/images/right.png" />
							<img v-else-if = "todo.num == '1' && todo.result == '0'" src = "/static/images/wrong.png" />
						</li>
					</template>
				</ul>
			</div>
		</div>

		<div class="testBottom">
			<a class = "prev" v-if = "curNum != 1" @click = "prev">< 上一题</a>
			<a class = "next" v-if = "curNum != qNum" @click = "next">下一题 ></a>
		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'testReview',
  	data () {
    	return {
			themeId:this.$route.params.themeId,
			qList:[],
			curInd:0,
			curNum:1,
			qNum:""
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgAskController/queryUserBackTheme',this.$qs.stringify({
			themeId:this.themeId
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.qList = data.data;
				this.qNum = data.data.length;
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
		prev:function(){
			this.curInd--;
			this.curNum--;
		},
		next:function(){
			this.curInd++;
			this.curNum++;
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
	#testReview {position: absolute;top: 0;bottom: 0;left: 0;right: 0;background: url(~static/images/judgeBg.jpg) no-repeat;background-size: cover;background-position: center center;}
	#testReview .testRate {padding: 0 1.5rem;margin-bottom: 4.5rem;text-align: center;}
	#testReview .testRate .testNum {color: #999999;font-size: 1.5rem;line-height: 4.7rem;}
	#testReview .testRate .testNum .which {color: #429c84;font-weight: bold;}
	
	#testReview .testRate .progressBar {position: relative;width: 100%; height: .8rem;background-color: #e8e8e8;border-radius: 10px;}
	#testReview .testRate .progressBar em {display: block;position: absolute;left: 0;top: 0;height: 100%;border-radius: 10px;background-color: #95c2b6;}
	
	#testReview .testDescription {padding: 0 1.5rem;font-size: 1.6rem;color: #333333;line-height: 2.4rem;}
	#testReview .testDescription img {width: 100%;height: auto;display: block;}
	#testReview .testChoice {padding: 0 1.5rem;margin-top: 2.3rem;}
	#testReview .testChoice ul {width: 100%;border: 1px solid #dbdbdb;border-radius: 5px;background-color: #FFFFFF;}
	#testReview .testChoice ul li {border-top: 1px solid #dbdbdb;padding:1.2rem 1.4rem;position: relative;font-size: 1.5rem;line-height: 2.6rem;}
	#testReview .testChoice ul li img {width: 2.8rem;position: absolute;right: 1rem;top: 50%;transform: translateY(-50%);}
	#testReview .testChoice ul li:first-child {border: none;}
	#testReview .tc_right {color: #FFFFFF!important;background-color: #429c84!important;}
	#testReview .tc_wrong {color: #FFFFFF!important;background-color: #bababa!important;}

	#testReview .testBottom {position: fixed;left: 0;bottom: 0;width: 100%;height: 4.9rem;border-top: 1px solid #429c84;line-height: 4.9rem;}
	#testReview .testBottom a {font-size: 1.6rem;position: absolute;top: 0;color: #429c84;padding: 0 1.5rem;}
	#testReview .testBottom .prev {left: 0;}
	#testReview .testBottom .next {right: 0;}
</style>
