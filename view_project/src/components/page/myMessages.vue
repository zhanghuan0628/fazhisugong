<template>
  	<div id="myMessages">
		<div class="myMessagesList mescroll" id="list">
			<ul id="listItem">
				<template v-for = "todo in myMessagesList">
					<li :key = "todo.id" @click = "$router.push({name:'consultOnlineDetail',params:{topicId:todo.id}})">
						<div class="mm-top">
							<img :src = "todo.headImg" alt="">
							<p>{{ todo.replyName }}<i>回答了</i></p>
							<span v-text = "todo.createDate"></span>
						</div>
						<div class="mm-cont">
							<h1 v-text = "todo.fromContent" style="-webkit-box-orient:vertical;"></h1>
							<p v-text = "todo.replyContent" style="-webkit-box-orient:vertical;"></p>
						</div>
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
	name: 'myMessages',
  	data () {
    	return {
			myMessagesList:[],
			myMessagesMescroll:null
    	}
	},
	created () {
		
	},
	mounted () {
		let _this = this;
		this.myMessagesMescroll = new MeScroll("list", { 
			down: {
				callback: function (mescroll) { 
					_this.myMessagesList = [];
					mescroll.resetUpScroll(); 
				}
			},
			up: {
				auto:false,
				clearEmptyId: "listItem",
				callback: function (page) { _this.myMessages(page) }, 
				isBounce: false
				// htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
			}
		});

		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/myMessages";
		let wxtitle = "国网苏州供电公司的法治平台";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "法治苏供，为你提供法律服务";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		myMessages:function(page){
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/queryMyInfoList',this.$qs.stringify({
				pageNo:page.num
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.myMessagesList = this.myMessagesList.concat(data.data.dataList);

					this.myMessagesMescroll.endByPage(data.data.dataList.length, data.data.totalPage);
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
		this.myMessagesMescroll.destroy();
		next()
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#myMessages {height: 100%;}
	#myMessages .myMessagesList ul li {padding: 0 1.5rem 1.5rem 1.5rem;margin-top: .5rem;background-color: #ffffff;}
	#myMessages .myMessagesList ul li:first-child {margin: 0;}
	#myMessages .myMessagesList ul li .mm-top {height: 7rem;position: relative;}
	#myMessages .myMessagesList ul li .mm-top img {width: 4rem;height: 4rem;border-radius: 50%;position: absolute;left: 0;top: 50%;transform: translateY(-50%);}
	#myMessages .myMessagesList ul li .mm-top p {position: absolute;left: 4.7rem;top: 1.5rem; line-height: 1.4rem;}
	#myMessages .myMessagesList ul li .mm-top p i {font-size: 1.2rem;color: #429c84;margin-left: .7rem;}
	#myMessages .myMessagesList ul li .mm-top span {position: absolute;left: 4.7rem;bottom: 1.5rem; font-size: 1.2rem;color: #999999;line-height: 1.2rem;}
	#myMessages .myMessagesList ul li .mm-cont h1 {font-size: 1.7rem;margin-bottom: .9rem;overflow:hidden; text-overflow:ellipsis;display:-webkit-box; -webkit-line-clamp:4;}
	#myMessages .myMessagesList ul li .mm-cont p {font-size: 1.5rem;line-height: 2.2rem;overflow:hidden; text-overflow:ellipsis;display:-webkit-box; -webkit-line-clamp:3;}
</style>
