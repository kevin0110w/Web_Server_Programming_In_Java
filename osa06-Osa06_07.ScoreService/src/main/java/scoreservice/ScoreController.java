/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author woohoo
 */
@RestController
public class ScoreController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @PostMapping("/games/{name}/scores")
    public Score createResult(@RequestBody Score score, @PathVariable String name) {
        Game g = gameRepository.findByName(name);
        score.setGame(g);
        return scoreRepository.save(score);
    }

    @GetMapping("/games/{name}/scores")
    public List<Score> getScoresForAGame(@PathVariable String name) {
        Game g = gameRepository.findByName(name);
        return scoreRepository.findByGame(g);
    }

    @GetMapping("/games/{name}/scores/{id}")
    public Score getScoresForAGameById(@PathVariable String name, @PathVariable Long id) {
         Game g = gameRepository.findByName(name);
        return scoreRepository.findByGameAndId(g, id);
        
    }

    @DeleteMapping("/games/{name}/scores/{id}")
    public Score deleteScoreById(@PathVariable String name, @PathVariable Long id) {
        Game g = gameRepository.findByName(name);
        Score s = scoreRepository.findByGameAndId(g, id);
        scoreRepository.delete(s);
        return s;
    }
}
