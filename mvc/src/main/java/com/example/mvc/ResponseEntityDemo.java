package com.example.mvc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

class User {
    private String id;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }
}

class ResponseQuote {
    @JsonProperty("quote")
    private String quote;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "quote='" + quote + '\'' +
                '}';
    }
}


@RestController
@RequestMapping("/response_entity_demo")
public class ResponseEntityDemo {

    @GetMapping("/demo1")
    // 제네릭 타입이 Void인 이유는 해당 응답메시지에 바디데이터가 없기 때문임
    public ResponseEntity<Void> demo1() {
        // 상태 코드와 관련된 메서드와 build 메서드 호출하여 바로 바디 데이터 없이 응답
        return ResponseEntity.ok().build();
        // 바디데이터 없이 404 주고 싶은 경우
        // return ResponseEntity.notFound().build();
    }

    @GetMapping("/demo2")
    // 제네릭 타입이 String이니 바디에 평문(text/plain)이 들어갈 것임을 유추 가능
    public ResponseEntity<String> demo2() {
        String responseBody = "Hello, world!";
        // 상태 코드와 관련된 메소드에 바디 데이터를 같이 전달 가능
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/demo3")
    public ResponseEntity<String> demo3() {
        // 좀 더 세분화해서 응답메시지를 커스터마이징하고 싶은 경우
        // 헤더 영역 정보 만들기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "value");
        String responseBody = "Hello, world!";
        return ResponseEntity
                .status(HttpStatus.OK) // 상태 코드
                .headers(headers) // 헤더
                .body(responseBody); // 바디
    }

    @GetMapping("/demo4")
    public ResponseEntity<String> demo4() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "value");
        String responseBody = "Hello, world!";
        // 빌더 패턴 없이 직접 new 키워드로 객체 생성하여 응답
        return new ResponseEntity<>(
                responseBody, // 바디
                headers, // 헤더
                HttpStatus.OK); // 상태 코드
    }

    @GetMapping("/demo5")
    // 커스텀 클래스인 경우 내부적으로 JSON 직렬화 시도
    // (아마도 JSON 포맷을 압도적으로 많이 쓰므로 그냥 해당 메시지 컨버터를 쓰도록 하는 듯?, 아마 객체 직렬화의 기본 방식이 JSON인듯함)
    public ResponseEntity<User> demo5() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new User("1", "hello@example.com"));
    }

    @DeleteMapping("/demo6")
    public ResponseEntity<Void> demo6() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/github/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String githubUser(@PathVariable("user") String user) {
        RestTemplate restTemplate = new RestTemplate();

        // 요청 메시지 생성 및 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 요청 메시지에 바디 데이터가 없으므로 Void 타입으로 설정
        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, headers, HttpMethod.GET, URI.create("https://api.github.com/users/" + user));

        // 응답 메시지
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        // 응답 메시지의 바디 데이터를 문자열로 해석
        String responseBody = response.getBody();

        return responseBody;
    }

    @GetMapping(value="/quote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseQuote Quote() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, headers, HttpMethod.GET,
                URI.create("https://api.kanye.rest/")
        );

//        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
//        String responseBody = response.getBody();

        ResponseEntity<ResponseQuote> response2 = restTemplate.exchange(requestEntity, ResponseQuote.class);
        ResponseQuote responseBody2 = response2.getBody();

        return responseBody2;
    }

}