package com.fekim.tagdiary2.service;//package com.fekim.tagdiary.service;
//
//import com.fekim.tagdiary.tag.dto.TagDTO;
//import com.fekim.tagdiary.tag.service.TagService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//
//@SpringBootTest
//public class TagServiceTests {
//
//    @Autowired
//    private TagService tagService;
//
//    @Transactional
//    @Test
//    public void testGetMod(){
//        TagDTO dto = tagService.getMostPopularTag("emotion");
//
//        System.out.println("-----------------------------tno = " + dto);
//
//    }
//
//    @Test
//    public void testGetList(){
//        for(TagDTO tagDTO : tagService.getList()){
//            System.out.println(tagDTO);
//        }
//    }
//
//}
