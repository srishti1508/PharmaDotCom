package tgs.com.pharmadotcom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaleModel {

    @SerializedName("response")
    @Expose
    private List<Response> response = null;

    public List<Response> getFilter_params() {
        return filter_params;
    }

    public void setFilter_params(List<Response> filter_params) {
        this.filter_params = filter_params;
    }

    @SerializedName("filter_params")
    @Expose
    private List<Response> filter_params = null;


    public List<Response> getTotal_calc() {
        return total_calc;
    }

    public void setTotal_calc(List<Response> total_calc) {
        this.total_calc = total_calc;
    }

    @SerializedName("total_calc")
    @Expose
    private List<Response> total_calc = null;


    @SerializedName("message")
    @Expose
    private String message;

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    @SerializedName("grand_total")
    @Expose
    private String grand_total;
    @SerializedName("status")
    @Expose
    private String status;

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

    public class Response {

      String sn;
      String medicine;
      String opening_stock;
      String opening_value;
      String purchase_qty;
      String sale_return_qty;
      String purchase_return_qty;
      String sale_qty;
      String total_medicine;
      String total_op_amt;
      String total_pur_qty;
      String total_sale_ret_qty;
      String total_pur_ret_qty;
      String total_sale_qty;
      String total_sale_amt;

        public String getTotal_medicine() {
            return total_medicine;
        }

        public void setTotal_medicine(String total_medicine) {
            this.total_medicine = total_medicine;
        }

        public String getTotal_op_amt() {
            return total_op_amt;
        }

        public void setTotal_op_amt(String total_op_amt) {
            this.total_op_amt = total_op_amt;
        }

        public String getTotal_pur_qty() {
            return total_pur_qty;
        }

        public void setTotal_pur_qty(String total_pur_qty) {
            this.total_pur_qty = total_pur_qty;
        }

        public String getTotal_sale_ret_qty() {
            return total_sale_ret_qty;
        }

        public void setTotal_sale_ret_qty(String total_sale_ret_qty) {
            this.total_sale_ret_qty = total_sale_ret_qty;
        }

        public String getTotal_pur_ret_qty() {
            return total_pur_ret_qty;
        }

        public void setTotal_pur_ret_qty(String total_pur_ret_qty) {
            this.total_pur_ret_qty = total_pur_ret_qty;
        }

        public String getTotal_sale_qty() {
            return total_sale_qty;
        }

        public void setTotal_sale_qty(String total_sale_qty) {
            this.total_sale_qty = total_sale_qty;
        }

        public String getTotal_sale_amt() {
            return total_sale_amt;
        }

        public void setTotal_sale_amt(String total_sale_amt) {
            this.total_sale_amt = total_sale_amt;
        }

        public String getTotal_res_qty() {
            return total_res_qty;
        }

        public void setTotal_res_qty(String total_res_qty) {
            this.total_res_qty = total_res_qty;
        }

        public String getTotal_res_amt() {
            return total_res_amt;
        }

        public void setTotal_res_amt(String total_res_amt) {
            this.total_res_amt = total_res_amt;
        }

        public String getTotal_pur_amt() {
            return total_pur_amt;
        }

        public void setTotal_pur_amt(String total_pur_amt) {
            this.total_pur_amt = total_pur_amt;
        }

        public String getTotal_sale_ret_amt() {
            return total_sale_ret_amt;
        }

        public void setTotal_sale_ret_amt(String total_sale_ret_amt) {
            this.total_sale_ret_amt = total_sale_ret_amt;
        }

        public String getTotal_pur_ret_amt() {
            return total_pur_ret_amt;
        }

        public void setTotal_pur_ret_amt(String total_pur_ret_amt) {
            this.total_pur_ret_amt = total_pur_ret_amt;
        }

        String total_res_qty;
      String total_res_amt;
      String total_pur_amt;
      String total_sale_ret_amt;
      String total_pur_ret_amt;

        public String getSrch_from() {
            return srch_from;
        }

        public void setSrch_from(String srch_from) {
            this.srch_from = srch_from;
        }

        public String getSrch_to() {
            return srch_to;
        }

        public void setSrch_to(String srch_to) {
            this.srch_to = srch_to;
        }

        String srch_from;
      String srch_to;

        public String getComapny_name() {
            return comapny_name;
        }

        public void setComapny_name(String comapny_name) {
            this.comapny_name = comapny_name;
        }

        String comapny_name;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getMedicine() {
            return medicine;
        }

        public void setMedicine(String medicine) {
            this.medicine = medicine;
        }

        public String getOpening_stock() {
            return opening_stock;
        }

        public void setOpening_stock(String opening_stock) {
            this.opening_stock = opening_stock;
        }

        public String getOpening_value() {
            return opening_value;
        }

        public void setOpening_value(String opening_value) {
            this.opening_value = opening_value;
        }

        public String getPurchase_qty() {
            return purchase_qty;
        }

        public void setPurchase_qty(String purchase_qty) {
            this.purchase_qty = purchase_qty;
        }

        public String getSale_return_qty() {
            return sale_return_qty;
        }

        public void setSale_return_qty(String sale_return_qty) {
            this.sale_return_qty = sale_return_qty;
        }

        public String getPurchase_return_qty() {
            return purchase_return_qty;
        }

        public void setPurchase_return_qty(String purchase_return_qty) {
            this.purchase_return_qty = purchase_return_qty;
        }

        public String getSale_qty() {
            return sale_qty;
        }

        public void setSale_qty(String sale_qty) {
            this.sale_qty = sale_qty;
        }

        public String getSale_amt() {
            return sale_amt;
        }

        public void setSale_amt(String sale_amt) {
            this.sale_amt = sale_amt;
        }

        public String getClosing_stock() {
            return closing_stock;
        }

        public void setClosing_stock(String closing_stock) {
            this.closing_stock = closing_stock;
        }

        public String getClosing_value() {
            return closing_value;
        }

        public void setClosing_value(String closing_value) {
            this.closing_value = closing_value;
        }

        public String getRe_order_qty() {
            return re_order_qty;
        }

        public void setRe_order_qty(String re_order_qty) {
            this.re_order_qty = re_order_qty;
        }

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
        }

        String closing_stock;
      String closing_value;
      String re_order_qty;
      String expiry_date;
      String sale_amt;


    }


}
