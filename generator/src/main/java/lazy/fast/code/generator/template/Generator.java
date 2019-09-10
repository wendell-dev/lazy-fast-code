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
    void generate();

    /**
     * 下划线转换为驼峰格式
     *
     * @param underscoreName
     *            下划线字符串
     * @return 驼峰格式字符串
     */
    default String underscoreConvertToCamelCase(String underscoreName) {
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

    /**
     * 驼峰转为斜杠加大写转小写，如：AxxBxx 则变为 /axx/bxx
     * 
     * @param camelCaseName
     *            驼峰字符串
     * @return 斜杠加大写转小写
     */
    default String camelCaseConvertToSlash(String camelCaseName) {
        String requestMapping = camelCaseName;
        String prefix = "/";
        for (int i = 0, length = requestMapping.length(); i < length; i++) {
            if (Character.isUpperCase(requestMapping.charAt(i))) {
                // 因为加了一个“/”, 所以需要多增加一次循环
                ++length;
                requestMapping = requestMapping.replace(String.valueOf(requestMapping.charAt(i)),
                    prefix + Character.toLowerCase(requestMapping.charAt(i)));
            }
        }
        if (!requestMapping.startsWith(prefix)) {
            requestMapping = prefix.concat(requestMapping);
        }
        return requestMapping;
    }

}
