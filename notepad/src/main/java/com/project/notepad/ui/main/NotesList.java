package com.project.notepad.ui.main;

import com.project.notepad.entity.Note;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.List;

public class NotesList extends VBox {

    public NotesList() {
        setSpacing(20);
        setPadding(new Insets(20));
    }

    public void loadNotes(List<Note> notes) {
        getChildren().clear();
        notes.forEach(note -> addNoteCard(new NoteCard(note)));
    }

    public void addNoteCard(NoteCard card) {
        getChildren().add(card);
    }
}
