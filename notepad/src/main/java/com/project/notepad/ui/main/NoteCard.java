package com.project.notepad.ui.main;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jspecify.annotations.NonNull;

import java.time.format.DateTimeFormatter;

public class NoteCard extends VBox {

    public NoteCard(@NonNull Note note) {
        setPrefSize(260,140);

        LinearGradient gradient = new LinearGradient(
                0, 0, 0, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#CCCCCC")),
                new Stop(1, Color.web("#808080"))
        );

        setStyle("-fx-border-color: black; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 15; "
        );

        setBackground(new Background(
                new BackgroundFill(
                        gradient,
                        new CornerRadii(14),
                        Insets.EMPTY
                )
        ));

        // DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(4.0);
        dropShadow.setRadius(4.0);
        dropShadow.setSpread(0.0);
        dropShadow.setColor(Color.web("#000000",0.25));
        setEffect(dropShadow);

        // Padding and Spacing
        setPadding(new Insets(10));
        setSpacing(8);

        // Elements
        getChildren().addAll(createTopRow(note), createMidRow(note), createBottomRow());
    }

    private HBox createTopRow(Note note) {
        HBox top = new HBox();
        top.setAlignment(Pos.TOP_LEFT);
        top.setSpacing(10);

        Label title = new Label(note.getTitle());
        title.setFont(Font.font("System", FontWeight.BOLD,16));
        title.setTextFill(Color.web("#000000"));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        top.getChildren().addAll(title, spacer, dateTimeBox(note));
        return top;
    }

    private VBox dateTimeBox(Note note) {
        VBox dateTimeBox = new VBox();
        dateTimeBox.setAlignment(Pos.CENTER_RIGHT);
        Label date = new Label(note.getTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        date.setTextFill(Color.web("#000000"));
        Label time = new Label(note.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        time.setTextFill(Color.web("#000000"));
        dateTimeBox.getChildren().addAll(date, time);

        return dateTimeBox;
    }

    private Label createMidRow(Note note) {
        Label message = new Label(note.getMessage());
        message.setTextFill(Color.web("#000000"));
        // Message Size Dimensions
        message.setPrefWidth(170);
        message.setMaxWidth(170);
        message.setMaxHeight(40);
        // Text wrap
        message.setWrapText(true);
        message.setTextOverrun(OverrunStyle.ELLIPSIS);
        return message;
    }

    private HBox createBottomRow() {
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        return new HBox(
            new Button("⚙"), spacer1,
            new Button("✓"), spacer2,
            new Button("🗑")
        );
    }
}