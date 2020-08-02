package spring.boot.template.keepalive;

import spring.boot.template.common.Operation;
import spring.boot.template.common.OperationResult;

public class KeepaliveOperation extends Operation {

    private long time;

    public KeepaliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        KeepaliveOperationResult operationResult = new KeepaliveOperationResult(time);
        return operationResult;
    }
}
