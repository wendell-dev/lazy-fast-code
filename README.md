## 简介
lazyFastCode 可用于快速构建 Spring Boot 2.x 应用工程骨架，自动生成基于SpringMVC的RESTFul API架构风格，同时集成了MyBatis通用Mapper模块、通用分页模块、Lombok工具包以及API在线文档Swagger等，旨在帮助JAVA后端API开发人员能够更快速的专注于业务代码开发。

## lazyFastCode结构一览
参考Spring官网
[The following listing shows a typical layout](https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/html/using-boot-structuring-your-code.html#using-boot-locating-the-main-class)

参考Maven官网
[Introduction to the Standard Directory Layout](http://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)

    lazy-fast-code/
    | - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - -
    | - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - -
    | - core/ - - - - lazyFastCode核心类集合，会打成Maven-jar包，供其它项目/模块依赖
    |   |   src/
    |   |   |   main
    |   |   |       java
    |   |   |           lazy.fast.code.core
    |   |   pom.xml
    |   |
    |   |
    | - generator/ - - - - lazyFastCode代码生成器，与Mybatis的生成器无关，基于FreeMarker定制开发，简单易懂、可定制化高
    |   |   src/
    |   |   |   main
    |   |   |       java
    |   |   |           lazy.fast.code.generator
    |   |   |               Main.java
    |   |   |       resources
    |   |   |           template
    |   |   |               xxx.ftl
    |   |   pom.xml
    |   |
    |   |
    | - demo/ - - - - 演示用例，实际中应该由lazyFastCode代码生成器生成一个单独的项目
    |   |   src/
    |   |   |   main
    |   |   |       java
    |   |   |           lazy.fast.code.demo
    |   |   |               Application.java
    |   |   |       resources
    |   |   |           application.yml
    |   |   pom.xml
    | - pom.xml
    | - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - -
    | - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - -

## 集成的组件
- [Spring Boot 2.1.7](https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/html/)
- [MyBatis通用Mapper](https://github.com/abel533/Mapper) - tk.mybatis
- [Mybatis通用分页插件](https://github.com/pagehelper/Mybatis-PageHelper) - PageHelper
- [lombok](https://projectlombok.org/) - 简化使用
- [swagger-spring-boot-starter](https://github.com/SpringForAll/spring-boot-starter-swagger) - 用做接口在线文档
- [Apache Freemarker](https://github.com/apache/freemarker) - 用于lazyFastCode代码生成器


