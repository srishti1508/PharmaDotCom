package tgs.com.pharmadotcom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tgs.com.pharmadotcom.retrofit.ApiClient;
import tgs.com.pharmadotcom.retrofit.InterfaceApi;

public class Sale_credit extends AppCompatActivity {
RecyclerView recyclerView;
TableAdapter tableAdapter;
ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_credit);
        recyclerView=findViewById(R.id.sale_crdt_dtl);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Sale_credit.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);

        progressBar = (ProgressBar)findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.VISIBLE);

        getServiceResponseData();


    }

 public void getServiceResponseData(){
     InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
     Call<SaleCraditModel> call =api.detail_data("admin","1234","1");
     call.enqueue(new Callback<SaleCraditModel>() {
         @Override
         public void onResponse(Call<SaleCraditModel> call,Response<SaleCraditModel> response) {
           progressBar.setVisibility(View.GONE);
             recyclerView.setVisibility(View.VISIBLE);
             final SaleCraditModel status = response.body();
             if (status.getResponse().size()>0) {
                 tableAdapter = new TableAdapter(Sale_credit.this,status);
                 recyclerView.setAdapter(tableAdapter);
             } else {

                 recyclerView.setVisibility(View.GONE);
             }
         }
         @Override
         public void onFailure(Call<SaleCraditModel> call, Throwable t) {
             Toast.makeText(Sale_credit.this, "Somthing is wrong", Toast.LENGTH_SHORT).show();
             Sale_credit.this.onBackPressed();
         }
     });
 }

    private class TableAdapter  extends RecyclerView.Adapter<TableAdapter.MyViewHolder>{
        private Context mContext;
        private List<SaleCraditModel.Response> albumList;

        public TableAdapter(Context mContext,SaleCraditModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }

        @NonNull
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.salecrdt_detail, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull TableAdapter.MyViewHolder holder,int i) {
            SaleCraditModel.Response crdSale_model  = albumList.get(i);

            holder.cust_name.setText(crdSale_model.getCustomer());
            holder.cust_amt.setText(crdSale_model.getCredit_amt());
            holder.amt_rcvd.setText(crdSale_model.getRec_amt());

        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView cust_name,cust_amt,amt_rcvd;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                cust_name = itemView.findViewById(R.id.cust_name);
                cust_amt = itemView.findViewById(R.id.cust_amt);
                amt_rcvd = itemView.findViewById(R.id.amt_rcvd);
            }
        }
    }
}
