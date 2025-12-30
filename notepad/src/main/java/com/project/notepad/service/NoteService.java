package com.project.notepad.service;

import com.project.notepad.dto.NoteDTO;
import com.project.notepad.entity.Note;
import com.project.notepad.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.project.notepad.entity.enum_repeat.Repeat.NONE;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final SchedulerService schedulerService;

    public NoteService(NoteRepository noteRepository,
                       SchedulerService schedulerService) {
        this.noteRepository = noteRepository;
        this.schedulerService = schedulerService;
    }

    @Transactional
    public Note create(Note note) {
        Note saved = noteRepository.save(note);
        schedulerService.schedule(note);
        return saved;
    }

    @Transactional(readOnly = true)
    public Note read(UUID id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));
    }

    @Transactional
    public void update(UUID id, NoteDTO dto) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));

        if (dto.title() != null) note.setTitle(dto.title());
        if (dto.message() != null) note.setMessage(dto.message());
        if (dto.repeat() != null) note.setRepeat(dto.repeat());

        if (dto.time() != null) {
            note.setTime(dto.time());
            schedulerService.reschedule(note);
        }

        if (dto.active() != null) {
            note.setActive(dto.active());

            if (note.isActive()) {
                schedulerService.schedule(note);
            } else {
                schedulerService.cancel(note.getId());
            }
        }
    }

    @Transactional
    public void delete(UUID id) {
        schedulerService.cancel(id);
        noteRepository.deleteById(id);
    }

    @Transactional
    public void onScheduleFire(UUID id) {
        noteRepository.findById(id).ifPresent(note -> {
            if (!note.isActive()) return;

            // Action (Here I will activate my note, and schedule if is a repeat note)
            System.out.println(note.getTitle());
            System.out.println(note.getMessage());
            System.out.println(note.getTime());

            if (note.getRepeat() != NONE) {
                note.setTime(nextTime(note));
                schedulerService.reschedule(note);
            } else {
                note.setActive(false);
            }
        });
    }

    private LocalDateTime nextTime(Note note) {
        return switch (note.getRepeat()) {
            case DAILY   -> note.getTime().plusDays(1);
            case WEEKLY  -> note.getTime().plusWeeks(1);
            case MONTHLY -> note.getTime().plusMonths(1);
            case NONE    -> null;
        };
    }
}