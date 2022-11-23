package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.MyEntityListener;
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
@EntityListeners(MyEntityListener.class)
public class Member implements  Auditable {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;
    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    //@Column(insertable = false)
    private LocalDateTime updatedAt;

    @Transient
    private String testData;

//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
}
