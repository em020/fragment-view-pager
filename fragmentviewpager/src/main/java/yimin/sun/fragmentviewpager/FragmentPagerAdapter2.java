package yimin.sun.fragmentviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by yzsh-sym on 2017/9/6.
 */

public abstract class FragmentPagerAdapter2 extends FragmentPagerAdapter {

    private FragmentManager fm;

    public abstract PagerFragment getItem2(int position);

    public FragmentPagerAdapter2(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return getItem2(position);
    }

    public FragmentManager getFragmentManager() {
        return fm;
    }

}
