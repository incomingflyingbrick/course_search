package search.yazhou.com.courserasearch;

import android.app.Activity;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

public class CoursePresenter {

    private MainActivity mActivity;

    /**
     *
     * @param activity passing the MainActivity
     */
    public CoursePresenter(Activity activity) {
        mActivity = (MainActivity) activity;
    }

    /**
     * get list of search result from catalog api
     * @param query
     * @param page start index
     * @param limit number of item to be returned from the api
     */
    public void getCourseList(String query, int page, int limit) {

        new MyTask(mActivity).execute("https://www.coursera.org/api/catalogResults.v2?q=search&query=" + query + "&start=" + page + "&limit=" + limit + "&fields=courseId,onDemandSpecializationId,courses.v1(name,photoUrl,partnerIds,description),onDemandSpecializations.v1(name,logo,courseIds,partnerIds,description),partners.v1(name)&includes=courseId,onDemandSpecializationId,courses.v1(partnerIds)");

    }

    /**
     * fetch courses
     */
    private class MyTask extends AsyncTask<String, Void, List<Model>> {
        // prevent memory leak
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
            networkTask.loadCourses(params[0]);
            return networkTask.loadCourses(params[0]);
        }

        @Override
        protected void onPostExecute(List<Model> models) {
            super.onPostExecute(models);
            MainActivity mainActivity = (MainActivity) weakReference.get();
            if (mainActivity != null) {
                mainActivity.dismissDialog();
                if (models != null && models.size() > 0) {
                    mainActivity.updateCourseList(models);
                }
            }

        }
    }
}
