package lazy.fast.code.generator;

import lazy.fast.code.generator.template.GeneratorEntity;

/**
 * 启动类
 *
 * @author wendell
 */
public class Application {

    public static void main(String[] args) {
        // 模板文件根路径, 为空的时候就默认使用内置模板，如果有自定义模板就写模板绝对路径
        Config.templateRootPath = null;
        Config.author = "wendell";
        // 【必填项】要生成的目标文件根路径，如果不存在目录会自动创建
        Config.targetRootPath = "D:\\test";
        // 【必填项】要生成的包名，如果不存在目录会自动创建
        Config.packageName = "lazy.fast.code.demo.user";
        // 【必填项】数据库表全名
        Config.tableName = "sys_user";
        // 【必填项】实体类名字,
        Config.entityClassName = "User";
        Config.classDescription = "用户信息";

        // 生成实体类
        new GeneratorEntity().generator();
    }

}
