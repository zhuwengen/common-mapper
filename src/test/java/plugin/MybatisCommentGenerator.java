package plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * Mybatis代码注释生成
 * @author coreyjk
 * @version 1.0.0
 */
public class MybatisCommentGenerator extends DefaultCommentGenerator {
	private Properties properties;
	private Properties systemPro;
	private boolean suppressDate;
	private boolean suppressAllComments;
	private String currentDateStr;

	public MybatisCommentGenerator() {
		super();
		properties = new Properties();
		systemPro = System.getProperties();
		suppressDate = false;
		suppressAllComments = false;
		currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		return;
	}

	@Override
	public void addComment(XmlElement xmlElement) {
		//		StringBuilder sb = new StringBuilder();
		//		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		//
		//		xmlElement.addElement(new TextElement("<!--"));
		//		xmlElement.addElement(new TextElement(sb.toString()));
		//		xmlElement.addElement(new TextElement("-->"));
	}

	@Override
	public void addRootComment(XmlElement rootElement) {
		return;
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);
		suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
		suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
	}

	@Override
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		if (suppressAllComments) {
			return;
		}
		javaElement.addJavaDocLine(" *");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge");
		}
		String s = getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}

	@Override
	protected String getDateString() {
		String result = "";
		if (!suppressDate) {
			result = currentDateStr;
		}
		return result;
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
		innerClass.addJavaDocLine(" * 数据库表：" + introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(" * @date " + getDateString());
		innerClass.addJavaDocLine(" * @author Mybatis Generator");
		innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		innerEnum.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerEnum.addJavaDocLine(sb.toString().replace("\n", " "));
		innerEnum.addJavaDocLine(" */");
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("/** ");
		sb.append(introspectedColumn.getRemarks());
		sb.append(" */");
		field.addJavaDocLine(sb.toString());
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("/** ");
		String fieldName = field.getName();
		if ("orderByClause".equals(fieldName)) {
			sb.append(" 排序字段");
		} else if ("distinct".equals(fieldName)) {
			sb.append(" 过滤重复数据");
		} else if ("oredCriteria".equals(fieldName)) {
			sb.append(" 当前的查询条件实例");
		} else if ("isDistinct".equals(fieldName)) {
			sb.append(" 是否过滤重复数据");
		}
		sb.append(" */");
		field.addJavaDocLine(sb.toString());
	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**");
		sb.append(" * ");
		if (method.isConstructor()) {
			sb.append(" 构造查询条件");
		}

		final List<Parameter> parameterList = method.getParameters();
		String methodName = method.getName();
		if ("setOrderByClause".equals(methodName)) {
			sb.append(" 设置排序字段");
		} else if ("setDistinct".equals(methodName)) {
			sb.append(" 设置过滤重复数据");
		} else if ("getOredCriteria".equals(methodName)) {
			sb.append(" 获取当前的查询条件实例");
		} else if ("isDistinct".equals(methodName)) {
			sb.append(" 是否过滤重复数据");
		} else if ("getOrderByClause".equals(methodName)) {
			sb.append(" 获取排序字段");
		} else if ("createCriteria".equals(methodName)) {
			sb.append(" 创建一个查询条件");
		} else if ("createCriteriaInternal".equals(methodName)) {
			sb.append(" 内部构建查询条件对象");
		} else if ("clear".equals(methodName)) {
			sb.append(" 清除查询条件");
		} else if ("countByExample".equals(methodName)) {
			sb.append(" 根据指定的条件获取数据库记录数");
		} else if ("deleteByExample".equals(methodName)) {
			sb.append(" 根据指定的条件删除数据库符合条件的记录");
		} else if ("deleteByPrimaryKey".equals(methodName)) {
			sb.append(" 根据主键删除数据库的记录");
		} else if ("insert".equals(methodName)) {
			sb.append(" 新写入数据库记录");
		} else if ("insertSelective".equals(methodName)) {
			sb.append(" 动态字段,写入数据库记录");
		} else if ("selectByExample".equals(methodName)) {
			sb.append(" 根据指定的条件查询符合条件的数据库记录");
		} else if ("selectByPrimaryKey".equals(methodName)) {
			sb.append(" 根据指定主键获取一条数据库记录");
		} else if ("updateByExampleSelective".equals(methodName)) {
			sb.append(" 动态根据指定的条件来更新符合条件的数据库记录");
		} else if ("updateByExample".equals(methodName)) {
			sb.append(" 根据指定的条件来更新符合条件的数据库记录");
		} else if ("updateByPrimaryKeySelective".equals(methodName)) {
			sb.append(" 动态字段,根据主键来更新符合条件的数据库记录");
		} else if ("updateByPrimaryKey".equals(methodName)) {
			sb.append(" 根据主键来更新符合条件的数据库记录");
		} else if ("or".equals(methodName)) {
			if (parameterList.isEmpty()) {
				sb.append(" 创建一个新的或者查询条件");
			} else {
				sb.append(" 增加或者的查询条件,用于构建或者查询");
			}
		}
		// sb.append(",");
		// sb.append(introspectedTable.getFullyQualifiedTable());
		method.addJavaDocLine(sb.toString());

		String paramterName;
		for (Parameter parameter : parameterList) {
			sb.setLength(0);
			sb.append(" * @param ");
			paramterName = parameter.getName();
			sb.append(paramterName);
			if ("orderByClause".equals(paramterName)) {
				sb.append(" 排序字段");
			} else if ("distinct".equals(paramterName)) {
				sb.append(" 是否过滤重复数据");
			} else if ("criteria".equals(paramterName)) {
				sb.append(" 过滤条件实例");
			}
			method.addJavaDocLine(sb.toString());
		}
		method.addJavaDocLine(" */");
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		// sb.append(" * ");
		// sb.append(introspectedColumn.getRemarks());
		// method.addJavaDocLine(sb.toString().replace("\n", " "));
		// sb.setLength(0);
		sb.append(" * @return ");
		sb.append(introspectedColumn.getActualColumnName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		method.addJavaDocLine(" */");
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		// sb.append(" * ");
		// sb.append(introspectedColumn.getRemarks());
		// method.addJavaDocLine(sb.toString().replace("\n", " "));
		Parameter parm = method.getParameters().get(0);
		// sb.setLength(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		method.addJavaDocLine(" */");
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		innerClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
		sb.setLength(0);
		sb.append(" * @author ");
		sb.append(systemPro.getProperty("user.name"));
		sb.append(" ");
		sb.append(currentDateStr);
		innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
		topLevelClass.addJavaDocLine(" * 数据库表：" + introspectedTable.getFullyQualifiedTable());
		if (!suppressDate) {
			topLevelClass.addJavaDocLine(" * @date " + getDateString());
		}
		topLevelClass.addJavaDocLine(" * @author Mybatis Generator");
		topLevelClass.addJavaDocLine(" */");
	}
}
