package com.fekim.tagdiary2.diary.service;

import com.fekim.tagdiary2.diary.domain.Tag;
import com.fekim.tagdiary2.diary.domain.TagRepository;
import com.fekim.tagdiary2.diary.dto.TagDTO;
import com.fekim.tagdiary2.diary.domain.WriteUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final WriteUpRepository writeUpRepository;
    private final TagRepository tagRepository;

    @Override
    public TagDTO getMostPopularTag(String tagType) {

        List<Long> list = writeUpRepository.getTnoListByTagType(tagType);

        log.info("============================list : " + list.toString());

        int targetTno = getTargetTno(list);

        log.info("============================targetTno = " + targetTno);

        Optional<Tag> result = tagRepository.findById((long) targetTno);

        if(result.isPresent()){
            return entityToDTO(result.get());
        }

        return null;
    }

    @Override
    public List<TagDTO> getList() {

        List<Tag> tagList = tagRepository.findAll();

        List<TagDTO> tagDTOList = new ArrayList<>();

        for(Tag tag : tagList){
            tagDTOList.add(entityToDTO(tag));
        }

        return tagDTOList;

    }

    /* 찾는 Tag는 가장 인기 있는 Tag입니다.
     *  아래 메서드로 조회해야 할 Tag의 tno를 찾습니다.
     * */
    public int getTargetTno(List<Long> list){

        Long max = -1L;
        for(Long i : list){
            if(i > max){
                max = i;
            }
        }

        int[] arr = new int[max.intValue()+1];

        for(Long i : list){
            arr[i.intValue()]++;
        }

        for(int i : arr)
            log.info(i);

        int tmpMax = -1;
        int targetTno = -1;
        for(int i=0; i<arr.length; ++i){
            if(arr[i] > tmpMax){
                tmpMax = arr[i];
                targetTno = i;
            }
        }

        return targetTno;
    }
}
