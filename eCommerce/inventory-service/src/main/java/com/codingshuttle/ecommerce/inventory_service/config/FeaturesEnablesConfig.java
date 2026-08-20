package com.codingshuttle.ecommerce.inventory_service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Data
public class FeaturesEnablesConfig
{

    @Value("${features.user-tracking-enabled}")
    private boolean isUserTrackingUnabled;

}
