package com.project.notepad.ui;

import com.project.notepad.NotepadApplication;
import com.project.notepad.entity.Note;
import com.project.notepad.entity.enum_repeat.Repeat;
import com.project.notepad.service.NoteService;
import com.project.notepad.ui.main.MainView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

public class UiPreview extends Application {

    private NoteService noteService;
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        // Inicializa Spring e pega o service
        context = SpringApplication.run(NotepadApplication.class);
        noteService = context.getBean(NoteService.class);
    }

    @Override
    public void start(Stage stage) {
        // Cria notas de teste
        noteService.create(new Note(
                "Lembrete 1",
                "Este é o Lembrete 1, para o teste de UI",
                LocalDateTime.now().plusSeconds(30),
                Repeat.NONE
        ));
        noteService.create(new Note(
                "Lembrete 2",
                "Este é o Lembrete 2, para o teste de UI - Este é o Lembrete 2, para o teste de UI",
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

    @Override
    public void stop() {
        if (context != null) {
            context.close();
        }
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
