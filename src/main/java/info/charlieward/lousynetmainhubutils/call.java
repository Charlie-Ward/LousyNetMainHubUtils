package info.charlieward.lousynetmainhubutils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class call {
    public static void main(String[] args) {

        String type = "playerCount";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.mcsrvstat.us/3/194.163.179.210:25566"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (type.equals("playerCount")) {
            String[] split = response.body().split("online", 2);
            String[] split2 = split[1].split(":", 2);

            if (split2[1].contains("max")) {
                String[] split3 = split2[1].split(",", 2);
                System.out.println(split3[0]);
            } else {
                System.out.println("Server Offline");
            }
        }

    }

}


//https://api.mcsrvstat.us/3/194.163.179.210:25566