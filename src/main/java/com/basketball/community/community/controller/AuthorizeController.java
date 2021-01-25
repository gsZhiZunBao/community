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
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("1db6cba09e4e72523c67");
//        个人token
//        454a6463f65a4fc6c2ec020401d53ee6c0b7c8b1
//        应用token
//        85575d3d1f1319ebd0fc763089941a7208abed05
        accessTokenDTO.setClient_secret("85575d3d1f1319ebd0fc763089941a7208abed05");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("第二处"+accessToken);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println("名字"+user.getName());
        System.out.println(user.getBio());
        System.out.println(user.getId());
        return "index";
    }
}
