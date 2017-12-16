package io.pivotal.fortune;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fortune {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @Override
    public String toString() {
        return "Fortune [id=" + id + ", text=" + text + "]";
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

}