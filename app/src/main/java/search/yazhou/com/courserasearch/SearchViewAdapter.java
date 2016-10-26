package search.yazhou.com.courserasearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by umeng on 10/26/16.
 */

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.SearchViewHolder> {

    private Context mContext;

    private List<Model> mModelList;

    public SearchViewAdapter(Context context , List<Model> dataList){
        mContext = context;
        mModelList = dataList;
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

    }

    static class SearchViewHolder extends RecyclerView.ViewHolder{
        public SearchViewHolder(View itemView) {
            super(itemView);
        }
    }
}
