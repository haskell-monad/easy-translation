package easy.trans.factory;

import easy.trans.Translator;

public interface TFactory {
	Translator get(String id);
}
