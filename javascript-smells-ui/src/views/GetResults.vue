<template>
  <section class="results-container">
    <b-loading is-full-page :active.sync="isLoading" :can-cancel="false"></b-loading>
    <h1 class="title is-2">
      Your Results!
    </h1>
    <b-collapse
      v-for="(item, index) in results"
      :key="index"
      class="card"
      :aria-id="`${index}DropDown`"
      :open="false"
    >
      <div
        slot="trigger"
        slot-scope="props"
        class="card-header"
        role="button"
        :aria-controls="`${index}DropDown`">
        <p class="card-header-title">
          {{ item.name }} Path: {{ item.path }}
        </p>
        <a class="card-header-icon">
          <b-icon
            :icon="props.open ? 'caret-down' : 'caret-up'">
          </b-icon>
        </a>
      </div>
      <div class="card-content">
        <div class="content">
          <p
            v-for="(smell, index2) in item.smells"
            :key="index2"
          >
            Type: {{ smell.type }} Line: {{ smell.line }} Column: {{ smell.column }}
          </p>
        </div>
      </div>
    </b-collapse>
    <br>
    <button class="button is-dark" @click="restartAnalysis">
      <b-icon
        pack="fas"
        icon="undo"
      />
      <span>Restart Analysis</span>
    </button>
  </section>
</template>
<script>
const { ipcRenderer } = require('electron');

export default {
  name: 'GetResults',
  data() {
    return {
      isLoading: true,
      results: [],
    };
  },
  created() {
    ipcRenderer.on('SMELLS_SUCCESS', () => {
      this.isLoading = true;
    });
    ipcRenderer.on('ANALYSIS_SUCCESS', (e, results) => {
      this.isLoading = false;
      this.results = results;
    });
    ipcRenderer.on('RESTART_SUCCESS', () => {
      this.$router.push({ name: 'SetFiles' });
    });
  },
  methods: {
    restartAnalysis() {
      ipcRenderer.send('RESTART');
    },
  },
};
</script>
<style scoped>
.results-container {
  padding-left: 10%;
  padding-right: 10%;
}
</style>
