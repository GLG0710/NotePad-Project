package com.project.notepad.dto;

import com.project.notepad.entity.enum_repeat.Repeat;

import java.time.LocalDateTime;

public record NoteDTO(
   String title,
   String message,
   LocalDateTime time,
   Boolean active,
   Repeat repeat
) {
}
