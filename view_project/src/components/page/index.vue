<template>
  	<div id="index">
		<swiper :options = "bannerSwiper" id="banner">
			<swiper-slide v-for = "todo in bannerList" :key = "todo.id" :style = "{'backgroundImage' : 'url('+todo.bannerImg+')'}">
				<a @click = "goUrl(todo.sourceId,todo.sortNum,todo.type)"></a>
			</swiper-slide>
			<div class="swiper-pagination" slot="pagination"></div>
		</swiper>

		<div class="menuList">
			<ul>
				<li>
					<img class="draw" src="~static/images/draw.png" alt="">
					<router-link to = '/iJudge'>
						<img class="menuImg" src="~static/images/menu1.png" alt="">
						<p>我是法官</p>
					</router-link>
				</li>
				<li>
					<router-link to = '/consultOnline'>
						<img class="menuImg" src="~static/images/menu2.png" alt="">
						<p>在线咨询</p>
					</router-link>
				</li>
				<li>
					<router-link to = '/legalrisk'>
						<img class="menuImg" src="~static/images/menu3.png" alt="">
						<p>法律风险</p>
					</router-link>
				</li>
				<li>
					<router-link to = '/talisman'>
						<img class="menuImg" src="~static/images/menu4.png" alt="">
						<p>苏供法宝</p>
					</router-link>
				</li>
				<li>
					<router-link to = '/dynamicRuleList'>
						<img class="menuImg" src="~static/images/menu5.png" alt="">
						<p>法治动态</p>
					</router-link>
				</li>
				<li>
					<router-link to = '/legalClassList'>
						<img class="menuImg" src="~static/images/menu6.png" alt="">
						<p>法治讲堂</p>
					</router-link>
				</li>
			</ul>
		</div>

		<div class="indexList">
			<h1 class="lineTitle"><em></em><span>法治动态</span><router-link to = '/dynamicRuleList'>更多 ></router-link></h1>
			<ul>
				<template v-for = "todo in dynamicRuleList">
					<li :key = "todo.id" @click = "$router.push({name:'dynamicRuleDetail',params:{id:todo.id}})">
						<div :style = "{'backgroundImage':'url('+todo.imgUrl+')'}"></div>
						<h1 v-text = "todo.title"></h1>
						<p v-text = "regTag(todo.content)"></p>
					</li>
				</template>
			</ul>
		</div>

		<div class="indexList">
			<h1 class="lineTitle"><em></em><span>法治讲堂</span><router-link to = '/legalClassList'>更多 ></router-link></h1>
			<ul>
				<template v-for = "todo in legalClassList">
					<li :key = "todo.id" @click = "$router.push({name:'legalClassDetail',params:{id:todo.id,sortnum:todo.sortnum}})">
						<div :style = "{'backgroundImage':'url('+todo.imgUrl+')'}"><em></em></div>
						<h1 v-text = "todo.title"></h1>
						<p v-text = "regTag(todo.content)"></p>
					</li>
				</template>
			</ul>
		</div>
		<bottomNavi></bottomNavi>
  	</div>
</template>

<script>
import store from '@/store/store';
import bottomNavi from '@/components/common/bottomNavi';
import util from '@/assets/js/util';

export default {
	name: 'index',
	components:{
		bottomNavi
	},
  	data () {
    	return {
			bannerList:[],
			dynamicRuleList:[],
			legalClassList:[],
      		bannerSwiper:{
				observer:true, //修改swiper自己或子元素时，自动初始化swiper
				observeParents:true,
				slidesPerView: 'auto',
				spaceBetween : 14,
				centeredSlides: true,
				// loop:true,
				pagination: {
					el: '.swiper-pagination'
				}
			}
    	}
	},
	created () {
		// banner轮播
		this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/queryBanner',this.$qs.stringify({
		}))
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.bannerList = data.data;
			}
		})
		.catch(err=>{
			console.log(err);
		});

		// 首页列表（动态）
		this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgLaw',this.$qs.stringify({
			category:"law_information"
		}))
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.dynamicRuleList = data.data.dataList.slice(0,3);
			}
		})
		.catch(err=>{
			console.log(err);
		});

		// 首页列表（讲堂）
		this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgLaw',this.$qs.stringify({
			category:"law_lecture_room"
		}))
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.legalClassList = data.data.dataList.slice(0,3);
			}
		})
		.catch(err=>{
			console.log(err);
		});
	},
	mounted () {
		// 分享配置
		let locationUrl = location.protocol+"//"+location.host;
		let wxtitle = "法治苏供";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "为您提供专业的法律维权服务!";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		regTag:function(cont){
			let reg = /<(?:.|\s)*?>/g;
			return cont.replace(reg,"")
		},
		goUrl:function(id,sortnum,type){
			if (type == "info") {
				this.$router.push({name:'dynamicRuleDetail',params:{id:id}})
			} else if (type == "hall") {
				this.$router.push({name:'legalClassDetail',params:{id:id,sortnum:sortnum}})
			} else if (type == "ask") {
				this.$router.push({name:'consultOnlineDetail',params:{topicId:id}})
			} else if (type == "theme") {
				this.$router.push({name:'iJudeg'})
			}
		}
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#index {padding-bottom: 5rem;}
	#index #banner {height: 15rem;background-color: #ffffff;}
	#index #banner .swiper-slide {width: 85.3%;background-size: cover;background-position: center center;}
	#index #banner .swiper-slide a {display: block;width: 100%;height: 100%;}

	#index .menuList {padding-bottom: 2.5rem;margin-bottom: .6rem;background-color: #ffffff;}
	#index .menuList ul {display: flex;display: -webkit-flex;justify-content: space-between;flex-wrap: wrap;}
	#index .menuList ul li {width: 33.3%;text-align: center;padding-top: 2rem;position: relative;}
	#index .menuList ul li .draw {position: absolute;top: 1.7rem;width: 5rem; left: 50%;margin-left: 1.5rem;animation : draw 1s linear infinite;-webkit-animation : draw 1s linear infinite;}
	@keyframes draw
	{
		0% {transform: translateY(0)}
		25% {transform: translateY(10%)}
		50% {transform: translateY(0)}
		75% {transform: translateY(-10%);}
		100% {transform: translateY(0);}
	}

	@-webkit-keyframes draw
	{
		0% {transform: translateY(0)}
		25% {transform: translateY(10%)}
		50% {transform: translateY(0)}
		75% {transform: translateY(-10%);}
		100% {transform: translateY(0);}
	}
	#index .menuList ul li .menuImg {width: 5.5rem;}
	#index .menuList ul li p {line-height: 1.4rem;margin-top: 1rem;color: #333333;}

	#index .indexList {background-color: #ffffff;margin-bottom: .6rem;}
	#index .indexList ul {padding: 0 1.5rem;}
	#index .indexList ul li {padding: 2rem 0;border-top: 1px solid #eeeeee;position: relative;}
	#index .indexList ul li:first-child {border: none;}
	#index .indexList ul li div {width: 10.5rem;height: 7rem;background-repeat: no-repeat;background-size: cover;background-position: center center;position: relative;}
	#index .indexList ul li div em {display: block;width: 3.2rem;height: 3.2rem;background: url(~static/images/playCover.png);background-size: 100% 100%;position: absolute;left: 50%;top: 50%;transform: translate(-50%,-50%);}
	#index .indexList ul li h1 {position: absolute;top: 1.6rem;left: 11.5rem;right: 0; font-size: 1.6rem;overflow:hidden; text-overflow:ellipsis;display:-webkit-box; -webkit-box-orient:vertical;-webkit-line-clamp:2;}
	#index .indexList ul li p {position: absolute;bottom: 2rem;left: 11.5rem;right: 0;font-size: 1.3rem;color: #999999; overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
</style>
