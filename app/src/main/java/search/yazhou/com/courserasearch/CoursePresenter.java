package search.yazhou.com.courserasearch;

import android.app.Activity;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.List;

public class CoursePresenter {

    private MainActivity mActivity;

    public CoursePresenter(Activity activity) {
        mActivity = (MainActivity) activity;
    }

    public void getCourseList(String query, int page, int limit) {

        new MyTask(mActivity).execute("https://www.coursera.org/api/catalogResults.v2?q=search&query=" + query + "&start=" + page + "&limit=" + limit + "&fields=courseId,onDemandSpecializationId,courses.v1(name,photoUrl,partnerIds),onDemandSpecializations.v1(name,logo,courseIds,partnerIds),partners.v1(name)&includes=courseId,onDemandSpecializationId,courses.v1(partnerIds)");

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
            return networkTask.loadCourses(params[0]);

        }

        @Override
        protected void onPostExecute(List<Model> models) {
            super.onPostExecute(models);
            MainActivity mainActivity = (MainActivity) weakReference.get();
            if (models != null && models.size() > 0 && mainActivity != null) {
                mainActivity.dismissDialog();
                mainActivity.updateCourseList(models);
            }
        }
    }
}
