package lazy.fast.code.generator.template;

import lazy.fast.code.generator.Config;
import lazy.fast.code.generator.util.GeneratorUtils;

/**
 * 生成Mapper接口
 *
 * @author wendell
 */
public class GeneratorRepository implements Generator {

    @Override
    public void generate() {
        GeneratorUtils.buildModule("repository", Config.className.concat("Repository"));
    }

}
