package com.fekim.tagdiary2.diary.domain;

import com.fekim.tagdiary2.diary.search.SearchDiaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long>, SearchDiaryRepository {
    
    // 특정 dno 조회
    @Query("select d, w, t " +
            "from Diary d " +
            "left join WriteUp w on w.diary = d " +
            "left join Tag t on w.tag = t " +
            "where d.dno = :dno")
    List<Object[]> getDiaryWithWritesUpAndTags(@Param("dno") Long dno);

    // 특정 회원의 모든 Diary 조회
    @Query("select d " +
            "from Diary d " +
            "where d.writer.id = :writerId")
    Page<Object> getList(Pageable pageable, @Param("writerId") Long writerId);


}
