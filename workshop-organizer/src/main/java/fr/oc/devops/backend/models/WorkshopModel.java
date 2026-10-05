package fr.oc.devops.backend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class WorkshopModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<NotionModel> notions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NotionModel> getNotions() {
        return notions;
    }

    public void setNotions(List<NotionModel> notions) {
        this.notions = notions;
    }
}
