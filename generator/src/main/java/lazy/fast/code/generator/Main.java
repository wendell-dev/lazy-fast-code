package lazy.fast.code.generator;

import lazy.fast.code.generator.template.GeneratorEntity;
import lazy.fast.code.generator.template.GeneratorLayout;

/**
 * 启动类
 *
 * @author wendell
 */
public class Main {

    public static void main(String[] args) {
        // 模板文件根路径, 为空的时候就默认使用内置模板，如果有自定义模板就写模板绝对路径
        Config.moduleTemplateRootPath = null;
        Config.layoutTemplateRootPath = null;
        Config.author = "wendell";
        // 【必填项】项目名称
        Config.projectName = "demo";
        // 【必填项】项目根路径 - src目录的上层，如果不存在目录会自动创建
        Config.projectRootPath = "D:\\workspace\\".concat(Config.projectName);
        // 【必填项】要生成的根包名，如果不存在目录会自动创建
        Config.basePackageName = "lazy.fast.code.demo";

        // 生成项目骨架
        generateLayout();

        // 【必填项】要生成的模块名称，如 user、order
        Config.moduleName = "user";
        // 【必填项】数据库表全名
        Config.tableName = "sys_user";
        // 【必填项】类名字, 首字母大写
        Config.className = "User";
        // 【必填项】类描述
        Config.classDescription = "用户信息";

        // 生成所有模板类
        generateAll();
    }

    /**
     * 生成项目骨架
     */
    private static void generateLayout() {
        new GeneratorLayout().generate();
    }

    /**
     * 生成所有模板类
     */
    private static void generateAll() {
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
    private static void generateRepository() {}

    /**
     * 生成Service接口
     */
    private static void generateService() {}

    /**
     * 生成Service实现类
     */
    private static void generateServiceImpl() {}

    /**
     * 生成Controller
     */
    private static void generateController() {}

}
