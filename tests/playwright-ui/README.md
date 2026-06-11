# Playwright UI Tests

This project uses Playwright to cover PetClinic GET endpoints that are not covered by the Cucumber UI or TestNG API suites.

Run it against a running PetClinic app:

```bash
npm test
```

Override the application URL when needed:

```bash
APP_BASE_URL=http://localhost:18080 npm test
```
