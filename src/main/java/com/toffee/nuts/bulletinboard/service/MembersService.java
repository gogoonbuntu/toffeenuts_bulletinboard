package com.toffee.nuts.bulletinboard.service;


import com.toffee.nuts.bulletinboard.domain.Members;
import com.toffee.nuts.bulletinboard.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MembersService {

    @Autowired
    private final MembersRepository membersRepository;

    /*
     * 회원 가입
     */
    @Transactional
    public Long join(Members member) {
        validateDuplicateMember(member);
        membersRepository.save(member);
        return member.getId();
    }

    public void save(Members member) {
        log.info(member.getPw());
        membersRepository.save(member);
    }

    public void delete(Long id) {
        membersRepository.delete(id);
    }

    private void validateDuplicateMember(Members member) {
        //추후 동시 회원가입일 시 중복될 가능성이 있기에, name 유니크 하도록 설정해야함.
        List<Members> findMembers = membersRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재함");
        }
    }

    public List<Members> findMembers() {
        return membersRepository.findAll();
    }

    public Members findOne(Long memberId) {
        return membersRepository.findOne(memberId);
    }
}
