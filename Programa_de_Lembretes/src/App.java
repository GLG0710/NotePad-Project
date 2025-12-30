import dto.NoteDTO;
import entity.Note;
import entity.NotePad;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Objetivo: Tirar a responsabilidade de criar o threadManager do Main e jogar pro notepad
public class App {
    public static void main(String[] args) throws Exception {
        NotePad notePad = NotePad.getInstance();

        String str = "23:52 20-12-2025";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        
        Note note = new Note("Teste", "Este teste foi concluído com sucesso!", dateTime, false);
        notePad.create(note);
        notePad.update(note.getId(), new NoteDTO(null, null, null, false, true));
    }
}
