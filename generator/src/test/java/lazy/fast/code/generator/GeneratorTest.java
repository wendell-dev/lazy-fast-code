package lazy.fast.code.generator;

/**
 * LazyFastCode代码生成器
 * 
 * @author wendell
 */
public class GeneratorTest {

    public static void main(String[] args) {
        // 模板文件根路径, 为空的时候就默认使用内置模板，如果有自定义模板就写模板绝对路径
        Config.moduleTemplateRootPath = null;
        Config.layoutTemplateRootPath = null;

        // 作者签名, 如果不填写则按System.getProperty("user.name")取值
        Config.author = "wendell";

        // 【必填项】项目名称
        Config.projectName = "demo";
        // 【必填项】项目根路径 - src目录的上层，如果不存在目录会自动创建
        Config.projectRootPath = "D:\\workspace\\".concat(Config.projectName);
        // 【必填项】要生成的根包名，如果不存在目录会自动创建
        Config.basePackageName = "lazy.fast.code.demo";

        // 生成项目骨架
        Generator.generateLayout();

        // 【必填项】要生成的模块名称，如 user、order
        Config.moduleName = "user";
        // 【必填项】数据库表全名
        Config.tableName = "sys_user";
        // 【必填项】类名字, 首字母大写, 以驼峰格式命名
        Config.className = "User";
        // 【必填项】类描述
        Config.classDescription = "用户信息";

        Config.jdbcClassName = "com.mysql.cj.jdbc.Driver";
        Config.jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        Config.jdbcUser = "root";
        Config.jdbcPassword = "123456";

        // 生成所有模板类
        Generator.generateAll();
    }

}
