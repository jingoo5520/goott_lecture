package com.miniproject.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class MyResponseWithData<T> {
	private int resultCode;
	private String resultMessage;
	private T data;
	
	@Builder // 생성자에 @builder 어노테이션 추가시 아래의 생성자가 가지고 있는 변수를 builder 패턴으로 만들어 줌 
	public MyResponseWithData(ResponseType responseType, T data) {
		this.resultCode = responseType.getResultCode();
		this.resultMessage = responseType.getResultMessage();
		this.data = data;
	}
	
	// data 없이 성공했다는 코드(200), "success" 만 전송
	public static MyResponseWithData success() {
		return MyResponseWithData.builder()
				.responseType(ResponseType.SUCCESS)
				.build();
	}
	
	// data + 성공
	public static <T> MyResponseWithData<T> success(T data) {
		return new MyResponseWithData<>(ResponseType.SUCCESS, data);
	}
	
	// 실패
	public static MyResponseWithData fail() {
		return MyResponseWithData.builder()
				.responseType(ResponseType.FAIL)
				.build();
	}
}
