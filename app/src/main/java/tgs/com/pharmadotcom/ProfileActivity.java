package tgs.com.pharmadotcom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class ProfileActivity extends AppCompatActivity {
TextView cmpnyname,username;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        cmpnyname=findViewById(R.id.cmpnyname);
        username=findViewById(R.id.username);
        username.setText(MainActivity.user_name);
        cmpnyname.setText(MainActivity.company_name);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        /*Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);*/
    }
}
