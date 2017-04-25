package uca.apps.isi.munchisuca.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Mayuyo on 24/4/2017.
 */

public class TypeModel extends RealmObject {

    public String name;
    public String description;
    public int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
