package entity;

import dto.NoteDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

// Bloco de notas, onde salvo as notas, é um map que linka as notas aos seus ids e onde possui minhas operações crud
public class NotePad {
    private static final NotePad instance = new NotePad();
    private final Map<UUID, Note> notePad = new HashMap<>();
    private final ThreadManager manager; // Verificar se: Caso eu ao criar o notepad já crie um manager nã ovai dar nenhum erro

    private NotePad() {
        this.manager = new ThreadManager(this);
    }

    public static NotePad getInstance() {
        return instance;
    }

    // CRUD
    public void create(Note note) { // Cada nota criada deve criar uma thread
        notePad.put(note.getId(), note);
        manager.schedule(note.getId());
    }

    public Optional<Note> read(UUID id) {
        return Optional.ofNullable(notePad.get(id));
    }

    // Quando fizer o update do ativo, chamar threadManager
    public void update(UUID id, NoteDTO dto){
        Note note = notePad.get(id);

        if (note == null) {
            IO.println("Lembrete não encontrado");
            return;
        }

        if (dto.title() != null)
            note.setTitle(dto.title());

        if (dto.message() != null)
            note.setMessage(dto.message());

        if (dto.dateTime() != null)
            note.setDateTime(dto.dateTime());

        if (dto.repeatDaily() != note.getRepeatDaily())
            note.setRepeatDaily(dto.repeatDaily());

        if (dto.activate() != note.getActivate())
            note.setActivate(dto.activate());
    }

    public void delete(UUID id) {
        notePad.remove(id);
    }

    public void deleteAll() {
        notePad.clear();
    }
}