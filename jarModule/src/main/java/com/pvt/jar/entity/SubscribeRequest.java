package com.pvt.jar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@ToString
@Table
public class SubscribeRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    long idSubscriber;

    @Column
    String usernameSubscriber;

    @Column
    long idChanel;

    @Column
    boolean status;
}
