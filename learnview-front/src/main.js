import Vue from 'vue'
import App from './App.vue'

import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import {routes} from './routes';
import './directives/Transform.js';
import VeeValidate from 'vee-validate';


import 'bootstrap/dist/css/bootstrap.css';

import './assets/css/main.css';
import './assets/js/main.js';

Vue.use(VueResource);

Vue.use(VueRouter); 

Vue.use(VeeValidate);

Vue.prototype.$API_URL = process.env.API_URL ?  'http://localhost:9000': 'http://localhost:9000'

const router = new VueRouter({
  routes, 
  mode: 'history'
});

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
