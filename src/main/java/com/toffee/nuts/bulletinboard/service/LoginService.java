package com.toffee.nuts.bulletinboard.service;

import com.toffee.nuts.bulletinboard.domain.Members;
import com.toffee.nuts.bulletinboard.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MembersRepository memberRepository;

    /**
     * @return null이면 로그인 실패
     */
    public Members login(String loginId, String password) {
        Members memb = memberRepository.findOne(Long.valueOf(loginId));
        return memb.getPw().equals(password) ? memb : null;
    }
}