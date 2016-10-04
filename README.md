# WisePersist-Settings

WisePersist Settings is a utility module which simplifies application configurations against different environments - test, dev, stage and prod.

Code coverage status:

            'instruction': 80.04,
            'branch'     : 71.88,
            'line'       : 75.0,
            'complexity' : 74.07,
            'method'     : 76.32,
            'class'      : 100.0

## Configure Maven dependency

WisePersist is available in Maven central repository: 

http://search.maven.org/#search%7Cga%7C1%7Cwisepersist-settings

To add the dependency to your `build.gradle` (for Gradle projects) or `pom.xml` (for Maven projects), use the following configuration:

For Gradle projects:

```
compile 'org.wisepersist:wisepersist-settings:1.0.1'
```

For Maven projects:

```
<dependency>
    <groupId>org.wisepersist</groupId>
    <artifactId>wisepersist-settings</artifactId>
    <version>1.0.1</version>
</dependency>
```

If you would like to use the 1.0.2-SNAPSHOT release, use this configuration.

For Gradle projects:

```
compile 'org.wisepersist:wisepersist-settings:1.0.2-SNAPSHOT'
```

For Maven projects:

```
<dependency>
    <groupId>org.wisepersist</groupId>
    <artifactId>wisepersist-settings</artifactId>
    <version>1.0.2-SNAPSHOT</version>
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

## How to use WisePersist-Settings?

TBD

## How to contribute

Here are the steps:

* Create a new issue for things to discuss
* After discussion about changes, fork the project and make changes
* Run `gradle clean build` to make sure no build errors
* Create a new pull request for review and discussion
* After confirmation, we will merge to the pull request
