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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;

    private SearchView mSearchView;

    private RecyclerView mRecyclerView;

    private SearchViewAdapter mSearchViewAdapter;

    private LinearLayoutManager mLayoutManager;

    private CoursePresenter mPresenter;

    private ArrayList<Model> mDataList = new ArrayList<>();

    private ProgressDialog mProgressDialog;

    private String mQuery = "";

    private int mPage = 0;

    private int mLimit = 15;

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
                if (!TextUtils.isEmpty(query)) {
                    mQuery = query;// record the last keyword entered by user
                    mPage = 0;
                    mSearchView.clearFocus();
                    mPresenter.getCourseList(query, 0, mLimit);
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
                if (mLayoutManager.findLastCompletelyVisibleItemPosition() == mDataList.size() - 1) {
                    mPage += mLimit;
                    mPresenter.getCourseList(mQuery, mPage, mLimit);
                }
            }
        });
    }

    public void updateCourseList(List<Model> datalist) {
        if (mSearchViewAdapter == null) {
            mSearchViewAdapter = new SearchViewAdapter(this, mDataList);
            mRecyclerView.setAdapter(mSearchViewAdapter);
        }
        if (mPage == 0) {
            mDataList.clear();
        }
        mDataList.addAll(datalist);
        mSearchViewAdapter.notifyDataSetChanged();
    }

    public void showDialog() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(getString(R.string.search_loading));
            mProgressDialog.show();
        }

    }

    public void dismissDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
