package lazy.fast.code.generator.template;

import lazy.fast.code.generator.Config;
import lazy.fast.code.generator.util.GeneratorUtils;
import lazy.fast.code.generator.util.Inflector;

/**
 * 生成Controller
 *
 * @author wendell
 */
public class GeneratorController implements Generator {

    @Override
    public void generate() {
        // RESTFul
        Config.DATA.put("requestMapping", Inflector.getInstance().pluralize(camelCaseConvertToSlash(Config.className)));
        GeneratorUtils.buildModule("controller", Config.className.concat("Controller"));
    }

}
