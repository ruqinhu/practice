# 题目
practice

17题：

登录相关：使用 salt 加密前后端传输的数据，增加拦截成本。

整体包结构:

filter：拦截请求，根据前端传递过来的 token 解析成信息存入 ThreadLocal 中，后续可从线程上下文中直接取，不需要使用参数进行传递。

context：存拦截器解密后的身份信息。

Exception：全局异常处理。发生异常，可直接中断程序抛出异常，不需要一层层传递错误信息。

util：工具类

model：模型类

controller：控制层

service：业务层
