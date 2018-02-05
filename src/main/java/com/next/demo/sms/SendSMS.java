package com.next.demo.sms;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * http://sms.webchinese.com.cn/api.shtml
 * @author EMan
 *
 */
public class SendSMS {
	public static void main(String[] args) throws Exception {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "用户名"), // 中国网建sms平台注册的用户名
				new NameValuePair("Key", "用户秘钥"), // 中国网建sms平台注册的用户密钥
				new NameValuePair("smsMob", "手机号"), // 将要发送到的手机号码
				new NameValuePair("smsText", "按照短信模板发送内容，短信模板中变量具体化即可") };// 要发送的短信内容
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
		System.out.println(result); // 打印返回消息状态

		post.releaseConnection();
	}
}
