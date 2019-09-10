package lazy.fast.code.generator.template;

import lazy.fast.code.generator.Config;
import lazy.fast.code.generator.util.GeneratorUtils;

/**
 * 生成Service接口
 *
 * @author wendell
 */
public class GeneratorService implements Generator {

    @Override
    public void generate() {
        GeneratorUtils.buildModule("service", Config.className.concat("Service"));
    }

}
