package ru.neocode.neocode.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_social_links")
public class UserSocialLinks {

    @Id
    @Column(unique = true, nullable = false)
    private long id;

    @Column(unique = true)
    private Long telegramId;

}
