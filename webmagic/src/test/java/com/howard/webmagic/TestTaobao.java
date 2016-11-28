package com.howard.webmagic;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpHost;
import org.junit.Test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

public class TestTaobao {

	@Test
	public void test1() {
		Spider.create(new TaobaoDetailPageProcessor())
		.addUrl("https://item.taobao.com/item.htm?id=10966631540")
		.thread(3)
		.addPipeline(new ConsolePipeline())
		.run();;
	}
	
	@Test
	public void test0() {
		Downloader downloader = new HttpClientDownloader();
		
		Task task = new Task() {
			public String getUUID() {
				return UUID.randomUUID().toString();
			}
			
			public Site getSite() {
				try {
					return Site
							.me()
							.setDomain("www.163.com")
							.setHttpProxy(new HttpHost(InetAddress.getByName("127.0.0.1"), 8888))
							.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2767.5 Safari/537.36");
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		
		Page page = downloader.download(
			new Request("http://www.163.com/"), task
		);
		
		System.out.println(page.getHtml());
	}
	
	@Test
	public void test2() {
		Downloader downloader = new HttpClientDownloader();
		
		Task task = new Task() {
			public String getUUID() {
				return UUID.randomUUID().toString();
			}
			
			public Site getSite() {
				try {
					return Site
							.me()
							.setDomain("item.taobao.com")
							.setHttpProxy(new HttpHost(InetAddress.getByName("127.0.0.1"), 8888))
							.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2767.5 Safari/537.36");
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		
		Page page = downloader.download(
//				https://item.taobao.com/item.htm?spm=a1z10.5-c.w4002-1694299437.44.tKNrpn&id=10966631540
			new Request("http://item.taobao.com/item.htm?spm=a1z10.5-c.w4002-1694299437.44.tKNrpn&id=10966631540"), task
		);

		Map<String, String> headers = task.getSite().getHeaders();
		
		for(String key : headers.keySet()) {
			String value = headers.get(key);
			System.out.println(key + " : " + value);
		}
		
//		List<String> links = page.getHtml().regex("//detailskip\\.taobao\\.com(/service/\\S*)',\\n").all();
//		
//		for(String link : links) {
//			String url = "https://detailskip.taobao.com" + link;
//			System.out.println(url);
//			Page p = downloader.download(new Request(url), task);
//			
//			System.out.println(p.getHtml());
//		}
		
	}
}
