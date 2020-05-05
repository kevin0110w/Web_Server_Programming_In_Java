/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author woohoo
 */
@Service
public class VoteService {
    
    @Autowired
    private VoteRepository voteRepository;
    
    public Vote findByJokeId(@PathVariable Long id) {
        return this.voteRepository.findByJokeId(id);
    }
    
    public void saveVote(Vote vote) {
        this.voteRepository.save(vote);
    }

    public Vote processVote(Vote vote, String value) {
         if ("up".equals(value)) {
            vote.setUpVotes(vote.getUpVotes() + 1);
        } else if ("down".equals(value)) {
            vote.setDownVotes(vote.getDownVotes() + 1);
        }
         return vote;
    }
}
