package com.SENA.FlightManagementSystem.Notifications.IService;

import com.SENA.FlightManagementSystem.Notifications.Entity.Notification;
import java.util.List;

public interface INotificationService {
    Notification createNotification(Notification notification);
    List<Notification> getAllNotifications();
    List<Notification> getNotificationsByType(String type);
    Notification getNotificationById(Long id);
}