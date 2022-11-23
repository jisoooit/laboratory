package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.MyEntityListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(MyEntityListener.class)
public class MemberHistory implements  Auditable {
    @Id
    @GeneratedValue
    private long id;

    private Long memberId;

    private String name;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;


}
