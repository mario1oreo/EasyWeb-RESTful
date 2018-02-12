# EasyWeb-RESTful
## 简介
前后端分离模式的后端管理系统模板，后端接口遵循RESTful风格设计，前端页面使用路由实现单页面效果。<br/>
演示地址：[http://115.159.40.243:8080/EasyWeb-RESTful](http://115.159.40.243:8080/EasyWeb-RESTful/login.html)。
   
   
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
核心框架(轻量简洁) | [Layui](http://www.layui.com/)、jQuery、zTree
路由框架(纯js打造) | [Q.js](https://github.com/itorr/q.js) (超级轻量、简单易学)

## 项目截图
![登录](https://github.com/whvcse/EasyWeb-RESTful/blob/master/WebRoot/assets/images/screenshot_login.png) 
![用户管理](https://github.com/whvcse/EasyWeb-RESTful/blob/master/WebRoot/assets/images/screenshot_user.png)
![角色管理](https://github.com/whvcse/EasyWeb-RESTful/blob/master/WebRoot/assets/images/screenshot_role.png)
![权限管理](https://github.com/whvcse/EasyWeb-RESTful/blob/master/WebRoot/assets/images/screenshot_permission.png)
![登录日志](https://github.com/whvcse/EasyWeb-RESTful/blob/master/WebRoot/assets/images/screenshot_loginrecode.png)
 
 
## 疑问说明
### 疑问1、为什么不选用Shiro、Auth2.0等知名权限框架？
  Shiro是基于Session的权限框架，并不适合于RESTful风格的架构([什么是RESTful？]())，RESTful是一定不能使用Session的。 
网上也有大牛出了Shiro实现RESTful的教程，步骤繁琐不说，这种霸王硬上弓的方法也很不优雅。 Auth2.0是做第三方授权的框架，像
我们常见的QQ、微信、微博授权登录这样的，并且学习成本高，对于企业项目的内部账号登录验证有点大材小用。 所以这里选用了[EasyTokenPermission](https://github.com/whvcse/EasyTokenPermission)。
        
### 疑问2、为什么不用Vue、React来做前后端分离？
对于职业前端人员来说，用Vue、React并不是什么稀奇的事情，但是对于一个后端人员来说就很尴尬了，虽然说前后端分离，开发人员也应该分离，但是有多少
公司正是因为前端人员的缺乏依然再使用古老的开发方式，有使用iframe的，有使用SiteMesh、甚至还有使用jsp的，  但是前端的技术多种多样，作为一个
后端人员完全没有精力去学习，大多数人都是只会一个jquery和bootstrap。 所以这里我选用了Q.js作为路由框架，选用layui作为核心框架，我个人是比较喜欢layui的数据表格的，以前用过EasyUI的表格，用过bootstarp的数据表格，EasyUI样式太丑，BootstrapTable的js功能太弱。
       
### 疑问3、为什么非要吹捧前后端分离？
关于前后端分离的讨论可以移步知乎去看看大家的看法，我个人有以下几个观点：
1. 通过controller去跳转页面是非常蛋疼的，这严重影响了页面的加载速度，不利于用户体验。
2. 在APP开发中没有cookie和session，而且APP只登录一次并且持久化，很多人通过保存账号、密码静默登录实现持久化的假象，
这种操作不规范、不安全，基于token的验证方式很适合APP的后端架构。
3. 多系统单点登录session也是有缺陷的，基于token验证就没这个问题，只要系统对于token验证方式一致，就可以一个token多个系统使用。
4. 既然都不用session了为什么还不分离。
           
### 疑问4、为什么非要吹捧RESTful风格的结构？
既然都不用session了，前后端都分离了，为什么不把我们的接口按照RESTful风格写的规范、国际通用一点呢？
     
### 疑问5、为什么非要用路由？
自古以来，我们的网站都是分头部、左侧和底部的，在传统项目中，大多数人都使用iframe来实现，稍微好一点的使用 SiteMesh，但是他们都不是最好的解决方法：
1. iframe，它有很多缺点，我就不说了，它每个页面都要引入css、js，会重复加载资源，造成浪费。
2. SiteMesh，利用后端技术拼接渲染，浏览器加载页面还是全局刷新，左侧、头部、底部每次都重新加载一遍，用户体验不好。
3. 路由： 前端路由是真正实现了页面局部刷新，资源重复利用，大大提升用户体验，提高网站的逼格！
      
## 知识补充
### 1、什么是RESTful
关于RESTful标准的解释我就不说了，我来划一划重点：
1. URL要代表资源，我们的url要能够体现出我们所请求的是服务器的什么资源，例如关于用户的操作，它的url应该是/user。
2. HTTP请求要代表动作，关于HTTP请求很多人肯定会说有GET和POST请求，GET和POST的区别是参数是否可见，POST比GET安全，甚至很多老师都是这样教学生的，但是现在告诉你，你以前所学的全是错误的，HTTP有八种请求，分别代表请求服务器的不同的动作方式，POST并不比GET安全多少，真正安全的是HTTPS协议，不要再全部用POST请求了，什么操作用什么请求。
3. HTTP请求是无状态的，传统的WEB中，浏览器传递cookie，服务器保存session，两者一一对应来保持请求的状态，代表是一次会话，但是RESTful认为这是错误的设计，HTTP的请求应该是无状态的，所以不要用session了，在APP中也是没有cookie的，用session你的服务器就只能做网页了。
       
### 2、什么是token
   有人把token翻译成令牌，这个就很形象了，传统的web登录认证是把登录后的user存储在session中，判断session中有无user决定是否登录，如果要做记住密码或者持久化登录(APP)就有很大缺陷，并且多系统单点登录还要共享session。<br/><br/>
   token认证的概念就是，使用账号密码验证之后服务器认为用户合法，给用户发一个令牌，这个令牌包含一些权限，用户拿着这个令牌可以访问服务器的一些资源。  只要用户没有弄丢这个令牌，他就可以一直拿着这个令牌访问服务器的资源，不需要每次输入账号密码登录了，令牌有一个过期时间，过期了就失效了。 <br/><br/>
   举一个形象的例子，你去酒店住房，第一次进去你需要用身份证登记，然后给你一个房卡，用身份证登记的过程就好比我们用账号密码登录，房卡就好比令牌token，然后你再进出酒店就拿着房卡就行了，不需要每次拿出身份证，这个房卡也是有一定的权限的，他只能开一个房间，不能卡其他房间，我们token也是有权限的，你把房卡弄丢了你就要用身份证重新领一张房卡。
     
### 3、什么是前端路由
   我理解的就是页面局部加载，跟传统的局部刷新不同的就是url也会变化，是把一个url的内容加载到网页的局部区域，而不是整个页面跳转。  
