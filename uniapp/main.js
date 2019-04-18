import Vue from 'vue'
import App from './App'

import cuCustom from './colorui/components/cu-custom.vue'
import range from './components/range-slider/range-slider.vue'
Vue.component('cu-custom',cuCustom)
Vue.component('Range',range)

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
