package cn.ffcs.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: ye.yongqin
 * @date: Created in 2019/4/30
 * 发送验证短信
 */
public class SendSmsUtils {
    private static Logger logger = LoggerFactory.getLogger(SendSmsUtils.class);
    public static void sendSMS(String telephone,String vCode) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
        NameValuePair[] data = {
                new NameValuePair("Uid", "yeyongqin"),
                new NameValuePair("Key", "d41d8cd98f00b204e980"),
                new NameValuePair("smsMob", telephone),
                new NameValuePair("smsText", "验证码："+vCode)
        };
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        logger.info(String.valueOf(statusCode));
        for (Header header : headers) {
            logger.info(header.toString());
        }

        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        logger.info(result);

        post.releaseConnection();
    }
}
