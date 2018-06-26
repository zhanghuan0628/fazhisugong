package com.ffxl.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class ServiceAndImplPlugin extends PluginAdapter {
	/**
	 * 生成XXXService，XXXServiceImpl.java文件
	 * @author wison
	 */
	private ShellCallback shellCallback = null;

	private String businessTargetDir;

	private String businessTargetPackage;

	private String businessSuperClass;
	
	private String businessImplTargetPackage;

	private String businessImplSuperClass;
	
	private String genericMapper;//实现方法返回类型
	
	private String springFrameworkStereotypeService;
	
	private String slf4jLogger;
	
	private String slf4jLoggerFactory;
	
	private String springFrameworkAutowired;
	
	private String daoTargetPackage;
	
	
	public ServiceAndImplPlugin() {
		shellCallback = new DefaultShellCallback(false);
	}
	/* 检查xml参数是否正确
     * @see org.mybatis.generator.api.Plugin#validate(java.util.List)
     */
	public boolean validate(List<String> warnings) {
		businessTargetDir = properties.getProperty("businessTargetDir");
		boolean valid = stringHasValue(businessTargetDir);

		businessTargetPackage = properties.getProperty("businessTargetPackage");
		boolean valid2 = stringHasValue(businessTargetPackage);

		businessSuperClass = properties.getProperty("businessSuperClass");
		boolean valid3 = stringHasValue(businessSuperClass);
		
		businessImplTargetPackage = properties.getProperty("businessImplTargetPackage");
		boolean valid4 = stringHasValue(businessImplTargetPackage);
		
		businessImplSuperClass = properties.getProperty("businessImplSuperClass");
		boolean valid5 = stringHasValue(businessImplSuperClass);
		
		springFrameworkStereotypeService = properties.getProperty("springFrameworkStereotypeService");
		boolean valid6 = stringHasValue(springFrameworkStereotypeService);
		
		genericMapper = properties.getProperty("genericMapper");
		boolean valid7 = stringHasValue(genericMapper);
		
		slf4jLogger = properties.getProperty("slf4jLogger");
		boolean valid8 = stringHasValue(slf4jLogger);
		
		slf4jLoggerFactory = properties.getProperty("slf4jLoggerFactory");
		boolean valid9 = stringHasValue(slf4jLoggerFactory);
		
		springFrameworkAutowired = properties.getProperty("springFrameworkAutowired");
		boolean valid10 = stringHasValue(springFrameworkAutowired);
		
		daoTargetPackage = properties.getProperty("daoTargetPackage");
		boolean valid11 = stringHasValue(daoTargetPackage);
		
		return valid && valid2 && valid3 && valid4 && valid5 && valid6 &&  valid7 && valid8 && valid9 && valid10 &&  valid11;
	}
	
	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(
			IntrospectedTable introspectedTable) {
		System.out.println("===============开始：生成Service及ServiceImpl文件================");

		JavaFormatter javaFormatter = context.getJavaFormatter();

		List<GeneratedJavaFile> serviceJavaFiles = new ArrayList<GeneratedJavaFile>();
		for (GeneratedJavaFile javaFile : introspectedTable
				.getGeneratedJavaFiles()) {

			CompilationUnit unit = javaFile.getCompilationUnit();
			FullyQualifiedJavaType baseModelJavaType = unit.getType();

			String shortName = baseModelJavaType.getShortName();

			if (shortName.endsWith("Example")) {// 针对Example类不要生成Mapper
				continue;
			}

			String subModelType = getSubModelType(baseModelJavaType);
			String subModelExampleType = subModelType + "Example";

			System.out.println("shortName:" + shortName);

			String subModelName = shortName.replace("Base", "");

			//service接口
			Interface serviceInterface = new Interface(businessTargetPackage
					+ "." + subModelName + "Service");
			
			serviceInterface.setVisibility(JavaVisibility.PUBLIC);
			serviceInterface.addJavaDocLine(" /**");
			serviceInterface.addJavaDocLine(" * Generate By MBG ");
			serviceInterface.addJavaDocLine(" **/");
			
			FullyQualifiedJavaType subModelJavaType = new FullyQualifiedJavaType(
					subModelType);
			serviceInterface.addImportedType(subModelJavaType);
			FullyQualifiedJavaType subModelExampleJavaType = new FullyQualifiedJavaType(
					subModelExampleType);
			serviceInterface.addImportedType(subModelExampleJavaType);

			FullyQualifiedJavaType serviceSuperType = new FullyQualifiedJavaType(
					businessSuperClass);
			// 添加泛型支持
			serviceSuperType.addTypeArgument(subModelJavaType);
			serviceSuperType.addTypeArgument(subModelExampleJavaType);
			serviceSuperType.addTypeArgument(new FullyQualifiedJavaType(
					"java.lang.String"));
			
			// 添加通用接口方法
			Method queryByModelMethod = new Method("queryByModel");
			queryByModelMethod.addJavaDocLine(" /**");
			queryByModelMethod.addJavaDocLine(" * According to the model information query object  ");
			queryByModelMethod.addJavaDocLine(" * @param "+shortName);
			queryByModelMethod.addJavaDocLine(" * @return ");
			queryByModelMethod.addJavaDocLine(" **/");
			queryByModelMethod.setReturnType(subModelJavaType);
			Parameter parameter =new Parameter(subModelJavaType, toLowerCaseFirstOne(subModelName));
			queryByModelMethod.addParameter(parameter);
			serviceInterface.addMethod(queryByModelMethod);
			
			serviceInterface.addImportedType(serviceSuperType);
			serviceInterface.addSuperInterface(serviceSuperType);
			
			
			//serviceImpl实现类
			TopLevelClass serviceImplClass = new TopLevelClass(businessImplTargetPackage
					+ "." + subModelName + "ServiceImpl" );
			serviceImplClass.setVisibility(JavaVisibility.PUBLIC);
			serviceImplClass.addJavaDocLine(" /**");
			serviceImplClass.addJavaDocLine(" * Generate By MBG for serviceImpl");
			serviceImplClass.addJavaDocLine(" **/");
			//springService类
			FullyQualifiedJavaType springService = new FullyQualifiedJavaType(springFrameworkStereotypeService);
			serviceImplClass.addJavaDocLine("@Service");
			serviceImplClass.addImportedType(springService); //添加引用
			serviceImplClass.addImportedType(subModelJavaType);//添加model引用
			serviceImplClass.addImportedType(subModelExampleJavaType);//添加modelExample引用

			FullyQualifiedJavaType serviceImplSuperType = new FullyQualifiedJavaType(businessImplSuperClass);// 所继承的接口类
			// 添加泛型支持
			serviceImplSuperType.addTypeArgument(subModelJavaType);
			serviceImplSuperType.addTypeArgument(subModelExampleJavaType);
			serviceImplSuperType.addTypeArgument(new FullyQualifiedJavaType("java.lang.String"));
			serviceImplClass.setSuperClass(serviceImplSuperType); // 继承类
			serviceImplClass.addImportedType(serviceImplSuperType);
			
			//
			FullyQualifiedJavaType serviceImplInterfaceSuperType = new FullyQualifiedJavaType(
					businessTargetPackage + "." + subModelName + "Service");
			serviceImplClass.addSuperInterface(serviceImplInterfaceSuperType); //实现接口
			serviceImplClass.addImportedType(serviceImplInterfaceSuperType);
			
			//添加日志属性字段
			FullyQualifiedJavaType loggerType = new FullyQualifiedJavaType(slf4jLogger);
			Field loggerField = new Field("LOGGER",loggerType);
			loggerField.setVisibility(JavaVisibility.PRIVATE);
			loggerField.setStatic(true);
			loggerField.setFinal(true);
			loggerField.setInitializationString("LoggerFactory.getLogger("+ subModelName + "ServiceImpl.class)");
			serviceImplClass.addField(loggerField);
			serviceImplClass.addImportedType(loggerType);
			// 添加mapper属性字段
			FullyQualifiedJavaType modelMapper = new FullyQualifiedJavaType(daoTargetPackage + "." + subModelName + "Mapper");
			Field mapperField = new Field(toLowerCaseFirstOne(subModelName)+"Mapper",modelMapper);
			mapperField.setVisibility(JavaVisibility.PRIVATE);
			mapperField.addJavaDocLine("@Autowired");
			serviceImplClass.addField(mapperField);
			serviceImplClass.addImportedType(modelMapper);
			serviceImplClass.addImportedType(loggerType);
			serviceImplClass.addImportedType(slf4jLoggerFactory);
			serviceImplClass.addImportedType(springFrameworkAutowired);
			// 添加实现方法
			Method method = new Method("getGenericMapper");
			method.addJavaDocLine("@Override");
			method.setVisibility(JavaVisibility.PROTECTED);
			FullyQualifiedJavaType serviceImplMethodReturnType = new FullyQualifiedJavaType(genericMapper);
			//添加泛型支持
			serviceImplMethodReturnType.addTypeArgument(subModelJavaType);
			serviceImplMethodReturnType.addTypeArgument(subModelExampleJavaType);
			serviceImplMethodReturnType.addTypeArgument(new FullyQualifiedJavaType("java.lang.String"));
			method.setReturnType(serviceImplMethodReturnType);
			method.setVisibility(JavaVisibility.PUBLIC);
			method.addBodyLine("return "+toLowerCaseFirstOne(subModelName)+"Mapper;");
			serviceImplClass.addMethod(method);
			serviceImplClass.addImportedType(serviceImplMethodReturnType);
			
			// 添加通用接口的实现方法
			Method queryByModelMethodImpl = new Method("queryByModel");
			queryByModelMethodImpl.setReturnType(subModelJavaType);
			queryByModelMethodImpl.addParameter(parameter);
			queryByModelMethodImpl.setVisibility(JavaVisibility.PUBLIC);
			queryByModelMethodImpl.addBodyLine( subModelName +"Example example = new " + subModelName+"Example();");
			queryByModelMethodImpl.addBodyLine("Criteria c= example.createCriteria();");
			queryByModelMethodImpl.addBodyLine("List<"+subModelName+"> modelList =  "+toLowerCaseFirstOne(subModelName)+"Mapper.selectByExample(example);");
			queryByModelMethodImpl.addBodyLine("if(modelList.size() > 0){");
			queryByModelMethodImpl.addBodyLine("return modelList.get(0);");
			queryByModelMethodImpl.addBodyLine("}else{");
			queryByModelMethodImpl.addBodyLine("return null;");
			queryByModelMethodImpl.addBodyLine("}");
			serviceImplClass.addMethod(queryByModelMethodImpl);
			
			FullyQualifiedJavaType baseModelExampleCriteria = new FullyQualifiedJavaType("com.ffxl.cloud.model.base.Base"+subModelName+"Example.Criteria");
			FullyQualifiedJavaType utilList = new FullyQualifiedJavaType("java.util.List");
			serviceImplClass.addImportedType(baseModelExampleCriteria);
			serviceImplClass.addImportedType(utilList);
			
			try {
				//接口
				GeneratedJavaFile serviceJavafile = new GeneratedJavaFile(
						serviceInterface, businessTargetDir, javaFormatter);
				File serviceDir = shellCallback.getDirectory(businessTargetDir,
						businessTargetPackage);
				File serviceFile = new File(serviceDir,
						serviceJavafile.getFileName());
				//实现类
				GeneratedJavaFile serviceImplJavafile = new GeneratedJavaFile(
						serviceImplClass, businessTargetDir, javaFormatter);
				File serviceImplDir = shellCallback.getDirectory(businessTargetDir,
						businessImplTargetPackage);
				File serviceImplFile = new File(serviceImplDir,
						serviceImplJavafile.getFileName());
				// 文件不存在
				if (!serviceFile.exists()) {

					serviceJavaFiles.add(serviceJavafile);
				}
				// 文件不存在
				if (!serviceImplFile.exists()) {

					serviceJavaFiles.add(serviceImplJavafile);
				}
			} catch (ShellException e) {
				e.printStackTrace();
			}

		}

		System.out.println("===============结束：生成Service及ServiceImpl文件================");

		return serviceJavaFiles;
	}

	private String getSubModelType(FullyQualifiedJavaType fullyQualifiedJavaType) {
		String type = fullyQualifiedJavaType.getFullyQualifiedName();
		String temp = "base.Base";
		String newType = type.replace(temp, "");
		return newType;
	}
	
	//首字母转小写
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}
