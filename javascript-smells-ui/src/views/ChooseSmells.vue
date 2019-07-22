<template>
  <section class="smells-container">
    <h1 class="title is-2">
      Step 2. Choose the code smells for detecting
    </h1>
    <div
      v-for="item in Smells"
      :key="item.type"
      :class="SmellsSelected.includes(item.id)
       ? 'action-card-content-selected card material-card material-card-clickeable-selected'
       : 'action-card-content card material-card material-card-clickeable'"
      @click="selectSmell(item.id)"
    >
      <div class="device-info-title">
        <b-icon
          pack="fas"
          :icon="item.icon"
        />
        <span
          style="vertical-align:middle"
          :class="SmellsSelected.includes(item.id)
            ? 'action-card-title-selected' : 'action-card-title'"
        >{{ item.title}}</span>
      </div>
    </div>
    <div class="startButton">
      <button :disabled="SmellsSelected.length < 1" class="button is-dark" @click="startAnalysis()">
          <b-icon
            pack="fas"
            icon="play"
          />
          <span>Start Analysis</span>
      </button>
    </div>
  </section>
</template>
<script>
import CodeSmell from '../models/CodeSmells';

const { ipcRenderer } = require('electron');

export default {
  name: 'ChooseSmells',
  data() {
    return {
      Smells: CodeSmell(),
      SmellsSelected: [],
    };
  },
  created() {
    ipcRenderer.on('SMELLS_SUCCESS', () => {
      this.$router.push({ name: 'GetResults' });
    });
  },
  methods: {
    selectSmell(id) {
      if (this.SmellsSelected.includes(id)) {
        if (this.SmellsSelected.length === 1) {
          this.SmellsSelected = [];
        } else {
          this.SmellsSelected.splice(this.SmellsSelected.indexOf(id), 1);
        }
      } else {
        this.SmellsSelected.push(id);
      }
    },
    startAnalysis() {
      console.log('me presionaron');
      console.log(this.SmellsSelected);
      ipcRenderer.send('SMELLS', this.SmellsSelected);
    },
  },
};
</script>
<style scoped>
.smells-container {
  padding-left: 10%;
  padding-right: 10%;
}
.action-card-content {
  padding: 10px;
  margin: 4px 9px 4px 0px;
  display: inline-block;
  width: 196px;
  height: 100px;
}
.action-card-content-selected {
  padding: 10px;
  margin: 4px 9px 4px 0px;
  display: inline-block;
  width: 196px;
  height: 100px;
  color: #fff;
  background-color: #2366d1;
}
.action-card-title-selected {
  color: #fff;
  font-size: 18px;
  font-weight: 400;
  line-height: 1.25;
  width: 100%;
}
.action-card-title {
  color: #4a4a4a;
  font-size: 18px;
  font-weight: 400;
  line-height: 1.25;
  width: 100%;
}
.material-card-content{
  padding: 16px;
}
.material-card {
  border-radius: 3px;
  overflow: hidden;
  box-shadow: rgba(60, 64, 67, 0.08) 0px 1px 1px 0px, rgba(60, 64, 67, 0.16) 0px 1px 3px 1px;
}
.material-card-clickeable:hover {
  animation-name: hardShadow;
  animation-duration: 0.5s;
  -webkit-animation-name: hardShadow;
  -webkit-animation-duration: 0.5s;
  cursor: pointer;
  background-color: #F4F4F4;
  box-shadow: rgba(60, 64, 67, 0.2) 0px 1px 3px 1px , rgba(60, 64, 67, 0.1) 0px 2px 8px 4px;
}
.material-card-clickeable-selected:hover {
  animation-name: hardShadow;
  animation-duration: 0.5s;
  -webkit-animation-name: hardShadow;
  -webkit-animation-duration: 0.5s;
  cursor: pointer;
  background-color: #118fe4;
  box-shadow: rgba(60, 64, 67, 0.2) 0px 1px 3px 1px , rgba(60, 64, 67, 0.1) 0px 2px 8px 4px;
}
.device-info-title {
  margin-bottom: 0px !important;
  width: 100%;
  text-align: center;
  height: 100%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -webkit-align-items: center;
  -ms-flex-align: center;
  align-items: center;
}
</style>
