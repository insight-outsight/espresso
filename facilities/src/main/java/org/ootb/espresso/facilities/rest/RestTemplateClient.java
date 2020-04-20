package org.ootb.espresso.facilities.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
 

public class RestTemplateClient {
 
    private final Logger logger = LoggerFactory.getLogger(RestTemplateClient.class);
 
    private RestTemplate restTemplate; 
    
    public RestTemplateClient(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    
    public <T> T postForObject(String url,Class<T> t,Map<String,String> httpHeaders,String requestBodyStr) {
        HttpHeaders requestHeaders = new HttpHeaders();
        if(httpHeaders != null && httpHeaders.size()>0){
            for(String key:httpHeaders.keySet()){
                requestHeaders.add(key, httpHeaders.get(key));
            }
        }
        
        logger.debug("http request headers:{}",requestHeaders);

        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBodyStr, requestHeaders);
        T result = restTemplate.postForObject(url, httpEntity, t);
        return result;
    }
    
    /**
     * 未测试
     * @param url
     * @param t
     * @param httpHeaders
     * @param requestBodyStr
     * @return
     */
    public <T> ResponseEntity<T> postForEntity(String url,Class<T> t,Map<String,String> httpHeaders,String requestBodyStr) {
    	HttpHeaders requestHeaders = new HttpHeaders();
    	if(httpHeaders != null && httpHeaders.size()>0){
    		for(String key:httpHeaders.keySet()){
    			requestHeaders.add(key, httpHeaders.get(key));
    		}
    	}
    	
    	HttpEntity<String> formEntity = new HttpEntity<String>(requestBodyStr, requestHeaders);
    	 
    	logger.debug("http request headers:{}",requestHeaders);
    	
    	ResponseEntity<T> postForEntity = restTemplate.postForEntity(url, formEntity, t);
    	return postForEntity;
    }
    
    public <T> T getForObject(String url,Class<T> t,Map<String,String> httpHeaders) {
        HttpHeaders requestHeaders = new HttpHeaders();
        if(httpHeaders != null && httpHeaders.size()>0){
            for(String key:httpHeaders.keySet()){
                requestHeaders.add(key, httpHeaders.get(key));
            }
        }
        
        logger.debug("http request headers:{}",requestHeaders);
        
        T result = restTemplate.getForObject(url, t);
        return result;
    }
    
    /**
     * 未测试
     * @param url
     * @param t
     * @param httpHeaders
     * @return
     */
    public <T> ResponseEntity<T> getForEntity(String url,Class<T> t,Map<String,String> httpHeaders) {
    	HttpHeaders requestHeaders = new HttpHeaders();
    	if(httpHeaders != null && httpHeaders.size()>0){
    		for(String key:httpHeaders.keySet()){
    			requestHeaders.add(key, httpHeaders.get(key));
    		}
    	}
    	
    	logger.debug("http request headers:{}",requestHeaders);
    	
    	ResponseEntity<T> result = restTemplate.getForEntity(url, t);
    	return result;
    }

 
}