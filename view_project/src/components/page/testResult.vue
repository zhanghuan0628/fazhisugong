<template>
  	<div id="testResult">

		<div id ="layer" v-if = "isWinning" @click = "isWinning = false"></div>

		<div class="winningPop" v-show = "isWinning" @click = "isWinning = false">
			<img class="colorBar" :class = "{'zoomColorBar' : isWinning && awardCode != 3}" src="/static/images/colorBar.png" />
			<div class="winningInfo">
				<img class="winningTitle" :src = "awardCode != 3 ? '/static/images/winningTitle.png' : '/static/images/thankTitle.png'" alt="">
				<p class="congratulations" v-if = "awardCode != 3">恭喜你抽中了</p>
				<p class="awardNo" v-if = "awardCode != 3" v-text = "awardNo"></p>
				<p class="winningHint" v-if = "awardCode != 3">【奖品请联系管理员领取】</p>

				<p class="noPrize" v-else>很遗憾未能中奖，<br />欢迎下次再来！</p>
			</div>
		</div>

		<div class="reportCard">
			<img class="reportBg" src="/static/images/reportBg.jpg" alt="">
			<p class="reportName" v-text = "testResult.userName"></p>
			<p class="reportTitle">在{{ testResult.stage }}【我是法官】的成绩</p>
			<p class="score">{{ testResult.score }}<span>分</span></p>
			<p class="answerNum">一共答对{{ testResult.rightNum }}题</p>
			<router-link class="review" :to = "{name:'testReview',params:{themeId:this.themeId}}">回顾考题</router-link>
			<img class="isQualified" v-if = "testResult.state == '1'" src="~static/images/excellent.png" alt="">
			<img class="isQualified" v-if = "testResult.state == '2'" src="~static/images/good.png" alt="">
			<img class="isQualified" v-if = "testResult.state == '3'" src="~static/images/qualified.png" alt="">
			<img class="isQualified" v-if = "testResult.state == '4'" src="~static/images/disqualified.png" alt="">
		</div>

		<div class="luckyDraw">
			<h1 v-if = "testResult.state != '4' && !hasDraw">恭喜你获得一次抽奖机会</h1>
			<h1 v-if = "testResult.state == '4' && !hasDraw">很遗憾，你未能获得抽奖机会，再接再厉</h1>
			<h1 v-if = "testResult.state != '4' && hasDraw">你的抽奖次数已用完</h1>
			<!-- <h1 v-text = "testResult.state == '4' ? '很遗憾，你未能获得抽奖机会，再接再厉' : '恭喜你获得一次抽奖机会'"></h1> -->
			<div class="drawZone">
				<ul>
					<li class="award" :class = "{'award-on' : awardOn == 1}"><div><img src="~static/images/draw3.png" alt=""></div></li>
					<li class="award" :class = "{'award-on' : awardOn == 2}"><div><img src="~static/images/draw2.png" alt=""></div></li>
					<li class="award" :class = "{'award-on' : awardOn == 3}"><div><img src="~static/images/draw4.png" alt=""></div></li>
					<li class="award" :class = "{'award-on' : awardOn == 4}"><div><img src="~static/images/draw3.png" alt=""></div></li>
					<li class="playDraw" @click = "startDraw"><img :src = "playBtnUrl" alt=""></li>
					<li class="award" :class = "{'award-on' : awardOn == 5}"><div><img src="~static/images/draw4.png" alt=""></div></li>
					<li class="award" :class = "{'award-on' : awardOn == 6}"><div><img src="~static/images/draw2.png" alt=""></div></li>
					<li class="award" :class = "{'award-on' : awardOn == 7}"><div><img src="~static/images/draw3.png" alt=""></div></li>
					<li class="award" :class = "{'award-on' : awardOn == 8}"><div><img src="~static/images/draw1.png" alt=""></div></li>
				</ul>
			</div>
		</div>

  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'testResult',
  	data () {
    	return {
			themeId:this.$route.params.themeId,
			testResult:{},
			answerId:"",
			playBtn:"",
			playBtnUrl:"",
			awardOn:0,
			hasDraw:false,
			isWinning:false,
			awardCode:"",
			awardNo:""
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgAskController/queryAnswerLogByUser',this.$qs.stringify({
			themeId:this.themeId
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.testResult = data.data;
				
				if (data.data.getAward == "1") {
					this.hasDraw = true;
					if (data.data.code == "0") {
						this.playBtn = "3";
					} else if (data.data.code == "1") {
						this.playBtn = "4";
					} else if (data.data.code == "2") {
						this.playBtn = "5";
					} else if (data.data.code == "3") {
						this.playBtn = "6";
					} 
				} else {
					this.hasDraw = false;
					if (data.data.state == "4") {
						this.playBtn = "2";
					} else {
						this.playBtn = "1";
					} 
				} 
				this.answerId = data.data.id;
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
	watch:{
		playBtn:function(val,oldVal){
			if (val == "1") {
				this.playBtnUrl = "/static/images/playDraw.png"
			} else if (val == "2") {
				this.playBtnUrl = "/static/images/forbidden.png"
			} else if (val == "3") {
				this.playBtnUrl = "/static/images/prize1.png"
			} else if (val == "4") {
				this.playBtnUrl = "/static/images/prize2.png"
			} else if (val == "5") {
				this.playBtnUrl = "/static/images/prize3.png"
			} else if (val == "6") {
				this.playBtnUrl = "/static/images/prize4.png"
			}
		}
	},
	methods: {
		startDraw:function(){
			if (!this.hasDraw && this.testResult.state != "4") {
				this.hasDraw = true;
				this.$http.post(this.$store.state.apiUrl+'/SgAskController/queryAllAward',this.$qs.stringify({
					themeId:this.themeId,
					answerId:this.answerId
				}),{
					headers:{
						'Authorization':'Bearer ' + this.$store.state.Authorization
					}
				})
				.then(res=>{
					let data = res.data;
					if (data.code == "2000") {
						this.awardCode = data.data.code;
						let randomAward = 0;
						if (data.data.code == "0") {
							randomAward = 8;
							this.awardNo = "一等奖";
						} else if (data.data.code == "1") {
							let randomArr = [2,6]
							let randomInd = Math.floor((Math.random()*2));
							randomAward = randomArr[randomInd];
							this.awardNo = "二等奖";
						} else if (data.data.code == "2") {
							let randomArr = [1,4,7]
							let randomInd = Math.floor((Math.random()*3));
							randomAward = randomArr[randomInd];
							this.awardNo = "三等奖";
						} else {
							let randomArr = [3,5]
							let randomInd = Math.floor((Math.random()*2));
							randomAward = randomArr[randomInd];
						}

						let rounds = randomAward + 32;
						let allNum = 0;
						for (let i = 1; i <= rounds; i++) {
							setTimeout(() => {
								if (allNum == 8) {
									allNum = 0;
								}
								allNum ++;
								this.awardOn = allNum;
							}, 4 * i * i);
						}

						setTimeout(() => {
							this.isWinning = true;
							if (this.awardCode == "0") {
								this.playBtn = "3"
							} else if (this.awardCode == "1") {
								this.playBtn = "4"
							} else if (this.awardCode == "2") {
								this.playBtn = "5"
							} else if (this.awardCode == "3") {
								this.playBtn = "6"
							} 
						}, 4 * rounds * rounds);

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
	#testResult {}
	#testResult .reportCard {position: relative;}
	#testResult .reportCard .reportBg {width: 100%;}
	#testResult .reportCard .reportName {font-size: 1.8rem;line-height: 1.8rem; position: absolute;top: 15.3%;left: 0;width: 100%;text-align: center;}
	#testResult .reportCard .reportTitle {font-size: 1.3rem;line-height: 1.3rem;color: #999999; position: absolute;top: 25.8%;left: 0;width: 100%;text-align: center;}
	#testResult .reportCard .score {font-size: 5.5rem;line-height: 5.5rem;color: #ff664d; position: absolute;top: 40%;left: 0;width: 100%;text-align: center;}
	#testResult .reportCard .score span {font-size: 1.5rem;color: #333333;}
	#testResult .reportCard .answerNum {font-size: 1.3rem;line-height: 1.3rem; position: absolute;top: 64.6%;left: 0;width: 100%;text-align: center;}
	#testResult .reportCard .review {display: block;width: 10rem;height: 3.3rem;line-height: 3.3rem;color: #333333; background-color: #dfede8;font-size: 1.3rem;text-align: center;border-radius: 50px;position: absolute;top: 73.8%;left: 50%;transform: translateX(-50%);}
	#testResult .reportCard .isQualified {position: absolute;right: 4.8rem;top: 48.7%;width: 7.9rem;}
	#testResult .luckyDraw {background-color: #5ea48c;padding-bottom: 3rem;}
	#testResult .luckyDraw h1 {font-size: 1.7rem;color: #ffffff;text-align: center;line-height: 6.1rem;}
	#testResult .luckyDraw .drawZone {width: 32.5rem;height: 32.5rem; margin: 0 auto;}
	#testResult .luckyDraw .drawZone ul {position: relative;width: 100%;height: 100%;}
	#testResult .luckyDraw .drawZone ul li {width: 10.6rem;height: 10.6rem;position: absolute;}
	#testResult .luckyDraw .drawZone ul li:nth-child(1) {left: 0;top: 0;}
	#testResult .luckyDraw .drawZone ul li:nth-child(2) {left: 50%;top: 0;transform: translateX(-50%);}
	#testResult .luckyDraw .drawZone ul li:nth-child(3) {right: 0;top: 0;}
	#testResult .luckyDraw .drawZone ul li:nth-child(4) {right: 0;top: 10.95rem;}
	#testResult .luckyDraw .drawZone ul li:nth-child(5) {left: 50%;top: 10.95rem;transform: translateX(-50%);}
	#testResult .luckyDraw .drawZone ul li:nth-child(6) {right: 0;bottom: 0;}
	#testResult .luckyDraw .drawZone ul li:nth-child(7) {left: 50%;bottom: 0;transform: translateX(-50%);}
	#testResult .luckyDraw .drawZone ul li:nth-child(8) {left: 0;bottom: 0;}
	#testResult .luckyDraw .drawZone ul li:nth-child(9) {left: 0;top: 10.95rem;}
	#testResult .luckyDraw .drawZone ul .award {border-radius: 10px;background-color: #d0f0e5;} 
	#testResult .luckyDraw .drawZone ul .award div {width: 100%;height: 10rem;border-radius: 10px;background-color: #ffffff;line-height: 10rem;}
	#testResult .luckyDraw .drawZone ul .award-on div {background-color: #ff7664;}
	#testResult .luckyDraw .drawZone ul li img {width: 100%;}

	#testResult .winningPop {position: fixed;left: 0;width: 100%;top: 50%; transform: translateY(-50%);z-index: 40;}
	#testResult .winningPop .colorBar {width: 100%;transform: scale(0);}
	#testResult .winningPop .zoomColorBar {animation : colorBar 1s forwards;-webkit-animation : colorBar 1s forwards;}
	@keyframes colorBar
	{
		100% {transform: scale(1);}
	}

	@-webkit-keyframes colorBar
	{
		100% {transform: scale(1);}
	}
	#testResult .winningPop .winningInfo {width: 23rem;height: 23rem;padding-top: 7.7rem;box-sizing: border-box; background: url(~static/images/winningBg.png);background-size: 100% 100%;position: absolute;left: 50%;top: 50%;transform: translate(-50%,-50%);text-align: center;}
	#testResult .winningPop .winningInfo .winningTitle {width: 25.1rem;position: absolute; left: 50%;transform: translateX(-50%);top: .8rem;}
	#testResult .winningPop .winningInfo .congratulations {font-size: 1.8rem;color: #ffffff;line-height: 1.8rem;margin-bottom: 1.5rem;}
	#testResult .winningPop .winningInfo .awardNo {font-size: 3rem;color: #ffffff;line-height: 3rem;margin-bottom: 1.5rem;}
	#testResult .winningPop .winningInfo .winningHint {font-size: 1.3rem;color: #ffffff;line-height: 1.3rem;}
	#testResult .winningPop .winningInfo .noPrize {font-size: 1.8rem;color: #ffffff;line-height: 4rem;}
</style>
