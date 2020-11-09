package easy.trans.factory;

import java.net.URISyntaxException;

import easy.trans.Translator;
import easy.trans.exception.DupIdException;

final public class TranslatorFactory extends AbstractTranslatorFactory {
	public TranslatorFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException {
		super();
	}
	@Override
	public Translator get(String id) {
		return translatorMap.get(id);
	}
}
