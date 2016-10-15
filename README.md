[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.theangrydev/domain-enforcer/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/io.github.theangrydev/domain-enforcer)
[![Javadoc](http://javadoc-badge.appspot.com/io.github.theangrydev/domain-enforcer.svg?label=javadoc)](http://javadoc-badge.appspot.com/io.github.theangrydev/domain-enforcer)
[![Gitter](https://badges.gitter.im/domain-enforcer/Lobby.svg)](https://gitter.im/domain-enforcer/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

[![Build Status](https://travis-ci.org/theangrydev/domain-enforcer.svg?branch=master)](https://travis-ci.org/theangrydev/domain-enforcer)
[![codecov](https://codecov.io/gh/theangrydev/domain-enforcer/branch/master/graph/badge.svg)](https://codecov.io/gh/theangrydev/domain-enforcer)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/204ebe44f8b94cd09f6d2810e630de16)](https://www.codacy.com/app/liam-williams/domain-enforcer?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=theangrydev/domain-enforcer&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/76e4bf20-cf8e-48d4-bf5e-1088bb17ca10)](https://codebeat.co/projects/github-com-theangrydev-domain-enforcer)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=io.github.theangrydev:domain-enforcer)](https://sonarqube.com/dashboard/index/io.github.theangrydev:domain-enforcer)

# domain-enforcer
Enforce dependencies between packages in a unit test!

```xml
<dependency>
    <groupId>io.github.theangrydev</groupId>
    <artifactId>domain-enforcer</artifactId>
    <version>1.1.1</version>
</dependency>
```

## Releases
### 1.1.1
* Use `%n` not `\n` when displaying new lines

### 1.1.0
* Made it easier to see violations by putting them on a new line (closes [#1](https://github.com/theangrydev/domain-enforcer/issues/1))

### 1.0.0
* Initial stab at business-flows
