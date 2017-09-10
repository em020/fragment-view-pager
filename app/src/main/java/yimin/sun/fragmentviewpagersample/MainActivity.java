package yimin.sun.fragmentviewpagersample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import yimin.sun.fragmentviewpager.FVPBlankFragment;
import yimin.sun.fragmentviewpager.FragmentPagerAdapter2;
import yimin.sun.fragmentviewpager.FragmentViewPager;
import yimin.sun.fragmentviewpager.PagerFragment;

public class MainActivity extends AppCompatActivity {
    FragmentViewPager fragmentViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentViewPager = (FragmentViewPager) findViewById(R.id.vp);
        fragmentViewPager.setAdapter(new FragmentPagerAdapter2(getSupportFragmentManager()) {
            @Override
            public PagerFragment getItem2(int position) {
                return FVPBlankFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 20;
            }
        });

    }

    public void pageLeft(View view) {
        fragmentViewPager.pageLeft();
    }

    public void pageRight(View view) {
        fragmentViewPager.pageRight();
    }

    public void pagePick(View view) {
        final NumberPicker np = new NumberPicker(this);
        np.setMinValue(0);
        np.setMaxValue(999);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("aaa")
                .setMessage("bbb")
                .setView(np)
                .setPositiveButton("XX", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int val = np.getValue();
                        fragmentViewPager.setCurrentItem(val);
                    }
                })
                .create().show();


    }
}
