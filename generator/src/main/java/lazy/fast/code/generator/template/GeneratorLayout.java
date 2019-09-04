package lazy.fast.code.generator.template;

import lazy.fast.code.generator.util.GeneratorUtils;

/**
 * 生成项目骨架
 *
 * @author wendell
 */
public class GeneratorLayout implements Generator {

    @Override
    public void generate() {
        GeneratorUtils.buildLayout();
    }

}
