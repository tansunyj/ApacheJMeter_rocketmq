# ApacheJMeter_rocketmq

#### 介绍
jmeter rocketmq插件
网上很多插件采用AbstractJavaSamplerClient等实现，局限性比较大，特地开发此插件以便方便测试工作

#### 软件架构
软件架构说明
  1. 插件中jmeter core采用v5.1.1版本;
  2. 插件中rocketmq采用v4.2.0版本；

#### 安装教程
使用此插件需按如下步骤进行
1.  需下载rocketmq-client、rocketmq-common、rocketmq-logging、rocketmq-remoting、netty-all包，并放到$JMETER_HOME/lib下
2.  项目为maven工程，打包后插件需放到$JMETER_HOME/lib/ext下


#### 使用说明

1.  添加配置元件Rocketmq Connection Configuration，设定rocketmq相关链接参数等，GUI界面有提示；
2.  添加取样器Rocketmq Sampler；


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
