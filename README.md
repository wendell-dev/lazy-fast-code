## 简介
LazyFastCode 可用于快速的构建一个 Spring Boot 2.x 应用工程骨架，并可自动生成基于SpringMVC的RESTFul API架构风格，同时集成了MyBatis通用Mapper、通用分页PageHelper、Lombok工具包以及API在线文档Swagger等，旨在帮助JAVA后端API开发人员能够更快速的专注于业务代码开发。


## 快速开始
- 生成项目骨架
```
# 克隆LazyFastCode项目到你本地目录
git clone https://github.com/wendell-dev/lazy-fast-code.git

# 进入LazyFastCode项目根目录
cd lazy-fast-code

# 执行maven
mvn clean install

# 执行生成项目骨架命令
# 参数说明： 
#     author 生成类注释@author，如：author=wendell
#     project.name 【必填项】项目名称，如：project.name=xxx-demo
#     project.path 【必填项】项目根路径，如：project.path=D:\\workspace
#     project.package 【必填项】包名，如：project.package=com.xxx.demo
java -Dauthor=wendell -Dproject.name=xxx-demo -Dproject.path=D:\\workspace -Dproject.package=com.xxx.demo -jar generator/target/code-generator-1.0-SNAPSHOT-exec.jar

# 导入新生成的D:\\workspace\\xxx-demo项目到你的IDE中, 你可以尽情的进行业务开发了
```

- 新生成的项目结构一览
```
生成的典型的 Spring Boot Maven 工程结构

+- xxx-demo
    +- src
    |   +- main
    |   |   +- java
    |   |   |   +- com.xxx.demo
    |   |   |       +- Application.java
    |   |   +- resources
    |   |   |   +- application.yml
    |   +- test
    |       +- java
    |       |   +- com.xxx.demo
    |       |       +- GeneratorTest.java
    +- pom.xml
```

- 生成模块类
TODO


## Demo示例RESTFul API服务
```
# 克隆LazyFastCode项目到你本地目录
git clone https://github.com/wendell-dev/lazy-fast-code.git

# 进入LazyFastCode项目demo目录
cd lazy-fast-code/demo

# 执行maven
mvn clean package

# 执行启动命令
java -jar target/code-demo-1.0-SNAPSHOT.jar
```

- 访问接口文档地址 http://localhost:8080/swagger-ui.html

![demo-swagger.jpg](https://github.com/wendell-dev/resource-static/blob/master/lazy-fast-code/demo-swagger.jpg)


## RESTFul API
你可以通过项目中的 [demo](https://github.com/wendell-dev/lazy-fast-code/tree/master/demo) 模块查看示例RESTFul API服务（这是基于我们的LazyFastCode代码生成器自动完成的）。 

对于RESTFul API服务各有各的见解，网上大多是采用封装一个Result类在controller层作为统一格式返回，通常情况下，不管你怎么请求，它总是响应你的HTTP状态码为200。 

而本项目中的理念是充分结合HTTP状态码规范，使用ResponseEntity + HttpStatus的方式完成我们的API。当然，你想做一个完全具有RESTFul风格的API，你还需要具有良好的RESTFul风格的资源设计能力。


## 全局异常处理
采用@RestControllerAdvice + @ExceptionHandler的方式对全局异常进行处理，同时加入了常见的一些自定义异常类。


## 参数验证器
完全遵守JSR-303规范，采用spring提供的@Validated注解结合hibernate的validator进行验证，你只需要在你的验证实体对象中使用验证注解，如@NotNull、@NotBlank等，同时在你的controller方法中加入@Validated注解即可，验证结果信息的处理已经由全局异常处理器帮你做好了。


## LazyFastCode结构一览
参考Spring官网
[The following listing shows a typical layout](https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/html/using-boot-structuring-your-code.html#using-boot-locating-the-main-class)

参考Maven官网
[Introduction to the Standard Directory Layout](http://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)

    | - - - - - - - - - - - - - - - - - - - -- - - - - - - - - -- - - - - - - - - -- - - - - - - - - - - - - -
    | - - - - - - - - - - - - - - - - - - - -- - - - - - - - - -- - - - - - - - - -- - - - - - - - - - - - - -
    +- lazy-fast-code
    | |
    | +- core  - - - - LazyFastCode核心类集合，会打成Maven-jar包，供其它项目/模块依赖
    | |      +- src/
    | |      |    +- main
    | |      |        +- java
    | |      |            +- lazy.fast.code.core
    | |      +- pom.xml
    | |
    | |
    | +- generator  - - - - LazyFastCode代码生成器，与Mybatis的生成器无关，基于FreeMarker定制开发，简单易懂、可定制化高
    | |      +- src
    | |      |    +- main
    | |      |    |   +- java
    | |      |    |   |    +- lazy.fast.code.generator
    | |      |    |   |        +- QuickStart.java
    | |      |    |   +- resources
    | |      |    |   |    +- template
    | |      |    |   |        +- xxx.ftl
    | |      |    +- test
    | |      |        +- java
    | |      |        |    +- lazy.fast.code.generator
    | |      |        |        +- GeneratorTest.java
    | |      +- pom.xml
    | |
    | |
    | +- demo  - - - - 演示用例，实际中应该由LazyFastCode代码生成器生成一个单独的项目
    | |      +- src
    | |      |    +- main
    | |      |        +- java
    | |      |        |    +- lazy.fast.code.demo
    | |      |        |        +- Application.java
    | |      |        |        |
    | |      |        |        +- address
    | |      |        |        |    +- Address.java
    | |      |        |        |    +- AddressController.java
    | |      |        |        |    +- AddressRepository.java
    | |      |        |        |    +- AddressService.java
    | |      |        |        |    +- AddressServiceImpl.java
    | |      |        |        |
    | |      |        |        +- user
    | |      |        |         |   +- User.java
    | |      |        |         |   +- UserController.java
    | |      |        |         |   +- UserRepository.java
    | |      |        |         |   +- UserService.java
    | |      |        |         |   +- UserServiceImpl.java
    | |      |        +- resources
    | |      |            +- application.yml
    | |      +- pom.xml
    | |
    | |
    | +- pom.xml
    | - - - - - - - - - - - - - - - - - - - -- - - - - - - - - -- - - - - - - - - -- - - - - - - - - - - - - -
    | - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - -


## 集成的组件
- [Spring Boot 2.1.7](https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/html/)
- [MyBatis通用Mapper](https://github.com/abel533/Mapper) - tk.mybatis
- [Mybatis通用分页插件](https://github.com/pagehelper/Mybatis-PageHelper) - PageHelper
- [lombok](https://projectlombok.org/) - 简化使用
- [swagger-spring-boot-starter](https://github.com/SpringForAll/spring-boot-starter-swagger) - 用做接口在线文档
- [Apache Freemarker](https://github.com/apache/freemarker) - 用于lazyFastCode代码生成器


