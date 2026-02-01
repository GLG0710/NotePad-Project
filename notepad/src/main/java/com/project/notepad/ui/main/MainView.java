package com.project.notepad.ui.main;

import com.project.notepad.service.NoteService;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class MainView extends BorderPane {
    private final NoteService noteService;
    private final NotesList notesList = new NotesList();

    public MainView(NoteService noteService) {
        this.noteService = noteService;
        setupBackground();
        setupUI();
        loadNotes();
    }

    private void setupBackground() {
        LinearGradient gradient = new LinearGradient(
                0, 0, 0, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#AA66CC", 0.8)),
                new Stop(1, Color.web("#553366", 0.8))
        );
        setBackground(new Background(
                new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)
        ));
    }

    private void setupUI() {
        setPrefSize(300, 450);

        Header header = new Header();

        ScrollPane scroll = new ScrollPane(notesList);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setBackground(Background.EMPTY);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        setTop(header);
        setCenter(scroll);
    }

    private void loadNotes() {
        notesList.loadNotes(noteService.readAll());
    }
}