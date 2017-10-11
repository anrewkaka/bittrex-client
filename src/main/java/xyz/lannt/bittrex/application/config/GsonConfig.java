package xyz.lannt.bittrex.application.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import xyz.lannt.bittrex.domain.vo.CryptoText;
import xyz.lannt.bittrex.domain.vo.CryptoValue;
import xyz.lannt.bittrex.presentation.serializer.CryptoTextSerializer;
import xyz.lannt.bittrex.presentation.serializer.CryptoValueSerializer;

@Configuration
@ConditionalOnClass(Gson.class)
public class GsonConfig extends GsonAutoConfiguration {

  @Override
  public Gson gson() {
    return new GsonBuilder()
        .registerTypeAdapter(CryptoValue.class, new CryptoValueSerializer())
        .registerTypeAdapter(CryptoText.class, new CryptoTextSerializer())
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
  }

  @Bean
  @ConditionalOnMissingBean
  public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
    GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
    converter.setGson(gson);
    return converter;
  }
}
