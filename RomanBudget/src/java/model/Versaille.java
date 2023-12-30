/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vothimaihoa
 */
public class Versaille {
    private int id;
    private Region region;
    private String name;
    private String image;
    

    public Versaille() {
    }

    public Versaille(Region region, String name, String image) {
        this.region = region;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    

    @Override
    public String toString() {
        return "Versaille{" + "id=" + id + ", region=" + region + ", name=" + name + ", image=" + image + '}';
    }    
}
