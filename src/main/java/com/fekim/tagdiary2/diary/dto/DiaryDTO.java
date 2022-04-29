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

    @Builder.Default
    private List<WriteUpDTO> writeUpDTOList = new ArrayList<>();

    public DiaryDTO(Long dno, String title, Long writerId, List<WriteUpDTO> writeUpDTOList){
        this.dno = dno;
        this.title = title;
        this.writerId = writerId;
        this.writeUpDTOList = writeUpDTOList;
    }

}
