package tgs.com.pharmadotcom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditModels {
    @SerializedName("response")
    @Expose
    private List<Response> response = null;

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

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public class Response{
      String sales_cr;
      String sales_ret;
      String received_amt;
      String amount_tbtaken;
      String pur_credit;
      String pur_retcr;

        public String getSales_cr() {
            return sales_cr;
        }

        public void setSales_cr(String sales_cr) {
            this.sales_cr = sales_cr;
        }

        public String getSales_ret() {
            return sales_ret;
        }

        public void setSales_ret(String sales_ret) {
            this.sales_ret = sales_ret;
        }

        public String getReceived_amt() {
            return received_amt;
        }

        public void setReceived_amt(String received_amt) {
            this.received_amt = received_amt;
        }

        public String getAmount_tbtaken() {
            return amount_tbtaken;
        }

        public void setAmount_tbtaken(String amount_tbtaken) {
            this.amount_tbtaken = amount_tbtaken;
        }

        public String getPur_credit() {
            return pur_credit;
        }

        public void setPur_credit(String pur_credit) {
            this.pur_credit = pur_credit;
        }

        public String getPur_retcr() {
            return pur_retcr;
        }

        public void setPur_retcr(String pur_retcr) {
            this.pur_retcr = pur_retcr;
        }

        public String getPaid_suppl() {
            return paid_suppl;
        }

        public void setPaid_suppl(String paid_suppl) {
            this.paid_suppl = paid_suppl;
        }

        public String getCr_note() {
            return cr_note;
        }

        public void setCr_note(String cr_note) {
            this.cr_note = cr_note;
        }

        public String getAmt_topaid() {
            return amt_topaid;
        }

        public void setAmt_topaid(String amt_topaid) {
            this.amt_topaid = amt_topaid;
        }

        String paid_suppl;
      String cr_note;
      String amt_topaid;
    }
}
