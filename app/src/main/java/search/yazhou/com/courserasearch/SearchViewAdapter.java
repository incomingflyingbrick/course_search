package search.yazhou.com.courserasearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by umeng on 10/26/16.
 */

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.SearchViewHolder> {

    private Context mContext;
    private static final int COURSE = 2;
    private static final int SPECIALIZATION = 1;
    private List<Model> mModelList;

    public SearchViewAdapter(Context context, List<Model> dataList) {
        mContext = context;
        mModelList = dataList;
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mModelList.get(position) instanceof Course){
            return COURSE;
        }else if(mModelList.get(position) instanceof Specialization){
            return SPECIALIZATION;
        }
        return 1;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new SearchViewHolder(inflater.inflate(R.layout.course_list,parent,false));
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        int type = getItemViewType(position);
        Course course;
        Specialization specialization;
        if(type == COURSE){
            course = (Course)mModelList.get(position);
            setData(course,holder);
        }else{
            specialization =(Specialization) mModelList.get(position);
            setData(specialization,holder);
        }
    }

    private void setData(Model model, SearchViewHolder viewHolder)
    {
        viewHolder.mCourseTextView.setText(model.getName());
        viewHolder.mUniversityTextView.setText(model.getUniversityName());
        Picasso.with(mContext).load(model.getPhotoUrl()).into(viewHolder.mImageView);
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mCourseTextView;
        public TextView mUniversityTextView;
        public TextView mCourseCountTextView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.course_iv);
            mCourseTextView = (TextView) itemView.findViewById(R.id.course_name_tv);
            mCourseCountTextView = (TextView) itemView.findViewById(R.id.course_counte_tv);
            mUniversityTextView = (TextView) itemView.findViewById(R.id.course_uni_name_tv);
        }
    }
}
