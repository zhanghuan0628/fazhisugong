<template>
  	<div id="legalrisk">
		<div class="legalSearch">
			<div class="searchBar">
				<em></em>
				<form id="searchForm" action="#" @submit.prevent = "search"><input type="search" placeholder="请输入关键词" v-model = "searchCont" /></form>
			</div>
		</div>

		<div v-if = "!searching">
			<div class="legalClassify">
				<template v-for = "todo in classifyList">
					<div :key = "todo.id" @click = "searchClassify(todo.id)">
						<img :src = "todo.img" alt="">
						<p v-text = "todo.name"></p>
					</div>
				</template>
			</div>

			<div class="suggestedReading">
				<h1 class="lineTitle"><em></em><span>推荐阅读</span></h1>
				<ul class="riskList">
					<template v-for = "todo in recommendRead">
						<li :key = "todo.id" v-text = "todo.title" @click = "$router.push({name:'legalriskDetail',params:{id:todo.id}})"></li>
					</template>
				</ul>
			</div>
		</div>

		<div v-else>
			<swiper :options = "classifySwiper" id="classifyTitle">
				<swiper-slide v-for = "todo in classifyList" :key = "todo.id">
					<div @click = "switchClassify(todo.id)">{{ todo.name }}<em v-if = "classifyId == todo.id"></em></div>
				</swiper-slide>
			</swiper>

			<div class="legalriskList">
				<div class="searchClassify mescroll" id="list">
					<ul class="riskList" id="listItem">
						<template v-for = "todo in legalriskList">
							<li :key = "todo.id" v-text = "todo.title" @click = "$router.push({name:'legalriskDetail',params:{id:todo.id}})"></li>
						</template>
					</ul>
				</div>
			</div>
			
		</div>
		
  	</div>
</template>

<script>
import store from '@/store/store';
import MeScroll from '../../../static/js/mescroll.min.js';
import util from '@/assets/js/util';

export default {
	name: 'legalrisk',
  	data () {
    	return {
			recommendRead:[],
			classifyList:[],
			searchCont:"",
			searching:false,
			classifySwiper:{
				observer:true, //修改swiper自己或子元素时，自动初始化swiper
				observeParents:true,
				slidesPerView:3.5,
				freeMode : true
			},
			classifyId:"",
			riskMescroll:null,
			legalriskList:[]
    	}
	},
	created () {
		// 所有分类
		this.$http.post(this.$store.state.apiUrl+'/SgLawRiskController/queryAllRiskMajor',{
		},{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.classifyList = data.data;
				this.classifyId = data.data[0].id;
			} else if (data.code == "3007") {
				this.$store.commit('logout');
				this.$router.push({name:'login'})
			}
		})
		.catch(err=>{
			console.log(err);
		});

		// 推荐阅读
		this.$http.post(this.$store.state.apiUrl+'/SgLawRiskController/queryRandLawRisk',{
		},{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.recommendRead = data.data
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
		let locationUrl = location.protocol+"//"+location.host+"/legalrisk";
		let wxtitle = "国网苏州供电公司的法治平台";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "法治苏供，为你提供法律服务";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	watch:{
		searching:function(){
			this.$nextTick(() => {

				let _this = this;
				this.riskMescroll = new MeScroll("list", { 
					down: {
						callback: function (mescroll) { 
							_this.legalriskList = [];
							mescroll.resetUpScroll(); 
						}
					},
					up: {
						auto:false,
						clearEmptyId: "listItem",
						callback: function (page) { _this.legalrisk(page) }, 
						isBounce: false
						// htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
					}
				});

			})
		}
	},
	methods: {
		search:function(){
			if (this.searchCont != "") {
				if (this.searching) {
					this.riskMescroll.resetUpScroll();
				}
				this.searching = true;
			} else {
				this.searching = false;
				this.classifyId = this.classifyList[0].id;
			}
		},
		searchClassify:function(id){
			this.classifyId = id;
			this.searching = true;
		},
		switchClassify:function(id){
			this.classifyId = id;
			this.riskMescroll.resetUpScroll();
		},
		legalrisk:function(page){
			this.$http.post(this.$store.state.apiUrl+'/SgLawRiskController/queryLawRiskByMajor',this.$qs.stringify({
				id:this.classifyId,
				title:this.searchCont,
				pageNo:page.num
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.legalriskList = this.legalriskList.concat(data.data.dataList);

					this.riskMescroll.endByPage(data.data.dataList.length, data.data.totalPage);
				}
			})
			.catch(err=>{
				console.log(err);
			});
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
	#legalrisk {height:100%;}
	#legalrisk .legalSearch {height: 5rem;background-color: #429c84;position: relative;}
	#legalrisk .legalSearch .searchBar {position: absolute;left: 1.5rem;right: 1.5rem;height: 3.3rem;top: 50%;transform: translateY(-50%);background-color: #68b09d;border-radius: 50px;}
	#legalrisk .legalSearch .searchBar em {position: absolute;display: block;width: 1.5rem;height: 1.5rem;left: 1rem;top: 50%;transform: translateY(-50%);background: url(~static/images/search.png);background-size: 100% 100%;}
	#legalrisk .legalSearch .searchBar #searchForm {height: 100%;}
	#legalrisk .legalSearch .searchBar #searchForm input {width: 100%;height: 100%;border: none;background-color: transparent;padding-left: 3.3rem;color: #ffffff;}
	#legalrisk .legalSearch .searchBar #searchForm input::-webkit-search-cancel-button {display: none;}
	#legalrisk .legalSearch .searchBar #searchForm input::-webkit-input-placeholder {color: #ffffff;}
	#legalrisk .legalSearch .searchBar #searchForm input:-moz-placeholder {color: #ffffff;}
	#legalrisk .legalSearch .searchBar #searchForm input::-moz-placeholder {color: #ffffff;}
	#legalrisk .legalSearch .searchBar #searchForm input:-ms-input-placeholder {color: #ffffff;}

	#legalrisk .legalClassify {padding: 4.3rem 1.5rem 3rem 1.5rem;display: flex;display: -webkit-flex;background-color: #ffffff;justify-content: space-between;flex-wrap: wrap;}
	#legalrisk .legalClassify div {width: 30.5%;height: 10.4rem;padding-top: 3.6rem; background-color: #ffffff;border-radius: 10px;text-align: center;box-shadow: 0px 0px 6px 3px #dfeeea;margin-bottom: 2rem;}
	#legalrisk .legalClassify div img {width: 3.4rem;height: 3.4rem;margin-bottom: .8rem;}
	#legalrisk .legalClassify div p {font-size: 1.7rem;line-height: 1.7rem;}
	
	#legalrisk .legalriskList {position: absolute;top: 10rem;left: 0;right: 0;bottom: 0;}
	
	#legalrisk .riskList li {padding: 1.2rem;font-size: 1.5rem;color: #666666;line-height: 2.2rem;}
	#legalrisk .riskList li:first-child {border: none;}

	#legalrisk #classifyTitle {height: 5rem;border-bottom: 1px solid #e5e5e5;}
	#legalrisk #classifyTitle .swiper-slide {line-height: 5rem;font-size: 1.5rem;text-align: center;}
	#legalrisk #classifyTitle .swiper-slide div {position: relative;}
	#legalrisk #classifyTitle .swiper-slide em {position: absolute;bottom: .3rem;left: 50%;transform: translateX(-50%);width: 5.8rem;height: .3rem;background-color: #429c84;border-radius: 10px;}


</style>
