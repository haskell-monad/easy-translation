package easy.trans.impl;

import easy.common.Constant;
import easy.http.HttpParams;
import easy.http.HttpPostParams;
import easy.trans.AbstractOnlineTranslator;
import easy.trans.LANG;
import easy.trans.annotation.TranslatorComponent;

@TranslatorComponent(id = "有道")
final public class YoudaoTranslator extends AbstractOnlineTranslator {
	public YoudaoTranslator() {
		langMap.put(LANG.EN, "EN");
		langMap.put(LANG.ZH, "ZH_CN");
	}
	@Override
	protected String getResponse(LANG from, LANG targ, String query) throws Exception {
		HttpParams params = new HttpPostParams().put("type", langMap.get(from) + "2" + langMap.get(targ)).put("i", query).put("doctype", "json").put("xmlVersion", "1.8").put("keyfrom", "fanyi.web").put("ue", "UTF-8").put("action", "FY_BY_CLICKBUTTON").put("typoResult", "true");
		return params.send2String(Constant.YOU_DAO_API);
	}
	@Override
	protected String parseString(String jsonString) {
//		StringBuilder result = new StringBuilder();
//		JSONObject jsonObject = JSONObject.fromObject(jsonString);
//		JSONArray segments = jsonObject.getJSONArray("translateResult");
//		for (int i = 0; i < segments.size(); i++) {
//			result.append(i == 0 ? "" : "\r\n");
//			JSONArray parts = jsonObject.getJSONArray("translateResult").getJSONArray(i);
//			for (int j = 0; j < parts.size(); j++) {
//				result.append(parts.getJSONObject(j).getString("tgt"));
//			}
//		}
//		return new String(result);
        return "";
	}
}
