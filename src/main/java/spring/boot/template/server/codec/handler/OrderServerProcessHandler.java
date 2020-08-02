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

        //发送给pepline中的下一个handler
        channelHandlerContext.writeAndFlush(responseMessage);

        //发送到pepline中去，消息会沿着所有的handler走一遍
        //channelHandlerContext.channel().writeAndFlush(responseMessage);

        //值是把消息发送到队列，并没有发送
        //channelHandlerContext.write(responseMessage);
        //SimpleChannelInboundHandler 可以帮我们自动释放ByteBuf内存（可能是堆，也可能是堆外内存），如果是其他的handler则需要手动释放
    }
}
