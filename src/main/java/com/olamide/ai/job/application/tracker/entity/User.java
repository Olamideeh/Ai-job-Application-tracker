package com.olamide.ai.job.application.tracker.entity;
import jakarta.persistence.Table;
import com.olamide.ai.job.application.tracker.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String fullname;
    @Column(nullable = false,length = 50)
    private String username;
    @Column(nullable = false,length = 50)
    private String password;
    @Column(nullable = false,length = 50)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
