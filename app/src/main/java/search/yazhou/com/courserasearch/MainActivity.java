package search.yazhou.com.courserasearch;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private Toolbar mToolBar;

    private SearchView mSearchView;

    private RecyclerView mRecyclerView;

    private SearchViewAdapter mSearchViewAdapter;

    private LinearLayoutManager mLayoutManager;

    private CoursePresenter mPresenter;

    private ArrayList<Model> mDataList  = new ArrayList<>();

    private ProgressDialog mProgressDialog;

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
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mProgressDialog = new ProgressDialog(this);
        setSupportActionBar(mToolBar);

        // setting up search manager

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query)){
                    mSearchView.clearFocus();
                    mPresenter.getCourseList(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(mLayoutManager.findLastCompletelyVisibleItemPosition()==mDataList.size()-1){
                    mPresenter.getCourseList("");
                }
            }
        });
    }

    public void updateCourseList(List<Model> datalist) {
        if(mSearchViewAdapter==null){
            mSearchViewAdapter = new SearchViewAdapter(this,mDataList);
            mRecyclerView.setAdapter(mSearchViewAdapter);
        }
        mDataList.addAll(datalist);
        mSearchViewAdapter.notifyDataSetChanged();
    }

    public void showDialog(){
        if(!mProgressDialog.isShowing()){
            mProgressDialog.setMessage(getString(R.string.search_loading));
            mProgressDialog.show();
        }

    }

    public void dismissDialog(){
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    public void clearListResult(){
        mDataList.clear();
    }


}
