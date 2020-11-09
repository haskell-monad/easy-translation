package easy.trans;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public abstract class AbstractOnlineTranslator implements easy.trans.Translator {
	protected Map<easy.trans.LANG, String> langMap = new HashMap<>(8);
    protected final static Gson gson = new Gson();

	@Override
	final public String trans(easy.trans.LANG from, easy.trans.LANG targ, String query) {
		String response = "";
		try {
			response = getResponse(from, targ, query);
			String result = parseString(response);
			return result;
		} catch (Exception e) {
            System.out.println("trans error: "+e.getMessage());
			return response;
		}
	}
	abstract protected String getResponse(easy.trans.LANG from, LANG targ, String query) throws Exception;
	abstract protected String parseString(String jsonString);
}
