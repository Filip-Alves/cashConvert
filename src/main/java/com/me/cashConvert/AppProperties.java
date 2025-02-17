package com.me.cashConvert;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "app.url")
public class AppProperties {
	private String api_url;
}
