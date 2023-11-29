package com.billing.configuration;

import com.billing.util.DtoEntityConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DtoEntityConverter dtoEntityConverter() { return  new DtoEntityConverter(); }
}
