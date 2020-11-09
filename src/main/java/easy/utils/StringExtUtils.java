package easy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


/**
 * @author limengyu
 * @create 2018/11/10
 */
public class StringExtUtils {
    public static final Pattern PATTERN = Pattern.compile("/\\*+|\\*+/|\\s+\\*\\s+|\\*|\r|\n");

    private StringExtUtils() {
    }

    public static boolean isContainsSpace(String text) {
        return text.trim().contains(" ");
    }

    public static String formatText(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        String formatText = text.trim();
        if (StringUtils.isBlank(formatText)) {
            return formatText;
        }
        if (!isContainsSpace(formatText)) {
            StringBuffer buffer = new StringBuffer();
            boolean end = false;

            for (int i = 0; i < formatText.length(); ++i) {
                if (formatText.charAt(i) == '_') {
                    buffer.append(" ");
                    end = true;
                } else if (Character.isUpperCase(formatText.charAt(i))) {
                    if (end) {
                        buffer.append(" " + formatText.charAt(i));
                        end = false;
                    } else {
                        buffer.append(formatText.charAt(i));
                    }
                } else {
                    buffer.append(formatText.charAt(i));
                    end = true;
                }
            }

            formatText = buffer.toString();
        } else {
            Matcher m = PATTERN.matcher(formatText);
            formatText = m.replaceAll(" ");
        }
        return replaceText(formatText.trim());
    }

    public static String replaceText(String text){
        if(StringUtils.isBlank(text)){
            return text;
        }
        text = text.replaceAll("<em>", "`");
        text = text.replaceAll("</em>", "`");
        text = text.replaceAll("<i>", "`");
        text = text.replaceAll("</i>", "`");
        text = text.replaceAll("<a\\s?[^>]*>", "`");
        text = text.replaceAll("</a>", "`");
        text = text.replaceAll("\\{@code (.*?)\\}", "`$1`");
        text = text.replaceAll("\\{@link (.*?)\\}", "`$1`");
        text = text.replaceAll("\\{@linkplain (.*?)\\}", "`$1`");
        text = text.replaceAll("<p>", " ");
        text = text.replaceAll("</p>", " ");
        text = text.replaceAll("<tt>", "`");
        text = text.replaceAll("</tt>", "`");
        text = text.replaceAll("\\(&nbsp;(.*)&nbsp;\\)", "`$1`");
        text = text.replaceAll("&nbsp;", " ");
        text = text.replaceAll("&lt;", "`<`");
        return text;
    }
    public static void main(String[] args) {
        String str = " * <p>A {@code String} represents a string in the UTF-16 format\n" +
                " * in which <em>supplementary characters</em> are represented by <em>surrogate\n" +
                " * pairs</em> (see the section <a href=\"Character.html#unicode\">Unicode\n" +
                " * Character Representations</a> in the {@code Character} class for\n" +
                " * more information).\n" +
                " * Index values refer to {@code char} code units, so a supplementary\n" +
                " * character uses two positions in a {@code String}.";

       str = " * Returns the {@code char} value at the\n" +
               "     * specified index. An index ranges from {@code 0} to\n" +
               "     * {@code length() - 1}. The first {@code char} value of the sequence\n" +
               "     * is at index {@code 0}, the next at index {@code 1},\n" +
               "     * and so on, as for array indexing.";

        System.out.println(formatText(str));
    }
}
