package com.pvt.jar.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_table")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "text",nullable = false)
    private String text;

    @CreationTimestamp
    @Column()
    private Date createDate;

    @UpdateTimestamp
    @Column()
    private Date updateDate;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @ManyToOne()
    @JoinColumn(name = "user_ID")
    private User user;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.REMOVE})
    private Set<Comment> comments= new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getID() == post.getID() && getName().equals(post.getName()) && getText().equals(post.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getName(), getText());
    }
}
