<template>
	<div id="bottomNavi">
    	<div class="bottom-navi">
			<router-link to="/" exact><img :src = "$route.path == '/' ? 'static/images/navi1on.png' : 'static/images/navi1.png'"/>首页</router-link>
			<router-link to="/personalCenter"><img :src = "headUrl !='' && headUrl != null ? headUrl : 'static/images/navi2.png'"/>个人中心</router-link>
			<!-- <router-link to="/personalCenter"><img :src = "$route.path == '/personalCenter' ? 'static/images/navi2on.png' : 'static/images/navi2.png'"/>个人中心</router-link> -->

		</div>
	</div>
</template>

<script>
export default {
	name: 'bottomNavi',
	data () {
		return {
			headUrl:localStorage.getItem("headUrl")
		}
	},
	created () {
		
	},
	mounted () {
		
    },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	.bottom-navi {width: 100%;height: 5rem;position: fixed;bottom: 0;left: 0;background-color: #FFFFFF;border-top: 1px solid #dddddd;display: flex;display: -webkit-flex;justify-content: center;text-align: center;z-index: 20;}
	.bottom-navi a {line-height: 5rem;flex: 1;color: #999999;font-size: 1.5rem;}
	.bottom-navi a img {width: 1.9rem;height: 1.9rem;margin-right: .7rem;border-radius: 50%;}
	.bottom-navi .router-link-active {color: #429c84;}
</style>
