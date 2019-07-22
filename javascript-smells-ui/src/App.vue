<template>
  <div id="app">
    <div id="steps-layout" v-if="$route.path !== '/'">
      <div id="progress-container">
        <ProgressBar />
      </div>
      <div id="steps-container">
        <router-view />
      </div>
    </div>
    <div id="home-layout" v-else>
      <router-view />
    </div>
  </div>
</template>
<script>
import ProgressBar from './components/ProgressBar.vue';

export default {
  name: 'App',
  components: {
    ProgressBar,
  },
};
</script>


<style>
body, html {
  height: 100%;
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}
#progress-container {
  height: 20%;
  padding-top: 2%;
  padding-right: 10%;
  padding-left: 10%;
}
#steps-container {
  height: 80%;
}
#steps-layout {
  height: 100%;
}
#home-layout {
  height: 100%;
  background-image: url('./assets/background.jpg');
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
