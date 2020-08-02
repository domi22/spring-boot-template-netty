package spring.boot.template.server.codec;


import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 解决粘包和半包问题的编解码工具（客户端）
 */
public class OrderFrameEncoder extends LengthFieldPrepender {


    public OrderFrameEncoder() {
        super(2);
    }
}
