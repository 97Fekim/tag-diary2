package com.fekim.tagdiary2.diary.service;

import com.fekim.tagdiary2.diary.domain.Tag;
import com.fekim.tagdiary2.diary.dto.TagDTO;

import java.util.List;

public interface TagService {

    /* 홈페이지에서 사용할 "가장 인기있는 태그"를 가져옵니다.
     * 파라미터로 태그의 키워드(일상, 감정 등)을 받습니다.
     * */
    TagDTO getMostPopularTag(String tagType);

    /* 등록, 수정 페이지에서 사용할 태그 리스트를 가져옵니다 */
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
