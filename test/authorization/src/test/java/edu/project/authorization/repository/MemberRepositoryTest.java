package edu.project.authorization.repository;

import edu.project.authorization.domain.MemberRoleVO;
import edu.project.authorization.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertTest() {
        for(int i = 0; i<10; i++) {
            MemberVO memberVO = new MemberVO();
            memberVO.setUserId("user" + i);
            memberVO.setUserPasswd("pw" + i);
            memberVO.setUserEmail("kmk" + i + "@gmail.com");
            MemberRoleVO memberRoleVO = new MemberRoleVO();
            if (i <= 30) {
                memberRoleVO.setRoleName("BASIC");
            } else if (i <= 90) {
                memberRoleVO.setRoleName("MANAGER");
            } else {
                memberRoleVO.setRoleName("ADMIN");
            }
            /*List<MemberRoleVO> list = new ArrayList<>();
            list.add(memberRoleVO);
            memberVO.setMemberRole(list);*/
            memberVO.setMemberRole(Arrays.asList(memberRoleVO));
            log.info("save : " + memberRepository.save(memberVO));
        }
    }

    @Test
    public void testMember() {
//        Optional<MemberVO> result = Optional.ofNullable(memberRepository.findById(50L));
        Optional<MemberVO> result = memberRepository.findById(10L);
        result.ifPresent(memberVO -> {
            log.info("member : " + memberVO);
        });
    }
}
