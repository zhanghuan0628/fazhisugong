import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

const state = {
    // apiUrl:location.protocol+"//"+location.host+"/apis",
    apiUrl:location.protocol+"//pre-sg.feifanxinli.com/api",
    userId:"",
    userName:"",
    headUrl:"",
    Authorization:"",
    randomKey:""
}


const mutations = {
    setAuthorization(state,res) {
        state.Authorization = res.token;
        state.randomKey = res.randomKey;
        // axios.defaults.headers.common['Authorization'] = 'Bearer ' + state.Authorization;
        localStorage.setItem("Authorization",state.Authorization);
    },
    setUserInfo(state,res){
        state.userId = res.id;
        state.userName = res.userName;
        state.headUrl = res.headUrl;
        localStorage.setItem("userId",state.userId);
        localStorage.setItem("userName",state.userName);
        localStorage.setItem("headUrl",state.headUrl);
    },
    editUsername(state,res){
        state.userName = res;
        localStorage.setItem("userName",state.userName);
    },
    editHeadurl(state,res){
        state.headUrl = res;
        localStorage.setItem("headUrl",state.headUrl);
    },
    logout(state){
        state.Authorization = "";
        state.randomKey = "";
        state.userId = "";
        state.userName = "";
        state.headUrl = "";
        localStorage.removeItem("userId");
        localStorage.removeItem("userName");
        localStorage.removeItem("headUrl");
    },
    setWxShareInfo(state,res){
        state.appId = res.appId;
        state.nonceStr = res.nonceStr;
        state.signature = res.signature;
        state.timestamp = res.timestamp;
    },
}

const actions = {

}

export default new Vuex.Store({
    state,
    mutations,
    actions
})