package com.basketball.community.community.controller;

import com.basketball.community.community.dto.AccessTokenDTO;
import com.basketball.community.community.dto.GithubUser;
import com.basketball.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name = "state") String state) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("1db6cba09e4e72523c67");
        accessTokenDTO.setClient_secret("8d89c1e4bb0a70f66317e6e1ae7ad0cc083f53c6");
        accessTokenDTO.setRedirect_uri("http://localhost:8889/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("第二处"+accessToken);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
