package spring.boot.template.order;

import spring.boot.template.common.OperationResult;

public class OrderOperationResult extends OperationResult {

    private int tableId;
    private String dish;
    private boolean ok;

    public OrderOperationResult() {
    }

    public OrderOperationResult(int tableId, String dish, boolean ok) {
        this.tableId = tableId;
        this.dish = dish;
        this.ok = ok;
    }


}
