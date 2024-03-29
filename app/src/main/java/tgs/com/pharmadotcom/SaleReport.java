package tgs.com.pharmadotcom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import tgs.com.pharmadotcom.retrofit.ApiClient;
import tgs.com.pharmadotcom.retrofit.InterfaceApi;


public class SaleReport extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Button manageprofile, fee_structure, change_pwd;
    ImageView rightimage;
    TextView name, activityName,nodata,dateshow;
    String date="";
    TableAdapter tableAdapter;
    Dialog dialog;
    String month, SelectedYear;
    private List<SaleModel> albumList;
    public static  String SelectedMonth;
    int currnet_year, decrease, increase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sale, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView=view.findViewById(R.id.recycler_view);
        rightimage=view.findViewById(R.id.rightimage);
        activityName=view.findViewById(R.id.activityName);
        nodata=view.findViewById(R.id.nodata);
        dateshow=view.findViewById(R.id.dateshow);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime = sdf.format(new Date());
        dateshow.setText("Date: " +currentDateandTime);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        progressBar = (ProgressBar)view.findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

        //setHasOptionsMenu(true);
        getServiceResponseData(date);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*SaleDateReport fragment = new SaleDateReport();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.fade_in,
                        R.animator.fade_out);
                ft.replace(R.id.frag_container, fragment);
                ft.commit();*/
            }
        });
        return view;
    }

    private void getServiceResponseData(String Date) {

        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<SaleModel> call = api.mr_report_weeklySale("1","129","2019-07-16","2019-07-16");
        call.enqueue(new Callback<SaleModel>() {
            @Override
            public void onResponse(Call<SaleModel> call, Response<SaleModel> response) {
              progressBar.setVisibility(View.GONE);
              recyclerView.setVisibility(View.VISIBLE);
                final SaleModel status = response.body();

                if (status.getResponse().size()>0) {
                    activityName.setText("Total Sale: " +status.getGrand_total());
                    tableAdapter = new TableAdapter(getActivity(),status);
                    recyclerView.setAdapter(tableAdapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                    //Toast.makeText(getActivity(), ""+status.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SaleModel> call, Throwable t) {

                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }

    private class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder>  {
        private Context mContext;
        private List<SaleModel.Response> albumList;
        public TableAdapter(Context mContext,SaleModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.customer_single111, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TableAdapter.MyViewHolder holder, int position) {
            final SaleModel.Response table = albumList.get(position);

         /*   holder.subtotal.setText(table.getSubtotal());
            holder.total.setText(table.getTotal());
            holder.paid.setText(table.getStatus());

            if(table.getDiscount().equals("")){
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

            TextView subtotal,discount,total,paid;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

               /* subtotal=itemView.findViewById(R.id.subtotal);
                discount=itemView.findViewById(R.id.discount);
                total=itemView.findViewById(R.id.total);
                paid=itemView.findViewById(R.id.paid);*/
            }
        }
    }
}
