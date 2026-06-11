const { expect, test } = require('@playwright/test');

test('GET /vets returns the vets response body', {
  tag: ['@teamPetClinic', '@appPetClinic', '@featureVets', '@funcVetDirectory', '@subFuncReadVetsApi']
}, async ({ request }) => {
  const response = await request.get('/vets');

  expect(response.status()).toBe(200);
  expect(response.headers()['content-type']).toContain('application/json');

  const body = await response.json();
  const vetNames = body.vetList.map((vet) => `${vet.firstName} ${vet.lastName}`);
  expect(vetNames).toContain('James Carter');
  expect(vetNames).toContain('Helen Leary');
});
