<template>
  <div class="appbody">
    <simple-menu :routes="routes"/> 
    <transition name="page">
     <router-view></router-view>
    </transition>
  </div>
</template>

<script>
import { routes }  from './routes';
import SimpleMenu from './components/shared/menu/SimpleMenu.vue'

export default {
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
  .appbody {
    font-family: Helvetica, sans-serif;
    margin: 0 auto;
    width: 96%;
  }
  .page-enter, .painel-leave-active {
    opacity: 0;
  } 
  .page-enter-active, .painel-leave-active {
    transition: opacity .3s;
  }   
</style>