import Vue from 'vue'
import App from './App.vue'

import router from "@/router";
import echarts from "echarts";
import axios from "axios";


import "./style/quas.css";
import "./utils/rem"

Vue.config.productionTip = false;
Vue.prototype.$echarts = echarts;
Vue.prototype.$axios = axios;

new Vue({
  router: router,
  render: h => h(App),
}).$mount('#app')