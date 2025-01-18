package com.example.gdgoc_2025_whitesheepserver.service;

import com.example.gdgoc_2025_whitesheepserver.JPARepository.CorrectRepository;
import com.example.gdgoc_2025_whitesheepserver.JPARepository.BoardRepository;
import com.example.gdgoc_2025_whitesheepserver.JPARepository.MissionRepository;
import com.example.gdgoc_2025_whitesheepserver.JPARepository.UserInfoRepository;
import com.example.gdgoc_2025_whitesheepserver.dto.GetBoardMissionResponseDto;
import com.example.gdgoc_2025_whitesheepserver.dto.GetBoardsResponseDto;
import com.example.gdgoc_2025_whitesheepserver.dto.PostBoardRequestDto;
import com.example.gdgoc_2025_whitesheepserver.entity.Correct;
import com.example.gdgoc_2025_whitesheepserver.entity.Board;
import com.example.gdgoc_2025_whitesheepserver.entity.Mission;
import com.example.gdgoc_2025_whitesheepserver.entity.UserInfo;
import com.example.gdgoc_2025_whitesheepserver.global.dto.QuestionDto;
import com.example.gdgoc_2025_whitesheepserver.global.enums.LevelType;
import com.example.gdgoc_2025_whitesheepserver.global.service.ChatgptService;
import com.example.gdgoc_2025_whitesheepserver.global.service.YoutubeService;
import java.net.URL;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MissionRepository missionRepository;
    private final UserInfoRepository userInfoRepository;
    private final CorrectRepository correctRepository;
    private final ChatgptService chatgptService;
    private final YoutubeService youtubeService;

    @Transactional(readOnly = true)
    public GetBoardsResponseDto getBoards(String keyword) {
        List<Board> boards = boardRepository.findByKeyword(keyword);
        return new GetBoardsResponseDto(
                boards.stream().map(it -> new GetBoardsResponseDto.GetBoardsBoardResponseDto(
                        it.getId(),
                        it.getUrl(),
                        it.getName()
                )).toList()
        );
    }

    @Transactional
    public GetBoardMissionResponseDto getMission(Long boardId, Integer level) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        LevelType levelType = LevelType.getByLevel(level);
        QuestionDto question;
        if (board.getUrl().contains("youtube.com")) {
            String videoId = getVideoId(board.getUrl());
            String subtitle = youtubeService.getVideoSubtitle(videoId);
            question = chatgptService.getQuestionByText(subtitle, levelType);
        } else {
            question = chatgptService.getQuestionByUrl(board.getUrl(), levelType);
        }
        Mission mission = missionRepository.save(new Mission(question.getCorrect_answer(), level));
        return new GetBoardMissionResponseDto(
                mission.getId(),
                board.getName(),
                question.getQuestion(),
                question.getOptions(),
                question.getOptions().get(Integer.parseInt(question.getCorrect_answer()) - 1)
        );
    }

    private String getVideoId(String url) {
        try {
            String key = "v";
            URL parsedUrl = new URL(url);
            String query = parsedUrl.getQuery();

            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                if (pair.length == 2 && pair[0].equals(key)) {
                    return pair[1];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public void submitMission(Long missionId, String answer, String id) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        UserInfo user = userInfoRepository.findAll().getFirst();
        if (mission.getAnswer().equals(answer)) {
            correctRepository.save(new Correct(mission.getCorrectType(), user.getId()));
        }
    }

    @Transactional
    public void postBoard(PostBoardRequestDto requestDto) {
        Board board = new Board(requestDto.url(), requestDto.keyword(), requestDto.name());
        boardRepository.save(board);
    }
}
