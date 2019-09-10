package lazy.fast.code.generator.template;

import lazy.fast.code.generator.Config;
import lazy.fast.code.generator.util.GeneratorUtils;

/**
 * 生成Service接口实现类
 *
 * @author wendell
 */
public class GeneratorServiceImpl implements Generator {

    @Override
    public void generate() {
        GeneratorUtils.buildModule("service-impl", Config.className.concat("ServiceImpl"));
    }

}
