# smart-sparrow 聪明的麻雀
1. 打造一个具有通用的单机项目，然后项目开发中可以直接复制黏贴。

## 功能说明

## 项目模块

## Maven编译命令
```
mvn clean package // 打包

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