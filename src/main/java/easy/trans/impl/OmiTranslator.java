package easy.trans.impl;

import easy.common.Constant;
import easy.http.HttpParams;
import easy.http.HttpPostParams;
import easy.trans.AbstractOnlineTranslator;
import easy.trans.LANG;
import easy.trans.annotation.TranslatorComponent;

@TranslatorComponent(id = "欧米")
final public class OmiTranslator extends AbstractOnlineTranslator {
	public OmiTranslator() {
		langMap.put(LANG.EN, "e");
		langMap.put(LANG.ZH, "c");
	}
	@Override
	public String getResponse(LANG from, LANG targ, String query) throws Exception {
		HttpParams params = new HttpPostParams().put("languageType", langMap.get(from) + "2" + langMap.get(targ)).put("userDbName", "").put("sentsToTrans", query);
		return params.send2String(Constant.OM_API);
	}
	@Override
	protected String parseString(String jsonString) {
//		JSONObject jsonObject = JSONObject.fromObject(jsonString);
//		JSONArray segments = jsonObject.getJSONArray("sentsResults").getJSONArray(1);
//		StringBuilder result = new StringBuilder();
//		for (int i = 0; i < segments.size(); i++) {
//			result.append(segments.getString(i));
//		}
//		return result.toString();
        return "";
	}
}
