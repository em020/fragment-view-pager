package yimin.sun.fragmentviewpagersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import yimin.sun.fragmentviewpager.FVPBlankFragment;
import yimin.sun.fragmentviewpager.FragmentPagerAdapter2;
import yimin.sun.fragmentviewpager.FragmentPagerAdapter2Tier2;
import yimin.sun.fragmentviewpager.FragmentViewPager;
import yimin.sun.fragmentviewpager.PagerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NestedFVPFragment extends PagerFragment {

//    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
//
//    @BindView(R.id.fvp)
//    FragmentViewPager fvp;

//    Unbinder unbinder;

    FragmentViewPager fvp;

    public NestedFVPFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("edmund", "onCreateView of NestedFVPFragment");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nested_fv, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*unbinder = ButterKnife.bind(this, view);*/

        fvp = (FragmentViewPager) view.findViewById(R.id.fvp);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        fvp.setAdapter(new FragmentPagerAdapter2Tier2(getChildFragmentManager()) {
            @Override
            public PagerFragment getItem2(int position) {
                return FVPBlankFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "C " + position;
            }
        });

        tabLayout.setupWithViewPager(fvp);
    }

    @Override
    public void onDestroyView() {
        Log.d("edmund", "onDestroyView of NestedFVPFragment");
        super.onDestroyView();
        /*unbinder.unbind();*/
        fvp = null;
    }

    @Override
    public void onSelect() {
        Log.d("edmund", "onSelect of NestedFVPFragment");
        fvp.foobar();

    }

    @Override
    public void onLeave() {
        Log.d("edmund", "onLeave of NestedFVPFragment");
        fvp.barfoo();
    }
}
