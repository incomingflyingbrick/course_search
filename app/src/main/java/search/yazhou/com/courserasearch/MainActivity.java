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
                Log.d("s",query);
                mSearchView.clearFocus();
                // now we need to search some course online
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });


    }

    public void updateCourseList(List<Model> datalist){

    }


}
