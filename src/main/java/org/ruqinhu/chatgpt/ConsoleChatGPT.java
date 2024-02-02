package org.ruqinhu.chatgpt;


import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.util.Proxys;

import java.net.Proxy;

public class ConsoleChatGPT {

    public static void main(String[] args) {
        //国内需要代理
        Proxy proxy = Proxys.http("127.0.0.1", 10809);

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey("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")
                .proxy(proxy)
                .apiHost("https://api.openai.com/") //反向代理地址
                .build()
                .init();

        String res = chatGPT.chat("模仿 余秋雨 的风格写一篇文章");
        System.out.println(res);
    }
}
