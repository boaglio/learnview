<template>
    <div>    
        <h1 class="centralizado">Learn View</h1>
  
        <ul class="lista-exams">
          <li class="lista-exams-item" v-for="exam of examsComFiltro">
              <meu-painel :titulo="exam.category">
                <img :src="'static/'+ exam.name+'.png'" /> 
                <router-link :to="{ name: 'new_test', params: { exam : exam.name , user: user }}">
                  <button class="btn btn-primary">Take test</button>
                </router-link>  
              </meu-painel>
          </li>
        </ul>
    </div>
</template>

<script>

import Painel from '../shared/painel/Painel.vue';  
import ExamService from '../../domain/exam/ExamService';

export default {

  components: {
    'meu-painel': Painel 
  },

  data () {
    return {
      user: "Anonymous User",
      exams: [],
      filtro: ''
    }
  },

  computed: {  

    examsComFiltro() {    
      if (this.filtro) {
        let exp = new RegExp(this.filtro.trim(), 'i');
        return this.exams.filter(exam => exp.test(exam.name));
      } else {
        return this.exams;
      }
    }
  },

  methods: {
 
     takeTest(exam) {
       alert("teste :" +exam.name);
     }
  },
  
  created() {
    this.service = new ExamService(this.$http);

    this.service.list()
      .then(exams => this.exams = exams, err => this.mensagem = err.message);
  }

}
</script>
<style>

  img {
      width: 100%;
  }

  .centralizado {
    text-align: center;
  }

  .lista-exams {
    list-style: none;
  }

  .lista-exams .lista-exams-item {
    display: inline-block;
  }

  .filtro {
    display: block;
    width: 40%;
  }
</style>