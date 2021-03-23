package ru.sapteh.dao;

import java.util.List;

public interface Dao<Entity, Key> {
    List<Entity> readByAll();
    void create (Entity entity);
    void update (Entity entity);
    void delete (Entity entity);
}