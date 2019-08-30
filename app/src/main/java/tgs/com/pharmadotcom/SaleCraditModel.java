package tgs.com.pharmadotcom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Response;

public class SaleCraditModel {
    @SerializedName("response")
    @Expose
    private List<Response> response = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
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

    @SerializedName("status")
    @Expose
    private String status;

    public class Response {
  String customer;

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public String getCredit_amt() {
            return credit_amt;
        }

        public void setCredit_amt(String credit_amt) {
            this.credit_amt = credit_amt;
        }

        public String getRec_amt() {
            return rec_amt;
        }

        public void setRec_amt(String rec_amt) {
            this.rec_amt = rec_amt;
        }

        String credit_amt;

        public String getCr_amt() {
            return cr_amt;
        }

        public void setCr_amt(String cr_amt) {
            this.cr_amt = cr_amt;
        }

        String cr_amt;

        public String getRec_date() {
            return rec_date;
        }

        public void setRec_date(String rec_date) {
            this.rec_date = rec_date;
        }

        String rec_date;
        String ret_amt;

        public String getRet_amt() {
            return ret_amt;
        }

        public void setRet_amt(String ret_amt) {
            this.ret_amt = ret_amt;
        }

        public String getBal_amt() {
            return bal_amt;
        }

        public void setBal_amt(String bal_amt) {
            this.bal_amt = bal_amt;
        }

        String bal_amt;
        String tot_cr_amt;
        String tot_rec_amt;

        public String getTot_cr_amt() {
            return tot_cr_amt;
        }

        public void setTot_cr_amt(String tot_cr_amt) {
            this.tot_cr_amt = tot_cr_amt;
        }

        public String getTot_rec_amt() {
            return tot_rec_amt;
        }

        public void setTot_rec_amt(String tot_rec_amt) {
            this.tot_rec_amt = tot_rec_amt;
        }

        String rec_amt;
    }
}
