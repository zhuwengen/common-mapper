package plugin;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * Xml生成插件,XML重新生成时覆盖历史生成的文件
 * @author coreyjk
 * @version 1.0.0
 */
public class XmloverridePluginAdapter extends PluginAdapter {

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		sqlMap.setMergeable(false);
		return super.sqlMapGenerated(sqlMap, introspectedTable);
	}

	public boolean validate(List<String> list) {
		return true;
	}
}
