import Vue from 'vue'
import App from './App.vue'

import router from "@/router";
import echarts from "echarts";


import "./style/quas.css";
import "./utils/rem"

Vue.config.productionTip = false;
Vue.prototype.$echarts = echarts;

new Vue({
  router: router,
  render: h => h(App),
}).$mount('#app')
