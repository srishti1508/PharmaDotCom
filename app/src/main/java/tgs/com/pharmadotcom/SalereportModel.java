package tgs.com.pharmadotcom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SalereportModel {

    @SerializedName("response")
    @Expose
    private List<SalereportModel.Response> response = null;



    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("status")
    @Expose
    private String status;

    public List<SalereportModel.Response> getResponse() {
        return response;
    }

    public void setResponse(List<SalereportModel.Response> response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public class Response{
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        String url;
    }
}
