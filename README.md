# 微信多域名授权
## 由于微信公众平台授权域名仅可填写2个，但日常生产中会碰到不同域名的项目，希望使用同一个公众号授权，所以借助以填写的域名项目作为跳板，为多个其他域名项目提供授权。(具体实现方式查看代码)
## 使用方式
>1. 首先将项目所属域名配置到微信公众平台。(详见[微信官方文档](https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842))
>2. 将代码部署至对应的项目中，具体访问路径可自行更改。
>3. 确定授权的控制层可正常访问之后，直接将正常方式授权的步骤中的微信授权地址(`https://open.weixin.qq.com/connect/oauth2/authorize`),更换为代码配置的访问路径即可(如:`http://xxx.com/project/oauth2/authorize`),参数无需更改。
