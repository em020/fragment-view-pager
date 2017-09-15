package yimin.sun.fragmentviewpagersample;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yimin.sun.fragmentviewpager.FVPBlankFragment;
import yimin.sun.fragmentviewpager.FragmentPagerAdapter2;
import yimin.sun.fragmentviewpager.FragmentViewPager;
import yimin.sun.fragmentviewpager.PagerFragment;

public class NestedFVPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_fvp);

        FragmentViewPager fvp = (FragmentViewPager) findViewById(R.id.fvp);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        fvp.setAdapter(new FragmentPagerAdapter2(getSupportFragmentManager()) {
            @Override
            public PagerFragment getItem2(int position) {
                if (position == 1) {
                    return new NestedFVPFragment();
                } else {
                    return new BlankFragment();
                    /*return FVPBlankFragment.newInstance(position);*/
                }
                /*return FVPBlankFragment.newInstance(position);*/
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "parent" + position;
            }
        });

        tabLayout.setupWithViewPager(fvp);
    }
}
