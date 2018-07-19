<template>
  	<div id="talismanList">
		<div class="talismanSearch">
			<div class="searchBar">
				<em></em>
				<form id="searchForm" action="#" @submit.prevent = "search"><input type="search" placeholder="请输入关键词" v-model = "searchCont" maxlength = "30" /></form>
			</div>
		</div>

		<div class="talismanList">
			<ul v-if = "talismanList.length > 0">
				<template v-for = "todo in talismanList">
					<li :key = "todo.id" v-text = "todo.chapter + ' ' + todo.title" @click = "$router.push({name:'talismanDetail',params:{categoryCode:categoryCode,id:todo.id}})"></li>
				</template>
			</ul>
			<p class="noTalisman" v-else>暂无相关数据~</p>
		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'talismanList',
  	data () {
    	return {
			categoryCode:this.$route.params.categoryCode,
			talismanList:[],
			searchCont:""
    	}
	},
	created () {
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
		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/talismanList";
		let wxtitle = "国网苏州供电公司的法治平台";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "法治苏供，为你提供法律服务";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		search:function(){
			this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgMagic',this.$qs.stringify({
				category:"law_magic",
				categoryCode:this.categoryCode,
				title:this.searchCont
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
		}
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#talismanList {}
	#talismanList .talismanSearch {height: 5rem;background-color: #429c84;position: relative;}
	#talismanList .talismanSearch .searchBar {position: absolute;left: 1.5rem;right: 1.5rem;height: 3.3rem;top: 50%;transform: translateY(-50%);background-color: #68b09d;border-radius: 50px;}
	#talismanList .talismanSearch .searchBar em {position: absolute;display: block;width: 1.5rem;height: 1.5rem;left: 1rem;top: 50%;transform: translateY(-50%);background: url(~static/images/search.png);background-size: 100% 100%;}
	#talismanList .talismanSearch .searchBar #searchForm {height: 100%;}
	#talismanList .talismanSearch .searchBar #searchForm input {width: 100%;height: 100%;border: none;background-color: transparent;padding-left: 3.3rem;color: #ffffff;}
	#talismanList .talismanSearch .searchBar #searchForm input::-webkit-search-cancel-button {display: none;}
	#talismanList .talismanSearch .searchBar #searchForm input::-webkit-input-placeholder {color: #ffffff;}
	#talismanList .talismanSearch .searchBar #searchForm input:-moz-placeholder {color: #ffffff;}
	#talismanList .talismanSearch .searchBar #searchForm input::-moz-placeholder {color: #ffffff;}
	#talismanList .talismanSearch .searchBar #searchForm input:-ms-input-placeholder {color: #ffffff;}

	#talismanList .talismanList {padding: 0 1.5rem;}
	#talismanList .talismanList li {height: 5rem;line-height: 5rem;border-bottom: 1px solid #e5e5e5;font-size: 1.5rem;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
	#talismanList .noTalisman {padding-top: 20px;font-size: 14px;width: 100%;text-align: center;color: gray;}
</style>
