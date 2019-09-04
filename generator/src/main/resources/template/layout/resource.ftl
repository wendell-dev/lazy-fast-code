spring:
  datasource:
    url: ${jdbcUrl}
    username: ${jdbcUser}
    password: ${jdbcPassword}
    driver-class-name: ${jdbcClassName}
    tomcat:
      max-wait: 10000
      max-active: 20
      test-on-borrow: true
swagger:
  exclude-path: /error