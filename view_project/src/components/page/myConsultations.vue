<template>
  	<div id="myConsultations">
		<div class="myConsultationsList mescroll" id="list">
			<ul id="listItem">
				<template v-for = "todo in myConsultationsList">
					<li :key = "todo.id" @click = "$router.push({name:'consultOnlineDetail',params:{topicId:todo.id}})">
						<div v-text = "todo.title" style="-webkit-box-orient:vertical;"></div>
						<p v-text = "todo.createDate"></p>
						<span v-text = "todo.isAnswer == '1' ? '已解答' : '未解答'"></span>
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
	name: 'myConsultations',
  	data () {
    	return {
			myConsultationsList:[],
			consultMescroll:null
    	}
	},
	created () {
		
	},
	mounted () {
		let _this = this;
		this.consultMescroll = new MeScroll("list", { 
			down: {
				callback: function (mescroll) { 
					_this.myConsultationsList = [];
					mescroll.resetUpScroll(); 
				}
			},
			up: {
				auto:false,
				clearEmptyId: "listItem",
				callback: function (page) { _this.myConsultations(page) }, 
				isBounce: false
				// htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
			}
		});

		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/myConsultations";
		let wxtitle = "国网苏州供电公司的法治平台";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "法治苏供，为你提供法律服务";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		myConsultations:function(page){
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/queryMyAsk',this.$qs.stringify({
				pageNo:page.num
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.myConsultationsList = this.myConsultationsList.concat(data.data.dataList);

					this.consultMescroll.endByPage(data.data.dataList.length, data.data.totalPage);
				} else if (data.code == "3007") {
					this.$store.commit('logout');
					this.$router.push({name:'login'})
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
	#myConsultations {height: 100%;}
	#myConsultations .myConsultationsList ul li {padding: 1rem 1.5rem 4rem 1.5rem;margin-top: .5rem;background-color: #ffffff;position: relative;}
	#myConsultations .myConsultationsList ul li:first-child {margin: 0;}
	#myConsultations .myConsultationsList ul li div {font-size: 1.5rem;line-height: 2.2rem;overflow:hidden; text-overflow:ellipsis;display:-webkit-box; -webkit-line-clamp:4;}
	#myConsultations .myConsultationsList ul li p {position: absolute;left: 1.5rem;bottom: 1.6rem;font-size: 1.3rem;line-height: 1.3rem;color: #999999;}
	#myConsultations .myConsultationsList ul li span {position: absolute;right: 1.5rem;bottom: 1.6rem;font-size: 1.5rem;line-height: 1.5rem;color: #429c84;}
</style>
