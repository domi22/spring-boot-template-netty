package spring.boot.template.auth;

import spring.boot.template.common.Operation;
import spring.boot.template.common.OperationResult;

public class AuthOperation extends Operation {

    private String userName;
    private String password;


    @Override
    public OperationResult execute() {
        if ("admin".equals(this.userName)) {
            return new AuthOperationResult(true);
        }
        return new AuthOperationResult(false);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
