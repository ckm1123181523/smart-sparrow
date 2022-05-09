# smart-sparrow 聪明的麻雀
1. 打造一个具有通用的单机项目，然后项目开发中可以直接复制黏贴。

## 功能说明

## 项目模块

## Maven编译命令
```
mvn clean compile -DskipTests //编译+不测试
mvn clean compile -Dmaven.test.skip=true  //不编译+不测试
mvn clean package -Dmaven.test.skip=true  //打包+不测试

#devops环境不支持这样打不同环境包,每个环境都是一样的包,在发布的时候拷贝不同配置到应用下
mvn clean package  -DskipTests -P sit //打包测试环境
mvn clean package  -DskipTests -P uat  //打包预发环境
mvn clean package  -DskipTests -P prd  //打包生产环境
mvn -N versions:update-child-modules    //把父module版本同步到子modules
```

## 启动项目
```
sh app.sh restart dev
```

## 部署项目

## 环境支持
- dev（开发联调）
- sit（集成环境）
- uat (回归环境)
- sitautotest（自动化sit）
- uatautotest（自动化uat）
- jmeter（压测环境）





