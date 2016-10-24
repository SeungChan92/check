package com.daou.chasedae.web_test.common;

import java.util.Random;

public class RandomString {

	  private static final char[] symbols;

	  static {
	    StringBuilder tmp = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
	      tmp.append(ch);
	    for (char ch = 'a'; ch <= 'z'; ++ch)
	      tmp.append(ch);
	    symbols = tmp.toString().toCharArray();
	  }   

	  private final Random random = new Random();

	  private final char[] buf;

	  public RandomString(int length) {
	    if (length < 1)
	      throw new IllegalArgumentException("length < 1: " + length);
	    buf = new char[length];
	  }

	  public String nextString() {
	    for (int idx = 0; idx < buf.length; ++idx) 
	      buf[idx] = symbols[random.nextInt(symbols.length)];
	    return new String(buf);
	  }
	  	public String select_mode() {
			String mode = "";
			
			Random random = new Random();
			int seed = random.nextInt(3);
			
			switch(seed)
			{
			case 0:
				mode = "단문";
				break;
			case 1:
				mode = "장문";
				break;
			case 2:
				mode = "포토";
				break;
			}
			
			return mode;
		}
	}