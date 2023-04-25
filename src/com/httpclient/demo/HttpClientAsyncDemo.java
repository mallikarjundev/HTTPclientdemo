package com.httpclient.demo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HttpClientAsyncDemo {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(new URI("https://fakerestapi.azurewebsites.net//api/v1/Activities")).GET().build();

        request = HttpRequest.newBuilder(new URI("https://fakerestapi.azurewebsites.net//api/v1/Activities")).POST(HttpRequest.BodyPublishers.ofString("{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"string\",\n" +
                "  \"dueDate\": \"2023-04-25T01:29:06.141Z\",\n" +
                "  \"completed\": true\n" +
                "}")).header("Content-Type","application/json").build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String body = response.thenApply(HttpResponse::body).get();
        System.out.println(body);

    }
}
