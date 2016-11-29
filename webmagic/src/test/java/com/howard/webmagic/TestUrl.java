package com.howard.webmagic;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class TestUrl {

	@Test
	public void test1() throws MalformedURLException {
		String urlStr = "//www.taobao.com/a/b";
		
		URL url = new URL(new URL("http://item.taobao.com/"), urlStr);
		
		System.out.println(url.toString());
	}
}
