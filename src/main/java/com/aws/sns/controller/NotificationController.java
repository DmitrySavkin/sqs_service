package com.aws.sns.controller;

import com.aws.sns.model.Notification;
import com.aws.sns.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping("/allnotification")
    public ResponseEntity<List<Notification>> getAllNotification() {
            return new ResponseEntity<>(service.getAllNotifications(), HttpStatus.OK);
    }
    @DeleteMapping("/notification/{id}")
    public ResponseEntity<Object> deleteNotification(@PathVariable("id") Long id) {
        service.deleteNotification(id);
        return  new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @PutMapping("/notification/{id}")
    public ResponseEntity<Object> updateNotification(@RequestBody Notification notification) {
        service.updateNotification(notification);
        return  new ResponseEntity<>("OK", HttpStatus.OK);

    }
}
