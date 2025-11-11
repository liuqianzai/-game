package Utils;

import java.util.Random;

public class CodeUtil {
    public static String getCode() {
        // 验证码字符库：数字0-9 + 大写字母A-Z + 小写字母a-z
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        // 循环生成5位字符
        for (int i = 0; i < 5; i++) {
            // 随机获取字符库中的索引（0到chars长度-1）
            int index = random.nextInt(chars.length());
            // 拼接字符
            code.append(chars.charAt(index));
        }

        return code.toString();
    }

}
