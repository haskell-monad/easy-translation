package easy.config;

import java.awt.*;

import javax.swing.*;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.Disposer;

import easy.common.Constant;
import easy.form.TranslationForm;

/**
 * @author limengyu
 * @create 2017/10/30
 */
public class TranslationConfig implements SearchableConfigurable, Configurable.NoScroll, Disposable {

    private static TranslationForm translationForm;
    private static TranslationSetting translationSetting = TranslationSetting.getInstance();

    @NotNull
    @Override
    public String getId() {
        return Constant.CONFIG_ID;
    }

    @Nullable
    @Override
    public Runnable enableSearch(String s) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return Constant.DISPLAY_NAME;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return this.getId();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if(null == translationForm) {
            translationForm = new TranslationForm();
        }
        String engine = translationSetting.getEngine();
        if (Constant.TRANSLATE_TYPE.GOOGLE_1.getName().equals(engine)) {
            translationForm.google_1.setSelected(true);
        } else if (Constant.TRANSLATE_TYPE.GOOGLE_2.getName().equals(engine)) {
            translationForm.google_2.setSelected(true);
        } else if (Constant.TRANSLATE_TYPE.BAI_DU.getName().equals(engine)) {
            translationForm.baidu.setSelected(true);
        } else if (Constant.TRANSLATE_TYPE.JIN_SHAN.getName().equals(engine)) {
            translationForm.jinshan.setSelected(true);
        } else if (Constant.TRANSLATE_TYPE.TENCENT.getName().equals(engine)) {
            translationForm.tencent.setSelected(true);
        } else if (Constant.TRANSLATE_TYPE.OMI.getName().equals(engine)) {
            translationForm.omi.setSelected(true);
        }

        long ch2en = translationSetting.getZh2EnTimeout();
        long en2ch = translationSetting.getEn2ZhTimeout();
        translationForm.zh2En.setText(Long.toString(ch2en));
        translationForm.en2Zh.setText(Long.toString(en2ch));

        JPanel mainPanel = translationForm.mainPanel;

//        for (Component component : mainPanel.getComponents()) {
//            if(component instanceof JRadioButton){
//                if(StringUtils.isNotBlank(engine) && component.getName().equals(engine)){
//                    ((JRadioButton) component).setSelected(true);
//                }
//            }else if(component instanceof JTextField){
//                if("zh2En".equals(component.getName())){
//                    ((JTextField) component).setText(Long.toString(ch2en));
//                }else if("en2Zh".equals(component.getName())){
//                    ((JTextField) component)
//                            .setText(Long.toString(en2ch));
//                }
//             }
//        }
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return !translationSetting.getEngine().equals(translationForm.getSelectValue())
                || translationSetting.getZh2EnTimeout() != translationForm.getZh2ENTimeout()
                || translationSetting.getEn2ZhTimeout() != translationForm.getEn2ZhTimeout()
                ;
    }

    @Override
    public void apply() {
        translationSetting.setEngine(translationForm.getSelectValue());
        translationSetting.setZh2EnTimeout(translationForm.getZh2ENTimeout());
        translationSetting.setEn2ZhTimeout(translationForm.getEn2ZhTimeout());
    }

    @Override
    public void reset() {
        translationForm.google_1.setSelected(true);
        translationForm.zh2En.setText(Long.toString(Constant.ZH_2_EN));
        translationForm.en2Zh.setText(Long.toString(Constant.EN_2_ZH));
    }

    @Override
    public void disposeUIResources() {
        Disposer.dispose(this);
    }

    @Override
    public void dispose() {
        translationForm = null;
    }
}
