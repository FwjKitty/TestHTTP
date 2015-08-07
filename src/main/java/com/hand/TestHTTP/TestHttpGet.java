package com.hand.TestHTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TestHttpGet {

	public static void main(String[] args) {
		new ReadByGet().start();
	}
}

class ReadByGet extends Thread{
	@Override
	public void run(){
		try {
			//创建一个URL
			URL url = new URL("http://fanyi.youdao.com/openapi.do?keyfrom=guangshuzhijia&key=506831301&type=data&doctype=xml&version=1.1&q=welcome");
			//打开链接
			URLConnection connection = url.openConnection();
			//获取connection的输入流
			InputStream is = connection.getInputStream();
			//若有乱码，则指定编码格式为UTF-8
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			StringBuilder builder = new StringBuilder();
			while((line = br.readLine()) != null){
				builder.append(line);
			}
			br.close();
			isr.close();
			is.close();
			System.out.println(builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}