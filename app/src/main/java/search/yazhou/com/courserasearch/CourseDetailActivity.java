package search.yazhou.com.courserasearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseDetailActivity extends AppCompatActivity {

    private TextView mCourseNameTv;

    private TextView mUniversityNameTv;

    private TextView mCourseCountNameTv;

    private TextView mCourseDesciptionTv;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mImageView = (ImageView) findViewById(R.id.course_iv);
        mCourseNameTv = (TextView) findViewById(R.id.course_name_tv);
        mCourseCountNameTv = (TextView) findViewById(R.id.course_counte_tv);
        mUniversityNameTv = (TextView) findViewById(R.id.course_uni_name_tv);
        mCourseDesciptionTv = (TextView) findViewById(R.id.descrisption_tv);
        parseIntentData(getIntent());
    }

    private void parseIntentData(Intent intent){

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        parseIntentData(getIntent());
    }
}
