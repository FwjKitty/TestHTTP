package com.hand.TestHTTP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestHttpPost {

	public static void main(String[] args) {
		new ReadByPost().start();
	}
}

class ReadByPost extends Thread{
	@Override
	public void run(){
		try {
			/**
			 * 1、创建一个URL
			 * 2、打开URL的openConnection,而且还要把该connection转换成HttpURLConnection
			 * 3、设置连接的参数，包括字符集，请求方法为post
			 * 4、获取输出流，将向服务器传输的数据一次性写出
			 * 5、再获取输入流，从输入流中读取数据
			 */
			
			URL url = new URL("http://fanyi.youdao.com/openapi.do");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("encoding", "UTF-8");
			//设置connection可以从网络获取数据
			connection.setDoInput(true);
			//设置connection可以向服务器（互联网）传输数据
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write("keyfrom=guangshuzhijia&key=506831301&type=data&doctype=xml&version=1.1&q=fwj");
			bw.flush();
			
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			StringBuilder builder = new StringBuilder();
			while((line = br.readLine()) != null){
				builder.append(line);
			}
			bw.close();
			br.close();
			osw.close();
			isr.close();
			os.close();
			is.close();
			System.out.println(builder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}