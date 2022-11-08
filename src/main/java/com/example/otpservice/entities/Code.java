package com.example.otpservice.entities;

import com.example.otpservice.entities.enums.Status;
import jdk.jshell.Snippet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="code")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    Date endDate;
    @Enumerated(value = EnumType.ORDINAL)
    Status status;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    User userId;
}
