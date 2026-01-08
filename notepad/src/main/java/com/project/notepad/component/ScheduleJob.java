package com.project.notepad.component;

import com.project.notepad.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ScheduleJob implements Job {
    private final NoteService noteService;

    @Override
    public void execute(JobExecutionContext context) {
        UUID id = UUID.fromString(
                context.getMergedJobDataMap().getString("noteId")
        );

        noteService.onScheduleFire(id);
    }
}
