package com.codingshuttle.ecommerce.inventory_service.config;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig
{
    @Bean
    public ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public RestClient  getRestClient()
    {
        return RestClient.builder().build();        // though it is interface
    }

    @Bean
    public Capability capability(final MeterRegistry registry)
    {
        return new MicrometerCapability(registry);
    }
}
