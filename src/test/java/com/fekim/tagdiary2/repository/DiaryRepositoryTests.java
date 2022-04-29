package com.fekim.tagdiary2.repository;//package com.fekim.tagdiary.repository;

//@SpringBootTest
//public class DiaryRepositoryTests {
//
//    @Autowired
//    private DiaryRepository repository;
//
//    @Test
//    public void testInsertDummies(){
//
//        IntStream.rangeClosed(1,300).forEach(i -> {
//            int month = 1 + i / 30;
//            int day = 1 + i % 30;
//
//            Diary diary1 = Diary.builder()
//                    .writer(Member.builder().id(1L).build())
//                    .title(month + "월 " + day + "일")
//                    .build();
//
//            repository.save(diary1);
//        });
//
//        IntStream.rangeClosed(1,200).forEach(i -> {
//            int month = 1 + i / 30;
//            int day = 1 + i % 30;
//
//            Diary diary1 = Diary.builder()
//                    .writer(Member.builder().id(2L).build())
//                    .title(month + "월 " + day + "일")
//                    .build();
//
//            repository.save(diary1);
//        });
//
//    }
//
//    @Test
//    public void testInsertDummiesFail() {
//        Diary diary1 = Diary.builder()
//                .writer(Member.builder().id(3L).build())
//                .title("1월 1일")
//                .build();
//
//        repository.save(diary1);
//    }
//
//    @Transactional
//    @Test
//    public void testGetList(){
//
//        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "dno"));
//
//        Page<Object> result = repository.getList(pageRequest,1L);
//
//        for(Object arr : result){
//            System.out.println(arr.toString());
//        }
//    }
//
//    @Transactional
//    @Test
//    public void testGetDiaryWithWritesUpAndTags(){
//        List<Object[]> result = repository.getDiaryWithWritesUpAndTags(195L);
//
//        for(Object[] objects : result){
//            System.out.println(Arrays.toString(objects));
//        }
//    }
//
//    @Test
//    public void testSearchPage(){
//
//        Pageable pageable = PageRequest.of(0,10,
//                Sort.by("dno").descending());
//
//        Page<Diary> result = repository.searchPage("diaryTitle", "25",1L, pageable);
//
//        for (Diary diary : result.toList()){
//            System.out.println(diary);
//        }
//
//    }
//
//}
