<template>
  <section>
    <progress
      v-if="step === 1"
      class="progress is-danger"
      value="2"
      max="100"
    >
      2%
    </progress>
    <progress
      v-else-if="step === 2"
      class="progress is-warning"
      value="50"
      max="100"
    >
      50%
    </progress>
    <progress
      v-else
      class="progress is-success"
      value="100"
      max="100"
    >
      100%
    </progress>
    <div class="buttons buttons-container">
      <b-button
        rounded
        type="is-info"
        :disabled="step > 1"
      >
        <b-icon
          v-if="step > 1"
          pack="fas"
          icon="check"
        />
        <p v-else>1</p>
      </b-button>
      <b-button
        rounded
        :type="step === 1 ? 'is-light' : step === 2 ? 'is-info' : 'is-info'"
        :disabled="step > 2"
      >
        <b-icon
          v-if="step > 2"
          pack="fas"
          icon="check"
        />
        <p v-else>2</p>
      </b-button>
      <b-button
        rounded
        :type="step === 1 ? 'is-light' : step === 2 ? 'is-light' : 'is-info'"
      >
        <b-icon
          v-if="step > 3"
          pack="fas"
          icon="check"
        />
        <p v-else>3</p>
      </b-button>
    </div>
  </section>
</template>

<script>
const { ipcRenderer } = require('electron');

export default {
  name: 'ProgressBar',
  data() {
    return {
      step: 1,
    };
  },
  created() {
    ipcRenderer.on('SET_FILES_SUCCESS', () => {
      this.step = 2;
    });
    ipcRenderer.on('SMELLS_SUCCESS', () => {
      this.step = 3;
    });
    ipcRenderer.on('ANALYSIS_SUCCESS', () => {
      this.step = 4;
    });
    ipcRenderer.on('RESTART_SUCCESS', () => {
      this.step = 1;
    });
  },
};
</script>
<style scoped>
.buttons-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
</style>
