package com.aws.sns.service;

import com.aws.sns.model.Notification;
import com.aws.sns.model.Status;
import com.aws.sns.model.Type;
import com.aws.sns.repository.NotificationRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }
    @SqsListener("dsavkin-notifications-sqs-queue")
    public void receiver(String message) {
        log.info("Message {}", message);
        Notification notification = Notification.builder().created(LocalDate.now())
                                                    .modified(LocalDate.now())
                .createdBy("ME")
                .modifiedBy("ME")
                .status(Status.NEW)
                .type(Type.INFO)
                .build();
        repository.save(notification);

    }

    public void deleteNotification(Long id) {
        if (!repository.existsById(id)) {
            throw  new RuntimeException(String.format("Notification  with id {} not exists", id));
        }
        repository.deleteById(id);
    }

    public void updateNotification(Notification notification) {
        long id = notification.getId();
        if (!repository.existsById(id)) {
            throw  new RuntimeException(String.format("Notification  with id {} not exists", id));
        }
        repository.save(notification);
    }
}

