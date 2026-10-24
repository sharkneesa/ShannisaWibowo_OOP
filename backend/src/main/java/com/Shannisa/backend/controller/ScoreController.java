package com.Shannisa.backend.controller;

import com.Shannisa.backend.model.Score;
import com.Shannisa.backend.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// TODO: add an annotation that will mark this class as REST API Controller
@RestController

// TODO: add an annotation to map the API to "api/scores"
@RequestMapping("/api/scores")

@CrossOrigin(origins = "*")
public class ScoreController {

    // TODO: Add an annotation to do Dependency Injection from the existing instance (ScoreService)
    @Autowired

    // TODO: Add a private field for ScoreService
    private ScoreService scoreService;


    // TODO: add an annotation to map HTTP GET to this method with "/{scoreId}" as the path
    @GetMapping("/{scoreId}")
    public ResponseEntity<?> getScoreById(@PathVariable UUID scoreId) {

        // TODO: create a score variable to store the score given by scoreService
        // hint: use Optional data type
        // hint: use getScoreById method from scoreService using the correct parameter
        Optional<Score> score = scoreService.getScoreByID(scoreId);

        // check whether the score variable is present or not using isPresent()
        // if yes, return the posted score
        // if no, return NOT_FOUND status with the matching error body
        if (score.isPresent()) {
            return ResponseEntity.ok(score.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Score not found\"}");
        }
    }


    // POST /api/scores

    // TODO: add an annotation to map HTTP POST to this method
    @PostMapping
    public ResponseEntity<?> createScore(@RequestBody Score score) {

        try {
            // TODO: Create a new score instance using scoreService
            // with the data available from the parameter
            Score newScore = scoreService.createScore(score);

            // TODO: return the new score response data with CREATED status
            return ResponseEntity.status(HttpStatus.CREATED).body(newScore);

        } catch (RuntimeException e) {

            // TODO: return error response with BAD_REQUEST status
            // and a matching error body
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }


    // TODO:
    // 1. Add the appropriate annotation for a GET endpoint
    // along with the appropriate endpoint
    @GetMapping
    public ResponseEntity<List<Score>> getAllScores() {

        // 2. Use scoreService to call getAllScores()
        // and store those scores in a variable using List
        List<Score> score = scoreService.getAllScores();

        // 3. Return the variable containing those scores
        return ResponseEntity.ok(score);
    }


    // TODO:
    // 1. Add the appropriate annotation for a GET endpoint
    // along with the appropriate endpoint
    @GetMapping("/leaderboard")
    public ResponseEntity<List<Score>> getLeaderboardByPoint(

            /* 2. add the '@RequestParam' parameter with defaultValue 10
               3. as well as Integer limit */
            @RequestParam(defaultValue = "10") Integer limit) {

        // 4. Use scoreService to call getLeaderboard()
        // with the appropriate parameter
        // and store those scores in a variable using List
        List<Score> score = scoreService.getLeaderboard(limit);

        // 5. Return the variable containing those scores
        return ResponseEntity.ok(score);
    }


    // TODO:
    // 1. Add the appropriate annotation for a GET endpoint
    // along with the appropriate endpoint
    @GetMapping("/above/{minValue}")
    public ResponseEntity<List<Score>> getScoresAboveValue(

            /* 2. add '@PathVariable' for Integer minValue */
            @PathVariable Integer minValue) {

        // 3. Use scoreService to call getScoreAboveValue()
        // with the appropriate parameter
        // and store those scores in a variable using List
        List<Score> score = scoreService.getScoreAboveValue(minValue);

        // 4. Return the variable containing those scores
        return ResponseEntity.ok(score);
    }


    // TODO:
    // 1. Add the appropriate annotation for a GET endpoint
    // along with the appropriate endpoint
    @GetMapping("/recent")
    public ResponseEntity<List<Score>> getRecentScores() {

        // 2. Use scoreService to call getRecentScores()
        // and store those scores in a variable using List
        List<Score> score = scoreService.getRecentScores();

        // 3. Return the variable containing those scores
        return ResponseEntity.ok(score);
    }


    // TODO:
    // 1. Add the appropriate annotation for a DELETE endpoint
    // along with the appropriate endpoint
    @DeleteMapping("/{scoreId}")
    public ResponseEntity<?> deleteScore(

            /* 2. add '@PathVariable' for scoreId */
            @PathVariable UUID scoreId) {

        // 3. create a try-catch block
        // in the try block:
        // use scoreService to call deleteScore() with the appropriate parameter
        // return a response indicating the score was successfully deleted
        // in the catch block:
        // return an error response with status NOT_FOUND
        // along with an appropriate error body

        try {
            scoreService.deleteScore(scoreId);

            return ResponseEntity.ok(
                    "{\"message\": \"Score successfully deleted\"}"
            );

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}

