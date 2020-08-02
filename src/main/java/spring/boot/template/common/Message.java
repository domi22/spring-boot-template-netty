package spring.boot.template.common;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public abstract class Message<T extends MessageBody> {

    private MessageHeader messageHeader;
    private T messageBody;

    public T getMessageBody() {
        return messageBody;
    }

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(JSON.toJSONString(messageBody).getBytes());
    }

    public abstract Class<T> getMessageBodyDecodeClass(int opcode);


    public void decode(ByteBuf msg){
        int version = msg.readInt();
        long streamId = msg.readLong();
        int opCode = msg.readInt();

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setVersion(version);
        messageHeader.setOpCode(opCode);
        messageHeader.setStreamId(streamId);
        this.messageHeader = messageHeader;

        String str = msg.toString(Charset.forName("UTF-8"));
        Class<T> bodyClass = getMessageBodyDecodeClass(opCode);
        T body = JSON.parseObject(str, bodyClass);
        this.messageBody = body;
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public void setMessageBody(T messageBody) {
        this.messageBody = messageBody;
    }
}
