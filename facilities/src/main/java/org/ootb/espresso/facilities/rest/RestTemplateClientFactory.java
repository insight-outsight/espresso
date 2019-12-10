package org.ootb.espresso.facilities.rest;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

public class RestTemplateClientFactory {

    private static Logger LOG = LoggerFactory.getLogger(RestTemplateClientFactory.class);

    private final static String defaultCharsetName = "UTF-8";

    private final static int defaultConnectTimeout = 5000;
    private final static int defaultSocketTimeout = 3000;
    private final static int defaultPoolSize = 300;
    private final static int defaultMaxPerRouteSize = 20;
    
    public static RestTemplate simpleRestTemplateClient() {
        return simpleRestTemplateClient(defaultConnectTimeout,defaultSocketTimeout,defaultPoolSize,defaultMaxPerRouteSize);
    }
    
    public static RestTemplate simpleRestTemplateClient(int connectTimeout,int socketTimeout,int poolSize,int maxPerRoute) {
        return simpleRestTemplateClient(connectTimeout,socketTimeout,poolSize,maxPerRoute,true);
    }
    
    public static RestTemplate simpleRestTemplateClient(int connectTimeout,int socketTimeout,int poolSize,int maxPerRoute,boolean ignoreCookie) {

        String cookieSpec = CookieSpecs.IGNORE_COOKIES;
        
        if(!ignoreCookie) {
            cookieSpec = CookieSpecs.DEFAULT;
        }
        
        final RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(socketTimeout)
                .setCookieSpec(cookieSpec)
                .setStaleConnectionCheckEnabled(false).build();

        final ConnectionConfig connectionConfig = ConnectionConfig
                .custom()
                .setCharset(Charset.availableCharsets().get(defaultCharsetName))
                .build();

        final SocketConfig socketConfig = SocketConfig.custom()
                .setSoKeepAlive(true)
                /* .setSoLinger(1) */
                .setSoReuseAddress(true).setSoTimeout(socketTimeout).setTcpNoDelay(true)
                .build();

        // 创建连接池
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager
                .setDefaultConnectionConfig(connectionConfig);
        poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
        // 连接池最大生成连接数
        poolingHttpClientConnectionManager.setMaxTotal(poolSize);
        // 默认每个route最大连接数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
        
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
//        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
//        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
//        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        
        HttpClient httpClient = HttpClients.custom()
                .setDefaultHeaders(headers)
            .disableAuthCaching()
            .setDefaultRequestConfig(requestConfig)
            .setUserAgent("ApacheHttpClient-Based")
            .setConnectionManager(poolingHttpClientConnectionManager)
            //通过设置ConnectionKeepAliveStrategy来关闭空闲连接
            .setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
                public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                    // Honor 'keep-alive' header
                    HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                    while (it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && param.equalsIgnoreCase("timeout")) {
                            try {
                                return Long.parseLong(value) * 1000;
                            } catch(NumberFormatException ignore) {
                            }
                        }
                    }
                    HttpHost target = (HttpHost) context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
                    if ("www.naughty-server.com".equalsIgnoreCase(target.getHostName())) {
                        // Keep alive for 5 seconds only
                        return 5 * 1000;
                    } else {
                        // otherwise keep alive for 65 seconds
                        return 65 * 1000;
                    }
                }
            })/*这个例子指明在访问www.naughty-server.com和其他未知服务器时的keeplive为一个固定值，如
                果服务器返回keeplive过期时间，则通过服务器告诉客户端该连接大概什么时候过期（注意这不是一个标准的http协议，不是所有服务器都支持）。*/
            .build();
        
        
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        clientHttpRequestFactory.setConnectTimeout(defaultConnectTimeout);
        clientHttpRequestFactory.setReadTimeout(socketTimeout);
        clientHttpRequestFactory.setConnectionRequestTimeout(socketTimeout);
        // clientHttpRequestFactory.setBufferRequestBody(false);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName(defaultCharsetName)));
        messageConverters.add(new FormHttpMessageConverter());
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter 
            = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = Lists.newArrayList();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        messageConverters.add(new MappingJackson2HttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(clientHttpRequestFactory);

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        LOG.info("simpleRestTemplateClient初始化完成");

        return restTemplate;
    }

}