package com.project.notepad.ui;

import com.project.notepad.entity.Note;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jspecify.annotations.NonNull;

public class NoteCard extends StackPane {

    public NoteCard(Note note) {
        setPrefSize(260, 100);

        // Box
        Rectangle bg = new Rectangle(260, 100);
        // BorderRadius
        bg.setArcWidth(15);
        bg.setArcHeight(15);
        // BackgroundColor
        bg.setFill(new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#CCCCCC")),
                new Stop(1, Color.web("#808080"))
        ));
        // BoxBorder
        bg.setStroke(Color.BLACK);

        // Extract the information to create the card
        VBox content = createBox(note);

        getChildren().addAll(bg, content);
    }

    private static VBox createBox(Note note) {
        Label title = new Label(note.getTitle());
        title.setFont(Font.font("System", FontWeight.BOLD, 18));

        Label date = new Label(note.getTime().toString());
        date.setFont(Font.font("System", FontWeight.BOLD, 18));

        // Top Row, with Title and DateTime
        HBox topRow = new HBox(title, new Region(), date);
        HBox.setHgrow(topRow.getChildren().get(1), Priority.ALWAYS);

        // Mid-Row, with only message
        Label message = new Label(note.getMessage());

        // Bottom Row, with the buttons: Configuration and Delete, and the label Active
        Button configBtn = new Button("Config");
        Label activeLabel = new Label("Ativo: " + (note.isActive() ? "V" : "X"));
        Button deleteBtn = new Button("Excluir");

        HBox bottomRow = new HBox(20, configBtn, activeLabel, deleteBtn);

        VBox content = new VBox(10, topRow, message, bottomRow);
        content.setPadding(new Insets(15));
        return content;
    }
}
