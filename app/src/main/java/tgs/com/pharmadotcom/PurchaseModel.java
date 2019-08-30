package tgs.com.pharmadotcom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PurchaseModel {
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
        public String getSuppl() {
            return suppl;
        }

        public void setSuppl(String suppl) {
            this.suppl = suppl;
        }

        public String getCr_amt() {
            return cr_amt;
        }

        public void setCr_amt(String cr_amt) {
            this.cr_amt = cr_amt;
        }

        public String getPaid_amt() {
            return paid_amt;
        }

        public void setPaid_amt(String paid_amt) {
            this.paid_amt = paid_amt;
        }

        String suppl;
        String p_date;

        public String getP_date() {
            return p_date;
        }

        public void setP_date(String p_date) {
            this.p_date = p_date;
        }

        String cr_amt;
        String note_amt;
        String bal_amt;

        public String getNote_amt() {
            return note_amt;
        }

        public void setNote_amt(String note_amt) {
            this.note_amt = note_amt;
        }

        public String getBal_amt() {
            return bal_amt;
        }

        public void setBal_amt(String bal_amt) {
            this.bal_amt = bal_amt;
        }

        String paid_amt;

        public String getRet_amt() {
            return ret_amt;
        }

        public void setRet_amt(String ret_amt) {
            this.ret_amt = ret_amt;
        }

        String ret_amt;
    }
}
