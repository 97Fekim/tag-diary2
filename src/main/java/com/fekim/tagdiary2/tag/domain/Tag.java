package com.fekim.tagdiary2.tag.domain;

import com.fekim.tagdiary2.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    String tagName;

    String tagType;

    @Column(unique = true)
    String tagColor;

}
