package com.project.notepad.ui;

import com.project.notepad.NotepadApplication;
import com.project.notepad.entity.Note;
import com.project.notepad.entity.enum_repeat.Repeat;
import com.project.notepad.service.NoteService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class UiPreview extends Application {

    private NoteService noteService;

    @Override
    public void init() {
        noteService = NotepadApplication
                .getContext()
                .getBean(NoteService.class);
    }

    @Override
    public void start(Stage stage) {

        noteService.create(new Note(
                "Lembrete 1",
                "Este é o Lembrete 1, para o teste de UI",
                LocalDateTime.now().plusSeconds(30),
                Repeat.NONE
        ));
        noteService.create(new Note(
                "Lembrete 2",
                "Este é o Lembrete 2, para o teste de UI",
                LocalDateTime.now().plusSeconds(60),
                Repeat.NONE
        ));
        noteService.create(new Note(
                "Lembrete 3",
                "Este é o Lembrete 3, para o teste de UI",
                LocalDateTime.now().plusSeconds(90),
                Repeat.NONE
        ));

        MainView root = new MainView(noteService);

        Scene scene = new Scene(root);
        stage.setTitle("Preview - NoteCard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}