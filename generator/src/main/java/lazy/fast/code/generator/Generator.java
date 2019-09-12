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
