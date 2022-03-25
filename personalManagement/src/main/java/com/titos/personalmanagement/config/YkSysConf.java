package com.titos.personalmanagement.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 云客系统配置信息
 * @author Titos
 */
@Component
@Data
@RefreshScope
public class YkSysConf {
    @Value("${YK.sys-conf.enable-mail-register}")
    private Boolean enableMailRegister;
}
