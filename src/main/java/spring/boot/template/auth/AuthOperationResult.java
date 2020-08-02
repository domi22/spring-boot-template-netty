package spring.boot.template.auth;

import spring.boot.template.common.OperationResult;

public class AuthOperationResult extends OperationResult {

    private boolean passAuth;

    public boolean isPassAuth() {
        return passAuth;
    }

    public void setPassAuth(boolean passAuth) {
        this.passAuth = passAuth;
    }

    public  AuthOperationResult() {

    }

    public  AuthOperationResult(boolean passAuth) {
        this.passAuth = passAuth;
    }


}
