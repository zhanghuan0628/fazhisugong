<template>
  	<div id="legalLeaveword">
		<h1 class="legalriskTitle" v-text = "legalriskDetail.title"></h1>
		
		<div class="legalLeaveCont">
			<textarea name="" id="" placeholder="留言将由管理员筛选后显示，对所有人可见。" v-model = "content"></textarea>
		</div>
		

		<a @click = "insertComment">留言</a>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'legalLeaveword',
  	data () {
    	return {
			id:this.$route.params.id,
			legalriskDetail:{},
			content:"",
			hasInsert:false
    	}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgLawRiskController/queryLawRiskById',this.$qs.stringify({
			id:this.id
		}),{
			headers:{
				'Authorization':'Bearer ' + this.$store.state.Authorization
			}
		})
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.legalriskDetail = data.data;
			} else if (data.code == "3007") {
				this.$store.commit('logout');
				this.$router.push({name:'login'})
			}
		})
		.catch(err=>{
			console.log(err);
		});
	},
	mounted () {
		// 分享配置
		let locationUrl = location.protocol+"//"+location.host;
		let wxtitle = "法治苏供";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "为您提供专业的法律维权服务!";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		insertComment:function(){
			if (!this.hasInsert && this.content != "") {
				if (this.content.length >200) {
					this.$toast.center("留言最多不超过200字");
					return false;
				}
				this.hasInsert = true;
				this.$http.post(this.$store.state.apiUrl+'/SgLawRiskController/insertLawComment',this.$qs.stringify({
					topicId:this.id,
					content:this.content,
				}),{
					headers:{
						'Authorization':'Bearer ' + this.$store.state.Authorization
					}
				})
				.then(res=>{
					let data = res.data;
					if (data.code == "2000") {
						this.$toast.center("留言成功");
						setTimeout(() => {
							this.$router.go(-1);
						}, 1000);
					} else if (data.code == "3007") {
						this.$store.commit('logout');
						this.$router.push({name:'login'})
					}
				})
				.catch(err=>{
					console.log(err);
				});
			}
			
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
	#legalLeaveword {}
	#legalLeaveword .legalriskTitle {height: 5.4rem;line-height: 5.4rem;background-color: #f2f2f2;font-size: 1.8rem;;padding:0 1.5rem;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
	#legalLeaveword .legalLeaveCont {height: 12.5rem;border-top: 1px solid #eeeeee;border-bottom: 1px solid #eeeeee;position: relative;overflow: hidden;}
	#legalLeaveword textarea {position: absolute;top: 0;left: 0; width: 100%;height: 100%; border: none; resize: none;padding: 1.6rem 1.6rem;box-sizing: border-box;font-size: 1.5rem;color:#333333;line-height: 2.2rem;}
	#legalLeaveword textarea::-webkit-input-placeholder {color: #bbbbbb;}
	#legalLeaveword textarea:-moz-placeholder {color: #bbbbbb;}
	#legalLeaveword textarea::-moz-placeholder {color: #bbbbbb;}
	#legalLeaveword textarea:-ms-input-placeholder {color: #bbbbbb;}
	#legalLeaveword a {display: block;width: 92%;height: 4.5rem;background-color: #419c84;color: #FFFFFF;text-align: center;line-height: 4.5rem;border-radius: 50px;margin: 2rem auto 0 auto;}
</style>
