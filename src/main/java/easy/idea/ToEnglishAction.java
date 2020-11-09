package easy.idea;

import javax.swing.*;

import org.apache.commons.lang.StringUtils;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import easy.common.Constant;
import easy.config.TranslationSetting;
import easy.utils.FutureUtils;

/**
 * 中文转英文
 * @author Administrator
 */
public class ToEnglishAction extends AnAction {
	private TranslationSetting translationSetting;



    @Override
	public void actionPerformed(AnActionEvent event) {
		this.translationSetting = TranslationSetting.getInstance();
		Project project = event.getData(PlatformDataKeys.PROJECT);
		String chineseName = Messages.showInputDialog(project, "想翻译成英文？", "中文转英文", Messages.getQuestionIcon());
		String engine = this.getEngine();
		String title = engine;
        if (StringUtils.isNotBlank(chineseName)) {
            String englishName = this.transaction(engine, chineseName,this.getZh2EnTimeout());
            Messages.showTextAreaDialog(new JTextField(englishName), title, englishName);
        }
	}

	public String transaction(String engine, String text,long timeout) {
        String result = FutureUtils.asyncZH2EN(engine,text,timeout);
		return result;
	}

	public String getEngine() {
		return this.translationSetting.getEngine();
	}
	public long getZh2EnTimeout(){
	    return this.translationSetting.getZh2EnTimeout();
    }



}
