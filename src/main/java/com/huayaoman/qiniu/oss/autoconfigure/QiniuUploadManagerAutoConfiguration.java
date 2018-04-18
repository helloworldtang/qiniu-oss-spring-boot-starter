package com.huayaoman.qiniu.oss.autoconfigure;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.huayaoman.qiniu.oss.autoconfigure.properties.QiniuOssProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangcheng
 * 2018/04/13
 */
@Configuration
@ConditionalOnClass({UploadManager.class})
@EnableConfigurationProperties(QiniuOssProperties.class)
@ConditionalOnProperty(prefix = "qiniu.oss", value = "enabled", matchIfMissing = true)
public class QiniuUploadManagerAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuUploadManagerAutoConfiguration.class);

    @Autowired
    private QiniuOssProperties qiniuOssProperties;

    @ConditionalOnMissingBean(QiniuUploadManager.class)
    @Bean
    public QiniuUploadManager qiniuUploadManager() {
        Auth auth = Auth.create(qiniuOssProperties.getAccessKey(), qiniuOssProperties.getSecretKey());
        QiniuOssProperties.Bucket bucket = qiniuOssProperties.getBucket();
        UploadManager uploadManager = new UploadManager(new com.qiniu.storage.Configuration(bucket.getQiniuZone()));
        String token = auth.uploadToken(bucket.getName());
        LOGGER.info("init QiniuUploadManager");
        return new QiniuUploadManager(uploadManager, token);
    }

}
