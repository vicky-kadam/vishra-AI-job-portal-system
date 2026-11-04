package com.vishra.job.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class ApplicationNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    private Long addedByUserId;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;
}
