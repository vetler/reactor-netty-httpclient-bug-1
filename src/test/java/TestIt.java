import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import reactor.netty.http.client.HttpClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

class TestIt {
    @RegisterExtension
    WireMockExtension wm1 = WireMockExtension.newInstance()
            .options(wireMockConfig().enableBrowserProxying(true).port(10001))
            .build();

    @Test
    void testIt() {
        wm1.stubFor(
                get(urlMatching(".*"))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-Type", "text/plain")
                                        .withBody("foo")
                        )
        );

        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", Integer.toString(wm1.getRuntimeInfo().getHttpPort()));
        HttpClient client = HttpClient.create().proxyWithSystemProperties();
        client.get().uri("http://example.com").response().block();

        //assertEquals("foo", result);
    }

}