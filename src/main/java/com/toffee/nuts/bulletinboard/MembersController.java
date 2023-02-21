package com.toffee.nuts.bulletinboard;

import com.toffee.nuts.bulletinboard.domain.Members;
import com.toffee.nuts.bulletinboard.service.MembersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MembersController {

    private final MembersService membersService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        log.debug("whatwhatwhatwhatwhatwhatwhat");
        model.addAttribute("membersForm", new MembersForm());
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MembersForm membersForm) {
        Members member = new Members();

        membersService.join(member);
        return "redirect:/";
    }

}
