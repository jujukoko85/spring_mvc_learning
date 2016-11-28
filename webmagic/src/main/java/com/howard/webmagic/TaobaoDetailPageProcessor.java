package com.howard.webmagic;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class TaobaoDetailPageProcessor implements PageProcessor {

	private Site site = Site.me();
	
	public Site getSite() {
		return this.site;
	}

	public void process(Page page) {
		Html html = page.getHtml();
//		page.putField("first source text", html.getFirstSourceText());
		List<String> contents = html.regex("//detailskip\\.taobao\\.com(/service/\\S*)',\\n").all();
		System.out.println(contents.size());
		for(String content : contents) {
			page.addTargetRequest("https://detailskip.taobao.com" + content);
		}
		
		System.out.println(page.getRawText());
		
	}

}
