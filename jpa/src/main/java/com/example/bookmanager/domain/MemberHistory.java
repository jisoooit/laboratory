package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.MyEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class MemberHistory extends BaseEntity implements  Auditable {
    @Id
    @GeneratedValue
    private long id;

    private Long memberId;

    private String name;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;


}
