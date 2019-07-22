import {
  app, protocol, BrowserWindow, ipcMain,
} from 'electron';
import { createProtocol, installVueDevtools } from 'vue-cli-plugin-electron-builder/lib';
import fileService from './services/fileService';

const isDevelopment = process.env.NODE_ENV !== 'production';

let win;

protocol.registerSchemesAsPrivileged([{
  scheme: 'app',
  privileges: {
    secure: true,
    standard: true,
  },
}]);

function createWindow() {
  win = new BrowserWindow({
    width: 1000,
    height: 800,
    webPreferences: {
      nodeIntegration: true,
    },
  });

  if (process.env.WEBPACK_DEV_SERVER_URL) {
    win.loadURL(process.env.WEBPACK_DEV_SERVER_URL);
    // if (!process.env.IS_TEST) win.webContents.openDevTools();
  } else {
    createProtocol('app');
    win.loadURL('app://./index.html');
  }

  win.on('closed', () => {
    win = null;
  });
}

ipcMain.on('SET_FILES', async (e, options) => {
  const result = fileService.setFiles(options);
  if (result) {
    e.sender.send('SET_FILES_SUCCESS');
  }
});

ipcMain.on('SMELLS', async (e, options) => {
  const result = fileService.setSmells(options);
  if (result) {
    e.sender.send('SMELLS_SUCCESS');
    const analysisResult = await fileService.analysis();
    e.sender.send('ANALYSIS_SUCCESS', analysisResult);
  }
});
ipcMain.on('RESTART', async (e) => {
  const result = fileService.restart();
  if (result) {
    e.sender.send('RESTART_SUCCESS');
  }
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit();
  }
});

app.on('activate', () => {
  if (win === null) {
    createWindow();
  }
});

app.on('ready', async () => {
  if (isDevelopment && !process.env.IS_TEST) {
    try {
      await installVueDevtools();
    } catch (e) {
      console.error('Vue Devtools failed to install:', e.toString());
    }
  }
  createWindow();
});

if (isDevelopment) {
  if (process.platform === 'win32') {
    process.on('message', (data) => {
      if (data === 'graceful-exit') {
        app.quit();
      }
    });
  } else {
    process.on('SIGTERM', () => {
      app.quit();
    });
  }
}
