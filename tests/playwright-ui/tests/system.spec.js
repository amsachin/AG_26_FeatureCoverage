const { expect, test } = require('@playwright/test');

test('GET / renders the welcome page', {
  tag: ['@teamPetClinic', '@appPetClinic', '@featureSystem', '@funcSystemPages', '@subFuncWelcomePage']
}, async ({ page }) => {
  const response = await page.goto('/');

  expect(response.status()).toBe(200);
  await expect(page.locator('h2')).toContainText('Welcome');
  await expect(page.locator('img[src*="pets.png"]')).toBeVisible();
});

test('GET /oups renders the error page', {
  tag: ['@teamPetClinic', '@appPetClinic', '@featureSystem', '@funcSystemPages', '@subFuncErrorPage']
}, async ({ page }) => {
  const response = await page.goto('/oups');

  expect(response.status()).toBe(500);
  await expect(page.locator('h2')).toContainText('Something happened');
  await expect(page.locator('body')).toContainText('Expected: controller used to showcase');
});
