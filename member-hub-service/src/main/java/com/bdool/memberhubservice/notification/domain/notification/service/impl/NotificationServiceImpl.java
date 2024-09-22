package com.bdool.memberhubservice.notification.domain.notification.service.impl;

import com.bdool.memberhubservice.notification.domain.notification.entity.Notification;
import com.bdool.memberhubservice.notification.domain.notification.entity.model.NotificationModel;
import com.bdool.memberhubservice.notification.domain.notification.repository.NotificationRepository;
import com.bdool.memberhubservice.notification.domain.notification.service.NotificationService;
import com.bdool.memberhubservice.notification.domain.setting.entity.NotificationTargetType;
import com.bdool.memberhubservice.notification.domain.setting.service.NotificationTargetSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationTargetSettingService targetSettingService;

    @Override
    public Notification createNotification(NotificationModel notificationModel) {
        Long profileId = notificationModel.getProfileId();
        Long targetId = notificationModel.getTargetId();
        NotificationTargetType targetType = notificationModel.getTargetType();

        boolean isTargetNotificationEnabled = targetSettingService.isNotificationEnabledForTarget(profileId, targetId, targetType);
        if (!isTargetNotificationEnabled) {
            return null;  // 알림이 비활성화된 경우 알림을 생성하지 않음
        }

        Notification notification = Notification.builder()
                .profileId(profileId)
                .notificationType(notificationModel.getNotificationType())
                .message(notificationModel.getMessage())
                .metadata(notificationModel.getMetadata())
                .expiresAt(getExpiresAt())
                .isRead(false)
                .build();

        // 3. 알림 저장
        return notificationRepository.save(notification);
    }

    // 만료 시간을 현재 시간 기준 30일 후로 설정하는 메서드
    private LocalDateTime getExpiresAt() {
        return LocalDateTime.now().plusDays(30);
    }

    @Override
    public List<Notification> findByProfileIdAndReadFalse(Long profileId) {
        // 읽지 않은 알림 조회
        return notificationRepository.findByProfileIdAndReadFalse(profileId);
    }

    @Override
    public List<Notification> findByProfileIdOrderByCreatedAtDesc(Long profileId) {
        // 모든 알림을 생성 시간 순으로 조회
        return notificationRepository.findByProfileIdOrderByCreatedAtDesc(profileId);
    }

    @Override
    public void markAsRead(Long notificationId) {
        // 알림을 조회하여 읽음 처리
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (!notification.isRead()) {
            notification.markAsRead();  // 읽음 처리 메서드 호출
            notificationRepository.save(notification);  // 변경 사항 저장
        }
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")  // 매일 자정에 실행
    public void cleanupExpiredNotifications() {
        LocalDateTime cutoffDate = LocalDateTime.now();
        notificationRepository.deleteExpiredNotifications(cutoffDate);
    }
}