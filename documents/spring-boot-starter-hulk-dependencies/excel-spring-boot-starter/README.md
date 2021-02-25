## EasyExcel

EasyExcel是一个基于Java的简单、省内存的读写Excel的开源项目。在尽可能节约内存的情况下支持读写百M的Excel。
64M内存1分钟内读取75M(46W行25列)的Excel,当然还有急速模式能更快，但是内存占用会在100M多一点

参考：https://github.com/alibaba/easyexcel

## 使用方法

只需要在 `Controller` 层返回 List 并增加 `@RespExcel`注解即可




## 实体对象
```java
@Data
public class DemoData {
    @ColumnWidth(50)  // 定义宽度
	@ExcelProperty("用户名") // 定义列名称
    @ContentStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 40)
	private String username;
	@ExcelProperty("密码")
	private String password;
}
```

## 导出单sheet

```java
@RespExcel(name = "模板测试excel", sheet = "sheet")
@GetMapping("/e1")
public List<DemoData> e1() {
    List<DemoData> dataList = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
        DemoData data = new DemoData();
        data.setUsername("tr1" + i);
        data.setPassword("tr2" + i);
        dataList.add(data);
    }
    return dataList;
}
```

## 导出多sheet

```java
@RespExcel(name = "模板测试excel", sheet = {"sheet1","sheet2"})
@GetMapping("/e1")
public List<List<DemoData>> e1() {
    List<List<DemoData>> lists = new ArrayList<>();
    lists.add(list());
    lists.add(list());
    return lists;
}
```

## 设置导出加密码

```java
@RespExcel(name = "模板测试excel", sheet = "sheetName",password = "123456")
@GetMapping("/e1")
public List<List<DemoData>> e1() {
    List<List<DemoData>> lists = new ArrayList<>();
    lists.add(list());
    lists.add(list());
    return lists;
}
```

## 高级用法模板导出

```java
@RespExcel(name = "模板测试excel", sheet = "sheetName",template = "example.xlsx")
@GetMapping("/e1")
public List<DemoData> e1() {
    return list();
}
```

