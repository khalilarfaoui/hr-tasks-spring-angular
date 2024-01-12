package com.example.nexus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@ToString
public class Ban implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime lastFailedLoginAttempt;

    private LocalDateTime expiryTime;
    private Date debutBan;
    private Date endBan;
    private int penalites;
    private boolean status;



    @JsonIgnoreProperties("ban")
    @OneToOne
    private User user ;


}
