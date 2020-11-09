package easy.test;

import easy.common.Constant;
import easy.trans.LANG;
import easy.trans.factory.TFactory;
import easy.trans.factory.TranslatorFactory;
import easy.utils.FutureUtils;

/**
 * @author limengyu
 * @create 2017/10/30
 */
public class Test {
    public static final String ZH = "欢迎使用翻译插件";
    public static final String EN = "The default method implementations (inherited or otherwise) do not apply any synchronization protocol.  If a {@code Collection} implementation has a specific synchronization protocol, then it must override default implementations to apply that protocol.";

    public static TFactory factory;

    static {
        try {
            factory = new TranslatorFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void googleMainTest() throws Exception {
        String result = factory.get("谷歌线路1").trans(LANG.EN, LANG.ZH, EN);
        System.out.println("翻译结果(谷歌线路1:英-汉)："+result);

        result = factory.get("谷歌线路1").trans(LANG.ZH, LANG.EN, ZH);
        System.out.println("翻译结果(谷歌线路1:汉-英)："+result);

    }

    public static void oMiTest() throws Exception {
        String result = factory.get("欧米").trans(LANG.EN, LANG.ZH, EN);
        System.out.println("翻译结果(欧米:英-汉)："+result);

        result = factory.get("欧米").trans(LANG.ZH, LANG.EN, ZH);
        System.out.println("翻译结果(欧米:汉-英)："+result);

    }

    public static void baiduTest() throws Exception {
        String result = factory.get("百度").trans(LANG.EN, LANG.ZH, EN);
        System.out.println("翻译结果(百度:英-汉)："+result);

        result = factory.get("百度").trans(LANG.ZH, LANG.EN, ZH);
        System.out.println("翻译结果(百度:汉-英)："+result);

    }

    public static void jinShanTest() throws Exception {
        String result = factory.get("金山").trans(LANG.EN, LANG.ZH, EN);
        System.out.println("翻译结果(金山:英-汉)："+result);

        result = factory.get("金山").trans(LANG.ZH, LANG.EN, ZH);
        System.out.println("翻译结果(金山:汉-英)："+result);

    }

    public static void youdaoTest() throws Exception {
        String result = factory.get("有道").trans(LANG.EN, LANG.ZH, EN);
        System.out.println("翻译结果(有道:英-汉)："+result);

        result = factory.get("有道").trans(LANG.ZH, LANG.EN, ZH);
        System.out.println("翻译结果(有道:汉-英)："+result);

    }

    public static void tengxunTest() throws Exception {
        String result = factory.get("腾讯").trans(LANG.EN, LANG.ZH, EN);
        System.out.println("翻译结果(腾讯:英-汉)："+result);

        result = factory.get("腾讯").trans(LANG.ZH, LANG.EN, ZH);
        System.out.println("翻译结果(腾讯:汉-英)："+result);

    }

    public static void testTrans() throws Exception {
        System.out.println("----------------------------");
        googleMainTest();

        System.out.println("----------------------------");
        jinShanTest();

//        System.out.println("----------------------------");
//        baiduTest();

//        System.out.println("----------------------------");
//        oMiTest();

//        System.out.println("----------------------------");
//        youdaoTest();

//        System.out.println("----------------------------");
//        tengxunTest();
    }

    public static void main(String[] args) throws Exception {
//        String result = FutureUtils.asyncZH2EN(Constant.TRANSLATE_TYPE.GOOGLE_1.getName(), ZH,Constant.ZH_2_EN);
//        System.out.println("异步翻译结果(中-英)："+result);
//        System.out.println("-------------------------------");
//
//
//        String en2Zh = FutureUtils.asyncEN2ZH(Constant.TRANSLATE_TYPE.OMI.getName(), EN,Constant.EN_2_ZH);
//        System.out.println("异步翻译结果(英-中)："+en2Zh);
        testTrans();
    }
}
