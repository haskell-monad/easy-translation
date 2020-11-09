package easy.utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import easy.common.Constant;
import easy.trans.LANG;
import easy.trans.factory.TFactory;
import easy.trans.factory.TranslatorFactory;

public class FutureUtils {

    private static final String ERROR = "翻译失败或超时,请重试";
    private static TFactory factory;

    static {
        try {
            factory = new TranslatorFactory();
        } catch (Exception e) {
            System.out.println("init error:" + e.getMessage());
        }
    }

    public static String asyncEN2ZH(String engine, String text, long timeout) {

        CompletableFuture<String> googleFuture =
                customAsyncTrans(Constant.TRANSLATE_TYPE.GOOGLE_1.getName(), LANG.EN, LANG.ZH, text);

        if (Constant.TRANSLATE_TYPE.GOOGLE_1.getName().equals(engine)) {
            try {
                String result = googleFuture.get(timeout, TimeUnit.MILLISECONDS);
                return "Google: " + result;
            } catch (Exception e) {
            }
        }
        System.out.println("-----not------");
        CompletableFuture<String> customFuture =
                customAsyncTrans(engine, LANG.EN, LANG.ZH, text);
        String custom = "", google = "";
        try {
            CompletableFuture.anyOf(googleFuture, customFuture)
                    .get(timeout, TimeUnit.MILLISECONDS);
            custom = customFuture.get();
            google = googleFuture.get();
        } catch (Exception e) {

        }
        if (StringUtils.isNotBlank(custom)) {
            return engine + ": " + custom;
        } else if (StringUtils.isNotBlank(google)) {
            return "Google: " + google;
        } else {
            return ERROR;
        }
    }

    public static String asyncZH2EN(String engine, String text, long timeout) {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() ->
                        {
                            try {
                                return factory.get(Constant.TRANSLATE_TYPE.GOOGLE_1.getName()).trans(LANG.ZH, LANG.EN, text);
                            } catch (Exception e) {
                                return "";
                            }
                        }
                ).exceptionally(th -> "")
                .thenCombineAsync(
                        CompletableFuture.supplyAsync(() -> {
                            try {
                                return factory.get(Constant.TRANSLATE_TYPE.JIN_SHAN.getName()).trans(LANG.ZH, LANG.EN, text);
                            } catch (Exception e) {
                                return "";
                            }
                        }).exceptionally(th -> ""),
                        (google, jinSan) -> "Google: "+google + "\n\n" +"金山翻译: "+ jinSan + "\n\n").exceptionally(th -> "");
        try {
            return future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            return ERROR;
        }
    }

    private static CompletableFuture<String> customAsyncTrans(String engine,LANG from,LANG to, String text){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            String result = "";
            try {
                result = factory.get(engine).trans(from,to, text);
            } catch (Exception e) {
            }
            return result;
        });
        return future;
    }
}
