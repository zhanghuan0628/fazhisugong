<template>
  	<div id="talisman">
		<ul>
			<template v-for = "(todo,index) in talisman">
				<li :key = "todo.id">
					<router-link :to = "{name:'talismanList',params:{categoryCode:todo.id}}">

						<p>{{ todo.title | splitTitle }}
							<img :src = "index % 2 == 1 ? 'static/images/grounding2.png' : 'static/images/grounding1.png'" alt="">
						</p>
					</router-link>
				</li>
			</template>
			<!-- <li><p>供电营业规则<img src="~static/images/grounding1.png" alt=""></p></li>
			<li><p>电力调控规则<img src="~static/images/grounding2.png" alt=""></p></li>
			<li><p>变电检修规则<img src="~static/images/grounding1.png" alt=""></p></li>
			<li><p>直流运检规则<img src="~static/images/grounding2.png" alt=""></p></li>
			<li><p>后检服务规则<img src="~static/images/grounding1.png" alt=""></p></li>
			<li><p>综合服务规则<img src="~static/images/grounding2.png" alt=""></p></li> -->
		</ul>
  	</div>
</template>

<script>
import store from '@/store/store';
import util from '@/assets/js/util';

export default {
	name: 'talisman',
  	data () {
    	return {
			talisman:[],
    	}
	},
	filters: {
		splitTitle: function (value) {
			if (!value) return ''
			value = value.toString()
			if (value.length <= 18) {
				return value
			} else {
				return value = value.substring(0,15)+"...";
			}
		}
	},
	created () {
		this.$http.post(this.$store.state.apiUrl+'/SgHomePageController/querySgMagic',this.$qs.stringify({
			category:"law_magic"
		}))
		.then(res=>{
			let data = res.data;
			if (data.code == "2000") {
				this.talisman = data.data
			}
		})
		.catch(err=>{
			console.log(err);
		});
	},
	mounted () {
		// 分享配置
		let locationUrl = location.protocol+"//"+location.host+"/talisman";
		let wxtitle = "法治苏供";
		let wximgUrl = location.protocol+"//"+location.host+"/static/images/wxShare.png";
		let wxdesc = "为您提供专业的法律维权服务!";
		util.wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
	},
	methods: {
		
	},
	store
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#talisman {padding: 0 1.5rem;}
	#talisman ul li {height: 4.9rem;border-radius: 50px;background-color: #ffffff;position: relative;margin-top: 2.5rem;}
	#talisman ul li p {height: 100%;line-height: 4.9rem;font-size: 1.5rem;text-align: center; font-weight: bold;position: relative;color: #333333;}
	#talisman ul li img {position: absolute;bottom: 1.1rem;left: 50%;transform: translateX(-50%); width: 9.4rem;}
	#talisman ul li::before {content: '';display: block;width: .9rem;height: .9rem; position: absolute;left: 1rem;top: 50%;transform: translateY(-50%);background-color: #e9e9e9;border-radius: 50%;}
	#talisman ul li::after {content: '';display: block;width: .9rem;height: .9rem; position: absolute;right: 1rem;top: 50%;transform: translateY(-50%);background-color: #e9e9e9;border-radius: 50%;}
</style>
