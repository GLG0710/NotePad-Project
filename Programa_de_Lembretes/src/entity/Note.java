package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

// Classe que recebe a nota, precisa de titulo, descrição e tempo
public class Note {
    private final UUID id;
    private String title;
    private String message;
    private LocalDateTime dateTime; // Estudar classe
    private boolean repeatDaily; // Para notas que serão repetidas diariamente
    private boolean activate; // Saber se a nota está ativa ou não

    public Note(String title, String mensage, LocalDateTime dateTime, boolean repeatDaily) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.message = mensage;
        this.dateTime = dateTime;
        this.repeatDaily = repeatDaily;
        this.activate = true; // Valor inicial de criação
    }

    // Getters 
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage () {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean getRepeatDaily() {
        return repeatDaily;
    }

    public boolean getActivate() {
        return activate;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage (String message) {
        this.message = message;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setRepeatDaily(boolean repeatDaily) {
        this.repeatDaily = repeatDaily;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    // Mostrar nota
    public void readNote() {
        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
        System.out.printf(
            "%s \n %s \n %s \n Repetir Nota: %s \n", title, dateTimeString, message, repeatDaily ? "Sim" : "Não" 
        );
        if (!repeatDaily) 
            activate = false;
    }
}