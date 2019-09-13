spring:
  application:
    name: ${projectName}
  datasource:
    url: ${jdbcUrl!"jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true&serverTimezone=Asia/Shanghai"}
    username: ${jdbcUser!"root"}
    password: ${jdbcPassword!"123456"}
    driver-class-name: ${jdbcClassName!"com.mysql.cj.jdbc.Driver"}
    hikari:
      minimum-idle: 5
      maximum-pool-size: 15
      idle-timeout: 30000
      max-lifetime: 1800000
      connection-timeout: 30000
      connection-test-query: SELECT 1
mybatis:
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
swagger:
  exclude-path: /error