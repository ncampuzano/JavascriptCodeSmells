<template>
  <div class="home">
    <div class="titleContainer">
      <h1 class="title is-1">Welcome to JavaScript Code Smell Detector</h1>
    </div>
    <div class="iconContainer">
      <img src="../assets/logo.png" style="max-width: 30%"/>
    </div>
    <div class="description">
      <p> Only with 3 steps you will get your code smells report! Start rigth now</p>
    </div>
    <div class="startButton">
      <button class="button is-dark" @click="startProcess()">
          <b-icon
            pack="fas"
            icon="play"
          />
          <span>Start</span>
      </button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'home',
  methods: {
    startProcess() {
      this.$router.push({ name: 'SetFiles' });
    },
  },
};
</script>
<style scoped>
.home {
  display: flex;
  flex-direction: column;
  flex: 1;
  min-height: 100%;
}
.titleContainer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex: 1;
}
.iconContainer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex: 1;
}
.description {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex: 2;
}
.startButton {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex: 1;
}
</style>
