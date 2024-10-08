package com.bdool.memberhubservice.mail.domain.verification.service;

import com.bdool.memberhubservice.mail.domain.verification.entity.Verification;
import com.bdool.memberhubservice.mail.domain.verification.entity.model.VerificationModel;

import java.util.List;
import java.util.Optional;

public interface VerificationService {

    Verification save(VerificationModel verificationModel);

    Boolean sendVerificationCode(String mail);

    Boolean verifyCode(String email, String verificationCode);
}
