package com.my.learn.match;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularDemo {
    //十八位身份证正则校验
    private static final String ID_CARD_MATCH = "^(\\d{6}(18|19|20)?\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}([0-9Xx]))$";
    //十五位身份证正则校验
    private static final String regularExpressionForFifteenDigits = "^(\\d{6})(\\d{6})(\\d{3})$";
    //手机号正则待确认
    private static final String PHONE_NUMBER_PATTERN = "^1[3-9]\\d{9}$";
    //姓名正则待确认
    private static final String CHINESE_NAME_PATTERN = "[\\u4e00-\\u9fa5]{1,2}[\\u4e00-\\u9fa5]+";
    public static List<String> extractIdNumbers(String text) {

        Pattern pattern = Pattern.compile(ID_CARD_MATCH);
        Matcher matcher = pattern.matcher(text);

        List<String> idNumbers = new ArrayList<>();
        while (matcher.find()) {
            idNumbers.add(matcher.group());  // 添加匹配到的身份证号码
        }

        return idNumbers;
    }
}
