package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.Enum.RoleEnum;
import cn.edu.zjut.examsystem.MoonshotAiUtils;
import cn.edu.zjut.examsystem.bean.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import okhttp3.Response;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kimi")
@Tag(name = "kimi管理")
public class KimiController {

    @Autowired
    private MoonshotChatModel model;

    // 演示用，实际要存入用户session
    private List<Message> messages = new ArrayList<>();

    @Operation(summary = "聊天")
    @GetMapping("/chat")
    public SseEmitter chat(String content, HttpSession session) throws IOException {
        List<Message> messages = (List<Message>) session.getAttribute("messages");
        if (messages == null) {
            messages = new ArrayList<>();
            session.setAttribute("messages", messages);
        }
        Message message = new Message(RoleEnum.user.name(), content);
        messages.add(message);


        return MoonshotAiUtils.chat("moonshot-v1-128k", messages);
    }
}