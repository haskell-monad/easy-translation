package easy.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.apache.commons.lang.StringUtils;

import easy.common.Constant;

/**
 * @author limengyu
 * @create 2017/10/30
 */
public class TranslationForm {

    public JRadioButton google_1;
    public JRadioButton google_2;
    public JRadioButton baidu;
    public JRadioButton jinshan;
    public JRadioButton tencent;
    public JRadioButton omi;
    public JPanel mainPanel;
    private JLabel desc;

    private String selectValue;
    private JRadioButton currentSelectButton;

    private long zh2EnTimeout;
    private long en2ZhTimeout;
    private JLabel en2ZhLabel;
    private JLabel zh2EnLabel;
    public JTextField zh2En;
    public JTextField en2Zh;


    public TranslationForm() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton temp = (JRadioButton)e.getSource();
                if(temp.isSelected()){
                    selectValue = temp.getText();
                    currentSelectButton = temp;
                }
            }
        };
        baidu.addActionListener(listener);
        jinshan.addActionListener(listener);
        tencent.addActionListener(listener);
        omi.addActionListener(listener);
        google_1.addActionListener(listener);
        google_2.addActionListener(listener);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                long timeout = 0;
                try {
                    String text = e.getDocument().getText(0, e.getDocument().getLength());
                    if(StringUtils.isNotBlank(text)){
                        timeout = Long.parseLong(text);
                    }
                } catch (BadLocationException e1) {

                }catch (NumberFormatException e2){

                }
                if(e.getDocument() == en2Zh.getDocument()){
                    en2ZhTimeout = timeout;

                }else if(e.getDocument() == zh2En.getDocument()){
                    zh2EnTimeout = timeout;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                long timeout = 0;
                try {
                    String text = e.getDocument().getText(0, e.getDocument().getLength());
                    if(StringUtils.isNotBlank(text)){
                        timeout = Long.parseLong(text);
                    }
                } catch (BadLocationException e1) {

                }catch (NumberFormatException e2){

                }
                System.out.println("--insertUpdate---"+timeout);
                if(e.getDocument() == en2Zh.getDocument()){
                    en2ZhTimeout = timeout;

                }else if(e.getDocument() == zh2En.getDocument()){
                    zh2EnTimeout = timeout;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                long timeout = 0;
                try {
                    String text = e.getDocument().getText(0, e.getDocument().getLength());
                    if(StringUtils.isNotBlank(text)){
                        timeout = Long.parseLong(text);
                    }
                } catch (BadLocationException e1) {

                }catch (NumberFormatException e2){

                }
                if(e.getDocument() == en2Zh.getDocument()){
                    en2ZhTimeout = timeout;

                }else if(e.getDocument() == zh2En.getDocument()){
                    zh2EnTimeout = timeout;
                }
            }
        };

        zh2En.getDocument().addDocumentListener(documentListener);
        en2Zh.getDocument().addDocumentListener(documentListener);
    }
    public String getSelectValue() {
        return selectValue;
    }

    public JRadioButton getCurrentSelectButton(){
        return currentSelectButton;
    }

    public long getZh2ENTimeout(){
        return (zh2EnTimeout <= 0 || zh2EnTimeout > 20000) ? Constant.ZH_2_EN : zh2EnTimeout;
    }

    public long getEn2ZhTimeout(){
        return (en2ZhTimeout <= 0 || en2ZhTimeout > 20000) ? Constant.EN_2_ZH : en2ZhTimeout;
    }
}
