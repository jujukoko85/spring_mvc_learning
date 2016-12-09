package com.howard.webmagic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CharMatcher;
import com.google.common.collect.Maps;
import com.howard.webmagic.util.RegexUtils;

import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class TestHttpClient {
	
	private String userAngent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2767.5 Safari/537.36";

	@Test
	public void test1() throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException {
		SSLContext sslContext = getSSLContext();
		
		HttpClient client = HttpClientBuilder
				.create()
				.setSSLContext(sslContext)
				.build();
		
//		String detailUrl = "http://item.taobao.com/item.htm?id=10966631540";
		String detailUrl = "https://item.taobao.com/item.htm?spm=a230r.1.14.20.vv8ZaF&id=38847761196&ns=1&abbucket=3#detail";
		HttpGet get = new HttpGet(detailUrl);
		get.addHeader(HttpHeaders.USER_AGENT, userAngent);
		
//		HttpHost proxy = new HttpHost(InetAddress.getByName("127.0.0.1"), 8888);
//		RequestConfig requestConfig = RequestConfig
//				.custom()
//				.setProxy(proxy)
//				.build();
//		
//		get.setConfig(requestConfig);
		
		HttpResponse response = client.execute(get);
		String content = EntityUtils.toString(response.getEntity(), "UTF8");

		//规格
		Map<String, String> map = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content);
		Elements elements = doc.getElementsByTag("li");
		Iterator<Element> i = elements.iterator();
		while(i.hasNext()) {
			Element e = i.next();
			if(e.hasAttr("data-value")) {
				Elements span = e.getElementsByTag("span");
				map.put(e.attr("data-value"), span.text());
			}
		}
		
		String priceUrl = RegexUtils.findGroup(content, "(//detailskip\\.taobao\\.com/service/\\S*)',\\n", 1);
		priceUrl = "https:" + priceUrl;
		System.out.println("######################");
		System.out.println(priceUrl);
		System.out.println("######################");
		String priceUrl2 = "http://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=10966631540&sellerId=76414762&modules=dynStock,qrcode,viewer,price,contract,duty,xmpPromotion,delivery,sellerDetail,activity,fqg,zjys,coupon,couponActivity,soldQuantity";
		
		CharMatcher ch = null;
		
		get = new HttpGet(priceUrl);
		get.addHeader(HttpHeaders.USER_AGENT, userAngent);
		get.addHeader(HttpHeaders.REFERER, detailUrl);
		
//		response = client.execute(proxy, get);
		response = client.execute(get);
		
		System.out.println(response.getStatusLine());
		
		content = EntityUtils.toString(response.getEntity(), "UTF8");
		System.out.println(content);
		
		JSONObject jsonContent = JSON.parseObject(content);
		JSONObject dataJsonObject = jsonContent.getJSONObject("data");
		String price = dataJsonObject.getString("price");
		System.out.println(price);
		JSONObject stockJsonObject = dataJsonObject.getJSONObject("dynStock");
		System.out.println(dataJsonObject);
		System.out.println(stockJsonObject);
		JSONObject jsonMap = stockJsonObject.getJSONObject("sku");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(jsonMap.toJSONString());
		Set<String> keys = jsonMap.keySet();

		LinkedHashMap<String, JSONObject> m = new LinkedHashMap<String, JSONObject>();
		for(String key : keys) {
			JSONObject stockInfo = jsonMap.getJSONObject(key);
			
			String[] guiges = key.split(";");
			if(null == guiges || 0 == guiges.length) continue;
			
			StringBuilder name = new StringBuilder();
			for(int k = 0; k < guiges.length; k++) {
				String guige = guiges[k];
				String g = map.get(guige);
				if(null == g || 0 == g.length()) continue;
				name.append(g);
			}
			m.put(name.toString(), stockInfo);
		}
		
		m = getOrder(m);
		Set<String> keySet = m.keySet();
		for(String key : keySet) {
			System.out.println(key + "\t\t" + m.get(key));
		}
		

	}

	private SSLContext getSSLContext() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			//信任所有
			public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
				return true;
			}
			
		}).build();
		return sslContext;
	}

	public  static LinkedHashMap<String, JSONObject> getOrder(Map<String, JSONObject>  map){
		List<Map.Entry<String, JSONObject>> infoIds =new ArrayList<Map.Entry<String, JSONObject>>(map.entrySet());
		
		//排序
		Collections.sort(infoIds, new Comparator<Map.Entry<String, JSONObject>>() {   
		    public int compare(Map.Entry<String, JSONObject> o1, Map.Entry<String, JSONObject> o2) {      
		    	String k1 = o1.getKey();
		    	String k2 = o2.getKey();
		    	return k1.compareTo(k2);
		    	
		    }
		}); 
		
		/*转换成新map输出*/
		LinkedHashMap<String, JSONObject> newMap = new LinkedHashMap <String, JSONObject>();
		
		for(Map.Entry<String,JSONObject> entity : infoIds){
			newMap.put(entity.getKey(), entity.getValue());
		}
		
		return newMap;
	}


}
