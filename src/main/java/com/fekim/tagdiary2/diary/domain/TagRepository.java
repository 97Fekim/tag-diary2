package com.fekim.tagdiary2.diary.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    /* 가장 인기 있는 태그를 찾기 위해,
    * List < (tno : 태그의 갯수) > 를 가져옵니다.
    * tno와 태그의 갯수 모두 long 타입이기 때문에 반환 타입은 List<long[]>입니다. */
    @Query("select t.tno, count(wu.wno) " +
            "from Tag t " +
            "left join WriteUp wu " +
            "on t.tno = wu.tag.tno " +
            "where t.tagType= :type " +
            "group by t.tno")
    List<long[]> getListOfTnoAndCountOfWriteUpByTagType(@Param("type")String type);

}
