# TestNG API Tests

This Maven project uses TestNG and RestAssured to verify PetClinic HTTP endpoints.

Run it against a running PetClinic app:

```bash
./mvnw -f tests/testng-api/pom.xml test
```

Override the application URL when needed:

```bash
./mvnw -f tests/testng-api/pom.xml test -Dapp.baseUrl=http://localhost:18080
```
