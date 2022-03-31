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

    // Enum을 쓰는게 좋음 (admin, user, manager)
    @ColumnDefault("'user'")
    private String role;

    // 시간이 자동으로 입력
    @CreationTimestamp
    private Timestamp createDate;
}
