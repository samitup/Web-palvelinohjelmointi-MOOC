package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    GameRepository gameRepository;

    @PostMapping("/games/{name}/scores")
    public Score postScore(@PathVariable String name, @RequestBody Score score) {
        Game game = gameRepository.findByName(name);
        score.setGame(game);
        return scoreRepository.save(score);
    }

    @GetMapping("/games/{name}/scores")
    public List<Score> getScores(@PathVariable String name) {
        Game game = gameRepository.findByName(name);
        return scoreRepository.findByGame(game);
    }

    @GetMapping("/games/{name}/scores/{id}")
    public Score getScore(@PathVariable String name, @PathVariable long id) {
        Game game = gameRepository.findByName(name);
        return scoreRepository.findByGameAndId(game, id);
    }

    @DeleteMapping("/games/{name}/scores/{id}")
    public Score deleteScore(@PathVariable String name, @PathVariable long id) {
        Game game = gameRepository.findByName(name);
        Score score = scoreRepository.findByGameAndId(game, id);
        scoreRepository.delete(score);
        return score;
    }
}
