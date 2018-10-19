package com.springboot.crm.sys.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Component
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
//        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "150");
        properties.setProperty("kaptcha.image.height", "34");
        properties.setProperty("kaptcha.textproducer.font.size", "25");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "楷体");
//        文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "4");
//        干扰颜色
        properties.setProperty("kaptcha.noise.color", "blue");
//        图片效果
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        com.google.code.kaptcha.util.Config config = new com.google.code.kaptcha.util.Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
