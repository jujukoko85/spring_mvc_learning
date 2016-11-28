package com.howard.webmagic;

import java.io.IOException;
import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	
	private String userAngent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2767.5 Safari/537.36";

	@Test
	public void test1() throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			//信任所有
			public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
				return true;
			}
			
		}).build();
		
		HttpClient client = HttpClientBuilder.create().setSSLContext(sslContext).build();
		
		String detailUrl = "https://item.taobao.com/item.htm?spm=a1z10.5-c.w4002-1694299437.44.tKNrpn&id=10966631540";
		HttpGet get = new HttpGet(detailUrl);
		get.addHeader(HttpHeaders.USER_AGENT, userAngent);
		
		HttpHost proxy = new HttpHost(InetAddress.getByName("127.0.0.1"), 8888);
		
		HttpResponse response = client.execute(proxy, get);
		
		String content = EntityUtils.toString(response.getEntity(), "UTF8");
		
		System.out.println(content.length());
		
		String priceUrl = "https://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=10966631540&sellerId=76414762&modules=dynStock,qrcode,viewer,price,contract,duty,xmpPromotion,delivery,sellerDetail,activity,fqg,zjys,coupon,couponActivity,soldQuantity";
		
		get = new HttpGet(priceUrl);
		get.addHeader(HttpHeaders.USER_AGENT, userAngent);
		get.addHeader(HttpHeaders.REFERER, detailUrl);
		
		response = client.execute(proxy, get);
		
		content = EntityUtils.toString(response.getEntity(), "UTF8");
		
		System.out.println(content);
	}
}
