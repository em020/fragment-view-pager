package yimin.sun.fragmentviewpager;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by yzsh-sym on 2017/9/6.
 */

public class FragmentViewPager extends ViewPager {

    private int prevPos = -1;

    public FragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    OnPageChangeListener l = new SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            // previous onLeave, current onSelect
            if (prevPos != getCurrentItem()) {
                Fragment prevFragment = getFragment(prevPos);
                if (prevFragment instanceof IPagerFragment) {
                    ((IPagerFragment) prevFragment).onLeave();
                }
            }

            Fragment fragment = getFragment(getCurrentItem());
            if (fragment instanceof IPagerFragment) {
                ((IPagerFragment) fragment).onSelect();
            }

            prevPos = getCurrentItem();
        }
    };

    public void foobar() {
        /*l.onPageSelected(getCurrentItem());*/
        Fragment fragment = getFragment(getCurrentItem());
        if (fragment instanceof IPagerFragment) {
            ((IPagerFragment) fragment).onSelect();
        }

        prevPos = getCurrentItem();
    }

    public void barfoo() {
        Fragment fragment = getFragment(getCurrentItem());
        if (fragment instanceof IPagerFragment) {
            ((IPagerFragment) fragment).onLeave();
        }
    }

    @Override
    public void setAdapter(final PagerAdapter adapter) {
        super.setAdapter(adapter);
        // implement onSelect onLeave

        if ( !(adapter instanceof FragmentPagerAdapter2) ) {
            throw new IllegalArgumentException("FragmentViewPager only accepts FragmentPagerAdapter2 instances as adapter!");
        }

        //在post中设置setOnPageChangeListener是为了应对在从后台杀死状态恢复现场时
        post(new Runnable() {
            @Override
            public void run() {
                //noinspection deprecation
                setOnPageChangeListener(l);
                if (adapter instanceof FragmentPagerAdapter2Tier2) {
                    // 嵌套的情况，不主动触发onSelect
                } else {
                    l.onPageSelected(getCurrentItem());
                }

            }
        });
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        FVPSavedState ss = new FVPSavedState(superState);
        ss.prevPos = prevPos;
        return ss;

    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        FVPSavedState ss = (FVPSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        prevPos = ss.prevPos;
    }

    // add set scrollable

    public void pageRight() {
        setCurrentItem(getCurrentItem() + 1);

    }

    public void pageLeft() {
        setCurrentItem(getCurrentItem() - 1);
    }


    private Fragment getFragment(int position) {
        FragmentPagerAdapter2 fpAdapter = (FragmentPagerAdapter2) getAdapter();
        FragmentManager fm = fpAdapter.getFragmentManager();
        return fm.findFragmentByTag(makeFragmentName(getId(), fpAdapter.getItemId(position)));
    }


    // copied from FragmentPagerAdapter
    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }


    private static class FVPSavedState extends AbsSavedState {

        int prevPos;

        FVPSavedState(Parcelable superState) {
            super(superState);
        }

        FVPSavedState(Parcel source) {
            this(source, null);
        }

        FVPSavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            prevPos = source.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(prevPos);
        }

        public static final Parcelable.Creator<FVPSavedState> CREATOR = new Parcelable.ClassLoaderCreator<FVPSavedState>() {

            @Override
            public FVPSavedState createFromParcel(Parcel source) {
                return new FVPSavedState(source);
            }

            @Override
            public FVPSavedState createFromParcel(Parcel source, ClassLoader loader) {
                return new FVPSavedState(source, loader);
            }

            @Override
            public FVPSavedState[] newArray(int size) {
                return new FVPSavedState[size];
            }
        };
    }
}
