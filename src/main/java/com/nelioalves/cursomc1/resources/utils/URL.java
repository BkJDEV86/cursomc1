package com.nelioalves.cursomc1.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	
	// Esse método é porque a pessoa pode digitar na url com espaços.
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		
	}
	

	// Esse método pega as strings da url do postman e converte para números inteiros...
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<vet.length; i++) {
			
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
	}
	
	
	
}
