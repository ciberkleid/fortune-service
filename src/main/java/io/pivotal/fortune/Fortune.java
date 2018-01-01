package io.pivotal.fortune;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fortune {

    @Id
    private Long id;

    private String text;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Fortune [id=" + id + ", text=" + text + "]";
    }
    
}
