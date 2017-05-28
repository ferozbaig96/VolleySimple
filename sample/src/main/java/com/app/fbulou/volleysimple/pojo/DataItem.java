package com.app.fbulou.volleysimple.pojo;

import com.google.gson.annotations.SerializedName;

public class DataItem {

    @SerializedName("id")
    private int id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("avatar")
    private String avatar;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "last_name = '" + lastName + '\'' +
                        ",id = '" + id + '\'' +
                        ",avatar = '" + avatar + '\'' +
                        ",first_name = '" + firstName + '\'' +
                        "}";
    }
}