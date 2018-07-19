<template>
  	<div id="dynamicRuleList">
		<div class="list mescroll" id="list">
			<ul id="listItem">
				<template v-for = "todo in dynamicRuleList">
					<li :key = "todo.id" @click = "$router.push({name:'dynamicRuleDetail',params:{id:todo.id}})">
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
	name: 'dynamicRuleList',
  	data () {
    	return {
			dynamicRuleList:[],
			dynamicMescroll:null
    	}
	},
	created () {
		
	},
	mounted () {
		this.$nextTick(() => {
			let _this = this;
			this.dynamicMescroll = new MeScroll("list", { 
				down: {
					callback: function (mescroll) { 
						_this.dynamicRuleList = [];
						mescroll.resetUpScroll(); 
					}
				},
				up: {
					auto:false,
					clearEmptyId: "listItem",
					callback: function (page) { _this.dynamicRule(page) }, 
					isBounce: false
					// htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
				}
			});
		})
		

		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/dynamicRuleList";
		let wxtitle = "国网苏州供电公司的法治平台";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "法治苏供，为你提供法律服务";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		dynamicRule:function(page){
			this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgLaw',this.$qs.stringify({
				category:"law_information",
				pageNo:page.num
			}))
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.dynamicRuleList = this.dynamicRuleList.concat(data.data.dataList);

					this.dynamicMescroll.endByPage(data.data.dataList.length, data.data.totalPage);
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
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#dynamicRuleList {height: 100%;}
	#dynamicRuleList .list {background-color: #ffffff;margin-bottom: .6rem;}
	#dynamicRuleList .list ul {padding: 0 1.5rem;}
	#dynamicRuleList .list ul li {padding: 2rem 0;border-top: 1px solid #eeeeee;position: relative;}
	#dynamicRuleList .list ul li:first-child {border: none;}
	#dynamicRuleList .list ul li div {width: 10.5rem;height: 7rem;background-repeat: no-repeat;background-size: cover;background-position: center center;}
	#dynamicRuleList .list ul li h1 {position: absolute;top: 1.6rem;left: 11.5rem;right: 0; font-size: 1.6rem;overflow:hidden; text-overflow:ellipsis;display:-webkit-box; -webkit-box-orient:vertical;-webkit-line-clamp:2;}
	#dynamicRuleList .list ul li p {position: absolute;bottom: 2rem;left: 11.5rem;right: 0;font-size: 1.3rem;line-height: 1.3rem;color: #999999; overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
</style>
