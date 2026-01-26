package com.project.notepad.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Header  extends StackPane {
    public Header() {

        // Box Dimensions
        int width = 300;
        int height = 50;

        setMinSize (width, height);
        setPrefSize(width, height);
        setMaxSize (width, height);

        // Config Box Header
        Rectangle bg = new Rectangle(width,height);
        bg.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#4C1F7A")),
                new Stop(1, Color.web("#8C36E2"))
        ));
        DropShadow shadow = new DropShadow();
        shadow.setRadius(8);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0, 0, 0, 0.25));
        bg.setEffect(shadow);

        // Add Content
        HBox content = createContent();
        getChildren().addAll(bg, content);
    }

    private static HBox createContent() {
        Label title = new Label("NotePad");
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        title.setTextFill(Color.web("#CCCCCC"));

        Button addButton = new Button("+");
        addButton.setFont(Font.font("System", FontWeight.BOLD, 16));
        addButton.setTextFill(Color.web("#CCCCCC"));
        // Add circular stroke around this button

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        HBox content = new HBox(title,space,addButton);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);


        return content;
    }
}
