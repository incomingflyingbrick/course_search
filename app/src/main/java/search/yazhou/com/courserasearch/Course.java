package search.yazhou.com.courserasearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by umeng on 10/26/16.
 */

public class Course extends Model{

    @SerializedName("name")
    private String courseName = "";

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
