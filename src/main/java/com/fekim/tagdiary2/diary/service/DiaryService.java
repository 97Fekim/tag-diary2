package com.fekim.tagdiary2.diary.service;

import com.fekim.tagdiary2.diary.domain.Diary;
import com.fekim.tagdiary2.diary.dto.DiaryDTO;
import com.fekim.tagdiary2.diary.dto.PageRequestDTO;
import com.fekim.tagdiary2.diary.dto.PageResultDTO;
import com.fekim.tagdiary2.diary.domain.Member;
import com.fekim.tagdiary2.diary.domain.Tag;
import com.fekim.tagdiary2.diary.dto.TagDTO;
import com.fekim.tagdiary2.diary.domain.WriteUp;
import com.fekim.tagdiary2.diary.dto.WriteUpDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface DiaryService {

    /* 일기 등록 */
    Long register(DiaryDTO diaryDTO);
    /* 일기 삭제 */
    void removeDiaryWithWriteUps(Long dno);
    /* 일기 조회 */
    DiaryDTO read(Long dno);
    /* 일기 리스트 조회 */
    PageResultDTO getListPage(PageRequestDTO pageRequestDTO);
    /* 일기 수정 */
    void modify(DiaryDTO diaryDTO);

    /* DiaryDTO -> Diary
     * DiaryDTO가 WriteUp을 List로 참조하고 있기때문에
     * 반환타입을 Map<String, Object>으로 지정 */
    default Map<String, Object> dtoToEntity(DiaryDTO diaryDTO){

        Map<String, Object> entityMap = new HashMap<>();

        /* Diary 처리 (엔티티 Diary생성) */
        Diary diary = Diary.builder()
                .dno(diaryDTO.getDno())
                .title(diaryDTO.getTitle())
                .writer(Member.builder()
                        .id(diaryDTO.getWriterId())
                        .build())
                .build();

        entityMap.put("diary", diary);

        /* List<WriteUp> 처리 */
        List<WriteUpDTO> writeUpDTOList = diaryDTO.getWriteUpDTOList();

        if(writeUpDTOList != null && writeUpDTOList.size() > 0){    // List가 존재하면(일기에 태그와 내용이 있으면)

            /* 엔티티 WriteUp 리스트 생성 */
            List<WriteUp> writeUpList = writeUpDTOList.stream().map(writeUpDTO -> {

                WriteUp writeUp = WriteUp.builder()
                        .content(writeUpDTO.getContent())
                        .diary(Diary.builder().dno(diaryDTO.getDno()).build())  // writeUp에 추가될 dno는 Diary에서 가져와야 한다.
                        .tag(Tag.builder()
                                .tno(writeUpDTO.getTagDTO().getTno())
                                .tagName(writeUpDTO.getTagDTO().getName())
                                .tagType(writeUpDTO.getTagDTO().getType())
                                .build())
                        .build();

                return writeUp;

            }).collect(Collectors.toList());

            entityMap.put("writeUpList", writeUpList);
        }

        return entityMap;
    }

    /* 조회에 필요한 DiaryDTO로 변환하는 메서드
    *  object[0] = Diary
    *  object[1] = WriteUp
    *  object[2] = Tag
    * */
    default DiaryDTO entityToDTO(List<Object[]> entity){

        DiaryDTO diaryDTO = new DiaryDTO();

        try{

            // entity.size()가 0일 경우,
            // 즉 조회된 엔티티가 없을 경우 예외를 발생합니다 
            Diary diary = (Diary) entity.get(entity.size() - 1)[0];
            diaryDTO.setDno(diary.getDno());
            diaryDTO.setTitle(diary.getTitle());
            diaryDTO.setWriterId(diary.getWriter().getId());
            diaryDTO.setRegDate(diary.getRegDate());
            diaryDTO.setModDate(diary.getModDate());

        } catch (NullPointerException e) {

            e.printStackTrace();
            System.out.println("조회한 Diary는 존재하지 않습니다.");

        }

        for(Object[] objects : entity){

            WriteUp writeUp = (WriteUp) objects[1];
            Tag tag = (Tag) objects[2];

            TagDTO tagDTO = TagDTO.builder()
                    .tno(tag.getTno())
                    .name(tag.getTagName())
                    .type(tag.getTagType())
                    .color(tag.getTagColor())
                    .build();

            WriteUpDTO writeUpDTO = WriteUpDTO.builder()
                    .wno(writeUp.getWno())
                    .dno(diaryDTO.getDno())
                    .content(writeUp.getContent())
                    .tagDTO(tagDTO)
                    .build();

            diaryDTO.getWriteUpDTOList().add(writeUpDTO);

        }

        return diaryDTO;

    }
}
