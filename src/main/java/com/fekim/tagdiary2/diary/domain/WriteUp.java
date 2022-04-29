package com.fekim.tagdiary2.diary.domain;

import com.fekim.tagdiary2.BaseEntity;
import com.fekim.tagdiary2.diary.domain.Diary;
import com.fekim.tagdiary2.diary.domain.Tag;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"diary", "tag"})
public class WriteUp extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    private String content;

}
