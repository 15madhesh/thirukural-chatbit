package com.madhesh.kural;

import java.io.*;
import java.net.*;
import com.google.gson.*;

public class LLMService {
    private static final String OLLAMA_API = "http://localhost:11434/api/generate";

    public static String askLLM(String context, String question) throws IOException {
        JsonObject payload = new JsonObject();
        payload.addProperty("model", "mistral");
        payload.addProperty("prompt", context + "\nQ: " + question + "\nA:");

        HttpURLConnection conn = (HttpURLConnection) new URL(OLLAMA_API).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.toString().getBytes());
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JsonObject chunk = JsonParser.parseString(line).getAsJsonObject();
                if (chunk.has("response")) {
                    response.append(chunk.get("response").getAsString());
                }
            }
        }

        return response.toString().trim();
    }
}