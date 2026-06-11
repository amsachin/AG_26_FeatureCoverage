// @ts-check
const { defineConfig, devices } = require('@playwright/test');

const baseURL = process.env.APP_BASE_URL || 'http://localhost:8080';

module.exports = defineConfig({
  testDir: './tests',
  reporter: [['list'], ['html', { open: 'never', outputFolder: 'target/playwright-report' }]],
  outputDir: 'target/test-results',
  use: {
    baseURL,
    channel: 'chrome',
    trace: 'on-first-retry'
  },
  projects: [
    {
      name: 'chrome',
      use: { ...devices['Desktop Chrome'] }
    }
  ]
});
