package search.yazhou.com.courserasearch;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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
        ArrayMap<String, String> universityMap = new ArrayMap<String, String>();
        if (uniArray != null) {
            for (int i = 0; i < uniArray.length(); i++) {
                JSONObject item = uniArray.optJSONObject(i);
                if (item != null) {
                    Model model = new Model();
                    pareUni(item, model);
                    universityMap.put(model.getId(), model.getUniversityName());
                }
            }
        }

        //course
        JSONArray array = obj.optJSONArray("courses.v1");

        if (array != null && array.length() > 0) {
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.optJSONObject(i);
                if (item != null) {
                    Course course = null;
                    course = parseCourse(course, item);
                    if (course != null) {
                        course.setUniversityName(universityMap.get(course.getPartnerId()[0]));
                    }
                    dataList.add(course);
                }
            }
        }

        // specialization
        JSONArray speArray = obj.optJSONArray("onDemandSpecializations.v1");

        if (speArray != null && speArray.length() > 0) {

            for (int i = 0; i < speArray.length(); i++) {
                JSONObject item = speArray.optJSONObject(i);
                if (item != null) {
                    Specialization specialization = new Specialization();
                    specialization = parseSepcialization(specialization, item);
                    specialization.setUniversityName(universityMap.get(specialization.getPartnerId()[0]));
                    specialization.setTotoalCourseNum(specialization.getCoursesIds().length);
                    dataList.add(specialization);
                }
            }
        }

    }

    private Specialization parseSepcialization(Specialization specialization, JSONObject obj) {
        if (obj != null) {

            Gson gson = new Gson();
            specialization = gson.fromJson(obj.toString(), Specialization.class);

        }
        return specialization;
    }

    private Model pareUni(JSONObject jsonObject, Model model) {
        model.setUniversityName(jsonObject.optString("name"));
        model.setId(jsonObject.optString("id"));
        return model;
    }

    public void loadCourseDetail(Course course, String field) {
        Request request = new Request.Builder()
                .url(courseUrl + course.getId() + "?" + field)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                parseCourse(course, new JSONObject(response.body().string()));
            }
        } catch (IOException e) {

        } catch (JSONException e) {

        }


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

    private Course parseCourse(Course courses, JSONObject jsonObject) {
        if (jsonObject != null) {
            Gson gson = new Gson();
            courses = gson.fromJson(jsonObject.toString(), Course.class);
        }
        return courses;
    }


}

// test url
// https://www.coursera.org/api/catalogResults.v2?q=search&query=machine%20learning&start=0&limit=5&fields=courseId,onDemandSpecializationId,courses.v1(name,photoUrl,partnerIds),onDemandSpecializations.v1(name,logo,courseIds,partnerIds),partners.v1(name)&includes=courseId,onDemandSpecializationId,courses.v1(partnerIds)

//course
//https://api.coursera.org/api/courses.v1/Gtv4Xb1-EeS-ViIACwYKVQ?fields=photoUrl,description,partnerIds

// spec
//https://api.coursera.org/api/onDemandSpecializations.v1/Q7ft0KTtEeWVehLHxyUMyQ?fields=logo,description,partnerIds

