package xyz.lannt.market.application.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  @Autowired
  private GsonHttpMessageConverter gsonHttpMessageConverter;

  @Bean
  public HttpMessageConverters customConverters() {
    Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    messageConverters.add(gsonHttpMessageConverter);
    return new HttpMessageConverters(true, messageConverters);
  }
}
