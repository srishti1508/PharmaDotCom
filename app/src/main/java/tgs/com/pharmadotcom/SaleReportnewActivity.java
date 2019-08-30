package tgs.com.pharmadotcom;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tgs.com.pharmadotcom.retrofit.ApiClient;
import tgs.com.pharmadotcom.retrofit.InterfaceApi;

public class SaleReportnewActivity extends AppCompatActivity {

    TextView Search,todate,fromdate,Company;
    WebView webView;
    int mYear,mMonth,mDay;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sale_reportnew);
        Company=findViewById(R.id.Company);
        Company.setText(MainActivity.company_name);
        Search=findViewById(R.id.search);
        todate=findViewById(R.id.todate);
        fromdate=findViewById(R.id.fromdate);
        webView = findViewById(R.id.webview);

        progressBar = (ProgressBar)findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);



        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker = new DatePickerDialog(SaleReportnewActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker,int selectedyear,int selectedmonth,int selectedday) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        fromdate.setText(sdf.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.show();
            }
        });

        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker = new DatePickerDialog(SaleReportnewActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        todate.setText(sdf.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.show();
            }
        });


        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                if(CheckNetwork.isInternetAvailable(SaleReportnewActivity.this)) //returns true if internet available
                {
                    if (validate()) {
                        progressBar.setVisibility(View.VISIBLE);
                        getServiceResponseData(fromdate.getText().toString(),todate.getText().toString());
                    }
                }else
                {
                    Toast.makeText(SaleReportnewActivity.this,"No Internet Connection",1000).show();
                }
            }
        });



    }
    private void vibrate() {
        Vibrator v = (Vibrator) SaleReportnewActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100); // 5000 miliseconds = 5 seconds

    }
    private void getServiceResponseData(String From,String To) {
        //System.out.println("hello");
       // System.out.println("hiii "+MainActivity.user_name);
       // System.out.println("bye "+MainActivity.company_id);
       // System.out.println("hellooooooo "+From);
       // System.out.println("hiiiiii "+To);
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<SalereportModel> call = api.sales_report_getparam(MainActivity.user_name,MainActivity.company_id,From,To);
        call.enqueue(new Callback<SalereportModel>() {
            @Override
            public void onResponse(Call<SalereportModel> call, Response<SalereportModel> response) {
                final SalereportModel salereportModel = response.body();
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(salereportModel.getResponse().get(0).getUrl());
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setUseWideViewPort(true);
                webView.getSettings().setBuiltInZoomControls(true);


            }
            @Override
            public void onFailure(Call<SalereportModel> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }


    private boolean dateValidation(String startDate,String endDate,String dateFormat) {
        try
        {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            Date date1 = df.parse(endDate);
            Date startingDate = df.parse(startDate);

            if (date1.after(startingDate)){
                return true;
            }else if (date1.equals(startingDate)){
                return true;
            } else{
                return false;}
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private boolean validate() {
        if(fromdate.getText().toString().equals("")){
            Toast.makeText(SaleReportnewActivity.this, "Please Select From Date !", Toast.LENGTH_SHORT).show();
            return false;
        }else if(todate.getText().toString().equals("")){
            Toast.makeText(SaleReportnewActivity.this, "Please Select To Date !", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!dateValidation(fromdate.getText().toString(), todate.getText().toString(), "dd-MM-yyyy")) {
            Toast.makeText(SaleReportnewActivity.this, "Always From Date should be less than To Date", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}
