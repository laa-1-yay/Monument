package com.xkcd.xkcd;

import java.util.ArrayList;

/**
 * Created by laavanye on 19/3/16.
 */
public class Places {
    String name;
    String address;
    String image;
    ArrayList<Checkpoints> checkpoints;

    public Places(String name, ArrayList<Checkpoints> checkpoints, String image, String address) {
        this.name = name;
        this.checkpoints = checkpoints;
        this.image = image;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Checkpoints> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(ArrayList<Checkpoints> checkpoints) {
        this.checkpoints = checkpoints;
    }
}
