package com.fekim.tagdiary2.controller;

import com.fekim.tagdiary2.diary.dto.DiaryDTO;
import com.fekim.tagdiary2.diary.dto.PageRequestDTO;
import com.fekim.tagdiary2.diary.dto.PageResultDTO;
import com.fekim.tagdiary2.diary.service.DiaryService;
import com.fekim.tagdiary2.security.dto.AuthMemberDTO;
import com.fekim.tagdiary2.tag.dto.TagDTO;
import com.fekim.tagdiary2.tag.service.TagService;
import com.fekim.tagdiary2.writeup.dto.WriteUpDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/diarys")
@PreAuthorize("#authMemberDTO != null") // 사용자의 DTO가 있을때만 가능한 요청
public class DiaryController {

    private final DiaryService diaryService;

    private final TagService tagService;

    @GetMapping("/register")
    public void registerGet(@AuthenticationPrincipal AuthMemberDTO authMemberDTO,
                            Model model){

        model.addAttribute("tagDTOList",tagService.getList());
    }

    @PostMapping("/register")
    public String registerPost(@AuthenticationPrincipal AuthMemberDTO authMemberDTO,
                               @RequestBody DiaryDTO diaryDTO,
                               RedirectAttributes redirectAttributes){
        log.info("==================register post");
        log.info("==================diaryDTO : " + diaryDTO);
        log.info("==================auth info : " + authMemberDTO);


        diaryDTO.setWriterId(authMemberDTO.getId());

        Long dno = diaryService.register(diaryDTO);

        redirectAttributes.addFlashAttribute("msg", dno);

        return "redirect:/diarys/list";

    }

    @GetMapping("/list")
    public void list(@AuthenticationPrincipal AuthMemberDTO authMemberDTO,
                     @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                     Model model){

        pageRequestDTO.setWriterId(authMemberDTO.getId());

        PageResultDTO pageResultDTO = diaryService.getListPage(pageRequestDTO);

        model.addAttribute("result", pageResultDTO);

        log.info(authMemberDTO);
    }

    @GetMapping("/read")
    public String read(@AuthenticationPrincipal AuthMemberDTO authMemberDTO,
                     @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                     Long dno,
                     Model model){

        log.info("dno : "+dno);
        
        DiaryDTO diaryDTO = diaryService.read(dno);

        /* 다른 회원의 게시글을 조회하려 접근하면, list로 redirect시킵니다. */
        if(!diaryDTO.getWriterId().equals(authMemberDTO.getId())) {
            return "redirect:/diarys/list";
        } else {
            model.addAttribute("diaryDTO", diaryDTO);
            return "/diarys/read";
        }

    }

    @GetMapping("/modify")
    public String modifyGet(@AuthenticationPrincipal AuthMemberDTO authMemberDTO,
                       @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                       Long dno,
                       Model model){

        log.info("dno : "+dno);

        DiaryDTO diaryDTO = diaryService.read(dno);
        List<TagDTO> tagDTOList = tagService.getList();
        log.info("===============listing completed=============");

        /* 중복 태그 지우기 */
        for(WriteUpDTO writeUpDTO : diaryDTO.getWriteUpDTOList()){
            int i = 0;
            for(TagDTO tagDTO : tagDTOList){
                if(writeUpDTO.getTagDTO().getTno().equals(tagDTO.getTno())){
                    log.info("============i : "+i);
                    tagDTOList.remove(i);
                    break;
                } else {
                    i++;
                }
            }
        }

        /* 다른 회원의 게시글을 수정하려 접근하면, list로 redirect시킵니다. */
        if(!diaryDTO.getWriterId().equals(authMemberDTO.getId())) {
            return "redirect:/diarys/list";
        } else {
            model.addAttribute("tags", tagDTOList);
            model.addAttribute("diaryDTO", diaryDTO);
            return "/diarys/modify";
        }
    }

    @PostMapping("/modify")
    public String modify(@AuthenticationPrincipal AuthMemberDTO authMemberDTO,
                         @RequestBody DiaryDTO diaryDTO,
                         @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes){

        diaryDTO.setWriterId(authMemberDTO.getId());

        diaryService.modify(diaryDTO);

        // 수정 후에 원래 있던 페이지로 가기 위해 request를 저장
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("dno", diaryDTO.getDno());

        return "redirect:/diarys/list";

    }

    @PostMapping("/remove")
    public String remove(@AuthenticationPrincipal AuthMemberDTO authMemberDTO,
                         Long dno,
                         RedirectAttributes redirectAttributes) {

        diaryService.removeDiaryWithWriteUps(dno);

        redirectAttributes.addFlashAttribute("msg", dno);

        return "redirect:/diarys/list";

    }

}
