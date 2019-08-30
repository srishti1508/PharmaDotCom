package tgs.com.pharmadotcom;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tgs.com.pharmadotcom.retrofit.ApiClient;
import tgs.com.pharmadotcom.retrofit.InterfaceApi;

public class AdminActivity extends AppCompatActivity {
TextView credit_sale,credit_saleRTN,credit_receive,credit_tknamt,purchase_sale,purchase_saleRTN,paid_supp,credit_note,credit_paidAmt;
LinearLayout lay_slcr,lay_slrtn,lay_recev,lay_amttkn,lay_prucr,lay_purrtn,lay_supp,lay_note,lay_amtpad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        credit_sale = findViewById(R.id.credit_sale);
        credit_saleRTN = findViewById(R.id.credit_saleRTN);
        credit_receive = findViewById(R.id.credit_receive);
        credit_tknamt = findViewById(R.id.credit_tknamt);
        purchase_sale = findViewById(R.id.purchase_sale);
        purchase_saleRTN = findViewById(R.id.purchase_saleRTN);
        paid_supp = findViewById(R.id.paid_supp);
        credit_note = findViewById(R.id.credit_note);
        credit_paidAmt = findViewById(R.id.credit_paidAmt);


        lay_slcr = findViewById(R.id.lay_slcr);
        lay_slrtn = findViewById(R.id.lay_slrtn);
        lay_recev = findViewById(R.id.lay_recev);
        lay_amttkn = findViewById(R.id.lay_amttkn);
        lay_prucr = findViewById(R.id.lay_prucr);
        lay_purrtn = findViewById(R.id.lay_purrtn);
        lay_supp = findViewById(R.id.lay_supp);
        lay_note = findViewById(R.id.lay_note);
        lay_amtpad = findViewById(R.id.lay_amtpad);
        getServiceResponseData();

        lay_slcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                Intent intent = new Intent(AdminActivity.this,Sale_credit.class);
                startActivity(intent);
            }
        });

        lay_slrtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                Intent intent = new Intent(AdminActivity.this,Crdit_Return.class);
                startActivity(intent);
            }
        });

        lay_recev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                Intent intent = new Intent(AdminActivity.this,Crdt_receive.class);
                startActivity(intent);
            }
        });

        lay_amttkn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                Intent intent = new Intent(AdminActivity.this,Credit_tkn.class);
                startActivity(intent);
            }
        });

        lay_prucr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                Intent intent = new Intent(AdminActivity.this,Purchase_credit.class);
                startActivity(intent);
            }
        });

        lay_purrtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                Intent intent = new Intent(AdminActivity.this,Purchase_rtn.class);
                startActivity(intent);
            }
        });

        lay_supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                Intent intent = new Intent(AdminActivity.this,Purchase_supp.class);
                startActivity(intent);
            }
        });

        lay_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                Intent intent = new Intent(AdminActivity.this,Purchase_paid.class);
                startActivity(intent);
            }
        });

        lay_amtpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
                Intent intent = new Intent(AdminActivity.this,Parchase_amtpaid.class);
                startActivity(intent);
            }
        });



    }

    private void vibrate() {
        Vibrator v = (Vibrator) AdminActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100); // 5000 miliseconds = 5 seconds

    }

    private void getServiceResponseData() {
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<CreditModels> call = api.calculate_amt("admin", "1234");
        call.enqueue(new Callback<CreditModels>() {
            @Override
            public void onResponse(Call<CreditModels> call, Response<CreditModels> response) {


                final CreditModels status = response.body();
                credit_sale.setText(status.getResponse().get(0).getSales_cr());
                credit_saleRTN.setText(status.getResponse().get(0).getSales_ret());
                credit_receive.setText(status.getResponse().get(0).getReceived_amt());
                credit_tknamt.setText(status.getResponse().get(0).getAmount_tbtaken());
                purchase_sale.setText(status.getResponse().get(0).getPur_credit());
                purchase_saleRTN.setText(status.getResponse().get(0).getPur_retcr());
                paid_supp.setText(status.getResponse().get(0).getPaid_suppl());
                credit_note.setText(status.getResponse().get(0).getCr_note());
                credit_paidAmt.setText(status.getResponse().get(0).getAmt_topaid());

            }
            @Override
            public void onFailure(Call<CreditModels> call, Throwable t) {
                Toast.makeText(AdminActivity.this, "Somthing is wrong", Toast.LENGTH_SHORT).show();
                AdminActivity.this.onBackPressed();
            }
        });
    }
}
