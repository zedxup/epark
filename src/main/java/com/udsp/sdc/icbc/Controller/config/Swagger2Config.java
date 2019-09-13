package com.udsp.sdc.icbc.Controller.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置类
 * 
 * @author huangjl,lixiaofeng
 *
 */
@Configuration
@ConfigurationProperties
public class Swagger2Config {

	private static final Logger LOG = LoggerFactory.getLogger(Swagger2Config.class);

	@Value("${swagger.apiTagFile:swaggerApiTags.properties}")
	String apiTagConfigPath = "swaggerApiTags.properties";

	/**
	 * 
	 * 配置swagger
	 * @return Docket docket
	 */
	@Bean
	public Docket createRestApi() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.tags(new Tag("EPark", "EPARK的API列表"), getTags()).select()
				// 扫描指定包中的swagger注解
				.apis(RequestHandlerSelectors.basePackage("com.udsp.sdc.icbc"))
				// 扫描所有有注解的api，用这种方式更灵活
				// .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	/**
	 * 
	 * 配置apiInfo信息
	 * @return ApiInfo swagger配置信息
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("工行E停车 APIs").description("基础平台 RESTful 风格的接口文档")
				.termsOfServiceUrl("").version("1.0").build();
	}

	/**
	 * 
	 * 获取Tag
	 * @return Tag[] 获取Tag[]
	 */
	private Tag[] getTags() {
		Tag[] tags = {};
		ResourcePropertySource rs = getApiTagConfigPath();
		if (rs != null) {
			Map<String, Object> map = rs.getSource();
			List<Tag> list = new ArrayList<Tag>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				list.add(new Tag(entry.getKey(), entry.getValue().toString()));
			}
			tags = list.toArray(new Tag[list.size()]);
		}
		return tags;
	}

	/**
	 * 
	 * 获取apiTagConfigPath
	 * 
	 * @return ResourcePropertySource 属性配置源
	 */
	private ResourcePropertySource getApiTagConfigPath() {
		ResourcePropertySource rs = null;
		try {
			rs = new ResourcePropertySource(apiTagConfigPath);
		} catch (IOException e) {
			LOG.warn("not found " + apiTagConfigPath, e);
		}
		return rs;
	}
}
