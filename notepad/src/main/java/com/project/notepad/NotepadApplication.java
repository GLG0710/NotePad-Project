package com.project.notepad;

import com.project.notepad.entity.Note;
import com.project.notepad.entity.enum_repeat.Repeat;
import com.project.notepad.service.NoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class NotepadApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotepadApplication.class, args);
	}

	@Bean
	CommandLineRunner test(NoteService noteService) {
		return args -> {
			Note note = new Note(
					"Teste Scheduler",
					"Se você está vendo isso, funcionou",
					LocalDateTime.now().plusSeconds(50),
					Repeat.NONE
			);

			noteService.create(note);
		};
	}
}
