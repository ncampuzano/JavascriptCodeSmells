<template>
  <section class="files-container">
    <h1 class="title is-2">
      Step 1. Choose your files!
    </h1>
    <b-field>
      <b-upload
        v-model="dropFiles"
        drag-drop
        multiple
      >
        <section class="section">
            <div class="content has-text-centered">
                <p>
                    <b-icon
                        icon="upload"
                        size="is-large">
                    </b-icon>
                </p>
                <p>Drop your files here or click to upload</p>
            </div>
        </section>
      </b-upload>
    </b-field>
    <p v-show="dropFiles.length > 0" class="subtitle" >
      Files choosed!
    </p>
    <div class="tags tag-container">
      <span v-for="(file, index) in dropFiles"
        :key="index"
        class="tag is-info" >
        {{file.name}}
        <button class="delete is-small"
          type="button"
          @click="deleteDropFile(index)">
        </button>
      </span>
    </div>
    <div class="startButton">
      <button :disabled="dropFiles.length < 1" class="button is-dark" @click="fileUploaded()">
          <b-icon
            pack="fas"
            icon="play"
          />
          <span>Next</span>
      </button>
    </div>
  </section>
</template>
<script>
const { ipcRenderer } = require('electron');

export default {
  name: 'SetFiles',
  data() {
    return {
      dropFiles: [],
    };
  },
  created() {
    ipcRenderer.on('SET_FILES_SUCCESS', () => {
      this.$router.push({ name: 'ChooseSmells' });
    });
  },
  methods: {
    fileUploaded() {
      const files = [];
      this.dropFiles.forEach((element) => {
        files.push({ name: element.name, path: element.path });
        return true;
      });
      ipcRenderer.send('SET_FILES', files);
    },
    deleteDropFile(index) {
      this.dropFiles.splice(index, 1);
    },
  },
};
</script>
<style scoped>
.files-container {
  padding-left: 10%;
  padding-right: 10%;
}
.tag-container {
  justify-content: center;
}
</style>
