package br.com.example.msscauth.domain.usecases.usernotification;

import br.com.example.msscauth.domain.models.Notification;

public interface UserNotification {

    void execute(Notification notification);
}
