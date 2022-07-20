package cn.happyloves.ali.tools.bean;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.AlibabaCloudCredentials;
import com.aliyuncs.auth.AlibabaCloudCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.IClientProfile;

/**
 * @author zc
 * @date 2022/7/16 23:59
 */
public class SMSClient extends DefaultAcsClient {
    public SMSClient(String regionId) throws ClientException {
        super(regionId);
    }

    public SMSClient(IClientProfile profile) {
        super(profile);
    }

    public SMSClient(IClientProfile profile, AlibabaCloudCredentials credentials) {
        super(profile, credentials);
    }

    public SMSClient(IClientProfile profile, AlibabaCloudCredentialsProvider credentialsProvider) {
        super(profile, credentialsProvider);
    }
}
