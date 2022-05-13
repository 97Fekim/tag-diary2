package com.fekim.tagdiary2.diary.domain;

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

    /* 추후에 태그의 타입은 계층형 카테고리 테이블로 변경합니다. */
    String tagType;

    @Column(unique = true)
    String tagColor;

}
