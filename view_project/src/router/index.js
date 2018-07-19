import Vue from 'vue';
import Router from 'vue-router';
import index from '@/components/page/index';
import login from '@/components/page/login';
import personalCenter from '@/components/page/personalCenter';
import iJudge from '@/components/page/iJudge';
import test from '@/components/page/test';
import testResult from '@/components/page/testResult';
import testReview from '@/components/page/testReview';
import consultOnline from '@/components/page/consultOnline';
import consultAnswerList from '@/components/page/consultAnswerList';
import consultOnlineDetail from '@/components/page/consultOnlineDetail';
import legalrisk from '@/components/page/legalrisk';
import legalriskDetail from '@/components/page/legalriskDetail';
import legalLeaveword from '@/components/page/legalLeaveword';
import talisman from '@/components/page/talisman';
import talismanList from '@/components/page/talismanList';
import talismanDetail from '@/components/page/talismanDetail';
import dynamicRuleList from '@/components/page/dynamicRuleList';
import dynamicRuleDetail from '@/components/page/dynamicRuleDetail';
import legalClassList from '@/components/page/legalClassList';
import legalClassDetail from '@/components/page/legalClassDetail';
import myMessages from '@/components/page/myMessages';
import myConsultations from '@/components/page/myConsultations';
import myJudges from '@/components/page/myJudges';
import myCollections from '@/components/page/myCollections';
import changePassword from '@/components/page/changePassword';




Vue.use(Router)

export default new Router({
	mode:'history',
  	routes: [
    	{
      		path: '/',
      		name: 'index',
      		component: index
		},
		{
			path: '/login',
			name: 'login',
			component: login
		},
		{
			path: '/personalCenter',
			name: 'personalCenter',
			component: personalCenter
		},
		{
			path: '/iJudge',
			name: 'iJudge',
			component: iJudge
		},
		{
			path: '/test/:themeId/:num',
			name: 'test',
			component: test
		},
		{
			path: '/testResult/:themeId',
			name: 'testResult',
			component: testResult
		},
		{
			path: '/testReview/:themeId',
			name: 'testReview',
			component: testReview
		},
		{
			path: '/consultOnline',
			name: 'consultOnline',
			component: consultOnline
		},
		{
			path: '/consultAnswerList',
			name: 'consultAnswerList',
			component: consultAnswerList
		},
		{
			path: '/consultOnlineDetail/:topicId',
			name: 'consultOnlineDetail',
			component: consultOnlineDetail
		},
		{
			path: '/legalrisk',
			name: 'legalrisk',
			component: legalrisk
		},
		{
			path: '/legalriskDetail/:id',
			name: 'legalriskDetail',
			component: legalriskDetail
		},
		{
			path: '/legalLeaveword/:id',
			name: 'legalLeaveword',
			component: legalLeaveword
		},
		{
			path: '/talisman',
			name: 'talisman',
			component: talisman
		},
		{
			path: '/talismanList/:categoryCode',
			name: 'talismanList',
			component: talismanList
		},
		{
			path: '/talismanDetail/:categoryCode/:id',
			name: 'talismanDetail',
			component: talismanDetail
		},
		{
			path: '/dynamicRuleList',
			name: 'dynamicRuleList',
			component: dynamicRuleList
		},
		{
			path: '/dynamicRuleDetail/:id',
			name: 'dynamicRuleDetail',
			component: dynamicRuleDetail
		},
		{
			path: '/legalClassList',
			name: 'legalClassList',
			component: legalClassList
		},
		{
			path: '/legalClassDetail/:id/:sortnum',
			name: 'legalClassDetail',
			component: legalClassDetail
		},
		{
			path: '/myMessages',
			name: 'myMessages',
			component: myMessages
		},
		{
			path: '/myConsultations',
			name: 'myConsultations',
			component: myConsultations
		},
		{
			path: '/myJudges',
			name: 'myJudges',
			component: myJudges
		},
		{
			path: '/myCollections',
			name: 'myCollections',
			component: myCollections
		},
		{
			path: '/changePassword',
			name: 'changePassword',
			component: changePassword
		}
  	]
})
