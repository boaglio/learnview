<template>
    <div>    
        <h1 class="center">Learn View</h1>  
        <h2 class="center">new tests</h2>  
        <ul class="list">
          <li class="list-item" v-for="exam of this.exams" :key="exam.id">
              <simple-panel :titulo="exam.category">
                <img :src="require(`@/assets/${exam.name}.png`)" /> 
                <router-link :to="{ name: 'new_test', params: { exam : exam.name , user: user }}">
                  <button class="btn btn-primary">Take test</button>
                </router-link>  
              </simple-panel>
          </li>
        </ul>
        <h2 class="center">old tests</h2> 
        <ul class="list">
          <li class="list-item" v-for="test of this.tests" :key="test.id">
              <simple-panel :titulo="test.exam">
                <img src="@/assets/complete.png" v-if="test.completed" /> 
                <img src="@/assets/in-progress.png" v-else />
                {{ test.exam }} - {{ test.when }}
                [{{ test.correct }}/{{ test.total }}]
                <router-link :to="{ name: 'old_test', params: { exam : test.name , user: user, id: test.id }}">
                  <button class="btn btn-primary">Show test</button>
                </router-link>                 
              </simple-panel>
          </li>
        </ul>
    </div>
</template>

<script>

import SimplePanel from './shared/SimplePanel.vue';  
import ExamService from '../domain/exam/ExamService';
import TestService from '../domain/test/TestService';

export default {

  components: {
    'simple-panel': SimplePanel 
  },

  data () {
    return {
      user: "Anonymous User",
      exams: [],
      tests: [] 
    }
  },

  computed: {   

  },

  methods: { 

  },
  
  created() {

    console.log('[API_URL] = '+this.$API_URL)
 
    this.examService = new ExamService(this.$API_URL);
    this.testService = new TestService(this.$API_URL);

    this.examService.list()
      .then(exams => { this.exams = exams; console.log("exams: "+this.exams.length ) }, err => this.mensagem = err.message);

    this.exams = this.examService.list();

    this.testService.list()
      .then(tests => { this.tests = tests;console.log("tests: "+ this.tests.length ) } , err => this.mensagem = err.message);      
     
  }

}
</script>
<style>
  img {
      width: 100%;
  }

  .center {
    text-align: center;
  }

  .list {
    list-style: none;
  }

  .list .list-item {
    display: inline-block;
  }
</style>