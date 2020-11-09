package easy.trans.factory;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import easy.trans.AbstractOnlineTranslator;
import easy.trans.Translator;
import easy.trans.annotation.TranslatorComponent;
import easy.trans.exception.DupIdException;
import easy.trans.impl.BaiduTranslator;
import easy.trans.impl.GoogleTranslation;
import easy.trans.impl.GoogleTranslator;
import easy.trans.impl.JinshanTranslator;
import easy.trans.impl.OmiTranslator;
import easy.trans.impl.TencentTranslator;
import easy.trans.impl.YoudaoTranslator;

public abstract class AbstractTranslatorFactory implements easy.trans.factory.TFactory {
	protected Map<String, Translator> translatorMap = new HashMap<>();
	private List<String> workPackages = new ArrayList<>();
	private List<Class<? extends AbstractOnlineTranslator>> translatorClasses =
			Arrays.asList(
					BaiduTranslator.class,
					GoogleTranslator.class,
					GoogleTranslation.class,
					JinshanTranslator.class,
					TencentTranslator.class,
					OmiTranslator.class,
					YoudaoTranslator.class);

	public AbstractTranslatorFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException {
		initSystemTranslator();
		initUserTranslator();
	}
	public AbstractTranslatorFactory(String[] workPackages) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException {
		this();
	}
	public AbstractTranslatorFactory(String workPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException {
		this(new String[] { workPackage });
	}
	private void initSystemTranslator() throws InstantiationException, IllegalAccessException, DupIdException {
		for (Class<?> translatorClass : translatorClasses) {
			TranslatorComponent component = translatorClass.getAnnotation(TranslatorComponent.class);
			if (component != null) {
				String id = component.id();
				if (translatorMap.containsKey(id)) {
					throw new DupIdException("Id duplication exception");
				} else {
					translatorMap.put(component.id(), (Translator) translatorClass.newInstance());
				}
			}
		}
	}
	private void initUserTranslator() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException {
		for (String workPackage : workPackages) {
			List<String> workClassesName = getClassNameByPackage(workPackage);
			for (String workClassName : workClassesName) {
				Class<?> workClass = Class.forName(workClassName);
				TranslatorComponent component = workClass.getAnnotation(TranslatorComponent.class);
				if (component != null) {
					String id = component.id();
					if (translatorMap.containsKey(id)) {
						throw new DupIdException("Id duplication exception");
					} else {
						translatorMap.put(component.id(), (Translator) workClass.newInstance());
					}
				}
			}
		}
	}
	private List<String> getClassNameByPackage(String packageName) throws URISyntaxException {
		List<String> classesName = new ArrayList<>();
		ClassLoader loader = getClass().getClassLoader();
		URL url = loader.getResource(packageName.replace(".", "/"));
		File packageDir = new File(new URI(url.getPath()).getPath());
		for (File classFile : packageDir.listFiles()) {
			String classNickName = classFile.getName();
			classNickName = classNickName.substring(0, classNickName.indexOf('.'));
			classesName.add(packageName + "." + classNickName);
		}
		return classesName;
	}
}
