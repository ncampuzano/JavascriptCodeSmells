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
  restart() {
    try {
      config.FILES = [];
      config.SMELLS = [];
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
    const executes = [];
    let smells = '';
    for (let i = 0; i < config.SMELLS.length; i += 1) {
      if (i + 1 < config.SMELLS.length) {
        smells += `${config.SMELLS[i]},`;
      } else {
        smells += config.SMELLS[i];
      }
    }
    for (let i = 0; i < config.FILES.length; i += 1) {
      executes.push(exec(`java -jar "${config.JAR_PATH}" "${config.FILES[i].path}" "${smells}"`));
    }
    const results = await Promise.all(executes);
    const result = [];
    for (let i = 0; i < config.FILES.length; i += 1) {
      const element = results[i];
      if (element.stderr) {
        result.push({
          path: config.FILES[i].path, name: config.FILES[i].name, error: 1, smells: [],
        });
      } else {
        console.log(element.stdout);
        result.push({
          path: config.FILES[i].path,
          name: config.FILES[i].name,
          error: 0,
          smells: JSON.parse(element.stdout),
        });
      }
    }
    return result;
  },
};
