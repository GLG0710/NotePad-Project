package com.project.notepad;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.project.notepad.entity.Note;
import com.project.notepad.entity.enum_repeat.Repeat;
import com.project.notepad.service.NoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootApplication
public class NotepadApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotepadApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner test(NoteService noteService) {
		return args -> {
			Note note = new Note(
					"Teste Scheduler - 1",
					"Se você está vendo isso, funcionou - 30",
					LocalDateTime.now().plusSeconds(30),
					Repeat.NONE
			);
			Note note2 = new Note(
					"Teste Scheduler - 2",
					"Se você está vendo isso, funcionou - 60",
					LocalDateTime.now().plusSeconds(60),
					Repeat.NONE
			);
			Note note3 = new Note(
					"Teste Scheduler - 3",
					"Se você está vendo isso, funcionou - 90",
					LocalDateTime.now().plusSeconds(90),
					Repeat.NONE
			);
			Note note4 = new Note(
					"Teste Scheduler - 4",
					"Se você está vendo isso, funcionou - 120",
					LocalDateTime.now().plusSeconds(120),
					Repeat.NONE
			);
			Note note5 = new Note(
					"Teste Scheduler - 5",
					"Se você está vendo isso, funcionou - 150",
					LocalDateTime.now().plusSeconds(150),
					Repeat.NONE
			);

			noteService.create(note);
			noteService.create(note2);
			noteService.create(note3);
			noteService.create(note4);
			noteService.create(note5);
		};
	}
}