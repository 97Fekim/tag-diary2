package com.fekim.tagdiary2.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;    // 몇번째 페이지를 가져올건지
    private int size;    // 한번에 몇 개의 페이지를 가져올건지
    private String type;    // 어떤 종류로 검색했는지
    private String keyword; // 검색명은 무엇인지
    private Long writerId;  // 현재 로그인중인 회원의 ID(primary key)

    // 기본생성자
    public PageRequestDTO(){
        this.page = 1;  // default로 1페이지
        this.size = 10; // 1페이지에 10개씩
    }

    // PageRequestDTO 는 가져올 페이지에 대한 설정정보를 담고 있다.
    // 이 getPage 메소드는, 그 설정정보를 바탕으로 페이지를 가져오는 기준 Pageable을 설정해서 가져온다.
    public Pageable getPageable(Sort sort){
        return PageRequest.of(page - 1 , size, sort);
    }
}