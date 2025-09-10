package com.SENA.FlightManagementSystem.Notifications.IRepository;

import com.SENA.FlightManagementSystem.Notifications.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByType(String type);
}