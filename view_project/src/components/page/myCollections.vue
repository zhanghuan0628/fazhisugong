<template>
  	<div id="myCollections">

		<swiper :options = "collectionSwiper" id="collections">
			<div class="swiper-pagination" slot="pagination"></div>
			<swiper-slide class="mescroll" id="trendsList">
				<ul class="trendsList" id="listItem1">
					<template v-for = "todo in trendsList">
						<li :key = "todo.id" @click = "$router.push({name:'dynamicRuleDetail',params:{id:todo.sourceId}})">
							<div :style = "{'backgroundImage' : 'url('+todo.imgUrl+')'}"></div>
							<h1 v-text = "todo.title"></h1>
							<p v-text = "regTag(todo.content)"></p>
						</li>
					</template>
				</ul>
			</swiper-slide>
			<swiper-slide class="mescroll" id="classList">
				<ul class="classList" id="listItem2">
					<template v-for = "todo in classList">
						<li :key = "todo.id" @click = "$router.push({name:'legalClassDetail',params:{id:todo.sourceId,sortnum:todo.sortNum}})">
							<div :style = "{'backgroundImage' : 'url('+todo.imgUrl+')'}"></div>
							<h1 v-text = "todo.title"></h1>
							<p v-text = "regTag(todo.content)"></p>
						</li>
					</template>
				</ul>
			</swiper-slide>
		</swiper>
		
  	</div>
</template>

<script>
import store from '@/store/store';
import MeScroll from '../../../static/js/mescroll.min.js';
import util from '@/assets/js/util';

export default {
	name: 'myCollections',
  	data () {
    	return {
			collectionSwiper:{
				observer:true, //修改swiper自己或子元素时，自动初始化swiper
				observeParents:true,
				pagination: {
					el: '.swiper-pagination',
					clickable: true,
					renderBullet: function (index, className) {
						let text = "";
						switch(index){
							case 0:text='法治动态';break;
							case 1:text='法治讲堂';break;
						}
						return '<span class="' + className + '">' + text + '<em></em></span>';
					}
				}
			},
			trendsMescroll:null,
			classMescroll:null,
			trendsList:[],
			classList:[]
    	}
	},
	created () {
		
	},
	mounted () {

		let _this = this;
		this.trendsMescroll = new MeScroll("trendsList", { 
			down: {
				callback: function (mescroll) { 
					_this.trendsList = [];
					mescroll.resetUpScroll(); 
				}
			},
			up: {
				auto:false,
				clearEmptyId: "listItem1",
				callback: function (page) { _this.trends(page) }, 
				isBounce: false
				// htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
			}
		});

		this.classMescroll = new MeScroll("classList", { 
			down: {
				callback: function (mescroll) { 
					_this.classList = [];
					mescroll.resetUpScroll(); 
				}
			},
			up: {
				auto:false,
				clearEmptyId: "listItem2",
				callback: function (page) { _this.class(page) }, 
				isBounce: false
				// htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
			}
		});

		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/myCollections";
		let wxtitle = "国网苏州供电公司的法治平台";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "法治苏供，为你提供法律服务";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		trends:function(page){
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/queryMyFavorite',this.$qs.stringify({
				sourceType:"law_information",
				pageNo:page.num
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.trendsList = this.trendsList.concat(data.data.dataList);

					this.trendsMescroll.endByPage(data.data.dataList.length, data.data.totalPage);
				} else if (data.code == "3007") {
					this.$store.commit('logout');
					this.$router.push({name:'login'})
				}
				
			})
			.catch(err=>{
				console.log(err);
			});
		},
		class:function(page){
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/queryMyFavorite',this.$qs.stringify({
				sourceType:"law_lecture_room",
				pageNo:page.num
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.classList = this.classList.concat(data.data.dataList);

					this.classMescroll.endByPage(data.data.dataList.length, data.data.totalPage);
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
	beforeRouteEnter (to, from, next) {
		if (localStorage.getItem("userId") != null && localStorage.getItem("userId") != "") {
			next();
		} else {
			next("/login");
		}
	},
	beforeRouteLeave (to, from, next) {
		this.trendsMescroll.destroy();
		this.classMescroll.destroy();
		next()
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#myCollections {}
	#myCollections #collections {position: absolute;top: 0;left: 0;right: 0;bottom: 0;}
	#myCollections #collections .swiper-slide {padding-top: 5.5rem;box-sizing: border-box;}
	#myCollections #collections .swiper-slide ul li {position: relative;height: 11rem;border-top: 1px solid #eeeeee;background-color: #FFFFFF;}
	#myCollections #collections .swiper-slide ul li:first-child {border: none;}
	#myCollections #collections .swiper-slide ul li div {position: absolute;left: 1.5rem;top: 50%;transform: translateY(-50%); width: 10.5rem;height: 7rem;box-sizing: border-box;border: 1px solid #eeeeee;background-size: cover;background-position: center center;}
	#myCollections #collections .swiper-slide ul li h1 {position: absolute;left: 13rem;top: 1.6rem;right: 1.5rem; font-size: 1.6rem;line-height: 2.4rem; overflow:hidden; text-overflow:ellipsis;display:-webkit-box; -webkit-box-orient:vertical;-webkit-line-clamp:2;}
	#myCollections #collections .swiper-slide ul li p {position: absolute;left: 13rem;bottom: 2rem;right: 1.5rem;font-size: 1.3rem;line-height: 1.3rem;color: #999999;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
</style>
