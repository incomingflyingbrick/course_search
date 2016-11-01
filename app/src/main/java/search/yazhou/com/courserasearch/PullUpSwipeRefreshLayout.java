package search.yazhou.com.courserasearch;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by umeng on 11/1/16.
 */

public class PullUpSwipeRefreshLayout extends SwipeRefreshLayout {

    private float oldY;

    public PullUpSwipeRefreshLayout(Context context) {
        super(context);
    }

    public PullUpSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldY = ev.getY();
                Log.d("motion down",ev.getY()+"");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("motion move",ev.getY()+"");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("motion up",ev.getY()+"");
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void setOnGenericMotionListener(OnGenericMotionListener l) {
        super.setOnGenericMotionListener(l);
    }
}
