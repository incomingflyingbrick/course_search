package search.yazhou.com.courserasearch;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CourseDetailActivity extends AppCompatActivity {

    private TextView mCourseNameTv;

    private TextView mUniversityNameTv;

    private TextView mCourseCountNameTv;

    private TextView mCourseDesciptionTv;

    private ImageView mImageView;

    private Model mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getSupportActionBar()!=null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Detail");
        }

        mImageView = (ImageView) findViewById(R.id.detail_imageview);
        mCourseNameTv = (TextView) findViewById(R.id.course_name_tv);
        mCourseCountNameTv = (TextView) findViewById(R.id.course_counte_tv);
        mUniversityNameTv = (TextView) findViewById(R.id.course_uni_name_tv);
        mCourseDesciptionTv = (TextView) findViewById(R.id.descrisption_tv);
        parseIntentData(getIntent());
    }

    private void parseIntentData(Intent intent){
        mModel = (Model) intent.getSerializableExtra("courseObj");
        if(mModel!=null){
            updateUI();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        parseIntentData(getIntent());
    }

    private void updateUI(){
        Picasso.with(this).load(mModel.getPhotoUrl()).placeholder(android.R.drawable.sym_def_app_icon).into(mImageView);
        mCourseNameTv.setText(mModel.getName());
        mUniversityNameTv.setText(mModel.getUniversityName());
        mCourseDesciptionTv.setText(mModel.getDiscription());
        if(mModel instanceof Specialization){
            mCourseCountNameTv.setText(((Specialization)mModel).getTotoalCourseNum()+" "+getString(R.string.courses));
        }
    }
}
