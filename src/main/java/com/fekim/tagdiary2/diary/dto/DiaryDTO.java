package com.fekim.tagdiary2.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDTO {

    private Long dno;

    private String title;

    private Long writerId;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    // 1개의 일기는 여러개의 WriteUp을 가지고 있습니다.
    @Builder.Default
    private List<WriteUpDTO> writeUpDTOList = new ArrayList<>();

    // 생성일, 수정일을 제외한 파라미터를 받는 생성자입니다.
    // 등록페이지에서 DiaryDTO를 생성해야 하기 때문에 이 생성자가 존재합니다.
    // 이 생성자가 없으면 "cannot deserialize from object value"가 발생합니다.
    public DiaryDTO(Long dno, String title, Long writerId, List<WriteUpDTO> writeUpDTOList){
        this.dno = dno;
        this.title = title;
        this.writerId = writerId;
        this.writeUpDTOList = writeUpDTOList;
    }

}
