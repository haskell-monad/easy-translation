package easy.trans.impl;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import easy.common.Constant;
import easy.http.HttpParams;
import easy.http.HttpPostParams;
import easy.trans.AbstractOnlineTranslator;
import easy.trans.LANG;
import easy.trans.annotation.TranslatorComponent;

@TranslatorComponent(id = "百度")
final public class BaiduTranslator extends AbstractOnlineTranslator {
    private static final ScriptEngine engine = (new ScriptEngineManager()).getEngineByName("nashorn");
	public BaiduTranslator() {
		langMap.put(LANG.EN, "en");
		langMap.put(LANG.ZH, "zh");
	}
    @Override
    public String getResponse(LANG from, LANG targ, String query) throws Exception {
        String sign = this.sign(query);
        System.out.println(sign);
        HttpParams params = (new HttpPostParams()).put("from", this.langMap.get(from)).put("to", this.langMap.get(targ)).put("query", query).put("transtype", "translang").put("simple_means_flag", "3").put("sign", sign).put("token", "14f224f9159e29f08d86f480bb43684e");
        return params.send2String(Constant.BAI_DU_API);
    }

	@Override
	protected String parseString(String jsonString) {
//		JSONObject jsonObject = JSONObject.fromObject(jsonString);
//		JSONArray segments = jsonObject.getJSONObject("trans_result").getJSONArray("data");
//		StringBuilder result = new StringBuilder();
//		for (int i = 0; i < segments.size(); i++) {
//			result.append(i == 0 ? "" : "\n");
//			result.append(segments.getJSONObject(i).getString("dst"));
//		}
//		return new String(result);
        return "";
	}

    public String sign(String val) throws Exception {
        String script = "function a(r,o){for(var t=0;t<o.length-2;t+=3){var a=o.charAt(t+2);a=a>=\"a\"?a.charCodeAt(0)-87:Number(a),a=\"+\"===o.charAt(t+1)?r>>>a:r<<a,r=\"+\"===o.charAt(t)?r+a&4294967295:r^a}return r}var C=null;var hash=function(r,_gtk){var o=r.length;o>30&&(r=\"\"+r.substr(0,10)+r.substr(Math.floor(o/2)-5,10)+r.substr(-10,10));var t=void 0,t=null!==C?C:(C=_gtk||\"\")||\"\";for(var e=t.split(\".\"),h=Number(e[0])||0,i=Number(e[1])||0,d=[],f=0,g=0;g<r.length;g++){var m=r.charCodeAt(g);128>m?d[f++]=m:(2048>m?d[f++]=m>>6|192:(55296===(64512&m)&&g+1<r.length&&56320===(64512&r.charCodeAt(g+1))?(m=65536+((1023&m)<<10)+(1023&r.charCodeAt(++g)),d[f++]=m>>18|240,d[f++]=m>>12&63|128):d[f++]=m>>12|224,d[f++]=m>>6&63|128),d[f++]=63&m|128)}for(var S=h,u=\"+-a^+6\",l=\"+-3^+b+-f\",s=0;s<d.length;s++)S+=d[s],S=a(S,u);return S=a(S,l),S^=i,0>S&&(S=(2147483647&S)+2147483648),S%=1e6,S.toString()+\".\"+(S^h)}";
        engine.eval(script);
        Invocable inv = (Invocable)engine;
        return inv.invokeFunction("hash", val, "320305.131321201").toString();
    }
}
