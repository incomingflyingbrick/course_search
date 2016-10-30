package search.yazhou.com.courserasearch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchViewAdapter.OnItemClickListener{

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

    private TextView mTextViewPlaceHolder;

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
        mTextViewPlaceHolder = (TextView) findViewById(R.id.place_holder_tv);
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

        if(datalist==null || datalist.size()==0){
            Toast.makeText(this,getString(R.string.no_result),Toast.LENGTH_SHORT).show();
            return;
        }

        if (mSearchViewAdapter == null) {
            mSearchViewAdapter = new SearchViewAdapter(this, mDataList);
            mSearchViewAdapter.setOnItemClickLitener(this);
            mRecyclerView.setAdapter(mSearchViewAdapter);
        }

        if (mPage == 0) {
            mDataList.clear();
        }
        mDataList.addAll(datalist);

        mSearchViewAdapter.notifyDataSetChanged();

        if(mRecyclerView.getAdapter().getItemCount()==0){
            mTextViewPlaceHolder.setVisibility(View.VISIBLE);
        }else{
            mTextViewPlaceHolder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this,CourseDetailActivity.class);
        intent.putExtra("courseObj",mDataList.get(position));
        startActivity(intent);
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
