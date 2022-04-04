package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@DynamicInsert  // Insert 시 null 필드 제외
public class User {

    // 프로젝트에서 연결된 DB의 Numbering 전략을 따라간다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30)
    private String username;

    // Password Encryption
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'")
    // DB는 RoleType이 없음
    @Enumerated(EnumType.STRING)
    private RoleType role;

    // 시간이 자동으로 입력
    @CreationTimestamp
    private Timestamp createDate;
}
