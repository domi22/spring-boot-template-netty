package spring.boot.template.common;

public class ResponseMessage extends Message<OperationResult> {

    /**
     * 用于从result中解码的class对象
     * @param opcode
     * @return
     */
    @Override
    public Class getMessageBodyDecodeClass(int opcode) {
        return OperationType.fromOpCode(opcode).getOperationResultClazz();
    }


}
