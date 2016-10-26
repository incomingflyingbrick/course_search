package search.yazhou.com.courserasearch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by umeng on 10/26/16.
 */

public class NetworkTask {

    private OkHttpClient okHttpClient = new OkHttpClient();

    private static String spcializationUrl = "https://api.coursera.org/api/onDemandSpecializations.v1/";

    private static String courseUrl = "https://api.coursera.org/api/courses.v1/";

    private static String catalogUrl = "https://www.coursera.org/api/catalogResults.v2?";


    public NetworkTask() {

    }

    public List<Model> loadCourses(String url) {
        List<Model> dataList = new ArrayList<Model>();
        return dataList;
    }

    public Course loadCourseDetail(String courseId,String field) {
        Course course = new Course();
        Request request = new Request.Builder()
                .url(courseUrl+courseId+"?"+field)
                .build();
        try {

            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                parseCourse(course,new JSONObject(response.body().string()));
            }else{
                course = null;
            }
        }
        catch (JSONException e){

        }catch (IOException e) {

        }

        return course;
    }

    public Specialization loadSpicalizationDetail(String specialId,String field) {
        Specialization specialization = new Specialization();

        return specialization;
    }

    private Course parseCourse(Course course, JSONObject jsonObject) {
        course = jsonObject.optString();
        return course;
    }


}
// test url
// https://www.coursera.org/api/catalogResults.v2?q=search&query=machine%20learning&start=0&limit=5&fields=courseId,onDemandSpecializationId,courses.v1(name,photoUrl,partnerIds),onDemandSpecializations.v1(name,logo,courseIds,partnerIds),partners.v1(name)&includes=courseId,onDemandSpecializationId,courses.v1(partnerIds)

//course
//https://api.coursera.org/api/courses.v1/Gtv4Xb1-EeS-ViIACwYKVQ?fields=photoUrl,description,partnerIds

// spec
//https://api.coursera.org/api/onDemandSpecializations.v1/Q7ft0KTtEeWVehLHxyUMyQ?fields=logo,description,partnerIds

