package search.yazhou.com.courserasearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private OnItemClickListener onItemClickLitener;
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
        Log.d("s","onCreateViewHolder called");
        LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new SearchViewHolder(inflater.inflate(R.layout.course_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder, final int position) {
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
        if(onItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLitener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    private void setData(Model model, SearchViewHolder viewHolder)
    {
        viewHolder.mCourseTextView.setText(model.getName());
        viewHolder.mUniversityTextView.setText(model.getUniversityName());
        if(model instanceof Specialization){
            viewHolder.mCourseCountTextView.setText(((Specialization)model).getTotoalCourseNum()+" "+mContext.getString(R.string.courses));
        }
        Picasso.with(mContext).load(model.getPhotoUrl()).placeholder(android.R.drawable.sym_def_app_icon).into(viewHolder.mImageView);
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mCourseTextView;
        TextView mUniversityTextView;
        TextView mCourseCountTextView;


        public SearchViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.course_iv);
            mCourseTextView = (TextView) itemView.findViewById(R.id.course_name_tv);
            mCourseCountTextView = (TextView) itemView.findViewById(R.id.course_counte_tv);
            mUniversityTextView = (TextView) itemView.findViewById(R.id.course_uni_name_tv);
            itemView.setBackgroundResource(R.drawable.background_selector);
        }
    }

    public void setOnItemClickLitener(OnItemClickListener onItemClickLitener)
    {
        this.onItemClickLitener = onItemClickLitener;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }
}
