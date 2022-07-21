package me.coldrain.ninetyminute.service;

import lombok.RequiredArgsConstructor;
import me.coldrain.ninetyminute.dto.response.HistoryDetailResponse;
import me.coldrain.ninetyminute.dto.response.HistoryResponse;
import me.coldrain.ninetyminute.entity.*;
import me.coldrain.ninetyminute.repository.FieldMemberRepository;
import me.coldrain.ninetyminute.repository.HistoryRepository;
import me.coldrain.ninetyminute.repository.ScorerRepository;
import me.coldrain.ninetyminute.repository.SubstituteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ScorerRepository scorerRepository;
    private final FieldMemberRepository fieldMemberRepository;
    private final SubstituteRepository substituteRepository;

    public List<HistoryResponse> searchMatchHistory(Long teamId, Member member) {
        List<HistoryResponse> historyResponseList = new ArrayList<>();
        List<History> homeHistoryList = historyRepository.findAllByHomeTeamId(teamId);
        for (History homeHistory : homeHistoryList) {
            HistoryResponse.TeamResponse team = new HistoryResponse.TeamResponse(
                    homeHistory.getBeforeMatching().getTeamName(),
                    homeHistory.getAfterMatching().getResult(),
                    homeHistory.getAfterMatching().getScore()
            );
            HistoryResponse.TeamResponse opposingTeam = new HistoryResponse.TeamResponse(
                    homeHistory.getBeforeMatching().getOpposingTeamName(),
                    homeHistory.getAfterMatching().getOpponentResult(),
                    homeHistory.getAfterMatching().getOpponentScore()
            );
            HistoryResponse historyResponse = new HistoryResponse(homeHistory.getId(), homeHistory.getBeforeMatching().getMatchDate(), team, opposingTeam);
            historyResponseList.add(historyResponse);
        }

        List<History> awayHistoryList = historyRepository.findAllByAwayTeamId(teamId);
        for (History awayHistory : awayHistoryList) {
            HistoryResponse.TeamResponse team = new HistoryResponse.TeamResponse(
                    awayHistory.getBeforeMatching().getOpposingTeamName(),
                    awayHistory.getAfterMatching().getOpponentResult(),
                    awayHistory.getAfterMatching().getOpponentScore()
            );
            HistoryResponse.TeamResponse opposingTeam = new HistoryResponse.TeamResponse(
                    awayHistory.getBeforeMatching().getTeamName(),
                    awayHistory.getAfterMatching().getResult(),
                    awayHistory.getAfterMatching().getScore()
            );
            HistoryResponse historyResponse = new HistoryResponse(awayHistory.getId(), awayHistory.getBeforeMatching().getMatchDate(), team, opposingTeam);
            historyResponseList.add(historyResponse);
        }
        return historyResponseList;
    }

    public HistoryDetailResponse searchMatchHistoryDetail(Long teamId, Long historyId, Member member) {
        History history = historyRepository.findById(historyId).orElseThrow(() -> new IllegalArgumentException("히스토리가 존재하지 않습니다."));

        // param 인 teamId 가 HomeTeam
        List<Scorer> homeScorerList;
        List<FieldMember> teamFieldMembers = fieldMemberRepository.findAllByMatchFieldMembers(teamId, history.getBeforeMatching().getId());
        List<SubstituteMember> teamSubMembers = substituteRepository.findAllByMatchSubstituteMembersAndAnonymousFalse(teamId, history.getAfterMatching().getId());
        HistoryDetailResponse historyDetailResponse = new HistoryDetailResponse(history.getId(), history.getBeforeMatching().getMatchDate(), null, null);
        HistoryDetailResponse.TeamResponse homeTeamResponse;
        HistoryDetailResponse.TeamResponse awayTeamResponse;
        HistoryDetailResponse.TeamResponse.MemberResponse memberResponse = new HistoryDetailResponse.TeamResponse.MemberResponse(null, null);

        if (teamId.equals(history.getBeforeMatching().getApply().getTeam().getId())) {
            // home team
            homeTeamResponse = HistoryDetailResponse.TeamResponse.builder()
                    .mvp(history.getAfterMatching().getMvpNickname())
                    .name(history.getBeforeMatching().getTeamName())
                    .record(history.getAfterMatching().getResult())
                    .score(history.getAfterMatching().getScore())
                    .scorer(null)
                    .fieldMembers(null)
                    .substituteMembers(null)
                    .build();

            homeScorerList = scorerRepository.findAllByAfterMatchingIdAndTeamId(history.getAfterMatching().getId(), teamId);
            homeTeamResponse.setScorer(homeScorerList);
            homeTeamResponse.setFieldMembers(teamFieldMembers, memberResponse);
            homeTeamResponse.setSubstituteMembers(teamSubMembers, memberResponse);

            // away team
            List<FieldMember> awayTeamFieldMembers = fieldMemberRepository.findAllByMatchFieldMembers(history.getBeforeMatching().getApply().getApplyTeam().getId(), history.getBeforeMatching().getId());
            List<SubstituteMember> awayTeamSubMembers = substituteRepository.findAllByAllMatchSubstituteMembers(history.getBeforeMatching().getApply().getApplyTeam().getId(), history.getAfterMatching().getId());

            awayTeamResponse = HistoryDetailResponse.TeamResponse.builder()
                    .mvp(history.getAfterMatching().getBeforeMatching().getApply().getApplyTeam().getHistory().getAfterMatching().getMvpNickname())
                    .name(history.getBeforeMatching().getOpposingTeamName())
                    .record(history.getAfterMatching().getOpponentResult())
                    .score(history.getAfterMatching().getOpponentScore())
                    .scorer(null)
                    .fieldMembers(null)
                    .substituteMembers(null)
                    .build();

            List<Scorer> awayScorerList = scorerRepository.findAllByAfterMatchingIdAndApplyTeamId(history.getAfterMatching().getId(), history.getBeforeMatching().getApply().getApplyTeam().getId());
            awayTeamResponse.setScorer(awayScorerList);
            awayTeamResponse.setFieldMembers(awayTeamFieldMembers, memberResponse);
            awayTeamResponse.setSubstituteMembers(awayTeamSubMembers, memberResponse);

            historyDetailResponse.addTeams(homeTeamResponse, awayTeamResponse);
        } else { // param 인 teamId 가 AwayTeam 즉, 히스토리 상세에서 away team 기준으로 보여야한다.
            // home team
            homeTeamResponse = HistoryDetailResponse.TeamResponse.builder()
                    .mvp(history.getAfterMatching().getBeforeMatching().getApply().getApplyTeam().getHistory().getAfterMatching().getMvpNickname())
                    .name(history.getBeforeMatching().getOpposingTeamName())
                    .record(history.getAfterMatching().getOpponentResult())
                    .score(history.getAfterMatching().getOpponentScore())
                    .scorer(null)
                    .fieldMembers(null)
                    .substituteMembers(null)
                    .build();

            homeScorerList = scorerRepository.findAllByAfterMatchingIdAndApplyTeamId(history.getAfterMatching().getId(), history.getBeforeMatching().getApply().getApplyTeam().getId());
            homeTeamResponse.setScorer(homeScorerList);
            homeTeamResponse.setFieldMembers(teamFieldMembers, memberResponse);
            homeTeamResponse.setSubstituteMembers(teamSubMembers, memberResponse);

            // away team
            List<FieldMember> awayTeamFieldMembers = fieldMemberRepository.findAllByMatchFieldMembers(history.getBeforeMatching().getApply().getApplyTeam().getId(), history.getBeforeMatching().getId());
            List<SubstituteMember> awayTeamSubMembers = substituteRepository.findAllByAllMatchSubstituteMembers(history.getBeforeMatching().getApply().getApplyTeam().getId(), history.getAfterMatching().getId());

            awayTeamResponse = HistoryDetailResponse.TeamResponse.builder()
                    .mvp(history.getAfterMatching().getMvpNickname())
                    .name(history.getBeforeMatching().getTeamName())
                    .record(history.getAfterMatching().getResult())
                    .score(history.getAfterMatching().getScore())
                    .scorer(null)
                    .fieldMembers(null)
                    .substituteMembers(null)
                    .build();

            List<Scorer> awayScorerList = scorerRepository.findAllByAfterMatchingIdAndTeamId(history.getAfterMatching().getId(), teamId);
            awayTeamResponse.setScorer(awayScorerList);
            awayTeamResponse.setFieldMembers(awayTeamFieldMembers, memberResponse);
            awayTeamResponse.setSubstituteMembers(awayTeamSubMembers, memberResponse);

            historyDetailResponse.addTeams(homeTeamResponse, awayTeamResponse);
        }
        return historyDetailResponse;
    }
}