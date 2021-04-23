package org.ruqinhu.reactor.netty;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;

public class HttpClientTest {

    static final boolean SECURE = System.getProperty("secure") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", SECURE ? "8443" : "8080"));
    static final boolean WIRETAP = System.getProperty("wiretap") != null;
    static final boolean COMPRESS = System.getProperty("compress") != null;

    public static void example() {
        HttpClient client =
                HttpClient.create()
                        .baseUrl("http://baidu.com");
//                        .port(PORT)
//                        .wiretap(WIRETAP)
//                        .compress(COMPRESS);

        if (SECURE) {
            client = client.secure(
                    spec -> spec.sslContext(SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)));
        }

        String response =
                client.get()
                        .uri("")
                        .responseContent()
                        .aggregate()
                        .asString()
                        .block();

        System.out.println("Response: " + response);
    }

    public static void post() {
        HttpClientResponse httpClientResponse = HttpClient.create()
                .baseUrl("https://www.baidu.com")
                .get()
                .response()
                .block();
        System.out.println(httpClientResponse.currentContext().toString());
    }

    public static void main(String[] args) {
        example();
//        post();
    }

}
