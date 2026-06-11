# AG_26_FeatureCoverage

Companion repository for the Test Guild Community Webinar **From Code Coverage to Feature Coverage**.

This project uses [Spring PetClinic](https://github.com/spring-projects/spring-petclinic) as the sample application for showing how traditional code coverage and feature-level coverage answer different questions. The fork adds external test suites and JaCoCo wiring so the demo can compare raw line coverage with coverage viewed through product workflows.

## Webinar Context

Most teams can report a unit-test coverage percentage from tools like JaCoCo, but that number does not tell a manager, QA lead, or product owner whether a feature is actually tested. The problem gets harder when one feature spans backend, web UI, mobile, and desktop clients with tests spread across Cucumber, TestNG, Playwright, and other frameworks.

The webinar uses two demos:

1. A baseline JaCoCo run on this Spring Boot application to show what code coverage provides.
2. A feature-level coverage dashboard demo that uses a shared tagging convention and JSON contract to aggregate tests across frameworks.

This repository supports the first demo and the PetClinic-side test/coverage inputs for the second demo. The live webinar also covers dashboard behavior, Jira bug links, product analytics, and the framework-neutral JSON contract even where those pieces are not fully represented in this fork.

## What You Will Take Away

- Where line and branch coverage stop being useful, and the questions they cannot answer.
- How a tagging convention and JSON contract can let one dashboard consume Cucumber, TestNG, Playwright, or any framework with a small generator.
- How to add a new framework like Playwright to an existing dashboard without changing the dashboard itself.
- Why coverage and product analytics answer different questions, and why teams need both.
- How AI sped up test scaffolding, generator design, dashboard iteration, and documentation.
- Where AI still needed human judgment, especially for product-aware feature and functionality decisions.

## What This Fork Adds

The upstream PetClinic application remains intact. This fork adds a coverage-focused testing layer around it:

| Area | Path | Purpose |
| --- | --- | --- |
| JaCoCo Maven wiring | `pom.xml` | Generates HTML/XML coverage reports and adds an advanced external-test coverage profile. |
| Cucumber UI tests | `tests/cucumber-ui` | Browser-driven Selenium/Cucumber coverage for selected user workflows. |
| TestNG API tests | `tests/testng-api` | RestAssured/TestNG coverage for selected HTTP/API flows. |
| Playwright UI tests | `tests/playwright-ui` | Playwright coverage for additional pages and routes. |
| Coverage notes | `tests/coverage/JACOCO_README.md` | Report locations, Maven lifecycle notes, and the current endpoint coverage matrix. |

## What This Fork Does Not Try To Be

This is not a replacement for the upstream Spring PetClinic project. It is a demo fork for coverage analysis.

It also does not contain the full feature dashboard shown during the live webinar. The dashboard portion is covered in the demo to explain the tagging convention, JSON contract, framework adapters, Jira bug-link handling, and how product-facing coverage views differ from raw code coverage.

## Prerequisites

- Java 17 or newer
- `./mvnw` from this repository
- Node.js and npm for the Playwright suite
- Chrome or another supported browser for browser-driven tests

## Run PetClinic

```bash
./mvnw spring-boot:run
```

Open:

```text
http://localhost:8080
```

The application uses an in-memory H2 database by default and loads sample data at startup.

## Demo 1: Baseline Code Coverage

Run the normal application test suite and generate the standard JaCoCo report:

```bash
./mvnw clean verify
```

Open:

```text
target/site/jacoco/index.html
```

This report answers structural coverage questions such as which lines, branches, methods, and classes were executed by the tests.

## Demo 2 Input: External Test Coverage

The external suites can be run independently against a running PetClinic instance.

Start PetClinic:

```bash
./mvnw spring-boot:run
```

Run Cucumber UI tests:

```bash
./mvnw -f tests/cucumber-ui/pom.xml test
```

Run TestNG API tests:

```bash
./mvnw -f tests/testng-api/pom.xml test
```

Run Playwright UI tests:

```bash
cd tests/playwright-ui
npm ci
npm test
```

By default, these suites expect PetClinic at:

```text
http://localhost:8080
```

## Advanced Coverage Flow

The advanced Maven profile starts PetClinic with the JaCoCo agent, runs the external Cucumber, TestNG, and Playwright suites, dumps coverage after each suite, and generates separate plus combined reports.

```bash
./mvnw clean verify -Padvanced-cucumber-coverage -DskipTests
```

Default app URL for this flow:

```text
http://localhost:18080
```

Generated reports:

```text
target/site/jacoco-cucumber-ui/index.html
target/site/jacoco-testng-api/index.html
target/site/jacoco-playwright-ui/index.html
target/site/jacoco-advanced/index.html
```

See [tests/coverage/JACOCO_README.md](tests/coverage/JACOCO_README.md) for the current endpoint coverage matrix and Maven lifecycle details.

## Feature Coverage Versus Code Coverage

JaCoCo tells developers which JVM bytecode was executed. That is valuable, but it does not answer product-facing questions such as:

- Is a specific feature covered?
- Which team owns the tests for that feature?
- Which framework contains the relevant tests?
- Are there open Jira bugs tied to those tests?
- Are important but low-traffic workflows covered before release?

The webinar uses this fork to ground the code-coverage side, then shows how the same testing activity can be rolled up into feature-level coverage using tags, generators, and a common JSON shape.

## AI-Assisted Build Notes

AI helped accelerate the first version of this solution by drafting test scaffolding, Maven profile wiring, coverage documentation, and first-pass endpoint mapping.

Human review was still required for:

- Deciding what counts as a feature versus a functionality.
- Confirming whether a test meaningfully covers product behavior.
- Choosing tagging conventions that would work for real teams.
- Separating demo artifacts from files that should be checked into a public repo.

## Upstream Project And License

This project is based on the upstream [spring-projects/spring-petclinic](https://github.com/spring-projects/spring-petclinic) project.

The Spring PetClinic sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0). See [LICENSE.txt](LICENSE.txt).
