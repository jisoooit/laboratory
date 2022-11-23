package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.MyEntityListener;
import com.example.bookmanager.domain.listener.MemberEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@EntityListeners( value = {MemberEntityListener.class})
public class Member extends BaseEntity implements  Auditable {
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
    @CreatedDate
    private LocalDateTime createdAt;

//    //@Column(insertable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
//
//    @Transient
//    private String testData;

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
