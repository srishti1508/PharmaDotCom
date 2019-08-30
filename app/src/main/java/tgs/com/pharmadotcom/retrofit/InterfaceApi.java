package tgs.com.pharmadotcom.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tgs.com.pharmadotcom.CreditModels;
import tgs.com.pharmadotcom.LoginModel;
import tgs.com.pharmadotcom.PurchaseModel;
import tgs.com.pharmadotcom.SaleCraditModel;
import tgs.com.pharmadotcom.SaleModel;
import tgs.com.pharmadotcom.SalereportModel;


public interface InterfaceApi {


    @POST("login")
    @FormUrlEncoded
    Call<LoginModel> Login(@Field("KEY") String APIKEY,
                           @Field("USERNAME") String USERNAME,
                           @Field("PASSWORD") String PWD
    );

    @POST("mr_report_weeklySale")
    @FormUrlEncoded
    Call<SaleModel> mr_report_weeklySale(@Field("log_userid") String log_userid,
                                         @Field("log_company") String log_company,
                                         @Field("srch_from") String srch_from,
                                         @Field("srch_to") String srch_to
    );

    @POST("sales_report_getparam")
    @FormUrlEncoded
    Call<SalereportModel> sales_report_getparam(@Field("log_userid") String log_userid,
                                                @Field("log_company") String log_company,
                                                @Field("srch_from") String srch_from,
                                                @Field("srch_to") String srch_to
    );

    @POST("calculate_amt")
    @FormUrlEncoded
    Call<CreditModels>calculate_amt(@Field("log_userid") String log_userid,
                                    @Field("key") String key


    );

    @POST("detail_data")
    @FormUrlEncoded
    Call<SaleCraditModel>detail_data(@Field("log_userid") String log_userid,
                                     @Field("key") String key,
                                     @Field("status") String status


    );

    @POST("detail_datapurchase")
    @FormUrlEncoded
    Call<PurchaseModel>detail_datapurchase(@Field("log_userid") String log_userid,
                                           @Field("key") String key,
                                           @Field("status") String status


    );





}
