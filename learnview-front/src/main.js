import { createApp } from 'vue'
import App from './App.vue' 
import router from './routes.js'   

import 'bootstrap/dist/css/bootstrap.css';
import './assets/main.css';
 
let app = createApp(App)
 
// global variables
app.config.globalProperties.$API_URL = 'http://localhost:9000';

app.use(router).mount('#app')
  