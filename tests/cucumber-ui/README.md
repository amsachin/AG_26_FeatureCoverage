# Cucumber UI Tests

This Maven project contains high-level Cucumber UI tests that use Selenium against a running PetClinic app.

Run PetClinic first:

```bash
./mvnw spring-boot:run
```

Then run the Cucumber suite from the repository root:

```bash
./mvnw -f tests/cucumber-ui/pom.xml test
```

Useful options:

```bash
./mvnw -f tests/cucumber-ui/pom.xml test -Dapp.baseUrl=http://localhost:8080
./mvnw -f tests/cucumber-ui/pom.xml test -Dbrowser=firefox
./mvnw -f tests/cucumber-ui/pom.xml test -Dheadless=false
```

Reports:

```text
tests/cucumber-ui/target/cucumber-reports/cucumber.html
tests/cucumber-ui/target/cucumber-reports/cucumber.json
```

Current UI coverage:

```text
/vets.html - verifies the Veterinarians page and visible vets table
```
