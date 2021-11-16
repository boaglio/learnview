import Home from './components/home/Home.vue';
import Question from './components/question/Question.vue';
import NewTest from './components/test/NewTest.vue';
import ReadTest from './components/test/ReadTest.vue';
import About from './components/about/About.vue';

export const routes = [

    { path: '',                name:'home',         component: Home,      titulo: 'Home',                     menu: true },
    { path: '/question',       name:'new_question', component: Question,  titulo: 'Submit New Question! ',    menu: true },        
    { path: '/test/new/:exam', name:'new_test',     component: NewTest,   titulo: 'New Test',                 menu: false },            
    { path: '/test/old/:id',   name:'old_test',     component: ReadTest,  titulo: 'Old Test',                 menu: false },
    { path: '/about',          name:'about',        component: About,     titulo: 'About',                    menu: true },        
    { path: '*',                                    component: Home,                                          menu: false }

];