package com.fekim.tagdiary2.repository;

import com.fekim.tagdiary2.diary.domain.Tag;
import com.fekim.tagdiary2.diary.domain.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class TagRepositoryTests {

    @Autowired
    private TagRepository repository;


    @Test
    public void testInsertDummies(){

//        repository.save(Tag.builder().tagName("기쁨").tagType("emotion").tagColor("#85efea").build());
//        repository.save(Tag.builder().tagName("억울").tagType("emotion").tagColor("#73dd77").build());
//        repository.save(Tag.builder().tagName("뿌듯").tagType("emotion").tagColor("#af98ec").build());
//        repository.save(Tag.builder().tagName("신남").tagType("emotion").tagColor("#e498a2").build());
//        repository.save(Tag.builder().tagName("열정").tagType("emotion").tagColor("#7ccb91").build());
//        repository.save(Tag.builder().tagName("기대").tagType("emotion").tagColor("#827ecb").build());
//        repository.save(Tag.builder().tagName("열망").tagType("emotion").tagColor("#cb91bf").build());
//        repository.save(Tag.builder().tagName("상쾌").tagType("emotion").tagColor("#cbac9f").build());
//        repository.save(Tag.builder().tagName("따분").tagType("emotion").tagColor("#accba4").build());
//        repository.save(Tag.builder().tagName("초조").tagType("emotion").tagColor("#5a66dd").build());

//        repository.save(Tag.builder().tagName("뭉클").tagType("emotion").tagColor("#dd332c").build());
//        repository.save(Tag.builder().tagName("후련").tagType("emotion").tagColor("#364085").build());
//        repository.save(Tag.builder().tagName("쓸쓸").tagType("emotion").tagColor("#3d8538").build());
//        repository.save(Tag.builder().tagName("원망").tagType("emotion").tagColor("#852e23").build());
//        repository.save(Tag.builder().tagName("허전").tagType("emotion").tagColor("#737372").build());
//        repository.save(Tag.builder().tagName("안타까움").tagType("emotion").tagColor("#323a73").build());
//        repository.save(Tag.builder().tagName("벅참").tagType("emotion").tagColor("#247326").build());
//        repository.save(Tag.builder().tagName("홀가분").tagType("emotion").tagColor("#732420").build());


//        repository.save(Tag.builder().tagName("허탈").tagType("emotion").tagColor("#364490").build());
//        repository.save(Tag.builder().tagName("막막").tagType("emotion").tagColor("#901e16").build());
//        repository.save(Tag.builder().tagName("울적").tagType("emotion").tagColor("#a53f1b").build());
//        repository.save(Tag.builder().tagName("후회").tagType("emotion").tagColor("#af3523").build());
//        repository.save(Tag.builder().tagName("허무").tagType("emotion").tagColor("#ea2d25").build());

//        repository.save(Tag.builder().tagName("공허").tagType("emotion").tagColor("#eac4a8").build());
//        repository.save(Tag.builder().tagName("절망").tagType("emotion").tagColor("#ea7539").build());
//        repository.save(Tag.builder().tagName("불안").tagType("emotion").tagColor("#4aea5d").build());
//        repository.save(Tag.builder().tagName("무기력").tagType("emotion").tagColor("#5c6bea").build());
//        repository.save(Tag.builder().tagName("짜증").tagType("emotion").tagColor("#179030").build());
//        repository.save(Tag.builder().tagName("외로움").tagType("emotion").tagColor("#6a1a21").build());

//        repository.save(Tag.builder().tagName("운동").tagType("hobby").tagColor("#eac4a8").build());
//        repository.save(Tag.builder().tagName("스포츠").tagType("hobby").tagColor("#ea7539").build());
//        repository.save(Tag.builder().tagName("요리").tagType("hobby").tagColor("#4aea5d").build());
        repository.save(Tag.builder().tagName("돈돈").tagType("hobby").tagColor("#0dcaf0").build());
        repository.save(Tag.builder().tagName("나나").tagType("hobby").tagColor("#0085A1").build());
        repository.save(Tag.builder().tagName("뿌뿌").tagType("hobby").tagColor("#0d6efd").build());
//        repository.save(Tag.builder().tagName("모임").tagType("hobby").tagColor("#accba4").build());

    }

    @Test
    public void getTnoAndCount(){

        List<long[]> result = repository.getListOfTnoAndCountOfWriteUpByTagType("emotion");
        for(long[] out : result){
            System.out.println("tno : " + out[0] + " count : " + out[1]);
        }
    }

}
