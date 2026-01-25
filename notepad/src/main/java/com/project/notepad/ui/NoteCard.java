package com.project.notepad.ui;

import com.project.notepad.entity.Note;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jspecify.annotations.NonNull;

import java.time.format.DateTimeFormatter;

public class NoteCard extends StackPane {
        public NoteCard(Note note) {

            // Box Size
            setMinSize(260, 100);
            setPrefSize(260, 100);
            setMaxSize(260, 100);

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

            // Shadow Box
            DropShadow shadow = new DropShadow();
            shadow.setRadius(8);
            shadow.setOffsetY(3);
            shadow.setColor(Color.rgb(0, 0, 0, 0.25));
            bg.setEffect(shadow);

            // Content
            VBox content = createBox(note);

            getChildren().addAll(bg, content);
            setAlignment(Pos.CENTER);
        }


    private static VBox createBox(Note note) {
        // Top Row, with Title and DateTime
        HBox topRow = createTopRow(note);

        // Mid-Row, with only message
        Label message = new Label(note.getMessage());
        message.setMaxWidth(170);
        message.setPrefWidth(170);
        message.setMaxHeight(40);
        message.setTextOverrun(OverrunStyle.ELLIPSIS);
        message.setWrapText(true);

        // Bottom Row, with the buttons: Configuration and Delete, and the label Active
        HBox bottomRow = createBottomRow(note);

        VBox content = new VBox(10, topRow, message, bottomRow);
        content.setPadding(new Insets(15));
        return content;
    }

    private static HBox createTopRow(Note note) {
        // Title
        Label title = new Label(note.getTitle());
        title.setFont(Font.font("System", FontWeight.BOLD, 14));
        title.setMaxWidth(115);
        title.setPrefWidth(115);
        title.setTextOverrun(OverrunStyle.ELLIPSIS);
        title.setWrapText(false);

        // Extracting time and date from note
        Label date = new Label(note.getTime().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        ));
        Label time = new Label(note.getTime().format(
                DateTimeFormatter.ofPattern("HH:mm")
        ));

        // Date box with date and time
        date.setFont(Font.font("System", FontWeight.SEMI_BOLD, 12));
        time.setFont(Font.font("System", FontWeight.SEMI_BOLD, 12));
        VBox dateBox = new VBox(2, date, time);
        dateBox.setAlignment(Pos.TOP_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox topRow = new HBox(title, spacer, dateBox);
        topRow.setAlignment(Pos.TOP_LEFT);

        return topRow;
    }

    private static HBox createBottomRow(Note note) {
        Button configBtn = new Button("⚙");
        Label activeLabel = new Label("Ativo: " + (note.isActive() ? "V" : "X"));
        Button deleteBtn = new Button("\uD83D\uDDD1");

        Region spacer1 = new Region();
        Region spacer2 = new Region();

        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        HBox bottomRow = new HBox(10, configBtn, spacer1, activeLabel, spacer2, deleteBtn);
        bottomRow.setAlignment(Pos.CENTER);

        return bottomRow;
    }
}