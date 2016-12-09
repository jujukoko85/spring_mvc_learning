package com.howard.webmagic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import org.junit.Test;

import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class TestXPath {

	private String xml = null;

	@Test
	public void test1() {
		Html html = Html.create(xml);
		for (String s : html.xpath("book").all()) {
			System.out.println(s);
		}

	}
	
	@Test
	public void test2() {
		Html html = Html.create(xml);
		Selectable selector = html.xpath("title[@lang]");

	}

	{
		InputStream is = TestXPath.class.getResourceAsStream("test.xml");

		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(reader);

		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while (null != (line = br.readLine())) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		xml = sb.toString();
	}

	@Test
	public void test3() {
		String id = UUID.randomUUID().toString();
		int length = id.length();
		System.out.println(id);
		System.out.println(length);
	}
	
	@Test
	public void test4() {
		String detailUrl = "https://item.taobao.com/item.htm?spm=a230r.1.14.20.vv8ZaF&id=38847761196&ns=1&abbucket=3#detail";
		System.out.println(detailUrl.length());
	}
}