package com.SENA.FlightManagementSystem.Notifications.Controller;

import com.SENA.FlightManagementSystem.Notifications.Entity.Notification;
import com.SENA.FlightManagementSystem.Notifications.IService.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    // 1. Crear plantilla de mensaje (Administrador)
    @PostMapping("/template")
    public Notification createTemplate(@RequestBody Notification notification) {
        notification.setType("TEMPLATE");
        return notificationService.createNotification(notification);
    }

    // 2. Enviar notificación de confirmación de ticket (Sistema)
    @PostMapping("/confirmation")
    public Notification sendConfirmation(@RequestBody Notification notification) {
        notification.setType("CONFIRMATION");
        return notificationService.createNotification(notification);
    }

    // 3. Enviar alerta de retraso de vuelo (Sistema)
    @PostMapping("/delay")
    public Notification sendDelayAlert(@RequestBody Notification notification) {
        notification.setType("DELAY");
        return notificationService.createNotification(notification);
    }

    // 4. Obtener todas las notificaciones
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    // 5. Obtener notificaciones por tipo
    @GetMapping("/type/{type}")
    public List<Notification> getNotificationsByType(@PathVariable String type) {
        return notificationService.getNotificationsByType(type);
    }
}