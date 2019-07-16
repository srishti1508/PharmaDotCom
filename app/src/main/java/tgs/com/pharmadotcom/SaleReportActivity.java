package tgs.com.pharmadotcom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tgs.com.pharmadotcom.retrofit.ApiClient;
import tgs.com.pharmadotcom.retrofit.InterfaceApi;

public class SaleReportActivity extends AppCompatActivity {
RecyclerView recycler_view;
ProgressBar progressBar;
TableAdapter tableAdapter;
Toolbar toolbar;
ImageView rightimage;
TextView Company,fromdate,todate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_report3);
        recycler_view=findViewById(R.id.recycler_view);
        toolbar=findViewById(R.id.toolbar);
        Company=findViewById(R.id.Company);
        fromdate=findViewById(R.id.fromdate);
        rightimage=findViewById(R.id.rightimage);
        todate=findViewById(R.id.todate);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recycler_view.setLayoutManager(mLayoutManager);
       /* JazzyRecyclerViewScrollListener jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        recycler_view.setOnScrollListener(jazzyScrollListener);
        jazzyScrollListener.setTransitionEffect(11);*/

        progressBar = (ProgressBar)findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        getServiceResponseData();

        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     showAlert();
            }
        });


    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SaleReportActivity.this);
        View view = LayoutInflater.from(SaleReportActivity.this).inflate(R.layout.alert, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getServiceResponseData() {
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<SaleModel> call = api.mr_report_weeklySale("3","129","2019-07-01","2019-07-16");
        call.enqueue(new Callback<SaleModel>() {
            @Override
            public void onResponse(Call<SaleModel> call, Response<SaleModel> response) {
                final SaleModel loginModel = response.body();
                progressBar.setVisibility(View.GONE);
                recycler_view.setVisibility(View.VISIBLE);

                tableAdapter = new TableAdapter(this,loginModel);
                recycler_view.setAdapter(tableAdapter);

                Company.setText(loginModel.getFilter_params().get(0).getComapny_name());
                fromdate.setText(loginModel.getFilter_params().get(0).getSrch_from());
                todate.setText(loginModel.getFilter_params().get(0).getSrch_to());

               /* if (loginModel.getStatus().equals("1")) {
                    tableAdapter = new TableAdapter(this,loginModel);
                    recycler_view.setAdapter(tableAdapter);


                   *//* if (status.getResponse().size()>0) {
                        activityName.setText("Total Sale: " +status.getGrand_total());
                        tableAdapter = new SaleReport.TableAdapter(getActivity(),status);
                        recyclerView.setAdapter(tableAdapter);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        nodata.setVisibility(View.VISIBLE);
                        //Toast.makeText(getActivity(), ""+status.getMessage(), Toast.LENGTH_SHORT).show();
                    }*//*
                } else {
                    Toast.makeText(SaleReportActivity.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                }*/
            }
            @Override
            public void onFailure(Call<SaleModel> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }


    private class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder>  {
        private Callback<SaleModel> mContext;
        private List<SaleModel.Response> albumList;
        public TableAdapter(Callback<SaleModel> mContext, SaleModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sale, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TableAdapter.MyViewHolder holder, int position) {
            final SaleModel.Response table = albumList.get(position);

            holder.name.setText(table.getMedicine());
            holder.qty.setText(table.getSale_qty());
            holder.amount.setText(table.getSale_amt());
            holder.opening.setText(table.getOpening_stock());
            holder.value.setText(table.getOpening_value());

           /* if(table.getDiscount().equals("")){
                holder.discount.setText("-");
            }else{
                holder.discount.setText(table.getDiscount());
            }*/
        }
        @Override
        public int getItemCount() {
            return albumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView amount,qty,name,opening,value;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                name=itemView.findViewById(R.id.name);
                qty=itemView.findViewById(R.id.qty);
                amount=itemView.findViewById(R.id.amount);
                opening=itemView.findViewById(R.id.opening);
                value=itemView.findViewById(R.id.value);

            }
        }
    }
}
