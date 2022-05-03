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

    private final TagRepository tagRepository;

    @Override
    public TagDTO getMostPopularTag(String tagType) {

        List<long[]> list = tagRepository.getListOfTnoAndCountOfWriteUpByTagType(tagType);

        long max = -1;
        long maxTno = 0;

        for(long[] pair : list){
            if(pair[1] > max){
                max = pair[1];
                maxTno = pair[0];
            }
        }
        log.info("============================maxTno = " + maxTno);

        Optional<Tag> targetTag = tagRepository.findById(maxTno);

        if(targetTag.isPresent()){
            return entityToDTO(targetTag.get());
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

}
