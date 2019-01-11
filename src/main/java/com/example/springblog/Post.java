package com.example.springblog;
import javax.persistence.*;

@Entity
public class Post {
    @Id @GeneratedValue
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String body;

    public Post() {

    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
