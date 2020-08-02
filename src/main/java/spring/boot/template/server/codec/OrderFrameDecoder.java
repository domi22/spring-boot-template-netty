package spring.boot.template.server.codec;


import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 解决粘包和半包问题的编解码工具（服务端）
 */
public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {

    public OrderFrameDecoder() {
        super(Integer.MAX_VALUE,
                0,
                2,
                0,
                2);
    }

}
