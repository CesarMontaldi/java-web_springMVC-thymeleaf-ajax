package br.com.cesarMontaldi.domain;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.util.UUID;

public class Emissor {

    private String id = UUID.randomUUID().toString();
    private SseEmitter emitter;
    private LocalDateTime ultimaData;

    public Emissor(SseEmitter emitter, LocalDateTime ultimaData) {
        this.emitter = emitter;
        this.ultimaData = ultimaData;
    }

    public String getId() {
        return id;
    }

    public SseEmitter getEmitter() {
        return emitter;
    }

    public void setEmitter(SseEmitter emitter) {
        this.emitter = emitter;
    }

    public LocalDateTime getUltimaData() {
        return ultimaData;
    }

    public void setUltimaData(LocalDateTime ultimaData) {
        this.ultimaData = ultimaData;
    }
}
