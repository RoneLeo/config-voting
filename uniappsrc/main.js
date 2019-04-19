import Vue from 'vue'
import App from './App'

import cuCustom from './colorui/components/cu-custom.vue'
import range from './components/range-slider/range-slider.vue'
import qrCode from './components/tki-qrcode/tki-qrcode.vue'
Vue.component('cu-custom',cuCustom)
Vue.component('Range',range)
Vue.component('QRCode',qrCode)

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
