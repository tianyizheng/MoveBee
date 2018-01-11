/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.annotations.SerializedName;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *Serialized for gson to create posters
 * @author Tianyi Zheng
 */
@ManagedBean
@SessionScoped
public class Posters {

    /**
    *Thumbnail String from Rotten Tomatoes.
    */
    @SerializedName("thumbnail")
    private String thumbnail;

  /**
  * gets the thumbnail images.
  * @return String
  */
    public String getThumbnail() {
        return thumbnail;
    }

  /**
  * sets the thumbnail images.
  * @param thumbnail
  */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}