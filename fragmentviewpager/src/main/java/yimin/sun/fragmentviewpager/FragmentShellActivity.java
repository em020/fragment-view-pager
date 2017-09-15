package yimin.sun.fragmentviewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentShellActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context, Class<? extends Fragment> fragmentClass, Bundle fBundle) {
        Intent intent = new Intent(context, FragmentShellActivity.class);
        intent.putExtra("fClass", fragmentClass);
        intent.putExtra("fBundle", fBundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fvp_activity_fragment_shell);
        
        Class fragmentClass = (Class) getIntent().getSerializableExtra("fClass");
        Bundle bundle = getIntent().getBundleExtra("fBundle");

        Fragment fragment = Fragment.instantiate(this, fragmentClass.getName(), bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.root, fragment);
        ft.commit();

    }
}
