# ali-tools
ali-tools目前支持
>OSS文件存储，文件上传、删除文件、批量删除、查看文件列表<br/>
>SMS短信服务

使用教程：

第一步：添加依赖
```pom
<dependency>
    <groupId>cn.happyloves</groupId>
    <artifactId>ali-tools</artifactId>
    <version>${ali.tools.version}</version>
</dependency>
```
第二步：配置属性
```yaml
ali-tools:
  oss:
    accessKeyId: ${accessKeyId}
    accessKeySecret: ${accessKeySecret}
    endpoint: oss-cn-shanghai.aliyuncs.com
    bucketName: happyloves
#    HTTPProtocol: https #默认为http
#    home: 默认为:upload
# 示例：
#oss (https://bucketNmae.oss-cn-beijing.aliyuncs.com/upload/xxx.xxx)
#cdn (https://xxx.xxx.com/upload/xxx.xxx)
#    cdn: 不传则为oss域名，反之为cdn
  sms:
    accessKeyId: ${ali-tools.oss.accessKeyId}
    accessKeySecret: ${ali-tools.oss.accessKeySecret}
    singleName:
    templateCode:
```
第三步：添加注解
```java
@EnableAliToolsOss//启用OSS
@EnableAliToolsSms//启用SMS
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```
第四步：如何使用
```java
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private OSSClient ossClient;
    @Autowired
    private OssProperties ossProperties;

    @Test
    void oss() {
        File file = new File("C:\\Users\\ZC\\OneDrive\\圖片\\001.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);

        String upload = OssUtils.OssUpload.upload(ossClient, ossProperties, "test2.png", fileInputStream);
        System.out.printf(upload);
    }

    @Autowired
    private IAcsClient smsClient;
    @Autowired
    private SmsProperties smsProperties;

    @Test
    void sms() {
       SmsUtils.SendSmsRequest par = new SmsUtils.SendSmsRequest();
       par.setPhoneNumber("手机号");
       par.setSignName("签名");
       par.setTemplateCode("模板CODE");
       par.setTemplateParam("{'name':'模板字符串JSON格式'}");
       SmsUtils.sendSms(smsClient, smsProperties, par);
    }


}
```
[开发工具使用JetBrains的IDEA](https://www.jetbrains.com/?from=ali-tool)
