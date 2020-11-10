package easy.common;

/**
 * @author limengyu
 * @create 2017/10/30
 */
    public class Constant {

    public static final String DEFAULT_ENGINE = Constant.TRANSLATE_TYPE.GOOGLE_1.getName();
    public static final String GOOGLE_API = "http://translate.hotcn.top/translate/api";
    public static final String GOOGLE_OFFICIAL_API = "http://translate.google.cn/translate_a/single";
    public static final String BAI_DU_API = "http://fanyi.baidu.com/v2transapi";
    public static final String JIN_SAN_API = "http://fy.iciba.com/ajax.php?a=fy";
    public static final String OM_API = "http://www.alifanyi1688.com/transSents.do";
    public static final String TEN_CENT_API = "http://fanyi.qq.com/api/translate";
    public static final String YOU_DAO_API = "http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule&smartresult=ugc&sessionFrom=https://www.baidu.com/link";
    public static final String ELEMENT_NAME = "TranslationSetting";
    public static final String ELEMENT_ATTR_NAME = "engine";
    public static final String DISPLAY_NAME = "Translation Config";
    public static final String CONFIG_ID = "TransConfig";
    public static final int FADE_OUT_TIME = 10000;


    public static final String EN_2_ZH_TIMEOUT = "en2ZhTimeout";
    public static final String ZH_2_EN_TIMEOUT = "ch2EnTimeout";
    public static final long EN_2_ZH = 3000;

    public static final long ZH_2_EN = 3000;


    public enum TRANSLATE_TYPE {
        GOOGLE_1(1, "谷歌线路1"),
        GOOGLE_2(1, "谷歌线路2"),
        BAI_DU(2, "百度"),
        JIN_SHAN(3, "金山"),
        YOU_DAO(4, "有道"),
        TENCENT(5, "腾讯"),
        OMI(6, "欧米");

        private int code;
        private String name;

        TRANSLATE_TYPE(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }
    }

    /**
     * 按照Code和类型获取名称
     * @param code
     * @return
     */
    public static String getNameByCode(int code) {
        TRANSLATE_TYPE[] values = TRANSLATE_TYPE.values();
        for (TRANSLATE_TYPE value : values) {
            if (value.getCode() == code) {
                return value.getName();
            }
        }
        return Constant.TRANSLATE_TYPE.GOOGLE_1.getName();
    }
}
