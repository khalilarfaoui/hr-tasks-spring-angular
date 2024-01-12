package com.example.nexus.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    @Id
    @GeneratedValue
    private Integer idEvent;
    private Date startdate;
    private Date fnDate;

    @ManyToMany(mappedBy = "eventList" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties("event")
    List<User> users;
}
