package nextstep.ladder;

import nextstep.ladder.domain.*;
import nextstep.ladder.view.Input;
import nextstep.ladder.view.Output;
import java.util.List;

public class Main {

    private static final String ALL_PARTICIPANTS = "all";
    private static final int DIFF_BETWEEN_PLAYER_AND_POINT_CNT = 1;

    public static void main(String[] args) {
        try {
            List<Player> players =  Input.readPlayerNames();
            List<LadderResult> ladderResults = Input.readResults();
            int maxLadderLength = Input.readMaxLadderLength();

            Ladder ladder = Ladder.of(maxLadderLength, players.size()-DIFF_BETWEEN_PLAYER_AND_POINT_CNT, new RandomPointCreationRule());
            Output.printLadderResult(players, ladder, ladderResults);

            GameResults gameResults =  GameResults.of(players, ladderResults, ladder);

            while(true){
                String command = Input.readCommandForResult();
                playTheGame(gameResults, command);
            }
        }catch (Exception exception){
            System.out.println(String.join("\n", "error", exception.getMessage()));
        }
    }

    private static void playTheGame(GameResults gameResults, String command) {
        if(command.equals(ALL_PARTICIPANTS)){
            Output.printAllGameResults(gameResults.getResults());
            return;
        }
        Output.printGameResultByPlayerName(gameResults.getResultByName(command));
    }
}