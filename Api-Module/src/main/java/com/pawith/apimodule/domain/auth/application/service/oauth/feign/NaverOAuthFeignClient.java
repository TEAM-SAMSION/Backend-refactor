package com.pawith.apimodule.domain.auth.application.service.oauth.feign;

import com.pawith.apimodule.domain.auth.application.service.oauth.feign.response.NaverUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "naverOAuth",url = "https://openapi.naver.com/v1/nid/me")
public interface NaverOAuthFeignClient {

    @GetMapping
    NaverUserInfo getNaverUserInfo(@RequestHeader("Authorization") String token);
}
