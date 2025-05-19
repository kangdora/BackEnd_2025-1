package com.example.bcsd.repository;

import com.example.bcsd.model.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberRepository {
    private final List<Member> members = new ArrayList<>();
    private static final Long firstId = 1L;

    public List<Member> getMembers() {
        return members;
    }

    public Member getMember(Long id) {
        return members.stream()
                .filter(member -> member.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void testMemberRepository() {
        members.add(new Member(
                1L,
                "kang",
                "ioprt123@koreatech.ac.kr",
                "password"
        ));
    }
}
