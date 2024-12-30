package cn.edu.zjut.examsystem;

import cn.edu.zjut.examsystem.bean.Message;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.NonNull;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.data.domain.Limit;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class MoonshotAiUtils {
    private static final String API_KEY = "sk-QqUsWLQVXlA0L9nGqnB1ibLoJux2BsLjmMWkZuNQzjkVtbb9";
    private static final String MODELS_URL = "https://api.moonshot.cn/v1/models";
    private static final String FILES_URL = "https://api.moonshot.cn/v1/files";
    private static final String ESTIMATE_TOKEN_COUNT_URL = "https://api.moonshot.cn/v1/tokenizers/estimate-token-count";
    private static final String CHAT_COMPLETION_URL = "https://api.moonshot.cn/v1/chat/completions";

    public static String getModelList() {
        return getCommonRequest(MODELS_URL)
                .execute()
                .body();
    }

    public static String uploadFile(@NonNull File file) {
        return getCommonRequest(FILES_URL)
                .method(Method.POST)
                .header("purpose", "file-extract")
                .form("file", file)
                .execute()
                .body();
    }

    public static String getFileList() {
        return getCommonRequest(FILES_URL)
                .execute()
                .body();
    }

    public static String deleteFile(@NonNull String fileId) {
        return getCommonRequest(FILES_URL + "/" + fileId)
                .method(Method.DELETE)
                .execute()
                .body();
    }

    public static String getFileDetail(@NonNull String fileId) {
        return getCommonRequest(FILES_URL + "/" + fileId)
                .execute()
                .body();
    }

    public static String getFileContent(@NonNull String fileId) {
        return getCommonRequest(FILES_URL + "/" + fileId + "/content")
                .execute()
                .body();
    }

    public static String estimateTokenCount(@NonNull String model, @NonNull List<Message> messages) {
        String requestBody = new JSONObject()
                .putOpt("model", model)
                .putOpt("messages", messages)
                .toString();
        return getCommonRequest(ESTIMATE_TOKEN_COUNT_URL)
                .method(Method.POST)
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .body(requestBody)
                .execute()
                .body();
    }

    @SneakyThrows
    public static SseEmitter chat(@NonNull String model, @NonNull List<Message> messages) {
        /*
        StringBuilder sb = new StringBuilder();
        String requestBody = new JSONObject()
                .putOpt("model", model)
                .putOpt("messages", messages)
                .putOpt("stream", true)
                .toString();
        Request okhttpRequest = new Request.Builder()
                .url(CHAT_COMPLETION_URL)
                .post(RequestBody.create(requestBody, MediaType.get(ContentType.JSON.getValue())))
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时为30秒
                .readTimeout(30, TimeUnit.SECONDS) // 设置读取超时为30秒
                .build();

        Call call = client.newCall(okhttpRequest);
        Response okhttpResponse = call.execute();
        BufferedReader reader = new BufferedReader(okhttpResponse.body().charStream());
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (StrUtil.isBlank(line)) {
                    continue;
                }
                if (JSONUtil.isTypeJSON(line)) {
                    Optional.of(JSONUtil.parseObj(line))
                            .map(x -> x.getJSONObject("error"))
                            .map(x -> x.getStr("message"))
                            .ifPresent(x -> System.out.println("error: " + x));
                    JSONObject jsonObject = JSONUtil.parseObj(line);
                    throw new Exception(jsonObject.getJSONObject("error").getStr("message"));
                }
                line = StrUtil.replace(line, "data: ", StrUtil.EMPTY);
                if (StrUtil.equals("[DONE]", line) || !JSONUtil.isTypeJSON(line)) {
                    return sb.toString();
                }
                Optional.of(JSONUtil.parseObj(line))
                        .map(x -> x.getJSONArray("choices"))
                        .filter(CollUtil::isNotEmpty)
                        .map(x -> (JSONObject) x.get(0))
                        .map(x -> x.getJSONObject("delta"))
                        .map(x -> x.getStr("content"))
                        .ifPresent(x -> sb.append(x));
            }
            return sb.toString();
        } finally {
            IoUtil.close(reader);
        }*/

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        String requestBody = new JSONObject()
                .putOpt("model", model)
                .putOpt("messages", messages)
                .putOpt("stream", true)
                .toString();

        Request request = new Request.Builder()
                .url(CHAT_COMPLETION_URL)
                .post(RequestBody.create(requestBody, MediaType.get(ContentType.JSON.getValue())))
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                emitter.completeWithError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    emitter.completeWithError(new IOException("Unexpected code " + response));
                    return;
                }
                try (BufferedReader reader = new BufferedReader(response.body().charStream())) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.trim().isEmpty()) {
                            emitter.send(SseEmitter.event().data(line + "\n"));
                        }
                    }
                    emitter.complete();
                } catch (IOException e) {
                    emitter.completeWithError(e);
                }
            }
        });
        return emitter;
    }

    private static HttpRequest getCommonRequest(@NonNull String url) {
        return HttpRequest.of(url).header(Header.AUTHORIZATION, "Bearer " + API_KEY);
    }
}
