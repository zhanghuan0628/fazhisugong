// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import qs from 'qs';
import store from '@/store/store';
import util from '@/assets/js/util';

import '@/assets/css/toast.css';
import Toast from '@/assets/js/toast';
import 'swiper/dist/css/swiper.css';
import VueAwesomeSwiper from 'vue-awesome-swiper';
import '@/assets/css/mescroll.min.css';
import VueVideoPlayer from 'vue-video-player'
import 'video.js/dist/video-js.css';

Vue.use(Toast);
Vue.use(VueAwesomeSwiper);
Vue.use(VueVideoPlayer)

Vue.config.productionTip = false;
Vue.prototype.$http = axios;
Vue.prototype.$qs = qs;


router.beforeEach((to, from, next) => {
	if (localStorage.getItem("userId") != null && localStorage.getItem("userId") != "") {
    let authObj = {};
    authObj.token = localStorage.getItem("Authorization");
    store.commit('setAuthorization',authObj);
    let userObj = {};
    userObj.id = localStorage.getItem("userId");
    userObj.userName = localStorage.getItem("userName");
    userObj.headUrl = localStorage.getItem("headUrl");
    store.commit('setUserInfo',userObj);
		next();
		return false;
	}
  next();
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
