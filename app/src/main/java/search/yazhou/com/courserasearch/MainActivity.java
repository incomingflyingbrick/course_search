package search.yazhou.com.courserasearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;

    private SearchView mSearchView;

    private RecyclerView mRecyclerView;

    private CoursePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new CoursePresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // find views !!!
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mSearchView = (SearchView) findViewById(R.id.search_view);
        setSupportActionBar(mToolBar);

        // setting up search manager

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("s", query);
                mSearchView.clearFocus();
                // now we need to search some course online
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        NetworkTask networkTask = new NetworkTask();
                        //networkTask.loadSpicalizationDetail("Q7ft0KTtEeWVehLHxyUMyQ", "fields=logo,description,partnerIds");
                        networkTask.loadCourses("https://www.coursera.org/api/catalogResults.v2?q=search&query=machine%20learning&start=0&limit=5&fields=courseId,onDemandSpecializationId,courses.v1(name,photoUrl,partnerIds),onDemandSpecializations.v1(name,logo,courseIds,partnerIds),partners.v1(name)&includes=courseId,onDemandSpecializationId,courses.v1(partnerIds)");
                    }
                });
                thread.start();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });


    }

    public void updateCourseList(List<Model> datalist) {

    }


}
