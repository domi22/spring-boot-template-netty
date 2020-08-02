package spring.boot.template.servers;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import spring.boot.template.server.codec.OrderFrameDecoder;
import spring.boot.template.server.codec.OrderFrameEncoder;
import spring.boot.template.server.codec.OrderprotocolDecoder;
import spring.boot.template.server.codec.OrderprotocolEncoder;
import spring.boot.template.server.codec.handler.OrderServerProcessHandler;

public class Server {

    public static void main(String[] args) throws Exception {


        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.group(new NioEventLoopGroup());
        //日志打印
        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));

        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();

                pipeline.addLast(new OrderFrameDecoder());
                pipeline.addLast(new OrderFrameEncoder());
                pipeline.addLast(new OrderprotocolEncoder());
                pipeline.addLast(new OrderprotocolDecoder());
                //日志打印
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast(new OrderServerProcessHandler());
            }
        });

        ChannelFuture sync = serverBootstrap.bind(8050).sync();

        sync.channel().closeFuture().get();


    }
}
