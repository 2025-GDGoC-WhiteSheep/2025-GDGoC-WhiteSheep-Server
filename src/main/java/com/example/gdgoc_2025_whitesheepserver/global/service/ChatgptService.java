package com.example.gdgoc_2025_whitesheepserver.global.service;

import com.example.gdgoc_2025_whitesheepserver.global.dto.QuestionDto;
import com.example.gdgoc_2025_whitesheepserver.global.enums.LevelType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatgptService {

    private final RestTemplate restTemplate;

    @Value("${gpt.data-api.key}")
    private String key;

    private static String PROMPT_PREFIX1 = "Please provide a JSON file with the following format: {\\\"question\\\": \\\"<string>\\\",\\\"options\\\": [\\\"1. <string>\\\", \\\"2. <string>\\\", \\\"3. <string>\\\", \\\"4. <string>\\\"],\\\"correct_answer\\\": \\\"<number>\\\"} Generate a 4-option multiple-choice ";
    private static String PROMPT_PREFIX2 = " difficulty question in Korean. You must strictly use only the information explicitly provided in this ";
    private static String PROMPT_POSTFIX = ". Ensure the options are numbered (1, 2, 3, 4) and the correct_answer field contains only the number corresponding to the correct option.";

    public QuestionDto getQuestionByText(String subtitle, LevelType levelType) {
        String prompt = PROMPT_PREFIX1 + levelType.name() + PROMPT_PREFIX2 + "text: " + subtitle + PROMPT_POSTFIX;
        String response = sendRequest(prompt);
        String content = getContent(response);
        QuestionDto question = getQuestionByContent(content);
        return question;
    }

    public QuestionDto getQuestionByUrl(String url, LevelType levelType) {
        String prompt = PROMPT_PREFIX1 + levelType.name() + PROMPT_PREFIX2 + "site: " + url + PROMPT_POSTFIX;
        String response = sendRequest(prompt);
        String content = getContent(response);
        QuestionDto question = getQuestionByContent(content);
        return question;
    }

    private String sendRequest(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(key);
        String jsonString = String.format(
                "{\n" +
                        "  \"model\": \"gpt-4\",\n" +
                        "  \"messages\": [\n" +
                        "    {\n" +
                        "      \"role\": \"user\",\n" +
                        "      \"content\": \"%s\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}", prompt);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    private String getContent(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject firstChoice = choices.getJSONObject(0);
        JSONObject message = firstChoice.getJSONObject("message");
        String content = message.getString("content");
        return content;
    }

    public QuestionDto getQuestionByContent(String content) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            QuestionDto question = objectMapper.readValue(content, QuestionDto.class);
            return question;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
