package yimin.sun.fragmentviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzsh-sym on 2017/9/6.
 */

public abstract class PagerFragment extends Fragment /*implements IPagerFragment*/ {

    private final String TAG = "PF-lifecycle";

    public static final int VIEW_CREATED = 103;
    public static final int SELECTED = 100;
    public static final int JUST_LEFT = 101;
    public static final int VIEW_DESTROYED = 102;

    private int mState;
    private List<Runnable> onSelectRunnableList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate of " + getClass().getSimpleName() + this.hashCode());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated of " + getClass().getSimpleName() + this.hashCode());
        mState = VIEW_CREATED;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView of " + getClass().getSimpleName() + this.hashCode());
        mState = VIEW_DESTROYED;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy of " + getClass().getSimpleName() + this.hashCode());
    }

    protected void onSelectInner() {
        Log.d(TAG, "onSelectInner of " + getClass().getSimpleName() + this.hashCode());
        mState = SELECTED;
        // fuck the queue
        for (Runnable runnable : onSelectRunnableList) {
            if (runnable != null) {
                runnable.run();
            }
        }
        onSelectRunnableList.clear();
        onSelect();
    }

    protected void onLeaveInner() {
        Log.d(TAG, "onLeaveInner of " + getClass().getSimpleName() + this.hashCode());
        mState = JUST_LEFT;
        onLeave();
    }

    public int getState() {
        return mState;
    }

    public void addOnSelectRunnable(@Nullable Runnable runnable) {
        onSelectRunnableList.add(runnable);
    }

    public abstract void onSelect();
    public abstract void onLeave();
}
