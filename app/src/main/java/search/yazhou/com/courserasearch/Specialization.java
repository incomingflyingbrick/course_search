package search.yazhou.com.courserasearch;

import android.graphics.PorterDuff;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by umeng on 10/26/16.
 */

public class Specialization extends Model {

    private int totoalCourseNum;

    private ArrayList<String> coursesIds = new ArrayList<>();

    public ArrayList<String> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(ArrayList<String> coursesIds) {
        this.coursesIds = coursesIds;
    }
}
