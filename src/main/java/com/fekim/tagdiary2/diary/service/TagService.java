package com.fekim.tagdiary2.diary.service;

import com.fekim.tagdiary2.diary.domain.Tag;
import com.fekim.tagdiary2.diary.dto.TagDTO;

import java.util.List;

public interface TagService {

    TagDTO getMostPopularTag(String tagType);

    List<TagDTO> getList();

    default TagDTO entityToDTO(Tag tag){

        TagDTO tagDTO = TagDTO.builder()
                .tno(tag.getTno())
                .name(tag.getTagName())
                .type(tag.getTagType())
                .color(tag.getTagColor())
                .build();

        return tagDTO;
    }

}
