package easy.trans.impl;

import java.util.HashMap;

import com.google.gson.Gson;

import easy.common.Constant;
import easy.http.HttpParams;
import easy.http.HttpPostParams;
import easy.trans.AbstractOnlineTranslator;
import easy.trans.LANG;
import easy.trans.annotation.TranslatorComponent;

/**
 * @author limengyu
 * @create 2017/10/30
 */
@TranslatorComponent(id = "谷歌线路2")
final public class GoogleTranslation extends AbstractOnlineTranslator {
	public GoogleTranslation() {
		langMap.put(LANG.EN, "en");
		langMap.put(LANG.ZH, "zh");
	}
	@Override
	protected String getResponse(LANG from, LANG targ, String query) throws Exception {
		HttpParams params = new HttpPostParams().put("f", langMap.get(from)).put("t", langMap.get(targ)).put("text", query);
		return params.send2String(Constant.GOOGLE_API);
	}
	@Override
	protected String parseString(String jsonString) {
        HashMap hashMap = gson.fromJson(jsonString, HashMap.class);
		String result = (String)hashMap.get("text");
		return result;
	}
}
