package com.example.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

@Controller
class SomeController {
    // 해당 필드에 저장된 String을 수정
    private String message = "";

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SomeController{" +
                "message='" + message + '\'' +
                '}';
    }
}

class CalcController {
    private int num1;
    private int num2;
    private String op;
    private int result;

    public String getOp() {
        return op;
    }

    public CalcController(int num1, int num2, String op) {
        this.num1 = num1;
        this.num2 = num2;
        this.op = op;
        this.result = calc(num1,num2,op);
    }

    public int calc(int num1, int num2, String op) {
        return switch (op) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return "CalcController{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", op='" + op + '\'' +
                ", result=" + result +
                '}';
    }
}
@RestController
@RequestMapping("/mvcTest")
public class MvcTest {
    @GetMapping("/get_test")
    public String getTest() {
        return "GET";
    }
    @PostMapping("/post_test")
    @ResponseBody
    public String postTest() {
        return "Post";
    }
    @PutMapping("/put_test")
    @ResponseBody
    public String putTest() {
        return "Put";
    }
    @PatchMapping("/patch_test")
    @ResponseBody
    public String patchTest() {
        return "Patch";
    }
    @DeleteMapping("/delete_test")
    @ResponseBody
    public String deleteTest() {
        return "Delete";
    }
    @PatchMapping("/update_message")
    public String updateMessage(@RequestParam("message") String mes) {
        SomeController controller = new SomeController();
        controller.setMessage(mes);
        return controller.getMessage();
    }
    @PatchMapping(value = "/update_message2/{new-message-content}")
    public String updateMessage2(@PathVariable("new-message-content") String mes) {
        SomeController controller = new SomeController();
        controller.setMessage(mes);
        return controller.getMessage();
    }
    @PatchMapping("/update_message3")
    public String updateMessage3(@RequestBody String mes) {
        SomeController controller = new SomeController();
        controller.setMessage(mes);
        return controller.getMessage();
    }

    @PostMapping("/calc")
    @ResponseBody
    public String calc(@RequestParam("num1") int num1,
                       @RequestParam("num2") int num2,
                       @RequestParam("op") String op) {
        CalcController calcController = new CalcController(num1, num2, op);
        calcController.calc(num1, num2, op);

        return calcController.toString();
    }

    int visitCount = 0;

    @PatchMapping("/update_visit_count")
    @ResponseBody
    public String updateVisitCount() throws IOException {
        visitCount++;
        return visitCount+"";
    }

    @PostMapping("/update_visit_count_json")
    @ResponseBody
    public HashMap<String, Integer> updateVisitCountJson() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        visitCount++;
        hashMap.put("visit_count", visitCount);
        return hashMap;
    }

    ArrayList<String> email = new ArrayList<>();

    @PostMapping("/register_email")
    @ResponseBody
    public HashMap<String, Object> registerEmail(@RequestBody String string) {
        String[] splitStr = string.split(",");
        email.addAll(Arrays.asList(splitStr));

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email_addresses", email);

        return hashMap;
    }

    class Vote {
        public String option;
        public int count;

        public Vote(String option, int count) {
            this.option = option;
            this.count = count;
        }

        public void addCount() {
            count++;
        }
    }
    ArrayList<Vote> votes = new ArrayList<>();

    @PatchMapping("/vote/register_option")
    @ResponseBody
    public void registerOption(@RequestBody String register) {
        Vote vote = new Vote(register, 0);
        votes.add(vote);
    }
    @GetMapping("/vote/show_option")
    @ResponseBody
    public HashMap<String, Object> showOption() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("result", votes);
        return hashMap;
    }
    @PostMapping("/vote/make_vote")
    @ResponseBody
    public ArrayList<Vote> makeVote(@RequestParam(value="index") int index) {
        votes.set(index, new Vote(votes.get(index).option, ++votes.get(index).count));

        return votes;
    }


}