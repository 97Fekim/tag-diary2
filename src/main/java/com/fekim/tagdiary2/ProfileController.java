package com.fekim.tagdiary2;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final Environment env;

    // 현재 구동중인 애플리케이션의 포트 번호를 제공하는 컨트롤러 입니다.
    @GetMapping("/profile")
    public String profile(){
        // 적용된 proflie 이름들을 리스트로 저장
        List<String> profiles = Arrays.asList(env.getActiveProfiles());

        /* real : 8080 포트
        * real1 : 8081 포트
        * real2 : 8082 포트 */
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");

        // 적용중인 profile이 없다면 "default"를 반환합니다.
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains) // real1은 8081포트, real2는 8082포트
                .findAny()
                .orElse(defaultProfile);
    }

}
