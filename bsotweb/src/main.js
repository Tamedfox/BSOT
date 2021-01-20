import Vue from 'vue'

import App from './App.vue'
import router from './router'
import store from './store'

import 'normalize.css/normalize.css'
import ElementUI from 'element-ui'
import './styles/element-variables.scss'

import '@/router/permission' // permission control
import '@/styles/index.scss' // global css

import './icons' // icon
import * as filters from './filters' // global filters

Vue.config.productionTip = false
Vue.use(ElementUI)

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

if (process.env.NODE_ENV === 'development') {
  require('../mock') // simulation data
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
