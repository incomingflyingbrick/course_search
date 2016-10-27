package search.yazhou.com.courserasearch;

import android.graphics.PorterDuff;

import java.util.ArrayList;

/**
 * Created by umeng on 10/26/16.
 */

public class Specialization extends Model {

    private int totoalCourseNum;

    private String specializationId = "";

    private ArrayList<String> coursesIds = new ArrayList<>();

    public int getTotoalCourseNum() {
        return totoalCourseNum;
    }

    public void setTotoalCourseNum(int totoalCourseNum) {
        this.totoalCourseNum = totoalCourseNum;
    }

    public String getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(String specializationId) {
        this.specializationId = specializationId;
    }

    public ArrayList<String> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(ArrayList<String> coursesIds) {
        this.coursesIds = coursesIds;
    }
}
