# JaCoCo Coverage

## Basic Coverage

Runs the existing Java test suite in the same JVM flow as the application code under test.

```bash
./mvnw clean verify
```

Reports:

```text
target/site/jacoco/index.html
target/site/jacoco/jacoco.xml
```

## Advanced External Test Coverage

Starts PetClinic with the JaCoCo agent, runs external Cucumber Selenium UI tests, TestNG RestAssured API tests, and Playwright UI tests, dumps coverage data, stops the app, and creates separate and combined reports.

```bash
./mvnw clean verify -Padvanced-e2e-coverage -DskipTests
```

Reports:

```text
target/site/jacoco-cucumber-ui/index.html
target/site/jacoco-cucumber-ui/jacoco.xml
target/site/jacoco-testng-api/index.html
target/site/jacoco-testng-api/jacoco.xml
target/site/jacoco-playwright-ui/index.html
target/site/jacoco-playwright-ui/jacoco.xml
target/site/jacoco-advanced/index.html
target/site/jacoco-advanced/jacoco.xml
```

Execution data:

```text
target/jacoco-cucumber-ui.exec
target/jacoco-testng-api.exec
target/jacoco-playwright-ui.exec
target/jacoco-advanced.exec
```

The `.exec` files are JaCoCo's raw binary execution data. `jacoco-cucumber-ui.exec` is dumped after the Cucumber UI suite and then the JaCoCo agent is reset. `jacoco-testng-api.exec` is dumped after the TestNG API suite and then the JaCoCo agent is reset. `jacoco-playwright-ui.exec` is dumped after the Playwright UI suite. `jacoco-advanced.exec` is created by merging those three files. You normally do not open these files directly; `jacoco:report` reads them and generates the human-readable HTML/XML reports under `target/site/`.

Default app URL for this flow:

```text
http://localhost:18080
```

## External Test Coverage Matrix

This matrix tracks which external suite currently covers each PetClinic endpoint. Cucumber is browser-driven Selenium coverage. TestNG is RestAssured HTTP coverage. Playwright uses browser tests for HTML pages and its request client for response-body checks.

| Area | Endpoint | Cucumber UI | TestNG API | Playwright UI |
| --- | --- | --- | --- | --- |
| System | `GET /` | No | No | Yes |
| System | `GET /oups` | No | No | Yes |
| Vets | `GET /vets.html` | Yes | No | No |
| Vets | `GET /vets` | No | No | Yes |
| Owners | `GET /owners/new` | No | No | Yes |
| Owners | `POST /owners/new` | No | No | No |
| Owners | `GET /owners/find` | Yes | No | No |
| Owners | `GET /owners` | Yes | No | No |
| Owners | `GET /owners/{ownerId}/edit` | No | No | No |
| Owners | `POST /owners/{ownerId}/edit` | No | No | No |
| Owners | `GET /owners/{ownerId}` | Yes | No | No |
| Pets | `GET /owners/{ownerId}/pets/new` | No | Yes | No |
| Pets | `POST /owners/{ownerId}/pets/new` | No | No | No |
| Pets | `GET /owners/{ownerId}/pets/{petId}/edit` | No | Yes | No |
| Pets | `POST /owners/{ownerId}/pets/{petId}/edit` | No | No | No |
| Visits | `GET /owners/{ownerId}/pets/{petId}/visits/new` | No | Yes | No |
| Visits | `POST /owners/{ownerId}/pets/{petId}/visits/new` | No | No | No |

## Report Columns

- **Instructions**: JVM bytecode instructions executed by tests. This is JaCoCo's core metric.
- **Branches**: Decision paths from `if`, `else`, `switch`, ternary, and similar logic.
- **Cxty**: Cyclomatic complexity, meaning independent paths through the code.
- **Lines**: Source lines executed by tests.
- **Methods**: Methods called by tests.
- **Classes**: Classes touched by tests.

## Colors

- **Green**: covered
- **Red**: missed
- **Yellow**: partially covered, usually because only some branch paths ran

## Maven Lifecycle Mapping

Maven runs phases in order. When you run `verify`, Maven also runs earlier phases such as `compile`, `test`, and `prepare-package`.

```text
./mvnw clean verify
        |
        +-- clean
        +-- validate
        +-- initialize
        |     +-- jacoco:prepare-agent attaches the coverage agent
        |
        +-- compile
        +-- test
        |     +-- tests run and write coverage data to target/jacoco.exec
        |
        +-- prepare-package
        |     +-- jacoco:report creates HTML/XML reports
        |
        +-- package
        +-- verify
```

The important idea:

```text
jacoco:prepare-agent -> attaches the coverage agent before tests run
jacoco:report        -> converts target/jacoco.exec into HTML/XML reports
```

Plugin goals run during a Maven build only when they are bound to a phase. In this project, `jacoco:report` is explicitly bound to `prepare-package` in `pom.xml`; `jacoco:prepare-agent` uses its default early lifecycle binding so it is ready before the `test` phase.

## Advanced Flow Mapping

The advanced profile binds extra goals to integration-test lifecycle phases:

```text
pre-integration-test
  -> spring-boot:start starts PetClinic with the JaCoCo javaagent in TCP server mode

integration-test
  -> exec:exec runs tests/cucumber-ui with app.baseUrl=http://localhost:18080
  -> jacoco:dump writes target/jacoco-cucumber-ui.exec and resets the JaCoCo agent

post-integration-test
  -> exec:exec runs tests/testng-api with app.baseUrl=http://localhost:18080
  -> jacoco:dump writes target/jacoco-testng-api.exec and resets the JaCoCo agent
  -> exec:exec runs npm ci in tests/playwright-ui
  -> exec:exec runs tests/playwright-ui with APP_BASE_URL=http://localhost:18080
  -> jacoco:dump writes target/jacoco-playwright-ui.exec

verify
  -> spring-boot:stop stops PetClinic
  -> jacoco:merge creates target/jacoco-advanced.exec
  -> jacoco:report creates target/site/jacoco-cucumber-ui
  -> jacoco:report creates target/site/jacoco-testng-api
  -> jacoco:report creates target/site/jacoco-playwright-ui
  -> jacoco:report creates target/site/jacoco-advanced
```

This measures server-side Java code reached by browser and API tests.
