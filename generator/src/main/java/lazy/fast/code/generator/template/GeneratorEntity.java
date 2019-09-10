package lazy.fast.code.generator.template;

import lazy.fast.code.generator.Config;
import lazy.fast.code.generator.util.GeneratorUtils;
import lazy.fast.code.generator.util.JdbcUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成实体类
 *
 * @author wendell
 */
public class GeneratorEntity implements Generator {

    @Override
    public void generate() {
        boolean isBigDecimal = false;
        boolean isLocalDate = false;
        boolean isLocalTime = false;
        boolean isLocalDateTime = false;
        boolean isImport = false;
        List<String> imports = new ArrayList<>();

        JdbcUtils jdbcUtils = new JdbcUtils();
        Connection conn = jdbcUtils.getConnection();
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            try (ResultSet rsColumns = metaData.getColumns(conn.getCatalog(), "%", Config.tableName, "%")) {
                List<Map<String, Object>> attributes = new ArrayList<>();
                Map<String, Object> dataAttribute;
                while (rsColumns.next()) {
                    if ("id".equals(rsColumns.getString("COLUMN_NAME"))) {
                        continue;
                    }
                    dataAttribute = new HashMap<>(16);
                    switch (rsColumns.getString("TYPE_NAME")) {
                        case "BLOB":
                            dataAttribute.put("type", "Byte[]");
                            break;
                        case "INT":
                        case "BOOLEAN":
                        case "TINYINT":
                        case "SMALLINT":
                        case "MEDIUMINT":
                            dataAttribute.put("type", "Integer");
                            break;
                        case "BIGINT":
                            isImport = true;
                            dataAttribute.put("type", "Long");
                            break;
                        case "BIT":
                            dataAttribute.put("type", "Boolean");
                            break;
                        case "INTEGER":
                        case "ID":
                            dataAttribute.put("type", "Long");
                            break;
                        case "FLOAT":
                            dataAttribute.put("type", "Float");
                            break;
                        case "DOUBLE":
                            dataAttribute.put("type", "Double");
                            break;
                        case "DATE":
                        case "YEAR":
                            isImport = true;
                            isLocalDate = true;
                            dataAttribute.put("type", "LocalDate");
                            break;
                        case "TIME":
                            isImport = true;
                            isLocalTime = true;
                            dataAttribute.put("type", "LocalTime");
                            break;
                        case "DATETIME":
                        case "TIMESTAMP":
                            isImport = true;
                            isLocalDateTime = true;
                            dataAttribute.put("type", "LocalDateTime");
                            break;
                        case "DECIMAL":
                            isImport = true;
                            isBigDecimal = true;
                            dataAttribute.put("type", "BigDecimal");
                            break;
                        default:
                            dataAttribute.put("type", "String");
                            break;
                    }
                    // 数据库的字段如果是下划线的转为驼峰命名
                    dataAttribute.put("name", underscoreConvertToCamelCase(rsColumns.getString("COLUMN_NAME")));
                    // 数据库的原始字段名
                    dataAttribute.put("column", rsColumns.getString("COLUMN_NAME"));
                    if (null == rsColumns.getString("REMARKS") || "".equals(rsColumns.getString("REMARKS"))) {
                        dataAttribute.put("remark", "TODO (请完善数据库字段备注)");
                    } else {
                        dataAttribute.put("remark", rsColumns.getString("REMARKS"));
                    }
                    attributes.add(dataAttribute);
                }
                Config.DATA.put("attributes", attributes);
                if (isBigDecimal) {
                    imports.add("java.math.BigDecimal");
                }
                if (isLocalDate) {
                    imports.add("java.time.LocalDate");
                }
                if (isLocalTime) {
                    imports.add("java.time.LocalTime");
                }
                if (isLocalDateTime) {
                    imports.add("java.time.LocalDateTime");
                }
                if (isImport) {
                    Config.DATA.put("imports", imports);
                }
            }

            GeneratorUtils.buildModule("entity", Config.className);
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            jdbcUtils.closeConnection();
        }
    }

}
