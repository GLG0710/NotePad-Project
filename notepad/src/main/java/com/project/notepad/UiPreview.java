package com.project.notepad;

import com.project.notepad.entity.Note;
import com.project.notepad.entity.enum_repeat.Repeat;
import com.project.notepad.ui.NoteCard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class UiPreview extends Application {

    @Override
    public void start(Stage stage) {
        // Nota fake só pra teste visual
        Note testNote = new Note(
                "Reunião com equipe extremamente longa",
                "Lembrar de enviar o relatório financeiro até amanhã de manhã, sem falta!",
                LocalDateTime.now(),
                Repeat.NONE
        );

        NoteCard card = new NoteCard(testNote);

        StackPane root = new StackPane(card);
        root.setStyle("-fx-background-color: #FFFFFFFFF;"); // fundo escuro p/ contraste
        root.setPrefSize(400, 300);

        Scene scene = new Scene(root);
        stage.setTitle("Preview - NoteCard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}