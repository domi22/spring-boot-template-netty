package spring.boot.template.order;

import spring.boot.template.common.Operation;
import spring.boot.template.common.OperationResult;

public class OrderOperation extends Operation {

    private int tableId;
    private String dish;

    public OrderOperation() {
    }

    public OrderOperation(int tableId, String dish) {
        this.tableId = tableId;
        this.dish = dish;
    }


    @Override
    public OperationResult execute() {
        OrderOperationResult orderOperationResult = new OrderOperationResult(tableId, dish, true);
        return orderOperationResult;
    }
}
