import Home from './components/home/Home.vue';
import Cadastro from './components/cadastro/Cadastro.vue';
import Question from './components/question/Question.vue';
import About from './components/about/About.vue';

export const routes = [

    { path: '', name: 'home', component: Home, titulo: 'Home', menu: true },
    { path: '/cadastro', name:'cadastro', component: Cadastro, titulo: 'Cadastro', menu: false },
    { path: '/cadastro/:id', name:'altera', component: Cadastro, titulo: 'Cadastro', menu: false },    
    { path: '/question', name:'new_question', component: Question, titulo: 'Submit New Question', menu: true },        
    { path: '/test/:exam', name:'new_test', component: Question, titulo: 'New Test', menu: false },            
    { path: '/about', name:'about', component: About, titulo: 'About', menu: true },        
    { path: '*', component: Home, menu: false }

];