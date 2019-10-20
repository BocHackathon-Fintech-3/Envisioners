import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import Axios from "axios";
import VueSSE from "vue-sse/src/vue-sse";

Vue.config.productionTip = false
Axios.defaults.baseURL = 'http://10.255.255.217:8081'
// Axios.defaults.baseURL = 'http://localhost:8081'
Vue.use(VueSSE);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
