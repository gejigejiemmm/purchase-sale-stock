# purchase-sale-stock
因为图片上传的位置应该是服务器

但是测试阶段有些局限性

请去往图片仓库下载图片

[purchase-sale-stock imgs](https://github.com/gejigejiemmm/imgs.git)

并且在 项目的 ⁨purchase-sale-stock⁩/⁨target⁩/classes 下
git 图片仓库
```
git clone https://github.com/gejigejiemmm/imgs.git
```
## 查看api文档
浏览器打开  http://localhost/swagger-ui.html
## 写api文档
#### 使用 @Api 注解标记类
#### 使用 @ApiOperation 接口描述api接口信息
示例代码如下：
```
@RestController
@RequestMapping("/order")
@Api(tags = "订单接口")
public class OrderController {
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiOperation(value = "条件获取订单信息，默认为查询所有，所有参数都非必须",httpMethod = "GET")
    public Msg getOrders(){
      ...
    }
  ...
}
```
## 前端注意事项
- css js html等静态资源的存放

   - main/resources/css
   - main/resources/js
   - main/resources/templates/*.html

- 获取图片
/img/get?imgUrl=图片名字
```
<img src="/img/get?imgUrl=201910191210438008569IMG_4197.JPG">
```
- 发送请求的时候一定要看好是 GET ，POST请求


## 后端注意事项
- 时间参数都使用 LocalDateTime 并且使用 @JsonFormat @DateTimeFormat 标注
```
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderCreateTime;
```
- 接收时间参数的时候 @DateTimeFormat 格式化时间
```
    @RequestParam(value = "orderCreateTime", required = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime orderCreateTime
```
- SQL 语句报错时可以在 Application.yaml 文件中开启 debug 注解
```
logging:
  level:
    cn.edu.zzuli.purchasesalestock.Mapper: debug
```
- 项目中 使用 Lombok，如果项目报错 ，去idea 插件库中下载 Lombok 插件
