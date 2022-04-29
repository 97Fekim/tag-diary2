package com.fekim.tagdiary2.diary.service;

import com.fekim.tagdiary2.diary.domain.Diary;
import com.fekim.tagdiary2.diary.domain.DiaryRepository;
import com.fekim.tagdiary2.diary.dto.DiaryDTO;
import com.fekim.tagdiary2.diary.dto.PageRequestDTO;
import com.fekim.tagdiary2.diary.dto.PageResultDTO;
import com.fekim.tagdiary2.diary.domain.Tag;
import com.fekim.tagdiary2.diary.dto.TagDTO;
import com.fekim.tagdiary2.diary.domain.WriteUp;
import com.fekim.tagdiary2.diary.domain.WriteUpRepository;
import com.fekim.tagdiary2.diary.dto.WriteUpDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Log4j2
@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService{

    /* Diary를 저장하기 위해서는 모든 Repository를 주입받아야 함 */
    private final DiaryRepository diaryRepository;

    private final WriteUpRepository writeUpRepository;

    @Override
    public Long register(DiaryDTO diaryDTO) {
        Map<String, Object> entityMap = dtoToEntity(diaryDTO);

        Diary diary = (Diary) entityMap.get("diary");   // Object -> Diary

        Diary saved = diaryRepository.save(diary);// Diary 먼저 저장

        /* Diary를 저장하면서 얻어온 dno를 diaryDTO의 dno에 저장 */
        diaryDTO.setDno(saved.getDno());

        // 다시 dto->entity
        entityMap = dtoToEntity(diaryDTO);

        List<WriteUp> writeUpList = (List<WriteUp>) entityMap.get("writeUpList");   // Object -> List


        writeUpList.stream().forEach(writeUp -> {
            log.info("==============dno : " + writeUp.getDiary().getDno());
            log.info("==============tno : " + writeUp.getTag().getTno());
            writeUpRepository.save(writeUp);
        });

        return diary.getDno();
    }

    @Transactional
    @Override   // Diary 하나 삭제
    public void removeDiaryWithWriteUps(Long dno) {

        /* 쿼리 메서드로 작성 */
        // WriteUp들 부터 삭제
        writeUpRepository.deleteByDno(dno);
        // Diary 삭제
        diaryRepository.deleteById(dno);
    }

    @Override
    public DiaryDTO read(Long dno) {
        return entityToDTO(diaryRepository.getDiaryWithWritesUpAndTags(dno));
    }

    @Override
    public PageResultDTO getListPage(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("dno").descending());

        List<DiaryDTO> diaryDTOList = new ArrayList<>();

        Page<Diary> result = diaryRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getWriterId(),
                pageable
        );

        int totalPages = result.getTotalPages();

        for(Diary diary : result){

            DiaryDTO diaryDTO = new DiaryDTO();

            diaryDTO.setDno(diary.getDno());
            diaryDTO.setTitle(diary.getTitle());
            diaryDTO.setWriterId(pageRequestDTO.getWriterId());
            diaryDTO.setModDate(diary.getModDate());
            diaryDTO.setRegDate(diary.getRegDate());

            // 여기서부터 WriteUpList 추가
            List<Object[]> writeUpList = writeUpRepository.getListByDno(diary.getDno());

            List<WriteUpDTO> writeUpDTOList = new ArrayList<>();

            // object[0] : WriteUp
            // object[1] : Tag
            for(Object[] objects : writeUpList){
                WriteUp writeUp = (WriteUp)objects[0];
                Tag tag = (Tag) objects[1];

                WriteUpDTO writeUpDTO = WriteUpDTO.builder()
                        .wno(writeUp.getWno())
                        .dno(diary.getDno())
                        .content(writeUp.getContent())
                        .tagDTO(TagDTO.builder()
                                .tno(tag.getTno())
                                .name(tag.getTagName())
                                .type(tag.getTagType())
                                .color(tag.getTagColor())
                                .build())
                        .build();

                writeUpDTOList.add(writeUpDTO);
            }

            diaryDTO.setWriteUpDTOList(writeUpDTOList);

            diaryDTOList.add(diaryDTO);

        }

        PageResultDTO pageResultDTO = new PageResultDTO(diaryDTOList, totalPages);

        pageResultDTO.makePageList(pageable);

        return pageResultDTO;

    }

    @Override
    @Transactional
    public void modify(DiaryDTO diaryDTO) {

        // DTO -> Entity
        Diary diary = (Diary) dtoToEntity(diaryDTO).get("diary");
        List<WriteUp> writeUpList = (List<WriteUp>) dtoToEntity(diaryDTO).get("writeUpList");

        // 1. dno를 참조하는 모든 WriteUp을 삭제한다
        writeUpRepository.deleteByDno(diary.getDno());

        log.info("=================diary delete completely===========");

        // 2. Diary의 제목을 바꾼 후, 수정(save)한다.
        Optional<Diary> origin = diaryRepository.findById(diary.getDno());

        if(origin.isPresent()){

            Diary modified = origin.get();

            modified.changeTitle(diary.getTitle());

            log.info("========================dno of diary : " + modified.getDno());

            diaryRepository.save(modified);

            log.info("=================diary modified completely===========");
        }

        // 3. DiaryDTO에 들어있는 모든 WriteUp을 저장한다.
        for(WriteUp writeUp : writeUpList){

            log.info("========================dno of writeUp : " + writeUp.getDiary().getDno());
            log.info("========================tno of writeUp : " + writeUp.getTag().getTno());

            writeUpRepository.save(writeUp);

        }
        log.info("====================save writeUp completely===============");

    }

}
