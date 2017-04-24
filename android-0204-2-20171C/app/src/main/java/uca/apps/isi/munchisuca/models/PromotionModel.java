package uca.apps.isi.munchisuca.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Grecia Guzmán on 23/4/2017.
 */

public class PromotionModel extends RealmObject {

    public String name;
    public String description;
    public Date dateStart;
    public  Date dateEnd;
    public int  idMenu;
    public int idPlace;


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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

}
