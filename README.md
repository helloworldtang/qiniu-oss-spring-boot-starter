# qiniu-oss-spring-boot-starter
qiniu-oss-spring-boot-starter

1. finish UploadManager
### Usage
![properties-demo](https://user-images.githubusercontent.com/13701989/38785476-1b9b9274-4153-11e8-80f7-80c71a310e0b.png)

在application.properties中设置
debug=true
可以看到QiniuUploadManagerAutoConfiguration自动配置成功的信息：
>   QiniuUploadManagerAutoConfiguration matched:
      - @ConditionalOnClass found required class 'com.qiniu.storage.UploadManager'; @ConditionalOnMissingClass did not find unwanted class (OnClassCondition)
      - @ConditionalOnProperty (qiniu.oss.enabled) matched (OnPropertyCondition)

   QiniuUploadManagerAutoConfiguration#qiniuUploadManager matched:
      - @ConditionalOnMissingBean (types: com.tangcheng.qiniu.oss.autoconfigure.QiniuUploadManager; SearchStrategy: all) did not find any beans (OnBeanCondition)
