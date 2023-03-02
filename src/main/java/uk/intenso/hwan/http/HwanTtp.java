package uk.intenso.hwan.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.intenso.hwan.annotations.VisibleForTesting;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class HwanTtp {


    private static Logger log = LoggerFactory.getLogger(HwanTtp.class);

    private HttpClient httpClient;

    private HttpClientBuilder httpClientBuilder;
    private RequestConfig.Builder requestConfigBuilder;
    private RequestConfig requestConfig;

    private boolean built = false;
    private HttpRequestBase requestBase;
    private String url;
    private String method;
    private Map<String, String> requestParams;
    private Map<String, String> headers;


    private List<String> pathVariables;

    private String body;

    private static final String GET = "GET";
    private static final String POST = "POST";

    private HwanTtp() {
    }

    private void init() {
        requestParams = new HashMap<>();
        headers = new HashMap<>();
        pathVariables = new LinkedList<>();
        requestConfigBuilder = RequestConfig.custom();

    }


    public static HwanTtp create(String method) {


        var fluentRequest = new HwanTtp();
        fluentRequest.method = method.toUpperCase();
        fluentRequest.httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build()).build();
        fluentRequest.init();
        return fluentRequest;
    }

    public HwanTtp standardCookieSpec() {
        this.requestConfigBuilder
                .setCookieSpec(CookieSpecs.STANDARD);
        return this;
    }

    public static HwanTtp createGet() {
        return HwanTtp.create(GET);
    }

    public static HwanTtp createGet(String url) {
        return HwanTtp.create(GET)
                .url(url);
    }

    public static HwanTtp createPost() {
        return HwanTtp.create(POST);
    }

    public HwanTtp method(String method) {
        this.method = method.toUpperCase();
        return this;
    }

    public HwanTtp url(String baseUrl) {
        this.url = baseUrl;
        return this;
    }

    public HwanTtp header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public HwanTtp bearerAuth(String bearerToken) {
        headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        return this;
    }

    public HwanTtp auth(String authToken) {
        headers.put(HttpHeaders.AUTHORIZATION, authToken);
        return this;
    }

    public HwanTtp content(String contentType) {
        headers.put(HttpHeaders.CONTENT_TYPE, contentType);
        return this;
    }

    public HwanTtp textHtmlContent() {
        headers.put(HttpHeaders.CONTENT_TYPE, "text/html");
        return this;
    }

    public HwanTtp jsonContentType() {
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json");
        return this;
    }

    public HwanTtp accept(String accept) {
        headers.put(HttpHeaders.ACCEPT, accept);
        return this;
    }

    public HwanTtp acceptJson() {
        accept("application/json");
        return this;
    }

    public HwanTtp requestParam(String key, String value) {
        requestParams.put(key, value);
        return this;
    }


    public HwanTtp body(String body) {
        this.body = body;
        return this;
    }

    private void preRequest() {
        this.requestBase = createRequest(url);
        this.httpClient = HttpClients.custom()
                .setDefaultRequestConfig(this.requestConfigBuilder.build()).build();
        addHeaders(this.requestBase);
    }

    public HwanTtp build() {

        addPathVariables();

        try {
            if (!requestParams.isEmpty()) {
                url += "?";
            }
            url = new URI(requestParams.keySet().stream()
                    .map(key -> key + "=" + requestParams.get(key))
                    .collect(joining("&", url, ""))).toString();
            System.out.println("Built Url: " + url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Url: " + url);
        this.preRequest();
        this.built = true;
        return this;
    }

    private void buildIfNotBuilt() {
        if (!built) {
            build();
        }
    }

    private void addPathVariables() {

        url += "/" + String.join("/", pathVariables);

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
    }

    public HwanTtp setJsonDefaultContentType() {
        return this.content(ContentType.APPLICATION_JSON.toString());
    }

    public String executeForContent() {
        var response = execute();
        try {
            return IOUtils.toString(response.getEntity().getContent(),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse execute() {
        buildIfNotBuilt();
        validateRequest(httpClient, "Http Client");
        try {
            logRequest();
            return httpClient.execute(requestBase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @VisibleForTesting
    HttpRequestBase createRequest(String encodedUrl) {
        return switch (method) {
            case GET:
                yield new HttpGet(encodedUrl);
            case POST:
                yield setRequestBody(new HttpPost(encodedUrl));
            default:
                throw new NotYetImplementedException("Unimplemented method: " + method);
        };
    }

    @VisibleForTesting
    HttpEntityEnclosingRequestBase setRequestBody(HttpEntityEnclosingRequestBase request) {
        StringEntity entity = null;
        try {
            entity = new StringEntity(body);
            entity.setContentType(headers.get(HttpHeaders.CONTENT_TYPE));
            request.setEntity(entity);
            return request;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    private String encodeUrl(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Couldn't encode url", e);
        }
    }

    private HwanTtp addHeaders(HttpRequestBase httpRequest) {
        if (body != null) {
            validateRequest(headers.get(HttpHeaders.CONTENT_TYPE), "Content-Type Header");
        }
        headers.forEach(httpRequest::addHeader);
        return this;
    }


    private void validateRequest(Object ob, String name) {
        if (Objects.isNull(httpClient)) {
            throw new HttpRequestNotValidException("Http Client Null");
        } else if (POST.equals(method) && Objects.isNull(body)) {
            throw new HttpRequestNotValidException("Body Null for Http Post");
        }
    }

    class HttpRequestNotValidException extends RuntimeException {

        public HttpRequestNotValidException(String message) {
            super(MessageFormat.format("{0} - unable to process request", message));
        }
    }

    private void logRequest() {
        printRequestHeaders();

        printRequest();
    }

    private void printRequest() {
        log.debug(MessageFormat.format("""
                        Request: Content Type: {0}
                        Auth: {1}
                        Method: {2} 
                        Body: {3}
                        Url: {4}""",
                headers.get(HttpHeaders.CONTENT_TYPE), headers.get(HttpHeaders.AUTHORIZATION), method, body, url));
    }

    private void printRequestHeaders() {
        System.out.println("Request Headers:");
        headers.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));
    }

    private void logResponse(HttpResponse response) {
        try {
            System.out.println("Response Headers:");
            List.of(response.getAllHeaders()).forEach((h) -> System.out.printf("%s=%s%n", h.getName(), h.getValue()));

            log.debug(MessageFormat.format("Response: Status Code: {0} \nBody: {1}",
                    response.getStatusLine().getStatusCode(),
                    IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String executeForStringResponse() {
        return returnResponseAsString(execute());
    }

    private String returnResponseAsString(HttpResponse response) {
        if (!HwanTppUtils.isOk(response.getStatusLine().getStatusCode())) {
            throw new RuntimeException("Request Failed with status code " + response.getStatusLine().getStatusCode()
                    + "\n" + "Reason " + response.getStatusLine().getReasonPhrase());
        }
        try {
            var resonse = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            log.warn(resonse);
            return resonse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
