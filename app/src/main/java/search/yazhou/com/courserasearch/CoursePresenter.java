package search.yazhou.com.courserasearch;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CoursePresenter {

    private MainActivity mActivity;

    private static int start = 0;

    private static int limit = 11;

    private String mQuery = "";

    public CoursePresenter(Activity activity) {
        mActivity = (MainActivity) activity;
    }

    public void getCourseList(String query) {
        if (query != null && !query.equals(mQuery)) {
            start = 0;
        }
        new MyTask(mActivity).execute("https://www.coursera.org/api/catalogResults.v2?q=search&query=" + mQuery + "&start=" + start + "&limit=" + limit + "&fields=courseId,onDemandSpecializationId,courses.v1(name,photoUrl,partnerIds),onDemandSpecializations.v1(name,logo,courseIds,partnerIds),partners.v1(name)&includes=courseId,onDemandSpecializationId,courses.v1(partnerIds)");
    }

    private class MyTask extends AsyncTask<String, Void, List<Model>> {

        private WeakReference<Activity> weakReference;

        public MyTask(MainActivity activity) {
            weakReference = new WeakReference<Activity>(activity);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((MainActivity) weakReference.get()).showDialog();
        }

        @Override
        protected List<Model> doInBackground(String... params) {
            NetworkTask networkTask = new NetworkTask();
            networkTask.loadSpicalizationDetail("Q7ft0KTtEeWVehLHxyUMyQ", "fields=logo,description,partnerIds");
            return (ArrayList<Model>) networkTask.loadCourses(params[0]);

        }

        @Override
        protected void onPostExecute(List<Model> models) {
            super.onPostExecute(models);
            MainActivity mainActivity = (MainActivity) weakReference.get();
            if (models != null && models.size() > 0 && mainActivity != null) {
                mainActivity.dismissDialog();
                if (start == 0) {
                    mainActivity.clearListResult();
                }
                mainActivity.updateCourseList(models);
                start = start + limit;
            }
        }
    }
}
