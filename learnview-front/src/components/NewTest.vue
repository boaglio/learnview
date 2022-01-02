<template>
   <div class="container">
    <head>
     <title>{{ exam }} - {{ user }}</title> 
    </head>
    <h1 class="c">New Test {{ exam }} - {{ user }} </h1>  
  </div>
</template>

<script>

import question from '../domain/question/Question.js';
import test from '../domain/test/Test.js';
import TestService from '../domain/test/TestService';

export default {
 
  created() {
    console.log('exam=['+this.exam+'] user=['+this.user+']');    
    console.log('Loading exam...');
    this.test = new test(this.exam,this.user);
    this.service = new TestService(this.$http);
  //  this.question = this.service.newTest(this.test);
    
  },

   data() {
     return {        
           exam: this.$route.params.exam,
           user: this.$route.params.user,
           question:new question()
     }
   },

}
</script>
<style>
 
</style>