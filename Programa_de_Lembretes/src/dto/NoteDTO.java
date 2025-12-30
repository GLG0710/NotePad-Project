package dto;

import java.time.LocalDateTime;

// Record para transferencia de dados (utilizado para update de notas)
public record NoteDTO (
    String title,
    String message,
    LocalDateTime dateTime,
    boolean repeatDaily,
    boolean activate
) {
}
