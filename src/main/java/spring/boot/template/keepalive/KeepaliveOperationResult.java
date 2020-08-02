package spring.boot.template.keepalive;

import spring.boot.template.common.OperationResult;

public class KeepaliveOperationResult extends OperationResult {

    private long time;

    public KeepaliveOperationResult() {
    }

    public KeepaliveOperationResult(long time) {
        this.time = time;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
