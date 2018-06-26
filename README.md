#非凡心理平台架构
工程引入顺序
1.lib_parent
2.platform_parent
3.cloud_parent

mbg_plugin定制代码生成插件
cloud_parent下的cloud_mbg代码生成器，当需要生成实体对象时，只需修改
配置文件后，运行mybatis-generator:generate 命令
#---ffxl-2016-09-26---#