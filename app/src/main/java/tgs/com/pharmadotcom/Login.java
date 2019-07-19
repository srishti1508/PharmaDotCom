package tgs.com.pharmadotcom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tgs.com.pharmadotcom.retrofit.ApiClient;
import tgs.com.pharmadotcom.retrofit.InterfaceApi;


public class Login extends AppCompatActivity {
    EditText Login,Passowrd;
    android.widget.Button Button;
    private ProgressDialog pDialog;
    Shared_Common_Pref shared_common_pref;

    // private static final String TAG = "Loginact";
    public static final String PREFS_NAME = "LoginPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        Login=findViewById(R.id.mail);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide_right);
        Login.startAnimation(anim);
        Passowrd=findViewById(R.id.password);
        Animation animtn = AnimationUtils.loadAnimation(this, R.anim.slide_right);
        Passowrd.startAnimation(animtn);
        Button=findViewById(R.id.button);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        shared_common_pref = new Shared_Common_Pref(Login.this);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getServiceResponseData();

                   /* if(Login.getText().toString().equals("admin") && Passowrd.getText().toString().equals("1234")) {
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("logged", "logged");
                        editor.putString("user_group_name", "admin");
                        editor.putString("user_id", "1");
                        editor.putString("user_name", "admin");
                        shared_common_pref.save(Shared_Common_Pref.userid,"1");
                        editor.apply();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("Outside_referal", "admin");
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Login.this, "Please Check Username and Password !!!", Toast.LENGTH_SHORT).show();
                    }*/

            }
        });
    }
    private void getServiceResponseData() {
        System.out.println("hello//////// = " +Login.getText().toString());
        System.out.println("hi....... = " +Passowrd.getText().toString());
        showDialog();
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<LoginModel> call = api.Login(ApiClient.Apikey, Login.getText().toString(), Passowrd.getText().toString());
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                final LoginModel loginModel = response.body();
                hideDialog();
                if (loginModel.getStatus().equals("1")) {
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("logged", "logged");
                    editor.putString("user_id", loginModel.getResponse().get(0).getLog_userid());
                    editor.putString("user_name", loginModel.getResponse().get(0).getLog_username());
                    editor.putString("user_group_name", loginModel.getResponse().get(0).getLog_usergroupname());
                    editor.putString("company_id", loginModel.getResponse().get(0).getLog_company());
                    editor.putString("company_name", loginModel.getResponse().get(0).getComapny_name());
                    editor.commit();
                    Toast.makeText(Login.this, ""+loginModel.getResponse().get(0).getComapny_name(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                hideDialog();
            }
        });
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
        pDialog.setCancelable(true);
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}