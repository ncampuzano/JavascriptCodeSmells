<template>
  <section>
    <b-loading is-full-page :active.sync="isLoading" :can-cancel="false"></b-loading>
    <p>ACÁ IRÁN LOS RESULTADOS</p>

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
      console.log(results);
      this.results = results;
    });
  },
};
</script>
