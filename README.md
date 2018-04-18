# qiniu-oss-spring-boot-starter
qiniu-oss-spring-boot-starter

1. finish UploadManager
### Usage
1. 安装在本地maven仓库    
  ``bash
  mvn clean install
  ``
2. add dependency
```xml
		<dependency>
			<groupId>com.huayaoman.qiniu.oss</groupId>
			<artifactId>qiniu-oss-spring-boot-starter</artifactId>
			<version>1.0.0</version>
		</dependency>
```
3. 在application.properties中配置      
![properties-demo](https://user-images.githubusercontent.com/13701989/38785476-1b9b9274-4153-11e8-80f7-80c71a310e0b.png)
4. 示例代码
```java
import com.huayaoman.qiniu.oss.autoconfigure.QiniuUploadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.io.File;

@SpringBootApplication
public class DemoQiniuStarterApplication implements CommandLineRunner {

    @Autowired
    private QiniuUploadManager qiniuUploadManager;

    @Value("classpath:404-qiniu-2.jpg") //这个示例图片需要自己准备一个
    private Resource resource;

    public static void main(String[] args) {
        SpringApplication.run(DemoQiniuStarterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        File file = resource.getFile();
        qiniuUploadManager.put(file, file.getName());
    }
}
```

Tips:

在application.properties中设置
debug=true
可以看到QiniuUploadManagerAutoConfiguration自动配置成功的信息：
>   QiniuUploadManagerAutoConfiguration matched:
      - @ConditionalOnClass found required class 'com.qiniu.storage.UploadManager'; @ConditionalOnMissingClass did not find unwanted class (OnClassCondition)
      - @ConditionalOnProperty (qiniu.oss.enabled) matched (OnPropertyCondition)
>   QiniuUploadManagerAutoConfiguration#qiniuUploadManager matched:
      - @ConditionalOnMissingBean (types: com.huayaoman.qiniu.oss.autoconfigure.QiniuUploadManager; SearchStrategy: all) did not find any beans (OnBeanCondition)
