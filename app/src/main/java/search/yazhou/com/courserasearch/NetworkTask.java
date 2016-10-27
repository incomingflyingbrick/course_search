package search.yazhou.com.courserasearch;

import android.graphics.PorterDuff;

import org.json.JSONArray;
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
        Request request = new Request.Builder().url(url).build();
        try {

            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                parseJSONArray(dataList, new JSONObject(response.body().string()));
            } else {
                dataList = null;
            }
        } catch (JSONException e) {

        } catch (IOException e) {

        } catch (Exception e) {
        }

        return dataList;
    }

    private void parseJSONArray(List<Model> dataList, JSONObject jsonObject) {
        JSONObject obj = jsonObject.optJSONObject("linked");
        JSONArray uniArray = jsonObject.optJSONArray("partners.v1");
        ArrayList<Model> modelArrayList = new ArrayList<Model>();
        for (int i = 0; i < uniArray.length(); i++) {
            JSONObject item = uniArray.optJSONObject(i);
            if (item != null) {
                Model model = new Model();
                modelArrayList.add(pareUni(item, model));
            }
        }
        //course
        JSONArray array = obj.optJSONArray("courses.v1");
        if (array != null) {
            ArrayList<Model> courseModel = new ArrayList<Model>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.optJSONObject(i);
                if (item != null) {
                    Course course = new Course();
                    parseCourse(course, item);
                    courseModel.add(course);
                }
            }
        }
        // specialization
        JSONArray speArray = obj.optJSONArray("onDemandSpecializations.v1");
        for (int i = 0; i < speArray.length(); i++) {

        }


    }

    private void parseSepcialization(Specialization specialization, JSONObject jsonObject) {
        specialization.setDiscription(jsonObject.optString("description"));
        specialization.setId(jsonObject.optString("id"));
        specialization.setSpecializationId(jsonObject.optString("id"));
        specialization.setPhotoUrl(jsonObject.optString("logo"));
        specialization.setPartnerId(jsonObject.optString("partnerIds"));
        JSONArray array = jsonObject.optJSONArray("courseIds");
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                if () {

                }
            }
        }
        specialization.setCoursesIds();
    }

    private Model pareUni(JSONObject jsonObject, Model model) {
        model.setPartnerId(jsonObject.optString("id"));
        model.setUniversityName(jsonObject.optString("name"));
        return model;
    }

    public Course loadCourseDetail(String courseId, String field) {
        Course course = new Course();
        Request request = new Request.Builder()
                .url(courseUrl + courseId + "?" + field)
                .build();
        try {

            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {

                parseCourse(course, new JSONObject(response.body().string()));
            } else {
                course = null;
            }
        } catch (JSONException e) {

        } catch (IOException e) {

        } catch (Exception e) {
        }

        return course;
    }

    public Specialization loadSpicalizationDetail(String specialId, String field) {
        Specialization specialization = new Specialization();

        return specialization;
    }

    private Course parseCourse(Course course, JSONObject jsonObject) {
        JSONArray obj = jsonObject.optJSONArray("elements");
        if (obj != null) {
            for (int i = 0; i < obj.length(); i++) {
                JSONObject item = obj.optJSONObject(i);
                course.setId(item.optString("id"));
                course.setDiscription(item.optString("description"));
                course.setPhotoUrl(item.optString("photoUrl"));
                course.setPartnerId(item.optJSONArray("partnerIds").optString(0));
            }
        }
        return course;
    }


}

// test url
// https://www.coursera.org/api/catalogResults.v2?q=search&query=machine%20learning&start=0&limit=5&fields=courseId,onDemandSpecializationId,courses.v1(name,photoUrl,partnerIds),onDemandSpecializations.v1(name,logo,courseIds,partnerIds),partners.v1(name)&includes=courseId,onDemandSpecializationId,courses.v1(partnerIds)

//course
//https://api.coursera.org/api/courses.v1/Gtv4Xb1-EeS-ViIACwYKVQ?fields=photoUrl,description,partnerIds

// spec
//https://api.coursera.org/api/onDemandSpecializations.v1/Q7ft0KTtEeWVehLHxyUMyQ?fields=logo,description,partnerIds

