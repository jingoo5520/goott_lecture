package com.miniproject.util;

import java.util.HashMap;
import java.util.Map;

public class ImageMimeType {
	private static Map<String, String> imageMimeType;
	
	static { // static 멤버 초기화 블럭
		
		imageMimeType = new HashMap<String, String>();
		
		imageMimeType.put("jpg", "image/jpeg");
		imageMimeType.put("jpeg", "image/jpeg");
		imageMimeType.put("gif", "image/gif");
		imageMimeType.put("png", "image/png");
	}
	
	// ext가 이미지인지 아닌지
	public static boolean isImage(String ext) {
		
		return imageMimeType.containsKey(ext);
	}
	
}
