# Proof of Concepts: multi-data-source with Spring Data JPA

## Overview

Spring Boot 공식 문서를 통해 여러개의 DataSource를 사용하는 방법을 확인한다.

## Objectives

- 하나의 프로젝트에서 여러개의 DataSource를 연결하는지 테스트를 하고 설정한 DataSource를 Spring Data Jpa가 사용하는지 확인한다.

## Getting Started

Clone the repository: `git clone https://github.com/songks0922/multi-database-proof-of-concept.git`

```shell
docker-compose up -d
```

## Conclusion

여러개의 DataSource를 사용하기 위해서는 기본적으로 Spring boot에서의 AutoConfiguration을 사용하지 않고 직접 설정을 해야한다.
application.yml에서 사용할 데이터 소스에 대한 설정 정보를 입력하고 이를 받아서 필요한 설정 클래스 파일을 직접 만들고 Bean을 직접 등록해야 한다. Bean을
등록할 때 어느 한 DataSource에는 @Primary를 붙여서 기본적으로 사용할 DataSource를 설정해야 한다. 다른 autoConfiguration 과정에서
DataSource를 사용하는 경우에 타입이 동일한 Bean이 여러개 있으면 에러가 발생하므로 @Primary를 붙여서 DataSource를 설정해야 한다. 필요하다면 각각
DataSource마다 hikari-pool의 설정을 변경하여 사용도 가능하다.

## More

```@SQL```를 통해 테스트를 진행하여 일관성 있는 테스트를 작성하고자 하였는데, 이 때 ```@primary```로 등록한 DataSource Bean만을 바라보고 있어서
테스트를 실패 하였다. 여러 테스트 방법을 시도하였는데 해결하지 못했다. 추후에는 이를 해결하기 위한 방법이 있는지 찾아보는 것도 좋을 것 같다.

## License

This project is licensed under the [MIT License](LICENSE).

## References

- Spring Boot Reference
  Documentation: [configure-two-datasources](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.data-access.configure-two-datasources)
