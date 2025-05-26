package com.example.bcsd.service;

import com.example.bcsd.dao.ArticleDao;
import com.example.bcsd.dao.MemberDao;
import com.example.bcsd.exception.CustomException;
import com.example.bcsd.exception.ErrorCode;
import com.example.bcsd.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final ArticleDao articleDao;
    private final MemberDao memberDao;

    @Autowired
    public MemberService(ArticleDao articleDao, MemberDao memberDao) {
        this.articleDao = articleDao;
        this.memberDao = memberDao;
    }

    public void deleteMember(Long memberId) {
        Member member = memberDao.getMember(memberId);
        if (member == null) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }

        if (articleDao.isExistArticleByAuthorId(memberId)) {
            throw new CustomException(ErrorCode.MEMBER_HAS_EXISTING_ARTICLES);
        }

        memberDao.deleteMember(memberId);
    }
}
