package uca.apps.isi.munchisuca.models;

/**
 * Created by mcama on 18/4/2017.
 */

public class ProfileModel {
    public String name;
    public String username;
    public String password;
    public int email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }
}
