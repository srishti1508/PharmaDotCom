package tgs.com.pharmadotcom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class Dashboard_Main extends AppCompatActivity {

    private static final String TAG = Dashboard_Main.class.getSimpleName();

    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_single);
        position = getIntent().getIntExtra("position",0);
        Fragment fragment = getFragmentName(position);
        if (savedInstanceState == null) {

            Fragment fragmentToReplace = null;
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            // fragmentToReplace = new StuSelectCourseFrag();
            transaction.setCustomAnimations(R.animator.fade_in,
                    R.animator.fade_out);
            fragmentToReplace = fragment;

            transaction.replace(R.id.frag_container, fragmentToReplace, TAG);
            transaction.commit();
        }
    }
    private Fragment getFragmentName(int position) {
        switch (position){

            case 0:
                //return new ProfileActivity();


            default:
                return null;

        }
    }
}
