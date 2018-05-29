package com.lmfun.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomStrUtils {

	/** 生成随机字符串的根 */
	private static final char[] GEN_NUM_STR = {'0','1','2','3','5','6','7','8','9'};
	
	/** 生成随机字符串的根 */
	private static final char[] GEN_STR = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
		                                   'n','p','q','r','s','t','u','v','w','x','y','z',
		                                   'A','B','C','D','E','F','G','H','I','J','K','L','M',
		                                   'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		                                   '0','1','2','3','4','5','6','7','8','9'};
	/* 字符串分隔符，这个分隔符不能与GEN_STR中的相同 */
	public static final char SPLITTER = ',';
	
	/**
	 * 使用common-lang.jar包中的RandomStringUtils生成指定长度的随机数字、大小写字母及特定符号字符串
	 * @param len
	 * @return
	 */
	public static String getComplexRandomStr(int len) {
		//使用指定的字符生成指定长度的随机字符串
		return RandomStringUtils.random(len, GEN_STR);
	}
	
	/**
	 * 使用common-lang.jar包中的RandomStringUtils生成指定长度的随机数字字符串
	 * @Title  :RandomStrUtil
	 * @param  :@param len
	 * @param  :@return
	 * @return :返回一个指定长度的验证码
	 * @throws
	 */
	public static String getRandomStr(int len) {
		//使用指定的字符生成指定长度的随机字符串
		return RandomStringUtils.random(len, GEN_NUM_STR);
		
		/*//产生5位长度的随机字符串，中文环境下是乱码
		RandomStringUtils.random(5);

		//使用指定的字符生成5位长度的随机字符串
		RandomStringUtils.random(5, new char[]{'a','b','c','d','e','f', '1', '2', '3'});

		//生成指定长度的字母和数字的随机组合字符串
		RandomStringUtils.randomAlphanumeric(5);

		//生成随机数字字符串
		RandomStringUtils.randomNumeric(5);

		//生成随机[a-z]字符串，包含大小写
		RandomStringUtils.randomAlphabetic(5);

		//生成从ASCII 32到126组成的随机字符串 
		RandomStringUtils.randomAscii(4)*/
	}
	
	/**
	 * 生成0.00到0.1之间的随机数
	 * @return
	 */
	public static String getRandomMoney(){
		int i =0;
		i = new Random().nextInt(1000);
		return i+"";
	}
}
