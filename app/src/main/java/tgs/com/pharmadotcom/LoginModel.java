package tgs.com.pharmadotcom;

import java.util.List;

public class LoginModel {

    String status;
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Response> response;
    public List<Response> getResponse() {
        return response;
    }
    public void setResponse(List<Response> response) {
        this.response = response;
    }
    public class Response {
        String log_usergroupname;
        String log_userid;
        String log_username;
        String log_company;

        public String getComapny_name() {
            return comapny_name;
        }

        public void setComapny_name(String comapny_name) {
            this.comapny_name = comapny_name;
        }

        String comapny_name;

        public String getLog_usergroupname() {
            return log_usergroupname;
        }

        public void setLog_usergroupname(String log_usergroupname) {
            this.log_usergroupname = log_usergroupname;
        }

        public String getLog_userid() {
            return log_userid;
        }

        public void setLog_userid(String log_userid) {
            this.log_userid = log_userid;
        }

        public String getLog_username() {
            return log_username;
        }

        public void setLog_username(String log_username) {
            this.log_username = log_username;
        }

        public String getLog_company() {
            return log_company;
        }

        public void setLog_company(String log_company) {
            this.log_company = log_company;
        }





    }
}
