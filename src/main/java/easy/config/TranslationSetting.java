package easy.config;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

import easy.common.Constant;

/**
 * @author limengyu
 * @create 2017/10/30
 */
@State(name = Constant.ELEMENT_NAME, storages = { @Storage(value = "$APP_CONFIG$/TranslationSetting.xml") })
public class TranslationSetting implements PersistentStateComponent<TranslationSetting> {
	private String engine;
	private long en2ZhTimeout;
	private long zh2EnTimeout;

	public TranslationSetting() {
	}
	public static TranslationSetting getInstance() {
		return ServiceManager.getService(TranslationSetting.class);
	}
//	@Nullable
//	@Override
//	public Element getState() {
//		Element element = new Element(Constant.ELEMENT_NAME);
//		element.setAttribute(Constant.ELEMENT_ATTR_NAME, this.getEngine());
//        element.setAttribute(Constant.EN_2_ZH_TIMEOUT,Long.toString(en2ZhTimeout));
//        element.setAttribute(Constant.ZH_2_EN_TIMEOUT,Long.toString(zh2EnTimeout));
//		return element;
//	}

    @Nullable
    @Override
    public TranslationSetting getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull TranslationSetting state) {
        XmlSerializerUtil.copyBean(state, this);
    }

//    @Override
//	public void loadState(Element element) {
//		this.setEngine(element.getAttributeValue(Constant.ELEMENT_ATTR_NAME));
//        String en2ch = element.getAttributeValue(Constant.EN_2_ZH_TIMEOUT);
//        this.setEn2ZhTimeout(StringUtils.isBlank(en2ch) ? Constant.EN_2_ZH : Long.parseLong(en2ch));
//
//        String ch2en = element.getAttributeValue(Constant.ZH_2_EN_TIMEOUT);
//        this.setZh2EnTimeout(StringUtils.isBlank(ch2en) ? Constant.ZH_2_EN : Long.parseLong(ch2en));
//	}
	public String getEngine() {
		return this.engine == null ? Constant.DEFAULT_ENGINE : this.engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}

	public void setEn2ZhTimeout(long timeout){
        this.en2ZhTimeout = timeout;
    }
    public void setZh2EnTimeout(long timeout){
        this.zh2EnTimeout = timeout;
    }

    public long getEn2ZhTimeout() {
        return (en2ZhTimeout <= 0 || en2ZhTimeout > 20000) ? Constant.EN_2_ZH : en2ZhTimeout;
    }

    public long getZh2EnTimeout() {
        return zh2EnTimeout <= 0 || zh2EnTimeout > 20000 ? Constant.ZH_2_EN : zh2EnTimeout;
    }
}
