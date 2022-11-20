package br.com.example.msscusers.domain.usecases.usernotification;

import br.com.example.msscusers.domain.models.Notification;

public interface UserNotification {

    void execute(Notification notification);
}
