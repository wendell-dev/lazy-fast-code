<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>lazy.fast.code</groupId>
        <artifactId>code-parent</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <groupId>${basePackageName}</groupId>
    <artifactId>${projectName}</artifactId>
    <packaging>jar</packaging>

    <name>${projectName}</name>
    <description>${projectName} generated by lazyFastCode</description>

    <dependencies>
        <dependency>
            <groupId>lazy.fast.code</groupId>
            <artifactId>core-web</artifactId>
        </dependency>
        <dependency>
            <groupId>lazy.fast.code</groupId>
            <artifactId>generator</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>