package com.fekim.tagdiary2.service;//package com.fekim.tagdiary.service;
//
//import com.fekim.tagdiary.diary.dto.DiaryDTO;
//import com.fekim.tagdiary.diary.dto.PageRequestDTO;
//import com.fekim.tagdiary.diary.dto.PageResultDTO;
//import com.fekim.tagdiary.diary.service.DiaryService;
//import com.fekim.tagdiary.tag.dto.TagDTO;
//import com.fekim.tagdiary.writeup.dto.WriteUpDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//public class DiaryServiceTests {
//
//    @Autowired
//    private DiaryService diaryService;
//
//    @Test
//    public void testRegister(){
//
//        List<WriteUpDTO> writeUpDTOList = new ArrayList<>();
//
//        writeUpDTOList.add(WriteUpDTO.builder()
//                .content("등록 테스트 내용7")
//                .tagDTO(TagDTO.builder().tno(5L).build())
//                .build());
//
//        writeUpDTOList.add(WriteUpDTO.builder()
//                .content("등록 테스트 내용8")
//                .tagDTO(TagDTO.builder().tno(6L).build())
//                .build());
//
//        DiaryDTO diaryDTO = DiaryDTO.builder()
//                .title("등록 테스트 제목4")
//                .writerId(1L)
//                .writeUpDTOList(writeUpDTOList)
//                .build();
//
//        diaryService.register(diaryDTO);
//
//    }
//
//    @Test
//    public void testRemoveDiary(){
//        diaryService.removeDiaryWithWriteUps(401L);
//    }
//
//    @Test
//    public void testRead(){
//        DiaryDTO dto = diaryService.read(195L);
//        System.out.println(dto.getDno());
//        System.out.println(dto.getTitle());
//        System.out.println(dto.getWriterId());
//        System.out.println(dto.getModDate());
//        System.out.println(dto.getRegDate());
//
//        for(WriteUpDTO writeUpDTO : dto.getWriteUpDTOList()){
//            System.out.println(writeUpDTO);
//        }
//
//    }
//
//    @Test
//    public void testGetList(){
//
//        PageRequestDTO pageRequestDTO = new PageRequestDTO();
//        pageRequestDTO.setWriterId(1L);
//
//        PageResultDTO pageResultDTO = diaryService.getListPage(pageRequestDTO);
//
//        for(DiaryDTO diaryDTO : pageResultDTO.getDtoList()){
//            System.out.println(diaryDTO);
//        }
//        System.out.println(pageResultDTO);
//
//    }
//
//    @Test
//    public void testModify(){
//        List<WriteUpDTO> writeUpDTOList = new ArrayList<>();
//
//        writeUpDTOList.add(WriteUpDTO.builder()
//                .content("수정 테스트 내용1")
//                .tagDTO(TagDTO.builder().tno(5L).build())
//                .build());
//
//        writeUpDTOList.add(WriteUpDTO.builder()
//                .content("수정 테스트 내용2")
//                .tagDTO(TagDTO.builder().tno(6L).build())
//                .build());
//
//        writeUpDTOList.add(WriteUpDTO.builder()
//                .content("수정 테스트 내용3")
//                .tagDTO(TagDTO.builder().tno(12L).build())
//                .build());
//
//        DiaryDTO diaryDTO = DiaryDTO.builder()
//                .dno(150L)
//                .title("수정 테스트 제목1")
//                .writerId(1L)
//                .writeUpDTOList(writeUpDTOList)
//                .build();
//
//        diaryService.modify(diaryDTO);
//
//
//    }
//
//}