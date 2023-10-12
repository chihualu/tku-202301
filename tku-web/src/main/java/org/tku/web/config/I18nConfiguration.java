package org.tku.web.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
@Log4j2
public class I18nConfiguration {

    private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";

    @Value("${web.i18n.basename:classpath:i18n/application-messages}")
    private List<String> basename;

    @Value("${web.i18n.encoding:UTF-8}")
    private String encoding;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        log.debug("i18n config file => {}", basename);
        messageSource.setBasenames(basename.toArray(new String[basename.size()]));
        messageSource.setDefaultEncoding(encoding);
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.TAIWAN);
        localeResolver.setLocaleAttributeName("locale");
        return localeResolver;
    }


    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
                localeChangeInterceptor.setParamName(ACCEPT_LANGUAGE_HEADER);
                registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/**");
            }
        };
    }

}