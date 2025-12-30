package com.project.notepad.service;

import com.project.notepad.entity.Note;
import com.project.notepad.schedule.NoteJob;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class SchedulerService {
    private final Scheduler scheduler;

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(Note note) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(NoteJob.class)
                    .withIdentity(jobKey(note))
                    .usingJobData("noteId", note.getId().toString())
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey(note))
                    .startAt(Date.from(note.getTime()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))
                    .build();

            scheduler.deleteJob(jobKey(note));
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException("Failed to schedule note", e);
        }
    }

    public void reschedule(Note note) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(note.getId().toString());

            if (!scheduler.checkExists(triggerKey))
                return;

            Trigger newTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .startAt(Date.from(note.getTime()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))
                    .build();

            scheduler.rescheduleJob(triggerKey, newTrigger);

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancel(UUID noteId) {
        try {
            scheduler.deleteJob(JobKey.jobKey(noteId.toString()));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    private JobKey jobKey(Note note) {
        return JobKey.jobKey(note.getId().toString());
    }

    private TriggerKey triggerKey(Note note) {
        return TriggerKey.triggerKey(note.getId().toString());
    }
}
