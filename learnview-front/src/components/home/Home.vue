<template>
    <div>    
        <h1 class="centralizado">Learn View</h1>
 
        <input type="search" class="filtro" @input="filtro = $event.target.value" placeholder="search">
        <ul class="lista-exams">
          <li class="lista-exams-item" v-for="exam of examsComFiltro">
              <meu-painel :titulo="exam.category">
                <img :src="'static/'+ exam.name+'.png'" /> 

                <router-link :to="{ name: 'altera', params: { id : exam._id }}">
                  <meu-botao 
                    rotulo="Take test" 
                    tipo="button"/>
                </router-link>  
                
              </meu-painel>
          </li>
        </ul>
    </div>
</template>

<script>

import Painel from '../shared/painel/Painel.vue';
import Botao from '../shared/botao/Botao.vue';
import ImagemResponsiva from '../shared/imagem-responsiva/ImagemResponsiva.vue'
import BotaoVue from '../shared/botao/Botao.vue';
import ExamService from '../../domain/exam/ExamService';

export default {

  components: {
    'meu-painel': Painel,
    'imagem-responsiva': ImagemResponsiva,
    'meu-botao': Botao
  },

  data () {
    return {
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
    width: 100%;
  }
</style>