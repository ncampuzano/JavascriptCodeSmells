import config from '../config';

const util = require('util');
const exec = util.promisify(require('child_process').exec);


export default {
  setFiles(files) {
    try {
      config.FILES = files;
      return true;
    } catch (e) {
      return false;
    }
  },
  setSmells(smells) {
    try {
      config.SMELLS = smells;
      return true;
    } catch (e) {
      return false;
    }
  },
  async analysis() {
    const result = [];
    for (let i = 0; i < config.FILES.length; i += 1) {
      result.push(exec(`java -jar "${config.JAR_PATH}" "${config.FILES[i].path}"`));
    }
    const r = await Promise.all(result);
    console.log(r);
  },
};
