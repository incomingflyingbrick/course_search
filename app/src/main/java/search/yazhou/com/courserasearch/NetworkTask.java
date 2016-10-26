package search.yazhou.com.courserasearch;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by umeng on 10/26/16.
 */

public class NetworkTask {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    private String url;

    public NetworkTask(String url){

    }

    public List<Model> loadCourses(String url){
        List<Model> dataList = new ArrayList<Model>();
        return dataList;
    }

    public Course loadCourseDetail(String courseId){
        Course course = new Course();

        return course;
    }

    public Specialization loadSpicalizationDetail(String specialId){
        Specialization specialization = new Specialization();

        return specialization;
    }




}
