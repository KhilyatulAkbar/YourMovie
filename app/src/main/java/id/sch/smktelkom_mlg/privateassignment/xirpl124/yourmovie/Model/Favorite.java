package id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by user on 14/05/2017.
 */

public class Favorite extends SugarRecord implements Serializable {
    public String title;
    public String overview;
    public int color;

    public Favorite() {

    }

    public Favorite(String title, String overview, int color) {
        this.title = title;
        this.overview = overview;
        this.color = color;
    }
}
