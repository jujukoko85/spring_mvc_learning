package com.howard.webmagic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestRegexUtils {

	@Test
	public void test1() {
		String target = RegexUtils.findGroup("adkjsflasjdlfjadlsjf//detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=10966631540&sellerId=76414762&modules=dynStock,qrcode,viewer,price,contract,duty,xmpPromotion,delivery,sellerDetail,activity,fqg,zjys,coupon,couponActivity,soldQuantity',\\ndsfadfas", "//detailskip\\.taobao\\.com(/service/\\S*)',", 1);
		System.out.println(target);
	}
	
	@Test
	public void test2() {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher("adlkfja112313LAJSD");
		System.out.println(matcher.find());
		System.out.println(matcher.group(0));
	}
}
