
package com.example.mvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static java.lang.Integer.parseInt;

class AddController {
    private int num1;
    private int num2;

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class User2 {
    private String username;
    private String password;
    public User2(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }


    public boolean equals(Object o2) {
        return (this.username.equals(o2) || this.password.equals(o2));
    }
}

@RestController
@RequestMapping("/suhang")
public class SuhangController {

    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Integer> Add(@RequestBody HashMap<String, Object> request) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("result", (parseInt(request.get("num1").toString()) + parseInt(request.get("num2").toString())));
        return hashMap;
    }

//    @PostMapping("/calc/{calc2}")
//    public HashMap<String, Object> calcAdd(@PathVariable("calc2") HashMap<String, Object> request1) {
//    }

    @GetMapping("/make-person1")
    @ResponseBody
    public String MakePerson1(@RequestParam String name, Integer age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);

        return "success";
    }

    @GetMapping("/make-person2/{name}/{age}")
    @ResponseBody
    public String MakePerson2(@PathVariable("name") String name,
                              @PathVariable("age") int age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);

        return person.toString();
    }

    @PostMapping("/login")
    public String Login(@RequestHeader("X-Username") String username,
                        @RequestHeader("X-Password") String password) {
        User2 userData = new User2("admin", "1234");
        if (userData.equals(username) && userData.equals(password)) {
            return "success";
        } else {
            return "fail";
        }
    }

}

