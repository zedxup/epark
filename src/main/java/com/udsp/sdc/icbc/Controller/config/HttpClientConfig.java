package com.udsp.sdc.icbc.Controller.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * httpclient配置
 * 
 * @author huangjl
 *
 */
@Configuration
public class HttpClientConfig {
	@Bean
	public CloseableHttpClient buildHttpClient() {
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000) // 设置从连接池获取连接超时时间10秒
				.setConnectTimeout(10000) // 设置连接超时10秒
				.setSocketTimeout(30000) // 设置获取数据超时时间30秒
				.build();
		return HttpClients.custom().setMaxConnTotal(100) // 设置连接池的最大连接数
				.setMaxConnPerRoute(100) // 设置单路由最大连接数
				.setDefaultRequestConfig(config).build();
	}

	@Bean
	public ClientHttpRequestFactory buildRequestFactory(CloseableHttpClient client) {
		return new HttpComponentsClientHttpRequestFactory(client);
	}
}
