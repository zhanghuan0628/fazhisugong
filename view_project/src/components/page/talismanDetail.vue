<template>
  	<div id="talismanDetail">

		<div id="layer" v-show = "pullMenu" @touchmove.prevent @click = "pullMenu = !pullMenu"></div>
		
		<div class="talismanTop">
			<a class="talismanMenuBtn" @click = "pullMenu = !pullMenu">目录</a>
			<p v-text = "talismanDetail.allChapter"></p>
		</div>

		<div class="talismanMenu" :style = "{left : pullMenu ? '0' : '-84.8%'}">
			<ul>
				<template v-for = "todo in talismanList">
					<li :key = "todo.id" v-text = "todo.chapter + ' ' + todo.title" @click = "$router.push({name:'talismanDetail',params:{categoryCode:categoryCode,id:todo.id}})" :style = "{'color' : todo.id == id ? '#429c84' : '#333333'}"></li>
				</template>
			</ul>
		</div>

		<div class="talismanCont">
			<h1 v-text = "talismanDetail.chapter + ' ' + talismanDetail.title"></h1>
			<div v-html = "talismanDetail.content"></div>
		</div>

		<div class="talismanBottom">
			<router-link class="prev" v-if = "talismanDetail.map.preId != undefined" :to = "{name:'talismanDetail',params:{categoryCode:categoryCode,id:talismanDetail.map.preId}}">< 上一章</router-link>
			<router-link class="next" v-if = "talismanDetail.map.nextId != undefined" :to = "{name:'talismanDetail',params:{categoryCode:categoryCode,id:talismanDetail.map.nextId}}">下一章 ></router-link>
		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'talismanDetail',
  	data () {
    	return {
			categoryCode:this.$route.params.categoryCode,
			id:this.$route.params.id,
			talismanDetail:{},
			talismanList:[],
			pullMenu:false
    	}
	},
	created () {
		// 法宝详情
		this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgMagicDetail',this.$qs.stringify({
			id:this.id
		}))
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.talismanDetail = data.data
			}
		})
		.catch(err=>{
			console.log(err);
		});

		// 目录
		this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgMagic',this.$qs.stringify({
			category:"law_magic",
			categoryCode:this.categoryCode
		}))
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.talismanList = data.data
			}
		})
		.catch(err=>{
			console.log(err);
		});
	},
	mounted () {
		
	},
	watch:{
		talismanDetail:function(){
			this.$nextTick(() => {
				// 分享配置
				let locationUrl = location.protocol+"//"+location.host+"/talismanDetail/"+this.categoryCode+'/'+this.id;
				let wxtitle = this.talismanDetail.title;
				let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
				let wxdesc = this.regTag(this.talismanDetail.content);
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
	beforeRouteUpdate (to, from, next) {
		next();
		if (to.name == "talismanDetail") {
			this.pullMenu = false;
			this.categoryCode = this.$route.params.categoryCode;
			this.id = this.$route.params.id;
			// 法宝详情
			this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgMagicDetail',this.$qs.stringify({
				id:this.id
			}))
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.talismanDetail = data.data
				}
			})
			.catch(err=>{
				console.log(err);
			});
		}
		
   	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#talismanDetail {}
	#talismanDetail .talismanTop {position: absolute;left: 0;top: 0;width: 100%; height: 5rem;line-height: 5rem;background-color: #ecf5f2;z-index: 40;}
	#talismanDetail .talismanTop .talismanMenuBtn {font-size: 1.8rem;padding-left: 3rem;background: url(~static/images/menu.png) no-repeat;background-size: 1.9rem 1.5rem;background-position: left center;position: absolute; left: 1.5rem;top: 0;}
	#talismanDetail .talismanTop p {position: absolute;right: 1.5rem;font-size: 1.5rem;color: #666666;top: 0;}

	#talismanDetail .talismanCont {position: absolute;top: 5rem;bottom: 5rem;left: 0;width: 100%;box-sizing: border-box;padding: 0 1.5rem;background-color: #ffffff;overflow: auto;overflow-y: scroll;-webkit-overflow-scrolling: touch;}
	#talismanDetail .talismanCont h1 {font-size: 2rem;font-weight: bold;line-height: 3rem;text-align: center;padding: 1.5rem 0;}
	#talismanDetail .talismanCont div {font-size: 1.5rem;line-height: 2.6rem;}

	#talismanDetail .talismanBottom {position: absolute;bottom: 0;left: 0;width: 100%;height: 5rem;background-color: #ecf5f2;line-height: 5rem;}
	#talismanDetail .talismanBottom .prev {position: absolute;left: 1.5rem;top: 0;font-size: 1.6rem;color: #429c84;}
	#talismanDetail .talismanBottom .next {position: absolute;right: 1.5rem;top: 0;font-size: 1.6rem;color: #429c84;}

	#talismanDetail .talismanMenu {position: absolute;top: 5rem;left: -84.8%;width: 84.8%;bottom: 0;background-color: #ffffff;transition: ease-in 0.5s;z-index: 40;overflow: auto;overflow-y: scroll;-webkit-overflow-scrolling: touch;}
	#talismanDetail .talismanMenu ul {padding-left: 1.3rem;}
	#talismanDetail .talismanMenu ul li {height: 5rem;line-height: 5rem;border-bottom: 1px solid #e5e5e5;font-size: 1.5rem;}
</style>
