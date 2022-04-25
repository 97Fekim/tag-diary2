package com.fekim.tagdiary2.diary.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO {

    private List<DiaryDTO> dtoList;
    private int page;
    private int size;
    private int totalPage;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;

    public PageResultDTO(List<DiaryDTO> result, int totalPage){
        this.dtoList = result;
        this.totalPage = totalPage;
    }

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
