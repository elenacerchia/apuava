# Apuava

Apuava is a utility module which contains reusable classes for Java applications. For example, settings module can simplify application configurations against different environments - test, dev, stage and prod.

Code coverage status:

            'instruction': 86.61,
            'branch'     : 78.57,
            'line'       : 83.52,
            'complexity' : 79.31,
            'method'     : 80.3,
            'class'      : 100.0

## Configure Maven dependency

WisePersist is available in Maven central repository: 

http://search.maven.org/#search%7Cga%7C1%7Capuava

To add the dependency to your `build.gradle` (for Gradle projects) or `pom.xml` (for Maven projects), use the following configuration:

For Gradle projects:

```
compile 'org.wisepersist:apuava:1.0.2'
```

For Maven projects:

```
<dependency>
    <groupId>org.wisepersist</groupId>
    <artifactId>apuava</artifactId>
    <version>1.0.2</version>
</dependency>
```

If you would like to use the 1.0.3-SNAPSHOT release, use this configuration.

For Gradle projects:

```
compile 'org.wisepersist:apuava:1.0.3-SNAPSHOT'
```

For Maven projects:

```
<dependency>
    <groupId>org.wisepersist</groupId>
    <artifactId>apuava</artifactId>
    <version>1.0.3-SNAPSHOT</version>
</dependency>
```

In order to use snapshot releases you also need to add the Sonatype snapshots repository to your POM:

```
<repositories>
    <repository>
        <id>sonatype-nexus-snapshots</id>
        <url>http://oss.sonatype.org/content/repositories/snapshots</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <releases>
            <enabled>false</enabled>
        </releases>
    </repository>
</repositories>
```

## How to use Apuava?

TBD

## How to contribute

Here are the steps:

* Create a new issue for things to discuss
* After discussion about changes, fork the project and make changes
* Run `gradle clean build` to make sure no build errors
* Create a new pull request for review and discussion
* After confirmation, we will merge to the pull request
