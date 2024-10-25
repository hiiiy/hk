package com.hk.ans.util;

import java.util.Iterator;

//Action Tag:<usebean/>는 DTO객체를 구현하는 태그
public class Util {
	private String arrowNbsp;//공백+화살표이미지 출력해줄 문자열을 저장

	public String getArrowNbsp() {
		return arrowNbsp;
	}

	public void setArrowNbsp(String depth) {
		String nbsp="";
		int depthInt=Integer.parseInt(depth);
		for (int i=0;i<depthInt;i++) {
			nbsp+="&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		this.arrowNbsp = nbsp+(depthInt>0?"<img src='img/173949_right_arrow_icon (1).png'/>":"");
	}
	
}
