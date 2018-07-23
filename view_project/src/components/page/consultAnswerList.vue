<template>
  	<div id="consultAnswerList">
		<div class="consultAnswerList mescroll" id="list">
			<ul id="listItem">
				<template v-for = "todo in consultAnswerList">
					<li :key = "todo.id" @click = "$router.push({name:'consultOnlineDetail',params:{topicId:todo.topicId}})">
						<div class="ca-top">
							<img :src = "todo.headImg" alt="">
							<p v-text = "todo.userName"></p>
							<span><i v-text = "todo.createDate"></i> 回答了</span>
						</div>
						<div class="ca-cont">
							<h1 v-text = "todo.qTitle"></h1>
							<p v-text = "todo.qContent" style = "-webkit-box-orient:vertical;"></p>
						</div>
					</li>
				</template>
			</ul>
		</div>

		<div class="consultAnswer">
			

		</div>
  	</div>
</template>

<script>
import store from '@/store/store';
import MeScroll from '../../../static/js/mescroll.min.js';
import util from '@/assets/js/util';

export default {
	name: 'consultAnswerList',
  	data () {
    	return {
			consultAnswerList:[],
			consultAnswerMescroll:null
    	}
	},
	created () {
		
	},
	mounted () {
		let _this = this;
		this.consultAnswerMescroll = new MeScroll("list", { 
			down: {
				callback: function (mescroll) { 
					_this.consultAnswerList = [];
					mescroll.resetUpScroll(); 
				}
			},
			up: {
				auto:false,
				clearEmptyId: "listItem",
				callback: function (page) { _this.consultAnswer(page) }, 
				isBounce: false
				// htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
			}
		});

		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/consultAnswerList";
		let wxtitle = "法治苏供";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "为您提供专业的法律维权服务!";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		consultAnswer:function(page){
			this.$http.post(this.$store.state.apiUrl+'/SgAskController/queryAllAskComment',this.$qs.stringify({
				pageNo:page.num
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.consultAnswerList = this.consultAnswerList.concat(data.data.dataList);

					this.consultAnswerMescroll.endByPage(data.data.dataList.length, data.data.totalPage);
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
	beforeRouteLeave (to, from, next) {
		this.consultAnswerMescroll.destroy();
		next()
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#consultAnswerList {height: 100%;}
	#consultAnswerList .consultAnswerList ul li {padding: 0 1.5rem 1rem 1.5rem;}
	#consultAnswerList .consultAnswerList ul li .ca-top {height: 7rem;position: relative;}
	#consultAnswerList .consultAnswerList ul li .ca-top img {width: 4rem;height: 4rem;border-radius: 50%;position: absolute;left: 0;top: 50%;transform: translateY(-50%);}
	#consultAnswerList .consultAnswerList ul li .ca-top p {position: absolute;top: 1.5rem;left: 4.7rem;line-height: 1.4rem;}
	#consultAnswerList .consultAnswerList ul li .ca-top span {position: absolute;bottom: 1.5rem;left: 4.7rem;font-size: 1.2rem;line-height: 1.2rem;color: #999999;}

	#consultAnswerList .consultAnswerList ul li .ca-cont h1 {font-size: 1.7rem;font-weight: bold;margin-bottom: .9rem;}
	#consultAnswerList .consultAnswerList ul li .ca-cont p {font-size: 1.5rem;line-height: 2.2rem;overflow:hidden; text-overflow:ellipsis;display:-webkit-box;-webkit-line-clamp:3;}


</style>
