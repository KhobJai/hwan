package uk.intenso.hwan.http;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.*;

class HwanTtpTest {


    @Test
    void googleGetTest() throws URISyntaxException, IOException {
        var response = HwanTtp.createGet().url("https://google.com").build().execute();
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        System.out.println(response.getEntity().toString());
    }

    @Test
    void googleParamGetTest() throws URISyntaxException, IOException {
        var response = HwanTtp.createGet()
                .url("https://google.com")
                .requestParam("client","gws-wiz")
                .requestParam("search","cheese")
                .build()
                .execute();
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        System.out.println(IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8));
    }

}