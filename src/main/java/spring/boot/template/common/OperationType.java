package spring.boot.template.common;

import spring.boot.template.auth.AuthOperation;
import spring.boot.template.auth.AuthOperationResult;
import spring.boot.template.keepalive.KeepaliveOperation;
import spring.boot.template.keepalive.KeepaliveOperationResult;
import spring.boot.template.order.OrderOperation;
import spring.boot.template.order.OrderOperationResult;

public enum OperationType {

    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    KEEPALIVE(2, KeepaliveOperation.class, KeepaliveOperationResult.class),
    ORDER(3, OrderOperation.class, OrderOperationResult.class);

    private int opCode;
    private Class<? extends Operation> operationClazz;
    private Class<? extends OperationResult> operationResultClazz;

    OperationType(int opCode, Class<? extends Operation> operationClazz, Class<? extends OperationResult> operationResultClazz) {
        this.opCode = opCode;
        this.operationClazz = operationClazz;
        this.operationResultClazz = operationResultClazz;
    }

    public int getOpCode() {
        return opCode;
    }

    public Class<? extends Operation> getOperationClazz() {
        return operationClazz;
    }

    public Class<? extends OperationResult> getOperationResultClazz() {
        return operationResultClazz;
    }

    public static OperationType fromOpCode(int opCode) {
        OperationType[] values = OperationType.values();
        for (OperationType value : values) {
            if (value.opCode == opCode) {
                return value;
            }
        }
        return null;
    }

    public static OperationType fromOperation(Operation operation) {
        OperationType[] values = OperationType.values();
        for (OperationType value : values) {
            if (value.operationClazz.equals(operation.getClass())) {
                return value;
            }
        }
        return null;
    }





}
