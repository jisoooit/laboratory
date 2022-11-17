package com.example.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Member {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;
    @NonNull
    private String email;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Transient
    private String testData;
}
