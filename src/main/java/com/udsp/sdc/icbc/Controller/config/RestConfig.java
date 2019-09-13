package com.udsp.sdc.icbc.Controller.config;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


/**
 * HttpClient与RestTemplate默认配置
 *
 * @author huangjl
 *
 */
@Configuration
public class RestConfig {

	@Autowired
	private RestTemplateBuilder builder;


	/**
	 * 默认字符集
	 */
	private static final String DEFAULT_CHARSET = "UTF-8";

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		RestTemplate template = builder.requestFactory(factory).build();
		template.getMessageConverters().forEach(v -> {
			if (v instanceof StringHttpMessageConverter) {
				// 设置String转换器默认字符集为UTF-8
				((StringHttpMessageConverter) v).setDefaultCharset(Charset.forName(DEFAULT_CHARSET));
			}
		});
		return template;
	}

}