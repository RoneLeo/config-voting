import Vue from 'vue'
import App from './App'
import Store from './store/index.js'

import cuCustom from './colorui/components/cu-custom.vue'
import login from './components/login/login.vue'
import range from './components/range-slider/range-slider.vue'
import qrCode from './components/tki-qrcode/tki-qrcode.vue'
import ElCheckbox from './components/checkbox/checkbox'
import modal from './components/modal/modal.vue'

import api from '@/common/request-http/'

Vue.prototype.$api = api

Vue.prototype.$url = 'http://182.151.22.247:8899'

Vue.component('el-checkbox', ElCheckbox)
Vue.component('cu-custom',cuCustom)
Vue.component('login',login)
Vue.component('Modal',modal)
Vue.component('Range',range)
Vue.component('QRCode',qrCode)

Vue.prototype.$store = Store

Vue.prototype.getGlobalUser = function(key) {
	var userInfo = uni.getStorageSync("userInfo");
	if (userInfo != null && userInfo != "" && userInfo != undefined) {
		return userInfo;
	} else {
		return null;
	}
}
Vue.prototype.getLogined = function(key) {
	var logined = uni.getStorageSync("logined");
	if (logined != null && logined != "" && logined != undefined) {
		return logined;
	} else {
		return null;
	}
}
let getToken = function(key) {
	var token = uni.getStorageSync("token");
	if (token != null && token != "" && token != undefined) {
		return token;
	} else {
		return null;
	}
}
Vue.prototype.$token = getToken()

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
