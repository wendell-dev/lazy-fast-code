package lazy.fast.code.generator;

import lazy.fast.code.generator.util.JdbcUtils;

import java.util.HashMap;
import java.util.Map;

public class Config {

    /**
     * 模板填充数据
     */
    public static final Map<String, Object> DATA = new HashMap<>(16);
    /**
     * 模块模板文件根路径
     */
    public static String moduleTemplateRootPath;
    /**
     * 项目骨架模板文件根路径
     */
    public static String layoutTemplateRootPath;
    /**
     * 要生成的目标文件根路径
     */
    public static String targetRootPath;
    /**
     * 作者签名
     */
    public static String author;
    /**
     * 根包名
     */
    public static String basePackageName;
    /**
     * 模块名
     */
    public static String moduleName;
    /**
     * 数据库表名
     */
    public static String tableName;
    /**
     * 类名
     */
    public static String className;
    /**
     * 类描述
     */
    public static String classDescription;

    /**
     * 模板填充数据值
     */
    public static void initTemplateData() {
        DATA.put("author", author);
        DATA.put("basePackageName", basePackageName);
        DATA.put("moduleName", moduleName);
        DATA.put("tableName", tableName);
        DATA.put("className", className);
        DATA.put("classDescription", classDescription);

        DATA.put("jdbcClassName", JdbcUtils.className);
        DATA.put("jdbcUrl", JdbcUtils.url);
        DATA.put("jdbcUser", JdbcUtils.user);
        DATA.put("jdbcPassword", JdbcUtils.password);
    }

}
