<template>
  	<div id="myJudges">
		<div class="myJudgesList mescroll" id="list">
			<ul id="listItem">
				<template v-for = "todo in myJudgesList">
					<li :key = "todo.id" @click = "$router.push({name:'testResult',params:{themeId:todo.themeId}})">
						<h1 v-text = "todo.stage + ' ' + todo.title"></h1>
						<p class="finishDate">{{ todo.createDate }} 完成答题</p>
						<p class="rightNum">答对<span v-text = "todo.rightNum"></span>道题</p>
						<p class="score">得分<span v-text = "todo.score"></span></p>
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
	name: 'myJudges',
  	data () {
    	return {
			myJudgesList:[],
			judgesMescroll:null
    	}
	},
	created () {

	},
	mounted () {
		let _this = this;
		this.judgesMescroll = new MeScroll("list", { 
			down: {
				callback: function (mescroll) { 
					_this.myJudgesList = [];
					mescroll.resetUpScroll(); 
				}
			},
			up: {
				auto:false,
				clearEmptyId: "listItem",
				callback: function (page) { _this.myJudges(page) }, 
				isBounce: false
				// htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
			}
		});

		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/myJudges";
		let wxtitle = "国网苏州供电公司的法治平台";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "法治苏供，为你提供法律服务";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		myJudges:function(page){
			this.$http.post(this.$store.state.apiUrl+'/SgUserCenterController/queryMyThemeLog',this.$qs.stringify({
				pageNo:page.num
			}),{
				headers:{
					'Authorization':'Bearer ' + this.$store.state.Authorization
				}
			})
			.then(res=>{
				let data = res.data;
				if (data.code == "2000") {
					this.myJudgesList = this.myJudgesList.concat(data.data.dataList);

					this.judgesMescroll.endByPage(data.data.dataList.length, data.data.totalPage);
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
		this.judgesMescroll.destroy();
		next()
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#myJudges {height: 100%;}
	#myJudges .myJudgesList {padding: 1.5rem 1.5rem 0 1.5rem;box-sizing: border-box;}
	#myJudges .myJudgesList ul li {height: 13.5rem;background-color: #ffffff;border-radius: 10px;position: relative;margin-top: 1.5rem;}
	#myJudges .myJudgesList ul li:first-child {margin: 0;}
	#myJudges .myJudgesList ul li h1 {font-size: 1.8rem;line-height: 1.8rem;position: absolute;top: 1.8rem;left: 1.5rem;right: 1.5rem;}
	#myJudges .myJudgesList ul li .finishDate {font-size: 1.3rem;line-height: 1.3rem;color: #999999; position: absolute;top: 5.4rem;left: 1.5rem;right: 1.5rem;}
	#myJudges .myJudgesList ul li .rightNum {position: absolute;bottom: 3rem;left: 1.5rem;right: 12rem;}
	#myJudges .myJudgesList ul li .rightNum span {color: #ec6958;}
	#myJudges .myJudgesList ul li .score {position: absolute;bottom: 3rem;right: 1.5rem;line-height: 1.4rem;width: 12rem;text-align: right;}
	#myJudges .myJudgesList ul li .score span {font-size: 3rem;color: #ec6958;}
</style>
