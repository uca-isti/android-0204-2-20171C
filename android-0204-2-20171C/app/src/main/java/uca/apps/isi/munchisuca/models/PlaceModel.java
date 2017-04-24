package uca.apps.isi.munchisuca.models;

import io.realm.RealmObject;

/**
 * Created by mcama on 18/4/2017.
 */

public class PlaceModel extends RealmObject {
    public String name;
    public String description;
    public String latitude;
    public String longitude;
    public String cover;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
