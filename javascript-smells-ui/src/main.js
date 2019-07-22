import Vue from 'vue';
import Buefy from 'buefy';
import { library } from '@fortawesome/fontawesome-svg-core';
import {
  faPlay, faCheck, faUpload, faExchangeAlt,
  faLink, faEquals, faCat, faMask, faUndo, faHeartBroken,
  faCaretDown, faCaretUp,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import App from './App.vue';
import router from './router';
import 'buefy/dist/buefy.css';

library.add(faPlay, faCheck, faUpload, faExchangeAlt,
  faLink, faEquals, faCat, faMask, faUndo, faHeartBroken,
  faCaretDown, faCaretUp);

Vue.component('vue-fontawesome', FontAwesomeIcon);

Vue.use(Buefy, {
  defaultIconComponent: 'vue-fontawesome',
  defaultIconPack: 'fas',
});
Vue.use(Buefy);

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
