# 题目

内测017

环境：java8、idea，没有使用springboot。

登录相关：
使用 salt 加密前后端传输的数据，增加拦截成本，还可以增加时间作为维度。

数据结构设计：
菜单轮播时段字段interval，半开闭：**'8-9,9-10**' 。
还可以设计成 8-20 中任意时间点+终结符,示例：**8#,9#,10#**。代码中使用第一种方式。

整体包结构:

filter：拦截请求，根据前端传递过来的 token 解析成信息存入 ThreadLocal 中，后续可从线程上下文中直接取，不需要使用参数进行传递。

context：存拦截器解密后的身份信息。

Exception：全局异常处理。发生异常，可直接中断程序抛出异常，不需要一层层传递错误信息。

util：工具类

model：模型类

controller：控制层

service：业务层

思路：
思路详情见代码注释，入口 [https://github.com/ruqinhu/practice/tree/master/src/main/java/org/ruqinhu/liang/food/controller](https://github.com/ruqinhu/practice/tree/master/src/main/java/org/ruqinhu/liang/food/controller)

像这种根据时间段解析执行的任务还可以更复杂一点，我以前遇到过类似增加闹钟任务的需求，以 年、月、季度、周等作为周期，周期内可选单次或者多次，每次可以有不同的执行时间段。
