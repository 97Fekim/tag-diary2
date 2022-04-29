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

        QDiary diary = QDiary.diary;
        QWriteUp writeUp = QWriteUp.writeUp;
        QTag tag = QTag.tag;

        JPQLQuery<Diary> jpqlQuery = from(diary);

        jpqlQuery.leftJoin(writeUp).on(writeUp.diary.eq(diary));
        jpqlQuery.leftJoin(tag).on(writeUp.tag.eq(tag));

        JPQLQuery<Diary> query = jpqlQuery.select(diary);

        /* Builder 만들기 시작 */
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(diary.dno.gt(0L));

        /* 검색 조건 작성 시작 */
        if(type != null){

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            switch (type){
                case "diaryTitle":
                    conditionBuilder.or(diary.title.contains(keyword));
                    break;
                case "tagName":
                    conditionBuilder.or(writeUp.tag.tagName.contains(keyword));
                    break;
            }

            booleanBuilder.and(conditionBuilder);
        }

        query.where(booleanBuilder);
        query.where(diary.writer.id.eq(writerId));

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

        List<Diary> result = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<Diary>(
                result,
                pageable,
                count);
    }

}
