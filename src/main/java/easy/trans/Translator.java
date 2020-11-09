package easy.trans;


public interface Translator {
	String trans(easy.trans.LANG from, easy.trans.LANG targ, String query) throws Exception;
}
