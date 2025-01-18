package com.example.gdgoc_2025_whitesheepserver.global.service;

import io.github.thoroldvix.api.Transcript;
import io.github.thoroldvix.api.TranscriptList;
import io.github.thoroldvix.api.TranscriptRetrievalException;
import io.github.thoroldvix.api.YoutubeTranscriptApi;
import io.github.thoroldvix.internal.TranscriptApiFactory;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YoutubeService {

    public String getVideoSubtitle(String videoId) {
        try {
            YoutubeTranscriptApi youtubeTranscriptApi = TranscriptApiFactory.createDefault();
            TranscriptList transcriptList = youtubeTranscriptApi.listTranscripts(videoId);

            Transcript automaticallyGeneratedTranscript = transcriptList.findGeneratedTranscript("ko");
            String apiUrl = automaticallyGeneratedTranscript.getApiUrl();
            Document doc = Jsoup.connect(apiUrl).get();

            Elements textElements = doc.getElementsByTag("text");

            StringBuilder fullText = new StringBuilder();

            for (Element textElement : textElements) {
                fullText.append(textElement.text()).append(" ");
            }
            return fullText.toString().trim();
        } catch (TranscriptRetrievalException transcriptRetrievalException) {
            transcriptRetrievalException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return "error. please check";
    }
}
