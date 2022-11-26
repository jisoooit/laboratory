package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.MyEntityListener;
import com.example.bookmanager.domain.listener.MemberEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class Member extends BaseEntity{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="member_id", insertable = false)
    @ToString.Exclude
    private List<MemberHistory> memberHistories = new ArrayList<>(); //null 발생하지 않도록 기본 리스트 생성해줌.

    @OneToMany
    @JoinColumn(name="member_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;

//    //@Column(insertable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
//
//    @Transient
//    private String testData;


}
