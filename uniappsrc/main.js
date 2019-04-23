import Vue from 'vue'
import App from './App'

import cuCustom from './colorui/components/cu-custom.vue'
import login from './components/login/login.vue'
import range from './components/range-slider/range-slider.vue'
import qrCode from './components/tki-qrcode/tki-qrcode.vue'
import ElCheckbox from './components/checkbox/checkbox'

import api from '@/common/request-http/'

Vue.prototype.$api = api

Vue.component('el-checkbox', ElCheckbox)
Vue.component('cu-custom',cuCustom)
Vue.component('login',login)
Vue.component('Range',range)
Vue.component('QRCode',qrCode)

Vue.prototype.getGlobalUser = function(key) {
	var userInfo = uni.getStorageSync("user");
	if (userInfo != null && userInfo != "" && userInfo != undefined) {
		return userInfo;
	} else {
		return null;
	}
}

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
