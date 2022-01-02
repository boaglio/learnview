import { createWebHistory, createRouter } from "vue-router";

import Home from './components/Home.vue';
import Question from './components/Question.vue';
import NewTest from './components/NewTest.vue';
import ReadTest from './components/ReadTest.vue';
import About from './components/About.vue';

export const routes = [

    { path: '',                 name:'home',         component: Home,      titulo: 'Home',                     menu: true },
    { path: '/question',        name:'new_question', component: Question,  titulo: 'Submit New Question! ',    menu: true },        
    { path: '/test/new/:exam',  name:'new_test',     component: NewTest,   titulo: 'New Test',                 menu: false },            
    { path: '/test/old/:id',    name:'old_test',     component: ReadTest,  titulo: 'Old Test',                 menu: false },
    { path: '/about',           name:'about',        component: About,     titulo: 'About',                    menu: true },        
    { path: '/:catchAll(.*)',   name:'all',          component: Home,      titulo: 'Home',                     menu: false }

];

const router = createRouter({
    history: createWebHistory(),
    routes,
  });
  
export default router;

