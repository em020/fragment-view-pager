package yimin.sun.fragmentviewpagersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yimin.sun.fragmentviewpager.PagerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends PagerFragment {

    int myFoo;

    public static BlankFragment newInstance(int foo) {
        Bundle bundle = new Bundle();
        bundle.putInt("foo", foo);
        BlankFragment bf = new BlankFragment();
        bf.setArguments(bundle);
        return bf;
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myFoo = getArguments().getInt("foo");
        Log.d("edmund", "onCreate of " + myFoo);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("edmund", "onCreateView of " + myFoo);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("BlankFragment " + myFoo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("edmund", "onDestroyView of " + myFoo);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("edmund", "onDestroy of " + myFoo);
    }

    @Override
    public void onSelect() {
        Log.d("edmund", "onSelect of " + myFoo);
    }

    @Override
    public void onLeave() {
        Log.d("edmund", "onLeave of " + myFoo);
    }
}
