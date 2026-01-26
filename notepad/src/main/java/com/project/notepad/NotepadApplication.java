package com.project.notepad;

import com.project.notepad.ui.UiPreview;
import javafx.application.Application;
import lombok.Getter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.project.notepad.entity.Note;
import com.project.notepad.entity.enum_repeat.Repeat;
import com.project.notepad.service.NoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootApplication
public class NotepadApplication {
	@Getter
    public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(NotepadApplication.class, args);
		Application.launch(UiPreview.class, args);
	}
}