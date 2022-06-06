package com.fekim.tagdiary2.diary.search;

import com.fekim.tagdiary2.diary.domain.Diary;
import com.fekim.tagdiary2.diary.domain.QDiary;
import com.fekim.tagdiary2.diary.domain.QTag;
import com.fekim.tagdiary2.diary.domain.QWriteUp;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class SearchDiaryRepositoryImpl extends QuerydslRepositorySupport implements SearchDiaryRepository {

    // QuerudslRepositorySupport에 생성자가 존재하기 때문에 super를 호출해야 한다.
    public SearchDiaryRepositoryImpl(){
        super(Diary.class);
    }

    @Override
    public Page<Diary> searchPage(String type, String keyword, Long writerId, Pageable pageable) {
        log.info("searchPage.......................");

        // queryDsl을 이용하여 동적으로 쿼리를 생성합니다.
        // 검색조건이 다양해질때, Repository 에서 일일이 쿼리를 만들기 않아도 되기 때문에 유용합니다.
        QDiary diary = QDiary.diary;
        QWriteUp writeUp = QWriteUp.writeUp;
        QTag tag = QTag.tag;

        // from diary d
        JPQLQuery<Diary> jpqlQuery = from(diary);

        // LEFT JOIN writeUp w on w.dno = d.dno 
        jpqlQuery.leftJoin(writeUp).on(writeUp.diary.eq(diary));
        // LEFT JOIN tag t on w.tno = t.tno
        jpqlQuery.leftJoin(tag).on(writeUp.tag.eq(tag));

        
        /* 최종 쿼리
        * SELECT * FROM diary d 
        * LEFT JOIN writeUp w on w.dno = d.dno
        * LEFT JOIN tag t on w.tno = t.tno
        * WHERE 절은 뒤에서 추가
        * */
        JPQLQuery<Diary> query = jpqlQuery.select(diary);

        /* Builder 만들기 시작 */
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // WHERE d.dno > 0
        booleanBuilder.and(diary.dno.gt(0L));

        /* 검색 조건 작성 시작 */
        if(type != null){

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            switch (type){
                 
                case "diaryTitle": // 제목으로 검색
                    conditionBuilder.or(diary.title.contains(keyword));
                    break;  // or d.title like '%keyword%'
                case "tagName": // 태그명으로 검색
                    conditionBuilder.or(writeUp.tag.tagName.contains(keyword));
                    break;  // or w.tag.tag_name like '%keyword%'
            }

            booleanBuilder.and(conditionBuilder);
        }

        query.where(booleanBuilder);

        // 해당 유저의 일기만 가져와야 함.
        // where d.member.mno = writerId
        query.where(diary.writer.id.eq(writerId));

        // 여기서부터 정렬 처리
        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {

            Order direction = order.isAscending() ? Order.ASC : Order.DESC;

            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Diary.class, "diary");

            query.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));

        });

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        query.groupBy(diary);
        // 여기까지 정렬 처리

        List<Diary> result = query.fetch();

        long count = query.fetchCount();


        return new PageImpl<Diary>(
                result,
                pageable,
                count);
    }

}
