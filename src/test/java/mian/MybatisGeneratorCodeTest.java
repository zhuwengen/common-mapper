package mian;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis代码生成
 * @author coreyjk
 * @version 1.0.0
 */
public class MybatisGeneratorCodeTest {

	static final Logger LOG = LoggerFactory.getLogger(MybatisGeneratorCodeTest.class);

	@org.junit.Test
	public void run() {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;

		List<String> configFileList = new ArrayList<String>();
		configFileList.add("/generatorConfig-canyin.xml");
		configFileList.stream().forEach((configFile) -> {
			try {
				Resource resource = new ClassPathResource(configFile);
				ConfigurationParser cp = new ConfigurationParser(warnings);
				Configuration config = cp.parseConfiguration(resource.getFile());
				DefaultShellCallback callback = new DefaultShellCallback(overwrite);
				MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
				myBatisGenerator.generate(null);
			} catch (Exception e) {
				LOG.error("mybatis generator code error!", e);
				throw new RuntimeException(e);
			}
		});
	}
}
