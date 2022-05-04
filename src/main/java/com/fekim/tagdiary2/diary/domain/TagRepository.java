package com.fekim.tagdiary2.diary.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select t.tno, count(wu.wno) " +
            "from Tag t " +
            "left join WriteUp wu " +
            "on t.tno = wu.tag.tno " +
            "where t.tagType= :type " +
            "group by t.tno")
    List<long[]> getListOfTnoAndCountOfWriteUpByTagType(@Param("type")String type);

}
