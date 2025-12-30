package entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadManager {
    private final ScheduledExecutorService manager;
    private final NotePad notePad;

    public ThreadManager(NotePad notePad) {
        this.manager = Executors.newSingleThreadScheduledExecutor();
        this.notePad = notePad;
    }

    // Adiciona a nota ao gerenciador
    public void schedule(UUID id) {
        notePad.read(id).ifPresent(this::scheduleNote);
    }

    // Dá o delay para ativação da nota
    private void scheduleNote(Note note) {
        // Armazena o delay em ms (aguenta milhões de anos, seguro)
        long delayMillis = Duration.between(
            LocalDateTime.now(),
            note.getDateTime()
        ).toMillis(); 

        // Cria o schedule com o delay, verificando antes se a nota está ativa
        manager.schedule(
            ()-> {
                if (note.getActivate()) { // Só mostra mensagem se ela estiver ativa
                    note.readNote();
                    if (note.getRepeatDaily()) { // Verifica se é uma mensagem do tipo "Repetir diaremente" e se for cria um novo schedule para daqui 24 horas
                        note.setDateTime(note.getDateTime().plusHours(24));
                        scheduleNote(note);
                    }
                }
            },
            delayMillis,
            TimeUnit.MILLISECONDS
        ); 
    }
}
