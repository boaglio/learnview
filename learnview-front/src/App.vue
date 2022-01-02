<template>
  <simple-menu :routes="routes"/>  
<router-view></router-view>

</template>

<script> 

import "bootstrap/dist/css/bootstrap.min.css";

import { routes }  from './routes';
import SimpleMenu from './components/shared/SimpleMenu.vue'

export default {
  name: 'App',
  components: { 
   'simple-menu' : SimpleMenu
  },
  data() {
    return {
      routes: routes.filter(route => route.menu)
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
