import config from '../config';

export default {
  setFiles(files) {
    try {
      config.FILES = files;
      return true;
    } catch (e) {
      return false;
    }
  },
};
