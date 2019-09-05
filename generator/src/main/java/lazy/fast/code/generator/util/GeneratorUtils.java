package lazy.fast.code.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lazy.fast.code.generator.Config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 生成项目骨架及模块文件的工具操作类
 *
 * @author wendell
 */
public class GeneratorUtils {

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_28);

    static {
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setDefaultEncoding("UTF-8");
        DefaultObjectWrapperBuilder owb = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
        owb.setForceLegacyNonListCollections(false);
        owb.setDefaultDateType(TemplateDateModel.DATETIME);
        CONFIGURATION.setObjectWrapper(owb.build());
    }

    /**
     * 生成项目骨架
     */
    public static void buildLayout() {
        try {
            mkPackageDirs();
            mkResourcesDirs();

            File javaFile = new File(getPackageDirs().concat(File.separator).concat("Application.java"));
            Template template = layoutTemplateConfiguration("main");
            templateProcess(javaFile, template);
            System.out.println("项目骨架 - 生成主应用入口文件：" + javaFile.getCanonicalPath());

            File resourceFile = new File(getResourcesDirs().concat(File.separator).concat("application.yml"));
            template = layoutTemplateConfiguration("resource");
            templateProcess(resourceFile, template);
            System.out.println("项目骨架 - 生成资源文件：" + resourceFile.getCanonicalPath());

            File pomFile = new File(Config.projectRootPath.concat(File.separator).concat("pom.xml"));
            template = layoutTemplateConfiguration("pom");
            templateProcess(pomFile, template);
            System.out.println("项目骨架 - 生成pom文件：" + pomFile.getCanonicalPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成模块文件
     *
     * @param templateFileName
     *            模板文件名
     * @param className
     *            类名
     */
    public static void buildModule(String templateFileName, String className) {
        try {
            mkModuleDirs();
            File javaFile = new File(getModuleJavaPath(className));
            Template template = moduleTemplateConfiguration(templateFileName);
            templateProcess(javaFile, template);
            System.out.println("生成模块文件：" + javaFile.getCanonicalPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建包目录：目标文件根路径 + 包名
     */
    private static void mkPackageDirs() {
        String targetDirs = getPackageDirs();
        if (new File(targetDirs).mkdirs()) {
            System.out.println("生成包目录：" + targetDirs);
        }
    }

    /**
     * 创建资源目录：目标文件根路径 + resources
     */
    private static void mkResourcesDirs() {
        String targetDirs = getResourcesDirs();
        if (new File(targetDirs).mkdirs()) {
            System.out.println("生成资源目录：" + targetDirs);
        }
    }

    /**
     * 创建模块目录：目标文件根路径 + 包名 + 模块名
     */
    private static void mkModuleDirs() {
        String targetDirs = getModuleDirs();
        if (new File(targetDirs).mkdirs()) {
            System.out.println("生成模块目录：" + targetDirs);
        }
    }

    /**
     * 获取包目录：项目根路径 + 包名
     */
    private static String getPackageDirs() {
        return Config.projectRootPath.concat(File.separator).concat("src").concat(File.separator).concat("main")
            .concat(File.separator).concat("java").concat(File.separator).concat(packageNameToDirs());
    }

    /**
     * 获取资源目录：项目根路径 + resources
     */
    private static String getResourcesDirs() {
        return Config.projectRootPath.concat(File.separator).concat("src").concat(File.separator).concat("main")
            .concat(File.separator).concat("resources");
    }

    /**
     * 获取模块目录：项目根路径 + 包名 + 模块名
     */
    private static String getModuleDirs() {
        return getPackageDirs().concat(File.separator).concat(Config.moduleName);
    }

    /**
     * 获取模块Java类路径：项目根路径 + 包名 + 模块名 + ${className}.java
     */
    private static String getModuleJavaPath(String className) {
        return getModuleDirs().concat(File.separator).concat(className).concat(".java");
    }

    /**
     * 包名转为目录格式
     */
    private static String packageNameToDirs() {
        return Config.basePackageName.replace('.', File.separatorChar);
    }

    /**
     * 英文文件名首字母转大写
     * 
     * @param fileName
     *            英文文件名
     * @return 首字母转大写后的英文文件名
     */
    public static String fileNameFirstToUpperCase(String fileName) {
        return fileName.substring(0, 1).toUpperCase().concat(fileName.substring(1));
    }

    /**
     * 模块模板配置
     *
     * @param fileName
     *            文件名
     */
    private static Template moduleTemplateConfiguration(String fileName) throws IOException {
        // 如果路径为空,就默认使用内置好的模板
        if (null == Config.moduleTemplateRootPath || "".equals(Config.moduleTemplateRootPath)) {
            CONFIGURATION.setClassForTemplateLoading(GeneratorUtils.class, "/template");
        } else {
            File file = new File(Config.moduleTemplateRootPath);
            CONFIGURATION.setDirectoryForTemplateLoading(file);
        }
        return CONFIGURATION.getTemplate(fileName.concat(".ftl"));
    }

    /**
     * 项目骨架模板配置
     *
     * @param fileName
     *            文件名
     */
    private static Template layoutTemplateConfiguration(String fileName) throws IOException {
        // 如果路径为空,就默认使用内置好的模板
        if (null == Config.layoutTemplateRootPath || "".equals(Config.layoutTemplateRootPath)) {
            CONFIGURATION.setClassForTemplateLoading(GeneratorUtils.class, "/template/layout");
        } else {
            File file = new File(Config.layoutTemplateRootPath);
            CONFIGURATION.setDirectoryForTemplateLoading(file);
        }
        return CONFIGURATION.getTemplate(fileName.concat(".ftl"));
    }

    /**
     * 模板处理
     *
     * @param file
     *            文件
     * @param template
     *            模板
     */
    private static void templateProcess(File file, Template template) throws IOException, TemplateException {
        Writer writer = new FileWriter(file);
        Config.initTemplateData();
        template.process(Config.DATA, writer);
        writer.flush();
        writer.close();
    }

}
