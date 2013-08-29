package br.ufpb.gerenciamento_fiado.URL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpUtils {
	
	//método quando enviar algum dado pelo o get
	public static String urlContent(String address) throws IOException, ClientProtocolException {
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet(address);
	    ResponseHandler<String> handler = new BasicResponseHandler();
	    return(client.execute(httpGet, handler));
	  }
	
	//método quando enviar algum dado pelo o post
	public static String urlContentPost(String address, String ... paramNamesAndValues) throws IOException, ClientProtocolException {
	    HttpClient client = new DefaultHttpClient();
	    HttpPost httpPost = new HttpPost(address);
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    for(int i=0; i<paramNamesAndValues.length-1; i=i+2) {
	      String paramName = paramNamesAndValues[i];
	      String paramValue = paramNamesAndValues[i+1];  // NOT URL-Encoded
	      params.add(new BasicNameValuePair(paramName, paramValue));
	    }
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
	    httpPost.setEntity(entity);
	    ResponseHandler<String> handler = new BasicResponseHandler();
	    return(client.execute(httpPost, handler));
	  }
	
}
