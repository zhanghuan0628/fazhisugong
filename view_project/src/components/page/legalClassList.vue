<template>
  	<div id="legalClassList">
		<div class="list mescroll" id="list">
			<ul id="listItem">
				<template v-for = "todo in legalClassList">
					<li :key = "todo.id" @click = "$router.push({name:'legalClassDetail',params:{id:todo.id,sortnum:todo.sortnum}})">
						<div :style = "{'backgroundImage':'url('+todo.imgUrl+')'}"></div>
						<h1 v-text = "todo.title"></h1>
						<p v-text = "regTag(todo.content)"></p>
					</li>
				</template>
			</ul>
		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import MeScroll from '../../../static/js/mescroll.min.js';
import util from '@/assets/js/util';

export default {
	name: 'legalClassList',
  	data () {
    	return {
			legalClassList:[],
			legalMescroll:null
    	}
	},
	created () {
		
	},
	mounted () {
		let _this = this;
		this.legalMescroll = new MeScroll("list", { 
			down: {
				callback: function (mescroll) { 
					_this.legalClassList = [];
					mescroll.resetUpScroll(); 
				}
			},
			up: {
				auto:false,
				clearEmptyId: "listItem",
				callback: function (page) { _this.legalClass(page) }, 
				isBounce: false
				// htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
			}
		});

		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/legalClassList";
		let wxtitle = "法治苏供";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "为您提供专业的法律维权服务!";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		legalClass:function(page){
			this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgLaw',this.$qs.stringify({
				category:"law_lecture_room",
				pageNo:page.num
			}))
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.legalClassList = this.legalClassList.concat(data.data.dataList);

					this.legalMescroll.endByPage(data.data.dataList.length, data.data.totalPage);
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
	beforeRouteLeave (to, from, next) {
		this.legalMescroll.destroy();
		next()
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#legalClassList {height: 100%;}
	#legalClassList .list {background-color: #ffffff;margin-bottom: .6rem;}
	#legalClassList .list ul {padding: 0 1.5rem;}
	#legalClassList .list ul li {padding: 2rem 0;border-top: 1px solid #eeeeee;position: relative;}
	#legalClassList .list ul li:first-child {border: none;}
	#legalClassList .list ul li div {width: 10.5rem;height: 7rem;background-repeat: no-repeat;background-size: cover;background-position: center center;}
	#legalClassList .list ul li h1 {position: absolute;top: 1.6rem;left: 11.5rem;right: 0; font-size: 1.6rem;overflow:hidden; text-overflow:ellipsis;display:-webkit-box; -webkit-box-orient:vertical;-webkit-line-clamp:2;}
	#legalClassList .list ul li p {position: absolute;bottom: 2rem;left: 11.5rem;right: 0;font-size: 1.3rem;line-height: 1.3rem;color: #999999; overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
</style>
