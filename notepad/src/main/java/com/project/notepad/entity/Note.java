package com.project.notepad.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.project.notepad.entity.enum_repeat.Repeat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Note {
    @Id
    @UuidGenerator  // ← Remove @GeneratedValue
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.PROTECTED)
    private UUID id;

    @Column(nullable = false)
    private String title;
    private String message;     // Message is optional
    @Column(nullable = false)
    private LocalDateTime time;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Repeat repeat;
    @Column(nullable = false)
    private boolean active;

    public Note(String title, String message, LocalDateTime time, Repeat repeat) {
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.message = message;
        this.time = Objects.requireNonNull(time, "time must not be null");
        this.repeat = repeat;
        this.active = true;
    }
}
