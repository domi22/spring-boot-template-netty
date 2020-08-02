package spring.boot.template.server.codec.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import spring.boot.template.common.Operation;
import spring.boot.template.common.OperationResult;
import spring.boot.template.common.RequestMessage;
import spring.boot.template.common.ResponseMessage;


public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMessage requestMessage) throws Exception {
        Operation operation = requestMessage.getMessageBody();
        OperationResult operationResult = operation.execute();

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());
        responseMessage.setMessageBody(operationResult);

        channelHandlerContext.writeAndFlush(responseMessage);
        //SimpleChannelInboundHandler 可以帮我们自动释放ByteBuf内存（可能是堆，也可能是堆外内存）
    }
}
