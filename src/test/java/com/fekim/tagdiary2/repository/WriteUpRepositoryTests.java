package com.fekim.tagdiary2.repository;//package com.fekim.tagdiary.repository;

import com.fekim.tagdiary2.diary.domain.Diary;
import com.fekim.tagdiary2.diary.domain.Tag;
import com.fekim.tagdiary2.diary.domain.WriteUp;
import com.fekim.tagdiary2.diary.domain.WriteUpRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class WriteUpRepositoryTests {

    @Autowired
    private WriteUpRepository repository;

    @Test
    public void testInsertDummies(){

        IntStream.rangeClosed(1,1200).forEach(i -> {

            // 일기장 번호
            Long dno = (long) (Math.random() * 400) + 1;

            // 태그 번호
            Long tno = (long)(Math.random() * 14) + 1;

            Diary diary = Diary.builder().dno(dno).build();

            WriteUp writeUp = WriteUp.builder()
                    .diary(diary)
                    .tag(Tag.builder().tno(tno).build())
                    .content("이 태그에 대한 " + i + "번째 내용이에요")
                    .build();

            repository.save(writeUp);

        });
    }

    //@Transactional
    @Test
    public void testGetTnoListByTagType(){

        List<Long> result = repository.getTnoListByTagType("emotion");

        System.out.println(result.toString());

    }

    @Test
    public void testGetListByDno(){

        List<Object[]> result = repository.getListByDno(195L);

        for(Object[] objects : result){
            System.out.println(Arrays.toString(objects));
        }

    }

    @Transactional
    @Commit
    @Test
    public void testDeleteByWno(){
        repository.deleteByWno(648L);
    }

}
