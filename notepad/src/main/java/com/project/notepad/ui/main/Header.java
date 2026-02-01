package com.project.notepad.ui.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Header extends HBox {
    public Header() {
        setPrefSize(300,50);
        setAlignment(Pos.CENTER_LEFT);

        // Background color
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#4C1F7A")),
                new Stop(1, Color.web("#8C36E2",0.8))
        );

        setBackground(new Background(
                new BackgroundFill(
                        gradient,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));

        // DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(8);
        dropShadow.setRadius(12);
        dropShadow.setSpread(0.2);
        dropShadow.setColor(Color.web("#553366", 0.4));
        setEffect(dropShadow);

        // Padding and Spacing
        setPadding(new Insets(20));
        setSpacing(10);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Elements
        getChildren().addAll(createTitle(), spacer, createAddButton());
    }

    private Label createTitle() {
        Label title = new Label("NotePad");
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        title.setTextFill(Color.web("#CCCCCC"));

        return title;
    }

    private StackPane createAddButton() {
        Circle circle = new Circle(10);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.web("#CCCCCC"));
        circle.setStrokeWidth(2);

        Label plus = new Label("+");
        plus.setFont(Font.font("System", FontWeight.BOLD, 16));
        plus.setTextFill(Color.web("#CCCCCC"));
        plus.setAlignment(Pos.CENTER);

        StackPane button = new StackPane(circle, plus);
        button.setMinSize(20, 20);
        button.setPrefSize(20, 20);
        button.setMaxSize(20, 20);
        button.setAlignment(Pos.CENTER);
        button.setCursor(javafx.scene.Cursor.HAND);

        return button;
    }
}
