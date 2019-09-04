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
        boolean isDate = false;
        boolean isTime = false;
        boolean isTimestamp = false;
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
                        // https://www.cnblogs.com/jerrylz/p/5814460.html对照表
                        case "BLOB":
                            dataAttribute.put("type", "Byte[]");
                            break;
                        case "INT":
                            dataAttribute.put("type", "Integer");
                            break;
                        case "TINYINT":
                            dataAttribute.put("type", "Integer");
                            break;
                        case "SMALLINT":
                            dataAttribute.put("type", "Integer");
                            break;
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
                            dataAttribute.put("type", "Long");
                            break;
                        case "FLOAT":
                            dataAttribute.put("type", "Float");
                            break;
                        case "DOUBLE":
                            dataAttribute.put("type", "Double");
                            break;
                        case "ID":
                            dataAttribute.put("type", "Long");
                            break;
                        case "BOOLEAN":
                            dataAttribute.put("type", "Integer");
                            break;
                        case "DATE":
                            isImport = true;
                            isDate = true;
                            dataAttribute.put("type", "Date");
                            break;
                        case "YEAR":
                            isImport = true;
                            isDate = true;
                            dataAttribute.put("type", "Date");
                            break;
                        case "TIME":
                            isImport = true;
                            isTime = true;
                            dataAttribute.put("type", "Time");
                            break;
                        case "DATETIME":
                            isImport = true;
                            isTimestamp = true;
                            dataAttribute.put("type", "Timestamp");
                            break;
                        case "TIMESTAMP":
                            isImport = true;
                            isTimestamp = true;
                            dataAttribute.put("type", "Timestamp");
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
                    dataAttribute.put("name", camelCaseName(rsColumns.getString("COLUMN_NAME")));
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
                if (isDate) {
                    imports.add("java.sql.Date");
                }
                if (isTime) {
                    imports.add("java.sql.Time");
                }
                if (isTimestamp) {
                    imports.add("java.sql.Timestamp");
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
