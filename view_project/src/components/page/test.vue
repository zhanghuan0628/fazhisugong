<template>
  	<div id="test">
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
						<li :key = "index" @click = "nextQuestion(todo.result,index)" :class = "{'tc_right' : todo.result == '1' && isChoose,'tc_wrong' : index === optInd && todo.result != '1'}">
							{{ todo.title }}
							<img v-if = "isChoose && todo.result == '1'" src = "/static/images/right.png" />
							<img v-else-if = "isChoose && index === optInd && todo.result != '1'" src = "/static/images/wrong.png" />
						</li>
					</template>
				</ul>
			</div>
		</div>
		
	

  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'test',
  	data () {
    	return {
			themeId:this.$route.params.themeId,
			num:this.$route.params.num,
			singleScore:"",
			qList:[],
			curInd:0,
			curNum:1,
			optInd:"",
			isChoose:false,
			qNum:"",
			rightNum:0,
			testData:[],
			hasSubmit:false
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
				this.singleScore = data.data.score;
			} else if (data.code == "3007") {
				this.$store.commit('logout');
				this.$router.push({name:'login'})
			}
		})
		.catch(err=>{
			console.log(err);
		});

		this.$http.post(this.$store.state.apiUrl+'/SgAskController/querySubjectByTheme',this.$qs.stringify({
			themeId:this.themeId,
			num:this.num
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
		nextQuestion:function(result,index){
			if (!this.isChoose) {
				this.optInd = index;
				this.isChoose = true;
				if (result == "1") {
					this.rightNum++;
				}
				
				this.testData.push({"question_id":this.qList[this.curInd].id,"correct":result,"choose":index});

				let _this = this;
				setTimeout(() => {
					if (_this.curNum == _this.qNum) {
						this.submitAnswer();
						return false;
					}
					_this.curInd++;
					_this.curNum++;
					_this.optInd = "";
					this.isChoose = false;
				}, 600);
			}
		},
		submitAnswer:function(){
			var obj = {
				themeId:this.themeId,
				answerJson:JSON.stringify(this.testData),
				score:parseInt(this.singleScore) * this.rightNum,
				rightNum:this.rightNum,
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
			console.log(obj)
			if (!this.hasSubmit) {
				this.hasSubmit = true;
				let score = parseInt(this.singleScore) * this.rightNum;
				this.$http.post(this.$store.state.apiUrl+'/SgAskController/insertAnswerLog',this.$qs.stringify({
					themeId:this.themeId,
					answerJson:JSON.stringify(this.testData),
					score:score,
					rightNum:this.rightNum
				}),{
					headers:{
						'Authorization':'Bearer ' + this.$store.state.Authorization
					}
				})
				.then(res=>{
					let data = res.data;
					if (data.code == "2000") {
						this.$router.replace({name:'testResult',params:{themeId:this.themeId}})
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
	#test {position: absolute;top: 0;bottom: 0;left: 0;right: 0;background: url(~static/images/judgeBg.jpg) no-repeat;background-size: cover;background-position: center center;}
	#test .testRate {padding: 0 1.5rem;margin-bottom: 4.5rem;text-align: center;}
	#test .testRate .testNum {color: #999999;font-size: 1.5rem;line-height: 4.7rem;}
	#test .testRate .testNum .which {color: #429c84;font-weight: bold;}
	
	#test .testRate .progressBar {position: relative;width: 100%; height: .8rem;background-color: #e8e8e8;border-radius: 10px;}
	#test .testRate .progressBar em {display: block;position: absolute;left: 0;top: 0;height: 100%;border-radius: 10px;background-color: #95c2b6;}
	
	#test .testDescription {padding: 0 1.5rem;font-size: 1.6rem;color: #333333;line-height: 2.4rem;}
	#test .testDescription img {width: 100%;height: auto;display: block;}
	#test .testChoice {padding: 0 1.5rem;margin-top: 2.3rem;}
	#test .testChoice ul {width: 100%;border: 1px solid #dbdbdb;border-radius: 5px;background-color: #FFFFFF;}
	#test .testChoice ul li {border-top: 1px solid #dbdbdb;padding:1.2rem 1.4rem;position: relative;font-size: 1.5rem;line-height: 2.6rem;}
	#test .testChoice ul li img {width: 2.8rem;position: absolute;right: 1rem;top: 50%;transform: translateY(-50%);}
	#test .testChoice ul li:first-child {border: none;}
	#test .tc_right {color: #FFFFFF!important;background-color: #429c84!important;}
	#test .tc_wrong {color: #FFFFFF!important;background-color: #bababa!important;}
	#test .testChoice ul li:active {color: #FFFFFF;background-color: #429c84;}




</style>
