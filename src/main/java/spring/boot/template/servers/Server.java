package spring.boot.template.servers;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import spring.boot.template.server.codec.OrderFrameDecoder;
import spring.boot.template.server.codec.OrderFrameEncoder;
import spring.boot.template.server.codec.OrderprotocolDecoder;
import spring.boot.template.server.codec.OrderprotocolEncoder;
import spring.boot.template.server.codec.handler.OrderServerProcessHandler;

public class Server {

    public static void main(String[] args) throws Exception {


        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);

        //完善线程的名字
        NioEventLoopGroup group = new NioEventLoopGroup(0,new DefaultThreadFactory("dome"));
        NioEventLoopGroup worker = new NioEventLoopGroup(0,new DefaultThreadFactory("dome-worker"));

        serverBootstrap.group(group,worker);
        //日志打印
        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));

        //小文件报文的时候提高效率
        serverBootstrap.childOption(NioChannelOption.TCP_NODELAY, true);
        serverBootstrap.option(NioChannelOption.SO_BACKLOG, 1024);

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
                pipeline.addLast("orderHandler",new OrderServerProcessHandler());
            }
        });

        ChannelFuture sync = serverBootstrap.bind(8050).sync();

        sync.channel().closeFuture().get();


    }
}
