package com.x4fhuozhu.app.bean;

/**
 * Created by vonde on 2018/9/26.
 */

public class ModelMenuItem {

    private String name;
    private String permissionCode;

    private int image;
    private int badgeNumber;

    private Class activity;

    public ModelMenuItem(String name, int image) {
        this.name = name;
        this.image = image;
    }
    public ModelMenuItem(String name, int image, Class activity) {
        this.name = name;
        this.image = image;
        this.activity = activity;
    }


    public ModelMenuItem(int image, Class activity) {
        this.image = image;
        this.activity = activity;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(int badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
