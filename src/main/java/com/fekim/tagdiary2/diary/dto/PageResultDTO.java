package com.fekim.tagdiary2.diary.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO {

    private List<DiaryDTO> dtoList; // 페이지의 모든 일기를 List로 가지고 있습니다.

    // pagination
    private int page;   // 몇번째 페이지인지
    private int size;   // 한 페이지에 몇개의 일기를 가져올지
    private int totalPage;  // 총 몇페이지인지
    private int start, end; // 첫페이지와, 끝페이지는 몇인지
    private boolean prev, next; // 이전페이지와 다음페이지가 있는지 없는지
    private List<Integer> pageList; // 모든 페이지 번호를 List로 가지고 있습니다.

    public PageResultDTO(List<DiaryDTO> result, int totalPage){
        this.dtoList = result;
        this.totalPage = totalPage;
    }

    // pagination 정보를 생성합니다.
    public void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnd = (int)Math.ceil(page / 10.0) * 10;
        start = tempEnd - 9;
        end = Math.min(tempEnd, totalPage);

        prev = start > 1;
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

}
