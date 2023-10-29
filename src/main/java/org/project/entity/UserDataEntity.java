package org.project.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_DATA")
public class UserDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "modified")
    private LocalDateTime modified;

    @CreationTimestamp
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "token")
    private UUID token;

    @Column(name = "is_active")
    private Boolean isActive;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<PhoneEntity> phones = new ArrayList<>();

    @PrePersist
    public void generateToken() {
        this.token = UUID.randomUUID();
        if (isActive == null) {
            isActive = true;
        }
    }


}
