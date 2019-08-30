package tgs.com.pharmadotcom;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class Purchase_rtn extends AppCompatActivity {
RecyclerView recyclerView;
TableAdapter tableAdapter;
ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchs_rtn);
        recyclerView=findViewById(R.id.sale_crdt_dtl);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Purchase_rtn.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);

        progressBar = (ProgressBar)findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.VISIBLE);

        getServiceResponseData();


    }

 public void getServiceResponseData(){
     InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
     Call<PurchaseModel> call =api.detail_datapurchase("admin","1234","2");
     call.enqueue(new Callback<PurchaseModel>() {
         @Override
         public void onResponse(Call<PurchaseModel> call,Response<PurchaseModel> response) {
           progressBar.setVisibility(View.GONE);
             recyclerView.setVisibility(View.VISIBLE);
             final PurchaseModel status = response.body();
             if (status.getResponse().size()>0) {
                 tableAdapter = new TableAdapter(Purchase_rtn.this,status);
                 recyclerView.setAdapter(tableAdapter);
             } else {

                 recyclerView.setVisibility(View.GONE);
             }
         }
         @Override
         public void onFailure(Call<PurchaseModel> call, Throwable t) {
             Toast.makeText(Purchase_rtn.this, "Somthing is wrong", Toast.LENGTH_SHORT).show();
             Purchase_rtn.this.onBackPressed();
         }
     });
 }

    private class TableAdapter  extends RecyclerView.Adapter<TableAdapter.MyViewHolder>{
        private Context mContext;
        private List<PurchaseModel.Response> albumList;

        public TableAdapter(Context mContext,PurchaseModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }

        @NonNull
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.purchasertn_detail, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull TableAdapter.MyViewHolder holder,int i) {
            PurchaseModel.Response crdSale_model  = albumList.get(i);

            holder.supp_name.setText(crdSale_model.getSuppl());
            holder.crdt_amt.setText(crdSale_model.getCr_amt());


        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView supp_name,crdt_amt;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                supp_name = itemView.findViewById(R.id.supp_name);
                crdt_amt = itemView.findViewById(R.id.crdt_amt);

            }
        }
    }
}
