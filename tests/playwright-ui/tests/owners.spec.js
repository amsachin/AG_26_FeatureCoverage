const { expect, test } = require('@playwright/test');

test('GET /owners/new renders the new owner form', {
  tag: ['@teamPetClinic', '@appPetClinic', '@featureOwners', '@funcOwnerManagement',
    '@subFuncOpenNewOwnerForm']
}, async ({ page }) => {
  const response = await page.goto('/owners/new');

  expect(response.status()).toBe(200);
  await expect(page.locator('h2')).toContainText('Owner');
  await expect(page.locator('#add-owner-form')).toBeVisible();
  await expect(page.locator('input[name="firstName"]')).toBeVisible();
  await expect(page.locator('input[name="lastName"]')).toBeVisible();
  await expect(page.locator('button[type="submit"]')).toContainText('Add Owner');
});
