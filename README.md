# EasyWeb-RESTful
## 简介
   前后端分离模式的后端管理系统模板，后端接口遵循RESTful风格设计，前端页面使用路由实现单页面效果。 演示地址：[http://115.159.40.243:8080/EasyWeb-RESTful](http://115.159.40.243:8080/EasyWeb-RESTful/login.html)。
   
   
## 使用技术
   此项目本着轻量、简洁、不多添加一个无用包的原则，特意精挑细选了如下的几个技术框架：

后端 | ... 
:---:|:---
核心框架 | spring、springmvc、mybatis
连接池 | Alibaba Druid
缓存框架 | Redis、[RedisUtil](https://github.com/whvcse/RedisUtil)
权限框架 | [EasyTokenPermission](https://github.com/whvcse/EasyTokenPermission)、jjwt
图片验证码(支持gif) | [EasyCaptcha](https://github.com/whvcse/EasyCaptcha)
密码加密 | [EndecryptUtil](https://github.com/whvcse/EndecryptUtil)

前端 | ... 
:---:|:---
核心框架(轻量简洁) | [Layui](http://www.layui.com/)、jQuery
路由框架(纯js打造) | [Q.js](https://github.com/itorr/q.js)

## 你的疑问
#### 疑问1、为什么不选用Shiro、Auth2.0等知名权限框架？
  Shiro是基于Session的权限框架，并不适合于RESTful风格的架构([什么是RESTful？]())，RESTful是一定不能使用Session的。 
网上也有大牛出了Shiro实现RESTful的教程，步骤繁琐不说，这种霸王硬上弓的方法也很不优雅。 Auth2.0是做第三方授权的框架，像我
我们常见的QQ、微信、微博授权登录这样的，并且学习成本高，对于企业项目的内部账号登录验证有点大材小用。 所以这里选用了EasyTokenPermission。

#### 疑问2、为什么不用Vue、React来做前后端分离？
     对于职业前端人员来说，用Vue、React并不是什么稀奇的事情，但是对于一个后端人员来说就很尴尬了，虽然说前后端分离，开发人员也应该分离，但是有多少
  公司正是因为前端人员的缺乏依然再使用古老的开发方式，有使用iframe的，有使用SiteMesh、甚至还有使用jsp的，但是前端的技术多种多样，作为一个
 后端人员完全没有精力去学习，我们会一个jquery一个bootstrap就不错了。所以这里我选用了Q.js作为路由框架，他是纯js写的，不依赖于其他框架，上手容易。
然后是layui，我个人是比较喜欢layui的数据表格的，以前用过EasyUI的表格，用过bootstarp的数据表格，EasyUI样式太丑，BootstrapTable的js功能太弱。
就举一个简单的例子，我们测试经常提的一个bug就是表格内容太长把页面都撑变形了，这个bug用BootstrapTable真是无法解决，layui的数据表格内容超出会显示省略号，点击会漂浮显示全部。

#### 疑问3、为什么非要吹捧前后端分离？
     关于前后端分离的讨论可以移步知乎去看看大家的看法，我个人有以下几个观点：
   
1.传统的通过后台controller再去跳转到前端页面是非常蛋疼的，这严重影响了前端页面的加载速度，不利于用户体验。
2.传统基于session的方式也是有缺陷的，我的第一职业是后端开发，第二职业是安卓开发，在安卓开发中是没有cookie和session的，并且APP都是要一次登录并且持久化的，
很多人都是通过缓存账号、密码然后静默登录来实现持久化的假象，这种并不规范，也不安全，基于token的验证方式就很适合移动端的后端服务架构。
3.对于多系统单点登录session也是有缺陷的，基于token的验证就没有这个问题，只要对系统之间对于token的验证方式一致，就可以一个token多个系统使用。
4.既然都不用session了为什么还不分离。

#### 疑问4、为什么非要吹捧RESTful风格的结构？
   既然都不用session了，前后端都分离了，为什么不把我们的接口按照RESTful风格写的规范、通用一点呢？
   
#### 疑问5、为什么非要用路由？
   自古以来，我们的网站都是分头部、左侧和底部的，在传统项目中，大多数人都使用iframe来实现，稍微好一点的使用 SiteMesh，但是他们都不是最好的解决方法：
1.iframe，它有很多缺点，我就不说了，它每个页面都要引入css、js，会重复加载资源，造成浪费。
2.SiteMesh，利用后端技术拼接渲染，浏览加载页面还是全局刷新，左侧、头部、底部每次都重新加载一遍，用户体验不好。
   
路由：
   
   前端路由是真正实现了页面局部刷新，资源重复利用，大大提升用户体验，提高网站的逼格！
   
   
## 知识补充
### 什么是RESTful



### 什么是token


### 什么是前端路由
