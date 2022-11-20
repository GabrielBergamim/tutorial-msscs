package br.com.example.msscnotification.domain.services;

import br.com.example.msscnotification.domain.model.Notification;

public interface EmailService {

    void sendEmail(Notification notification);
}
