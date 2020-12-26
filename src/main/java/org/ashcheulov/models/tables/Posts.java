package org.ashcheulov.models.tables;

import io.smallrye.common.constraint.NotNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "posts", schema = "sis")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    @Type(type = "text")
    private String body;

    @Column(name = "author_id")
    private int author_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Users users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
