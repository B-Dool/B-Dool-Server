package com.bdool.memberhubservice.member.domain.profile.entity.model;

import com.bdool.memberhubservice.member.domain.member.entity.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class ProfileModel {

    private String name; // 이름
    private String nickname; // 별명
    private String position; // 직책
    private String status; // 상태메세지
    private String profilePictureUrl; // 프로필 이미지 URL
    private Boolean isOnline; // 온라인/오프라인 표시
    private Boolean isWorkspaceCreator;
    private Long memberId;
    private String email;
    private Long workspaceId;
}