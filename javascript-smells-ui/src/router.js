import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/setFiles',
      name: 'SetFiles',
      component: () => import('./views/SetFiles.vue'),
    },
    {
      path: '/getResults',
      name: 'GetResults',
      component: () => import('./views/GetResults.vue'),
    },
    {
      path: '/chooseSmells',
      name: 'ChooseSmells',
      component: () => import('./views/ChooseSmells.vue'),
    },
  ],
});
