package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		// run 메서드의 반환값이 Bean 들이 저장될 컨테이너로 활용되는 ApplicationContext 타입의 객체
		ApplicationContext context = SpringApplication.run(Application.class, args);
		// 모든 Bean 들의 이름 가져와서
		String[] beanNames = context.getBeanDefinitionNames();
		// 확인해보기
		for (String beanName : beanNames) {
			// 단, 기본적으로 스프링에서 제공하는 Bean 들은 가급적 필터링해서 보여주기 (전부 다 걸러지진 않음)
			if(!beanName.startsWith("org.") && !beanName.startsWith("spring.")) {
				System.out.println(beanName);
			}
		}

		Person p = (Person) context.getBean("myPerson");
		p.sayName();

		MyCalculatorService myCalculatorService = (MyCalculatorService) context.getBean("myCalculatorService");
		System.out.println(myCalculatorService.calcAdd(3,6));


		MyDatabaseService myDatabaseService = (MyDatabaseService) context.getBean("myDatabaseService");
		System.out.println(myDatabaseService.getSimpleRepository() instanceof SimpleCrudRepositoryImpl);
		System.out.println(myDatabaseService.getComplexRepository() instanceof ComplexCrudRepositoryImpl);


	}

}
