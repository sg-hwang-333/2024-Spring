package com.example.mvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

class SignUpForm {
    private String id;
    private String email;
    private String username;
    private int age;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SignUpForm{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

// 커맨드 객체로 사용될 클래스 정의
class MyCommandObject {

    private String value1;
    private Integer value2;

    // 반드시 세터 메서드가 있어야 함
    public void setValue1(String value1) { this.value1 = value1; }
    public void setValue2(Integer value2) { this.value2 = value2; }

    @Override
    public String toString() {
        return "MyCommandObject{" +
                "value1='" + value1 + '\'' +
                ", value2=" + value2 +
                '}';
    }
}

class MyJsonData {
    private String value1;
    private Integer value2;

    public String getValue1() { return value1; }
    public Integer getValue2() { return value2; }
    public void setValue1(String value1) { this.value1 = value1; }
    public void setValue2(Integer value2) { this.value2 = value2; }
}

// 내부 객체용 클래스 정의
class InnerObject {
    private String innerValue1;

    // 세터, 게터 추가
    public String getInnerValue1() {return innerValue1;}
    public void setInnerValue1(String innerValue1) {this.innerValue1 = innerValue1;}
}

class JsonDataWithArrayAndInnerObject {
    private List<String> array1; // String 형식의 데이터만 존재
    private List<Object> array2; // 다양한 데이터 형식이 혼재
    private InnerObject inner; // 내부 객체

    // 세터, 게터 추가
    public List<String> getArray1() {return array1;}
    public void setArray1(List<String> array1) {this.array1 = array1;}
    public List<Object> getArray2() {return array2;}
    public void setArray2(List<Object> array2) {this.array2 = array2;}
    public InnerObject getInner() {return inner;}
    public void setInner(InnerObject inner) {this.inner = inner;}
}

class Test1 {
    private String message;
    private String from;
    private String to;
    private int importance;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}

class ResponseTest1 {
    private String result;
    private int code;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}


class ResponseTest2 {
    private String result;
    private String id;
    private boolean success;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseTest2{" +
                "result='" + result + '\'' +
                ", id='" + id + '\'' +
                ", success=" + success +
                '}';
    }
}

class RequestTest2 {
    private String title;
    private String author;
    private int first_realese;
    private double rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getFirst_realese() {
        return first_realese;
    }

    public void setFirst_realese(int first_realese) {
        this.first_realese = first_realese;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

class RequestTest3 {
    private String text;
    private int count;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}



@RestController
@RequestMapping("/renew")
public class MyRenewController {

    @GetMapping("/test1")
    public ResponseTest1 responseTest1 (@RequestBody Test1 test1) {
        System.out.println(test1.getMessage());
        ResponseTest1 responseTest1 = new ResponseTest1();
        responseTest1.setResult("success");
        responseTest1.setCode(1001);

        return responseTest1;
    }

    @GetMapping("/test2")
    public ResponseTest2 responseTest2 (@RequestBody RequestTest2 requestTest2) {
        System.out.println(requestTest2.toString());
        ResponseTest2 responseTest2 = new ResponseTest2();
        responseTest2.setId("a1234");
        responseTest2.setResult("created");
        responseTest2.setSuccess(true);

        return responseTest2;
    }

    @GetMapping("/test3")
    public String test(@RequestBody RequestTest3 requestTest3) {
        String str = "";
        for (int i = 0; i < requestTest3.getCount(); i++ ) {
            str = str.concat(requestTest3.getText());
        }
        return str;
    }



    @PostMapping("/test1-2")
    @ResponseBody
    public HashMap<String, Object> test1_2(@RequestBody HashMap<String, Object> request) {
        System.out.println(request);

        HashMap<String, Object> response = new HashMap<>();
        response.put("result", "success");
        response.put("code", 1001);

        return response;
    }
    @PostMapping("/test2-2")
    @ResponseBody
    public HashMap<String, Object> test2_2(@RequestBody HashMap<String, Object> request) {
        System.out.println(request);

        HashMap<String, Object> response = new HashMap<>();
        response.put("result", "created");
        response.put("id", "a1234");
        response.put("success", true);

        return response;
    }
    @PostMapping("/test3-2")
    @ResponseBody
    public String test3(@RequestBody HashMap<String, Object> request) {
        String str = "";
        for (int i = 0; i < parseInt(request.get("count").toString()); i++ ) {
            str = str.concat((String) request.get("text"));
        }
        return str;
    }


    @GetMapping(value = "/json-test-2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonDataWithArrayAndInnerObject jsonTest2(@RequestBody JsonDataWithArrayAndInnerObject jsonDataWithArrayAndInnerObject) {
        for(Object item : jsonDataWithArrayAndInnerObject.getArray2()) {
            if(item != null) System.out.println(item.getClass().getName());
        }
        return jsonDataWithArrayAndInnerObject;
    }



    @PostMapping("/sign_up")
    public String commandObjectTest(@ModelAttribute SignUpForm signUpForm) {
        System.out.println(signUpForm);
        return "success";
    }


    // produces 옵션을 통해서 미디어 타입 지정 가능 (유추해서 자동으로 지정하게 할 수도 있지만 가급적 써주는 것을 권장)
    @GetMapping(value = "/echo", produces = MediaType.TEXT_PLAIN_VALUE)
    // 반환한 문자열이 바로 응답 메시지의 바디 데이터에 삽입될 수 있도록 @ResponseBody 어노테이션 추가 (@RestController를 사용하면 생략 가능)
    @ResponseBody // string 이 response 의 body 에 들어감
    public String echo(@RequestBody byte[] content) {
        // 메서드 정보 접근할 필요 없음 (GET 메서드)
        // 주소 정보 접근할 필요 없음 (@PathVariable 사용)
        // 쿼리 스트링 정보 접근할 필요 없음 (@RequestParam 사용)
        // 프로토콜, HTTP 버전 정보 접근할 필요가 보통 없음
        // 헤더 정보 접근할 필요 없음 (@RequestHeader 사용)
        // 요청 메시지의 바디 데이터 접근은 @RequestBody 어노테이션을 이용해서 전달받을 수 있음
        String bytesToString = new String(content, StandardCharsets.UTF_8);
        System.out.println(bytesToString);

        // Content-Type 헤더의 경우 produces 옵션을 제공하여 미디어 타입 지정 가능
        return bytesToString;
    }

    // 또는
    @GetMapping(value = "/echo2", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String echo2(@RequestBody String content) {
        return content;
    }

    @GetMapping(value = "/hello-html",
            produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String helloHTML() {
        return "<h1>Hello</h1>";
    }

    @GetMapping(value = "/echo-repeat", produces = MediaType.TEXT_PLAIN_VALUE)
// @RequestHeader 어노테이션을 통해서 X-Repeat-Count에 적힌 숫자 정보 가져오고 없으면 1로 초기화
    public String echoRepeat(@RequestParam("word") String word, @RequestHeader(value = "X-Repeat-Count", defaultValue = "1") Integer repeatCount) throws IOException {
        String result = "";
        for (int i = 0; i < repeatCount; i++) {
            result += word;
        }
        return result;
    }


    @GetMapping(value = "/dog-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] dogImage() throws IOException {
        // resources 폴더의 static 폴더에 이미지 있어야 함
        File file = ResourceUtils.getFile("classpath:static/Y9S1_GADGET_KEYART_1x1.jpg");
        // 파일의 바이트 데이터 모두 읽어오기
        byte[] bytes = Files.readAllBytes(file.toPath());

        return bytes;
    }

    // 또는
    @GetMapping(value = "/dog-image2", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] dogImage2() throws IOException {
        File file = ResourceUtils.getFile("classpath:static/Y9S1_GADGET_KEYART_1x1.jpg");
        return Files.readAllBytes(file.toPath());
    }


    @GetMapping(value = "/dog-image-file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
// 헤더를 직접 조정하고 싶은 경우 ResponseEntity 타입을 반환하도록 설정 가능
// (꺽쇠 안에는 응답 메시지의 바디 데이터에 포함될 타입을 지정)
    public ResponseEntity<byte[]> dogImageFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:static/Y9S1_GADGET_KEYART_1x1.jpg");
        byte[] bytes = Files.readAllBytes(file.toPath());

        String filename = "Y9S1_GADGET_KEYART_1x1.jpg";
        // 헤더 값 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    @PostMapping("/test")
// @ModelAttribute 를 타입 앞에 붙여주고 메서드의 파라미터 값으로 전달되게 함
    public String commandObjectTest(@ModelAttribute MyCommandObject myCommandObject) {
        return myCommandObject.toString();
    }



}