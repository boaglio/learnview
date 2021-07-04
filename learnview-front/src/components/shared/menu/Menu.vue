 
<template>

<div class="container">
  <nav class="navbar navbar-dark bg-dark ">
   <ul class="nav nav-pills">     
    <li class="nav-item" v-for="rota in rotas">
      <button class="btn btn-outline-success me-4" type="button">
       <router-link :to="rota.path ? rota.path : '/'">{{rota.titulo}}</router-link>
      </button>
    </li>   
   </ul>
  </nav>
</div>
</template>

<script>
export default {

    props: {
        rotas: {
            type: Array, 
            required: true
        }
    }
}
</script>    

<style scoped>
</style>