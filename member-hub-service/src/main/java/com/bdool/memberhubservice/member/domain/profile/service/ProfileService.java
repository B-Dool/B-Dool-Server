package com.bdool.memberhubservice.member.domain.profile.service;


import com.bdool.memberhubservice.member.domain.profile.entity.Profile;
import com.bdool.memberhubservice.member.domain.profile.entity.model.ProfileModel;
import com.bdool.memberhubservice.member.domain.profile.entity.model.ProfileUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile save(ProfileModel profileModel, Long memberId, boolean isWorkspaceCreator);

    Optional<Profile> findById(Long profileId);

    List<Profile> findAll();

    long count();

    boolean existsById(Long profileId);

    void deleteById(Long profileId);

    Profile update(Long profileId, ProfileUpdateRequest profileUpdateRequest);

    String updateStatus(Long profileId, String status);

    Boolean updateOnline(Long profileId, boolean isOnline);
}