package ru.neocode.neocode.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "link_sessions")
public class LinkSession {

    @Id
    @Column(unique = true, nullable = false)
    private long userId;

    @Column(unique = true, nullable = false)
    private String sessionId;

}
