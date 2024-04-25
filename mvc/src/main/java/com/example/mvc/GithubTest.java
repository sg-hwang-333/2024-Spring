package com.example.mvc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

class GithubUserInfo {
    @JsonProperty("login")
    private String login;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("name")
    private String name;

    @Override
    public String toString() {
        return "GithubUserInfo{" +
                "login='" + login + '\'' +
                ", bio='" + bio + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

class pokeInfo {
    @JsonProperty("abilities")
    private List<List<String>> abilities;

    @JsonProperty("cries")
    private String cries;

    @Override
    public String toString() {
        return "pokeInfo{" +
                "abilities=" + abilities +
                ", cries=" + cries +
                '}';
    }
}

@RestController
@RequestMapping("/response_github_demo")
public class GithubTest {
    @GetMapping(value = "/github/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GithubUserInfo githubUserTest(@PathVariable("user") String user) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, headers, HttpMethod.GET, URI.create("https://api.github.com/users/" + user));


        ResponseEntity<GithubUserInfo> response2 = restTemplate.exchange(requestEntity, GithubUserInfo.class);
        GithubUserInfo responseBody = response2.getBody();

        return responseBody;
    }

    @GetMapping(value = "/pokemon/{poke}", produces = MediaType.APPLICATION_JSON_VALUE)
    public pokeInfo pokeTest(@PathVariable("poke") String poke) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, headers, HttpMethod.GET, URI.create("https://pokeapi.co/api/v2/pokemon/" + poke));


        ResponseEntity<pokeInfo> response = restTemplate.exchange(requestEntity, pokeInfo.class);
        pokeInfo responseBody = response.getBody();

        return responseBody;
    }
}
