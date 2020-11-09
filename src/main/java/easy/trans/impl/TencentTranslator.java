package easy.trans.impl;

import easy.common.Constant;
import easy.http.HttpParams;
import easy.http.HttpPostParams;
import easy.trans.AbstractOnlineTranslator;
import easy.trans.LANG;
import easy.trans.annotation.TranslatorComponent;

@TranslatorComponent(id = "腾讯")
final public class TencentTranslator extends AbstractOnlineTranslator {
	public TencentTranslator() {
		langMap.put(LANG.EN, "1");
		//腾讯是小写的
		langMap.put(LANG.zh, "0");
	}
	@Override
	protected String getResponse(LANG from, LANG targ, String query) throws Exception {
		HttpParams params = new HttpPostParams().put("source", langMap.get(from)).put("target", langMap.get(targ)).put("sourceText", query);
		return params.send2String(Constant.TEN_CENT_API);
	}
	@Override
	protected String parseString(String jsonString) {
//		StringBuilder str = new StringBuilder();
//		JSONObject rootObj = JSONObject.fromObject(jsonString);
//		JSONArray array = rootObj.getJSONArray("records");
//		for (int i = 0; i < array.size(); i++) {
//			str.append(array.getJSONObject(i).getString("targetText"));
//		}
//		return str.toString();
        return "";
	}
}
