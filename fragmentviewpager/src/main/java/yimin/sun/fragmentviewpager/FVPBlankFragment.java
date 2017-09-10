package yimin.sun.fragmentviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FVPBlankFragment extends PagerFragment {

    int myFoo;

    public static FVPBlankFragment newInstance(int foo) {
        Bundle bundle = new Bundle();
        bundle.putInt("foo", foo);
        FVPBlankFragment bf = new FVPBlankFragment();
        bf.setArguments(bundle);
        return bf;
    }

    public FVPBlankFragment() {
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
        tv.setText("FVPBlankFragment " + myFoo);
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
