# 企业级应用管理平台 V3.0.0
#### 版本说明:
该版本为企业管理平台第三版(Java版)  
第一版:ASP.NET MVC EF 领域驱动  
第二版:前后端分离 ASP.NET WebApi Dapper + angular.js  
第三版:前后端分离 ASP.NET 微服务架构/java + angular 8  

## 01.目录结构  
#####├── 01- presentation  
#####├── 02- application
#####│　　├── enterprise.framework.business
#####│　　└── enterprise.framework.pojo
#####├── 03-domain
#####│　　└── enterprise.framework.domain
#####├── 04-infrastructure
#####│　　├── 04.01-apigateway
#####│　　│　　└── enterprise.framework.gateway
#####│　　├── 04.02-structure
#####│　　│　　├── enterprise.framework.mapper
#####│　　│　　└── enterprise.framework.service
#####│　　├── 04.03-inside
#####│　　│　　├── enterprise.framework.core
#####│　　│　　└── enterprise.framework.utility
#####├── 05-communication
#####├── 06-microService
#####└── └──　└── enterprise.framework.erp

#####├── 01- 展示层  
#####├── 02- 全局
#####│　　├── enterprise.framework.business
#####│　　└── enterprise.framework.pojo
#####├── 03- 领域模型
#####│　　└── enterprise.framework.domain
#####├── 04- 基础设施
#####│　　├── 04.01- api网关
#####│　　│　　└── 网关
#####│　　├── 04.02- 结构
#####│　　│　　├── 仓储
#####│　　│　　└── 应用服务
#####│　　├── 04.03-inside
#####│　　│　　├── 核心包
#####│　　│　　└── 工具包
#####├── 05- 通信
#####├── 06- 微服务
#####└── └──　└── erp微服务
