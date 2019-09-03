package lazy.fast.code.generator.template;

/**
 * 生成器接口
 *
 * @author wendell
 */
public interface Generator {

    /**
     * 生成目标文件
     */
    void generator();

    /**
     * 字符串下划线转换为驼峰格式
     *
     * @param underscoreName
     *            下划线字符串
     * @return 驼峰格式字符串
     */
    default String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

}
