package easy.trans.impl;

import com.google.gson.internal.LinkedTreeMap;

import easy.common.Constant;
import easy.http.HttpParams;
import easy.http.HttpPostParams;
import easy.trans.AbstractOnlineTranslator;
import easy.trans.LANG;
import easy.trans.annotation.TranslatorComponent;

@TranslatorComponent(id = "金山")
final public class JinshanTranslator extends AbstractOnlineTranslator {
    public JinshanTranslator() {
        langMap.put(LANG.EN, "en");
        langMap.put(LANG.ZH, "zh");
    }

    @Override
    protected String getResponse(LANG from, LANG targ, String query) throws Exception {
        HttpParams params = new HttpPostParams().put("f", langMap.get(from)).put("t", langMap.get(targ)).put("w", query);
        return params.send2String(Constant.JIN_SAN_API);
    }

    @Override
    protected String parseString(String jsonString) {
        try {
            LinkedTreeMap hashMap = gson.fromJson(jsonString, LinkedTreeMap.class);
            LinkedTreeMap map = (LinkedTreeMap) hashMap.get("content");
            String result = (String) map.get("out");
            return result;
        } catch (Exception e) {
            System.out.println("金山: " + e.getMessage());
            return "";
        }
    }
}
