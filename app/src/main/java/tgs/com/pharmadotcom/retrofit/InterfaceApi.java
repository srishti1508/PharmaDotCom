package tgs.com.pharmadotcom.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tgs.com.pharmadotcom.LoginModel;
import tgs.com.pharmadotcom.SaleModel;


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

}
