package search.yazhou.com.courserasearch;

import android.graphics.PorterDuff;

import com.google.gson.annotations.SerializedName;



/**
 * Created by umeng on 10/26/16.
 */

public class Specialization extends Model {

    private int totoalCourseNum;

    @SerializedName("courseIds")
    private  String [] coursesIds;

    public String [] getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(String [] coursesIds) {
        this.coursesIds = coursesIds;
    }

    public int getTotoalCourseNum() {
        return totoalCourseNum;
    }

    public void setTotoalCourseNum(int totoalCourseNum) {
        this.totoalCourseNum = totoalCourseNum;
    }
}
