package search.yazhou.com.courserasearch;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
            Log.e("e", e.getMessage());
        }

        return dataList;
    }

    private void parseJSONArray(List<Model> dataList, JSONObject jsonObject) {
        JSONObject obj = jsonObject.optJSONObject("linked");
        JSONArray uniArray = obj.optJSONArray("partners.v1");
        ArrayList<Model> modelArrayList = new ArrayList<Model>();
        if (uniArray!=null) {
            for (int i = 0; i < uniArray.length(); i++) {
                JSONObject item = uniArray.optJSONObject(i);
                if (item != null) {
                    Model model = new Model();
                    modelArrayList.add(pareUni(item, model));
                }
            }
        }

        Collections.sort(modelArrayList, new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        Gson gson = new Gson();
        Log.d("d",gson.toJson(modelArrayList));

        //course
        JSONArray array = obj.optJSONArray("courses.v1");
        if (array != null)

        {
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
        if (speArray != null)

        {
            ArrayList<Model> specializationList = new ArrayList<Model>();
            for (int i = 0; i < speArray.length(); i++) {
                JSONObject item = speArray.optJSONObject(i);
                if (item != null) {
                    Specialization specialization = new Specialization();
                    specializationList.add(parseSepcialization(specialization, item));
                }
            }
        }


    }

    private Specialization parseSepcialization(Specialization specialization, JSONObject jsonObject) {
        JSONArray obj = jsonObject.optJSONArray("elements");
        if (obj != null) {

            Gson gson = new Gson();
            for (int i = 0; i < obj.length(); i++) {
                JSONObject item = obj.optJSONObject(i);
                specialization = gson.fromJson(item.toString(), Specialization.class);
            }
        }
        return specialization;
    }

    private Model pareUni(JSONObject jsonObject, Model model) {
        model.setUniversityName(jsonObject.optString("name"));
        model.setId(jsonObject.optString("id"));
        return model;
    }

    public Course loadCourseDetail(String courseId, String field) {
        Course course = null;
        Request request = new Request.Builder()
                .url(courseUrl + courseId + "?" + field)
                .build();
        try {

            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                course = parseCourse(course, new JSONObject(response.body().string()));

            } else {
                course = null;
            }
        } catch (IOException e) {

        } catch (JSONException e) {

        }

        return course;
    }

    public Specialization loadSpicalizationDetail(String specialId, String field) {
        Specialization specialization = new Specialization();
        Request request = new Request.Builder()
                .url(spcializationUrl + specialId + "?" + field)
                .build();
        try {

            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject obj = new JSONObject(response.body().string());
                specialization = parseSepcialization(specialization, obj);
            } else {
                specialization = null;
            }
        } catch (IOException e) {

        } catch (JSONException e) {

        }
        return specialization;
    }

    private Course parseCourse(Course course, JSONObject jsonObject) {
        JSONArray obj = jsonObject.optJSONArray("elements");
        if (obj != null) {
            Gson gson = new Gson();
            for (int i = 0; i < obj.length(); i++) {
                JSONObject item = obj.optJSONObject(i);
                course = gson.fromJson(item.toString(), Course.class);
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

