package com.pvt.jar.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment_table")

public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "text", nullable = false)
    private String text;

    @CreationTimestamp
    @Column()
    private Date createDate;

    @UpdateTimestamp
    @Column()
    private Date updateDate;


    @ManyToOne
    @JoinColumn(name = "post_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getID() == comment.getID() && getName().equals(comment.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getName());
    }

    @Override
    public String toString(){
        return name;
    }
}
