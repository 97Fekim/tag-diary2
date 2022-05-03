package com.fekim.tagdiary2.service;

import com.fekim.tagdiary2.diary.dto.TagDTO;
import com.fekim.tagdiary2.diary.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class TagServiceTests {

    @Autowired
    private TagService tagService;

    @Test
    public void testGetMod(){
        long beforeTime = System.nanoTime(); //코드 실행 전에 시간 받아오기

        TagDTO dto = tagService.getMostPopularTag("hobby");

        System.out.println("-----------------------------tag = " + dto);

        long afterTime = System.nanoTime(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);

    }

    @Test
    public void testGetList(){
        for(TagDTO tagDTO : tagService.getList()){
            System.out.println(tagDTO);
        }
    }

}
