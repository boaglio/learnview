<template>
 
<div class="container">
  <nav class="navbar ">
   <ul class="nav nav-pills">     
    <li class="nav-item" v-for="route in routes" :key="route.id" >
      <button class="btn btn-outline-success me-4" type="button">
       <router-link :to="route.path ? route.path : '/'">{{route.titulo}}</router-link>
      </button>
    </li>   
   </ul>
  </nav>
</div>
</template>

<script>

export default {

    props: {
        routes: {
            type: Array, 
            required: true
        }
    },
  created() {
 
      console.log('qt routes = '+ this.routes.length);
 
  } 
}
</script>    

<style scoped>
</style>