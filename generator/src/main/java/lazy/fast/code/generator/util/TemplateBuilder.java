package lazy.fast.code.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateExceptionHandler;
import lazy.fast.code.generator.Config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成JAVA文件
 *
 * @author wendell
 */
public class TemplateBuilder {

    /**
     * 模板填充数据
     */
    public static final Map<String, Object> DATA = new HashMap<>(16);

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_28);

    static {
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setDefaultEncoding("UTF-8");
        DefaultObjectWrapperBuilder owb = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
        owb.setForceLegacyNonListCollections(false);
        owb.setDefaultDateType(TemplateDateModel.DATETIME);
        CONFIGURATION.setObjectWrapper(owb.build());
    }

    static {
        DATA.put("author", Config.author);
        DATA.put("packageName", Config.packageName);
        DATA.put("tableName", Config.tableName);
        DATA.put("className", Config.entityClassName);
        DATA.put("classDescription", Config.classDescription);
    }

    /**
     * 生成JAVA文件
     *
     * @param templateFileName
     *            模板文件名
     * @param className
     *            类名
     */
    public static void build(String templateFileName, String className) {
        File javaFile =
            new File(Config.targetRootPath, Config.packageName.replace('.', File.separatorChar).concat(File.separator)
                .concat(className.substring(0, 1).toUpperCase().concat(className.substring(1))).concat(".java"));
        javaFile.getParentFile().mkdirs();
        try {
            Template template = templateConfiguration(templateFileName);
            Writer writer = new FileWriter(javaFile);
            template.process(DATA, writer);
            writer.flush();
            writer.close();
            System.out.println("文件生成成功：" + javaFile.getCanonicalPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 模板配置
     *
     * @param fileName
     *            文件名
     */
    private static Template templateConfiguration(String fileName) throws IOException {
        // 如果路径为空,就默认使用内置好的模板
        if (null == Config.templateRootPath || "".equals(Config.templateRootPath)) {
            CONFIGURATION.setClassForTemplateLoading(TemplateBuilder.class, "/template");
        } else {
            File file = new File(Config.templateRootPath);
            CONFIGURATION.setDirectoryForTemplateLoading(file);
        }
        return CONFIGURATION.getTemplate(fileName.concat(".ftl"));
    }

}
