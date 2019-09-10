package ${basePackageName}.${moduleName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lazy.fast.code.core.orm.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
<#if imports??><#list imports as import>
import ${import};
</#list></#if>

/**
 * ${classDescription}
 *
 * @author ${author}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
@Entity
@Table(name = "${tableName}")
@Data(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "${className?cap_first}", description = "${classDescription}")
public class ${className?cap_first} extends BaseEntity<Long> {

    <#list attributes as attribute>
    /**
     * ${attribute.remark}
     */
    @Column(name = "${attribute.column}")
    @ApiModelProperty(value = "${attribute.remark}")
    private ${attribute.type} ${attribute.name};

    </#list>
}
