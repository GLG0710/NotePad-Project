package com.project.notepad.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class MainView extends StackPane {
    private final VBox cardsContainer;

    public MainView() {
        setPrefSize(300, 450);

        // Box
        Rectangle background = new Rectangle(300, 450);
        // BorderRadius
        background.setArcWidth(15);
        background.setArcHeight(15);
        // Background Color
        background.setFill(new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#AA66CC")),
                new Stop(1, Color.web("#553366"))
        ));

        // Header
        //HeaderView header = new HeaderView();

        // Cards Container
        cardsContainer = new VBox(20);
        cardsContainer.setPadding(new Insets(20));
        cardsContainer.setLayoutY(120);



        Pane contentPane = new Pane(cardsContainer);

        //getChildren().addAll(background, contentPane, header);
    }

    public void addNoteCard(NoteCard card) {
        //cardsContainer.getChildren().add(card);
    }
}