package yimin.sun.fragmentviewpagersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import yimin.sun.fragmentviewpager.ActivityAdapter;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActivityAdapter adapter = new ActivityAdapter();
        adapter.addActivityItem("Simple sample", MainActivity.class);
        adapter.addActivityItem("Nested sample", NestedFVPActivity.class);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setAdapter(adapter);
    }
}
