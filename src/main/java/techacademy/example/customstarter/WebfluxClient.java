package techacademy.example.customstarter;

import reactor.netty.Connection;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import io.netty.channel.ChannelHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnClass(name = { "org.springframework.web.reactive.function.client.WebClient" })
public class WebfluxClient
{
     
    
    public WebfluxClient(){
        log.info("****** Created WebfluxClient component @ **********");
    }
    public WebClient.Builder builder() {
        WebfluxClient.log.info("configuration WebClient Prototype");
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient()));
    }
    
    public static HttpClient httpClient() {
        return HttpClient.create()
                         .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                         .responseTimeout(Duration.ofMillis(5000L))
                         .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000L, TimeUnit.MILLISECONDS))
                                                    .addHandlerLast(new WriteTimeoutHandler(5000L, TimeUnit.MILLISECONDS))
                                        );
    }
}