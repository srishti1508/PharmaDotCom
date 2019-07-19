package tgs.com.pharmadotcom;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
int mYear,mMonth,mDay;
LinearLayout data,bottom;
TextView Company,Search,ttlopeningstock,ttlopeningvalue,totalPurchase,totalSalereturn,totalPurchasereturn,totalSale,totalSaleValue,totalclosingstock,totalClosingValue;
EditText fromdate,todate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale);
        recycler_view=findViewById(R.id.recycler_view);
        toolbar=findViewById(R.id.toolbar);
        Company=findViewById(R.id.Company);
        Company.setText(MainActivity.company_name);
        fromdate=findViewById(R.id.fromdate);

        ttlopeningstock=findViewById(R.id.ttlopeningstock);
        ttlopeningvalue=findViewById(R.id.ttlopeningvalue);
        totalPurchase=findViewById(R.id.totalPurchase);
        totalSalereturn=findViewById(R.id.totalSalereturn);
        totalPurchasereturn=findViewById(R.id.totalPurchasereturn);
        totalSale=findViewById(R.id.totalSale);
        totalSaleValue=findViewById(R.id.totalSaleValue);
        totalclosingstock=findViewById(R.id.totalclosingstock);
        totalClosingValue=findViewById(R.id.totalClosingValue);

        Search=findViewById(R.id.search);
        todate=findViewById(R.id.todate);
        data=findViewById(R.id.data);
        bottom=findViewById(R.id.bottom);
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


        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    progressBar.setVisibility(View.VISIBLE);
                    getServiceResponseData(fromdate.getText().toString(), todate.getText().toString());
                }
            }
        });


        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker = new DatePickerDialog(SaleReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
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
                DatePickerDialog mDatePicker = new DatePickerDialog(SaleReportActivity.this, new DatePickerDialog.OnDateSetListener() {
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


    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SaleReportActivity.this);
        View view = LayoutInflater.from(SaleReportActivity.this).inflate(R.layout.alert, null);
        builder.setView(view);
        EditText fromdate=(EditText)view.findViewById(R.id.fromdate);
        EditText todate=(EditText)view.findViewById(R.id.todate);
        ImageView closed=(ImageView)view.findViewById(R.id.closed);
               Button button=(Button)view.findViewById(R.id.button);


        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker = new DatePickerDialog(SaleReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
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
                DatePickerDialog mDatePicker = new DatePickerDialog(SaleReportActivity.this, new DatePickerDialog.OnDateSetListener() {
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


        final AlertDialog dialog = builder.create();
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from=fromdate.getText().toString();
                String to=todate.getText().toString();
                //getServiceResponseData(from,to);
                dialog.dismiss();
            }
        });


        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void getServiceResponseData(String From,String To) {
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<SaleModel> call = api.mr_report_weeklySale(MainActivity.user_id,MainActivity.company_id,From,To);
        call.enqueue(new Callback<SaleModel>() {
            @Override
            public void onResponse(Call<SaleModel> call, Response<SaleModel> response) {
                final SaleModel loginModel = response.body();
                progressBar.setVisibility(View.GONE);
                recycler_view.setVisibility(View.VISIBLE);
                bottom.setVisibility(View.VISIBLE);

                ttlopeningstock.setText(loginModel.getTotal_calc().get(0).getTotal_medicine());
                ttlopeningvalue.setText(loginModel.getTotal_calc().get(0).getTotal_op_amt());
                totalPurchase.setText(loginModel.getTotal_calc().get(0).getTotal_pur_qty());
                totalSalereturn.setText(loginModel.getTotal_calc().get(0).getTotal_sale_ret_qty());
                totalPurchasereturn.setText(loginModel.getTotal_calc().get(0).getTotal_pur_ret_qty());
                totalSale.setText(loginModel.getTotal_calc().get(0).getTotal_sale_qty());
                totalSaleValue.setText(loginModel.getTotal_calc().get(0).getTotal_sale_amt());
                totalclosingstock.setText(loginModel.getTotal_calc().get(0).getTotal_res_qty());
                totalClosingValue.setText(loginModel.getTotal_calc().get(0).getTotal_res_amt());

                tableAdapter = new TableAdapter(this,loginModel);
                recycler_view.setAdapter(tableAdapter);

            }
            @Override
            public void onFailure(Call<SaleModel> call, Throwable t) {
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
             Toast.makeText(SaleReportActivity.this, "Please Select From Date !", Toast.LENGTH_SHORT).show();
             return false;
         }else if(todate.getText().toString().equals("")){
             Toast.makeText(SaleReportActivity.this, "Please Select To Date !", Toast.LENGTH_SHORT).show();
             return false;
         }else if (!dateValidation(fromdate.getText().toString(), todate.getText().toString(), "dd-MM-yyyy")) {
            Toast.makeText(SaleReportActivity.this, "Always From Date should be less than To Date", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
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
                    .inflate(R.layout.customer_single111, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TableAdapter.MyViewHolder holder, int position) {
            final SaleModel.Response table = albumList.get(position);

            holder.Medicine.setText(table.getMedicine());
            holder.openingstock.setText(table.getOpening_stock());
            holder.openingvalue.setText(table.getOpening_value());
            holder.Purchase.setText(table.getPurchase_qty());
            holder.Salereturn.setText(table.getSale_return_qty());
            holder.Purchasereturn.setText(table.getPurchase_return_qty());
            holder.Sale.setText(table.getSale_qty());
            holder.SaleValue.setText(table.getSale_amt());
            holder.closingstock.setText(table.getClosing_stock());
            holder.exdate.setText(table.getExpiry_date());
            holder.ClosingValue.setText(table.getClosing_value());
            holder.reorder.setText(table.getRe_order_qty());

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

            TextView Medicine,openingstock,openingvalue,Purchase,Salereturn,Purchasereturn,Sale,SaleValue,closingstock,exdate,ClosingValue,reorder;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                Medicine=itemView.findViewById(R.id.Medicine);
                openingstock=itemView.findViewById(R.id.openingstock);
                openingvalue=itemView.findViewById(R.id.openingvalue);
                Purchase=itemView.findViewById(R.id.Purchase);
                Salereturn=itemView.findViewById(R.id.Salereturn);
                Purchasereturn=itemView.findViewById(R.id.Purchasereturn);
                Sale=itemView.findViewById(R.id.Sale);
                SaleValue=itemView.findViewById(R.id.SaleValue);
                closingstock=itemView.findViewById(R.id.closingstock);
                exdate=itemView.findViewById(R.id.exdate);
                ClosingValue=itemView.findViewById(R.id.ClosingValue);
                reorder=itemView.findViewById(R.id.reorder);

            }
        }
    }
}
