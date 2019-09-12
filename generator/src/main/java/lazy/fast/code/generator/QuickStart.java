package lazy.fast.code.generator;

/**
 * 启动类
 * 
 * <pre>
 * java -Dfile.encoding=utf-8 -jar generator/target/code-generator-1.0-SNAPSHOT.jar \
 *     author=wendell projectName=demo projectRootPath=D:\workspace\demo basePackageName=lazy.fast.code.demo
 * </pre>
 *
 * @author wendell
 */
public class QuickStart {

    /**
     * java -jar 启动传入参数，生成项目骨架
     *
     * @param args
     *            author=wendell projectName=demo projectRootPath=D:\workspace\demo basePackageName=lazy.fast.code.demo
     */
    public static void main(String[] args) {
        for (String arg : args) {
            String val = arg.split("=")[1];
            if (arg.startsWith("author")) {
                // 作者签名, 如果不填写则按System.getProperty("user.name")取值
                Config.author = val;
            } else if (arg.startsWith("projectName")) {
                // 【必填项】项目名称
                Config.projectName = val;
            } else if (arg.startsWith("projectRootPath")) {
                // 【必填项】项目根路径 - src目录的上层，如果不存在目录会自动创建
                Config.projectRootPath = val;
            } else if (arg.startsWith("basePackageName")) {
                // 【必填项】要生成的根包名，如果不存在目录会自动创建
                Config.basePackageName = val;
            } else if (arg.startsWith("moduleTemplateRootPath")) {
                // 模板文件根路径, 为空的时候就默认使用内置模板，如果有自定义模板就写模板绝对路径
                Config.moduleTemplateRootPath = val;
            } else if (arg.startsWith("layoutTemplateRootPath")) {
                // 项目骨架模板文件根路径
                Config.layoutTemplateRootPath = val;
            }
        }

        if (Config.projectName == null || "".equals(Config.projectName)) {
            System.out.println("项目名称必传, 如 projectName=demo");
            return;
        }
        if (Config.projectRootPath == null || "".equals(Config.projectRootPath)) {
            System.out.println("项目根路径必传, 如 projectRootPath=D:\\workspace\\demo");
            return;
        }
        if (Config.basePackageName == null || "".equals(Config.basePackageName)) {
            System.out.println("包名必传, 如 basePackageName=lazy.fast.code.demo");
            return;
        }

        // 生成项目骨架
        Generator.generateLayout();
    }

}
