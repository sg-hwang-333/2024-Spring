package com.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Service
//public class MyDatabaseService {
//    // 여기에는 어떤 Bean을 주입시켜줘야 하는지?
//
//    //@Autowired
//    // CrudRepository repository;
//    // 에러 발생 (Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed)
//    // CrudRepository 를 구현한 클래스가 2개, Bean 이 2개라서 찾지 못함
//    // @Autowired
//    // SimpleCrudRepositoryImpl repository;  // 하위 클래스로 할 경우 에러 X
//
//    @Autowired
//    // 직접 주입받고 싶은 Bean 이름 지정 가능
//    @Qualifier("simpleCrudRepositoryImpl")
//    CrudRepository repository;
//}

@Service
public class MyDatabaseService {
    // 가급적이면 private, final 키워드를 붙이는 것 권장
    // private => 외부에서 접근해야 할 필요가 없으므로
    // final => 의존성 주입된 객체를 바꾸는 경우가 극히 드물기 떄문에 (만약 모종의 이유로 실행 중 해당 객체가 바꿔야 한다면 final로 하면 안 됨)
    private final CrudRepository simpleRepository;
    private final CrudRepository complexRepository;

    public CrudRepository getSimpleRepository() {
        return simpleRepository;
    }

    public CrudRepository getComplexRepository() {
        return complexRepository;
    }

    // 생성자를 이용한 의존성 주입 진행
    @Autowired
    public MyDatabaseService(
            @Qualifier("simpleCrudRepositoryImpl") CrudRepository simpleRepository,
            @Qualifier("complexCrudRepositoryImpl") CrudRepository complexRepository) {
        this.simpleRepository = simpleRepository;
        this.complexRepository = complexRepository;
    }
}