import com.toffee.nuts.bulletinboard.MembersForm;
import com.toffee.nuts.bulletinboard.SessionConstants;
import com.toffee.nuts.bulletinboard.domain.Members;
import com.toffee.nuts.bulletinboard.repository.MembersRepository;
import com.toffee.nuts.bulletinboard.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class LoginController {
    @PostMapping("/login")
    public String login(@ModelAttribute @Validated MembersForm loginForm,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        MembersRepository membersRepository = new MembersRepository();
        LoginService loginService = new LoginService(membersRepository);
        Members loginMember = loginService.login(loginForm.getId(), loginForm.getPw());


        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리
        HttpSession session = request.getSession();                         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
        session.setAttribute(SessionConstants.LOGIN_MEMBER, loginMember);   // 세션에 로그인 회원 정보 보관

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();   // 세션 날림
        }

        return "redirect:/";
    }
}
