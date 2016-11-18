package com.howard.springmvc.controller;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.util.Assert;

public class TestUtil {

	@Test
	public void test1() {
		String name = "";
		String message = "error, name must not null";
		try {
			Assert.hasText(name, message);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), message);
		}
	}
}
