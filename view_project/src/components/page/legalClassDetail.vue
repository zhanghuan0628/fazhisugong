<template>
  	<div id="legalClassDetail">
		<div class="legalClassDetail">
			<video-player v-if = "legalClassDetail.type == 'video'" id="legalVideo" class="video-player-box"
                 ref="videoPlayer"
                 :options="playerOptions"
                 :playsinline="true"
                 customEventName="customstatechangedeventname">
			</video-player>

			<img v-else class="legalClassImg" :src = "legalClassDetail.imgUrl" alt="">

			<div class="legalClassCont">
				<h1 v-text = "legalClassDetail.title"></h1>
				<div v-html = "legalClassDetail.content"></div>
				<router-link v-if = "legalClassDetail.map.preId != undefined" :to = "{name:'legalClassDetail',params:{id:legalClassDetail.map.preId,sortnum:legalClassDetail.map.preSortNum}}">上一节：{{ legalClassDetail.map.preTitle }}</router-link>
				<router-link v-if = "legalClassDetail.map.nextId != undefined" :to = "{name:'legalClassDetail',params:{id:legalClassDetail.map.nextId,sortnum:legalClassDetail.map.nextSortNum}}">下一节：{{ legalClassDetail.map.nextTitle }}</router-link>
			</div>
			
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
// import videojs from '../../../static/js/video.js';
import util from '@/assets/js/util';

export default {
	name: 'legalClassDetail',
  	data () {
    	return {
			id:this.$route.params.id,
			sortnum:this.$route.params.sortnum,
			legalClassDetail:{
				map:{}
			},
			isCollect:false,
			playerOptions:{}
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgLawDetail',this.$qs.stringify({
			userId:this.$store.state.userId,
			id:this.id,
			category:"law_lecture_room",
			sortnum:this.sortnum
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.legalClassDetail = data.data;
				this.playerOptions = {
					sources: [{
						type: "video/mp4",
						src: data.data.videoUrl
					}],
					poster: data.data.imgUrl,
				}
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
		
	},
	watch:{
		legalClassDetail:function(){
			// 分享配置
			let locationUrl = location.protocol+"//"+location.host+"/legalClassDetail/"+this.id+"/"+this.sortnum;
			let wxtitle = this.legalClassDetail.title;
			let wximgUrl = this.legalClassDetail.imgUrl;
			let wxdesc = this.regTag(this.legalClassDetail.content);
			util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
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
				sourceType:"law_lecture_room"
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
	beforeRouteUpdate (to, from, next) {
		next();
		if (to.name == "legalClassDetail") {
			this.id = this.$route.params.id;
			this.sortnum = this.$route.params.sortnum;

			this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgLawDetail',this.$qs.stringify({
				id:this.id,
				category:"law_lecture_room",
				sortnum:this.sortnum
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.legalClassDetail = data.data;
					this.playerOptions = {
						sources: [{
							type: "video/mp4",
							src: data.data.videoUrl
						}],
						poster: data.data.imgUrl,
					}
					if (data.data.favorite != undefined) {
						this.isCollect = true;
					} else {
						this.isCollect = false;
					}
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
<style>
	#legalVideo {width: 100%;height: 16.5rem;overflow: hidden;}
	#legalVideo .video-js {width: 100%;height: 100%;}
	#legalVideo .vjs-poster {background-size: cover;}
	#legalVideo .vjs-big-play-button {top: 50%!important;left: 50%!important;transform: translate(-50%,-50%);width: 4rem!important; height: 4rem!important;border-radius: 50%!important;box-sizing: content-box!important;background-color: rgba(0,0,0,0.3)!important;}
	#legalVideo .video-js .vjs-big-play-button {line-height: 4rem!important;}
</style>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#legalClassDetail {}
	#legalClassDetail .legalClassDetail {}
	#legalClassDetail .legalClassDetail .legalClassImg {width:100%;}
	#legalClassDetail .legalClassDetail .legalClassCont {padding: 0 1.5rem;}
	#legalClassDetail .legalClassDetail .legalClassCont>h1 {font-size: 2.1rem;line-height: 3.4rem;font-weight: bold;padding:1.5rem 0;}
	#legalClassDetail .legalClassDetail .legalClassCont>div {font-size: 1.5rem;color: #666666;line-height: 2.8rem;}
	#legalClassDetail .legalClassDetail .legalClassCont>a {font-size:1.5rem;color:#429c84;margin-top:2rem;text-decoration:underline;display: block;}

	#legalClassDetail .collectBottom {height: 11rem; padding-top: 1rem;}
	#legalClassDetail .collectBottom .collectBtn {width: 7rem;text-align: center;margin: 0 auto;}
	#legalClassDetail .collectBottom .collectBtn img {width: 100%;}
	#legalClassDetail .collectBottom .collectBtn p {font-size: 1.3rem;line-height: 1.3rem;color: #666666;}
</style>
