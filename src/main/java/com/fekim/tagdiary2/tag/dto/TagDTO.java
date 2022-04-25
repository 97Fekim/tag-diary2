package com.fekim.tagdiary2.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {

    private Long tno;
    private String name;
    private String type;
    private String color;

}
