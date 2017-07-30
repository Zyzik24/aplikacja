package com.example.zuzia.oszczedzanie2.Model.Entities;


public abstract class Entity {
    private long id;

    public Entity(long id) {
        this.id = id;
    }

    public Entity() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return "id: " + getId();
    }
}
