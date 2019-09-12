package lazy.fast.code.generator;

/**
 * 启动类
 * 
 * <pre>
 * java -Dauthor=wendell -Dproject.name=demo -Dproject.path=D:\workspace\demo \
 *      -Dproject.package=lazy.fast.code.demo -jar generator/target/code-generator-1.0-SNAPSHOT.jar
 * </pre>
 *
 * @author wendell
 */
public class QuickStart {

    /**
     * java -jar 启动传入参数，生成项目骨架
     */
    public static void main(String[] args) {
        // 作者签名
        Config.author = System.getProperty("author", System.getProperty("user.name"));

        // 【必填项】项目名称
        Config.projectName = System.getProperty("project.name");
        if (Config.projectName == null || "".equals(Config.projectName)) {
            System.out.println("项目名称必传, 如 project.name=demo");
            return;
        }

        // 【必填项】项目根路径 - src目录的上层，如果不存在目录会自动创建
        Config.projectRootPath = System.getProperty("project.path");
        if (Config.projectRootPath == null || "".equals(Config.projectRootPath)) {
            System.out.println("项目根路径必传, 如 project.path=D:\\workspace\\demo");
            return;
        }
        Config.projectRootPath = Config.projectRootPath.concat(Config.projectName);

        // 【必填项】要生成的根包名，如果不存在目录会自动创建
        Config.basePackageName = System.getProperty("project.package");
        if (Config.basePackageName == null || "".equals(Config.basePackageName)) {
            System.out.println("包名必传, 如 project.package=lazy.fast.code.demo");
            return;
        }

        // 模板文件根路径, 为空的时候就默认使用内置模板，如果有自定义模板就写模板绝对路径
        Config.moduleTemplateRootPath = System.getProperty("template.module", null);
        // 项目骨架模板文件根路径
        Config.layoutTemplateRootPath = System.getProperty("template.layout", null);

        // 生成项目骨架
        Generator.generateLayout();
    }

}
