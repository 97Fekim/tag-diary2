package com.fekim.tagdiary2.tag.service;

import com.fekim.tagdiary2.tag.domain.Tag;
import com.fekim.tagdiary2.tag.dto.TagDTO;

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
