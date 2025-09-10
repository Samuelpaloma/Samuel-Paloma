package com.SENA.FlightManagementSystem.Notifications.Service;

import com.SENA.FlightManagementSystem.Notifications.Entity.Notification;
import com.SENA.FlightManagementSystem.Notifications.IRepository.INotificationRepository;
import com.SENA.FlightManagementSystem.Notifications.IService.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getNotificationsByType(String type) {
        return notificationRepository.findByType(type);
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }
}