package com.project.notepad.ui;

import com.project.notepad.entity.Note;
import com.project.notepad.service.NoteService;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class MainView extends StackPane {
    private final VBox cardsContainer;
    private final NoteService noteService;

    public MainView(NoteService noteService) {
        this.noteService = noteService;

        // Box Size
        int height = 450;
        int width = 300;

        setMinSize (width,height);
        setPrefSize(width,height);
        setMaxSize (width,height);

        // Box Main
        Rectangle background = mainBox();

        // Header
        Header header = new Header();
        StackPane.setAlignment(header, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(header, new Insets(0, 0, 0, 0));

        // Cards Container
        cardsContainer = cardsContainer();

        Pane contentPane = new Pane(cardsContainer);
        loadNotes();

        getChildren().addAll(background, header, contentPane);
    }

    private static Rectangle mainBox () {
        Rectangle background = new Rectangle(300,450);
        // BorderRadius
        background.setArcWidth(15);
        background.setArcHeight(15);
        // Background Color
        background.setFill(new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#AA66CC")),
                new Stop(1, Color.web("#553366"))
        ));

        return background;
    }

    private static VBox cardsContainer() {
        VBox cardsContainer = new VBox(20);
        cardsContainer.setPadding(new Insets(20));
        cardsContainer.setLayoutY(50); // 50 is the size of the header height

        return cardsContainer;
    }

    private void loadNotes() {
        noteService.readAll().forEach(note ->
                cardsContainer.getChildren().add(new NoteCard(note))
        );
    }
}