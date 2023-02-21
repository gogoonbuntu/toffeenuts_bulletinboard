package com.toffee.nuts.bulletinboard;

import com.toffee.nuts.bulletinboard.domain.Address;
import com.toffee.nuts.bulletinboard.domain.Members;
import com.toffee.nuts.bulletinboard.service.MembersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MembersController {

    private final MembersService membersService;

    @GetMapping("/")
    public String list(Model model) {
        List<Members> members = membersService.findMembers();
        model.addAttribute("members", members);
        return "index";
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {
        log.debug("whatwhatwhatwhatwhatwhatwhat");
        model.addAttribute("membersForm", new MembersForm());
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MembersForm membersForm) {

        Members member = new Members();

        member.setName(membersForm.getId());
        member.setPw(membersForm.getPw());
        membersService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id){
        log.info("hey", id);
        membersService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/members/{id}/edit")
    public String edit(@ModelAttribute("form") MembersForm form, MembersForm membersForm) {

        Members member = new Members();
        member.setName(form.getId());
        member.setPw(form.getPw());
        membersService.save(member);
        log.info("form pw:" + form.getPw());
        log.info("mem pw:" + member.getPw());
        return "redirect:/";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Members mem = membersService.findOne(id);

        MembersForm form = new MembersForm();
        form.setId(mem.getName());
        form.setPw(mem.getPw());

        model.addAttribute("form", form);
        return "/members/editMemberForm";
    }

}
