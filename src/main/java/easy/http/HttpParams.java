package easy.http;

import java.io.InputStream;

public interface HttpParams {
	String send2String(String baseUrl) throws Exception;
	InputStream send2InputStream(String baseUrl) throws Exception;
	HttpParams put(String key, String value);
}
