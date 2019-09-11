package lazy.fast.code.generator;

import lazy.fast.code.generator.template.GeneratorController;
import lazy.fast.code.generator.template.GeneratorEntity;
import lazy.fast.code.generator.template.GeneratorLayout;
import lazy.fast.code.generator.template.GeneratorRepository;
import lazy.fast.code.generator.template.GeneratorService;
import lazy.fast.code.generator.template.GeneratorServiceImpl;

/**
 * LazyFastCode代码生成器
 *
 * @author wendell
 */
public class Generator {

    /**
     * java -jar 启动传入参数，生成项目骨架
     * 
     * @param args
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
        generateLayout();
    }

    /**
     * 生成项目骨架
     */
    public static void generateLayout() {
        new GeneratorLayout().generate();
    }

    /**
     * 生成所有模板类
     */
    public static void generateAll() {
        generateEntity();
        generateRepository();
        generateService();
        generateServiceImpl();
        generateController();
    }

    /**
     * 生成实体类
     */
    private static void generateEntity() {
        new GeneratorEntity().generate();
    }

    /**
     * 生成Mapper接口
     */
    private static void generateRepository() {
        new GeneratorRepository().generate();
    }

    /**
     * 生成Service接口
     */
    private static void generateService() {
        new GeneratorService().generate();
    }

    /**
     * 生成Service实现类
     */
    private static void generateServiceImpl() {
        new GeneratorServiceImpl().generate();
    }

    /**
     * 生成Controller
     */
    private static void generateController() {
        new GeneratorController().generate();
    }

}
