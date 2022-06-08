package com.fekim.tagdiary2.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {

    private Long tno;
    private String name;
    private String type;    // 태그의 종류입니다 ex) 여가, 감정
    private String color;   // 태그의 색입니다. ex) #0eb2ab3

}
