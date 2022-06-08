package com.fekim.tagdiary2.diary.dto;

import com.fekim.tagdiary2.diary.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WriteUpDTO {

    private Long wno;
    private Long dno;   // 몇번째 일기에 존재하는 WriteUp인지 나타냅니다
    private String content; // 내용은 1개의 Tag와 같이 쓰여집니다.
    private TagDTO tagDTO;  // 1개의 WriteUp은 1개의 Tag를 가지고 있습니다.

}
