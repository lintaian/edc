package com.lps.edc.util;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpHelper {
	public static byte[] post(String url, HttpEntity param, String reqContentType, String respContentType) throws Exception {
		byte[] rs = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost req = new HttpPost(url);
		req.setHeader("Content-Type", reqContentType);
		req.setEntity(param);
		CloseableHttpResponse resp = client.execute(req);
		try {
			HttpEntity entity = resp.getEntity();
			if (entity != null && entity.getContentType() != null) {
				String contentType = entity.getContentType().getValue();
				if (respContentType.equals(contentType)) {
					long l = entity.getContentLength();
					int length = l > 0 ? (int) l : 2048;
					rs = new byte[length];
					InputStream is = entity.getContent();
					int flag = 0;
					int a = 0;
					while ((a = is.read(rs, flag, length)) > 0) {
						flag += a;
					}
					is.close();
				} else {
//					throw new Exception("error");
				}
			}
		} finally {
			resp.close();
			client.close();
		}
		return rs;
	};
}
