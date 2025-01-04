# Baeldung Learning Assistant

## 项目概述

这是一个基于 OpenAI API 的学习助手程序，专门用于帮助用户创建 Baeldung 网站的编程学习课程。该助手可以根据用户的学习需求，推荐相关的 Baeldung 教程文章，并按照难度级别进行排序。

## 技术特点

### 基本设置
- 使用 OpenAI 的 GPT-3.5-Turbo 模型
- 设定最大令牌数为 900
- 通过系统环境变量获取 OpenAI API 密钥

### 核心功能
- 接收用户输入的学习主题
- 调用 OpenAI API 进行处理
- 返回 Baeldung.com 相关教程文章链接
- 按照从初级到高级的顺序排列文章

### 程序特点
- 持续对话：程序保持运行并持续接受用户输入
- 退出机制：用户输入"exit"时可以退出程序
- 令牌计数：显示每次对话使用的令牌数量
- 对话历史：保存整个对话历史以维持上下文

## 代码示例

```java
java
while (true) {
// 发送请求到 OpenAI
ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
.builder()
.model(MODEL)
.maxTokens(MAX_TOKENS)
.messages(messages)
.build();
// 获取响应并显示
ChatCompletionResult result = service.createChatCompletion(chatCompletionRequest);
System.out.println(response.getContent());
// 询问用户是否还有其他问题
System.out.print("Anything else?\n");
String nextLine = scanner.nextLine();
if (nextLine.equalsIgnoreCase("exit")) {
System.exit(0);
}
}
```


## 使用说明

1. 启动程序后，输入您想要学习的编程主题
2. 程序会返回相关的 Baeldung 教程文章链接
3. 文章会按照难度从低到高排序
4. 您可以继续提问或输入 "exit" 退出程序

## 注意事项

- 需要设置有效的 OpenAI API 密钥
- 确保网络连接正常
- 注意 API 调用的频率限制