/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Ala Jaidi
 */
public class Blog {
    
    private int id ;
    private String nomblog,description,image;
    private Date date ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomblog() {
        return nomblog;
    }

    public void setNomblog(String nomblog) {
        this.nomblog = nomblog;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", nomblog=" + nomblog + ", description=" + description + ", image=" + image + ", date=" + date + '}';
    }
    
    
    
}
