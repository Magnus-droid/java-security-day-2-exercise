package com.booleanuk.library.controllers;

import com.booleanuk.library.models.DVD;
import com.booleanuk.library.models.VideoGame;
import com.booleanuk.library.payload.response.*;
import com.booleanuk.library.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("videoGames")
public class VideoGameController {

    @Autowired
    private VideoGameRepository videoGameRepository;

    @GetMapping
    public ResponseEntity<VideoGameListResponse> getAllVideoGames() {
        VideoGameListResponse videoGameListResponse = new VideoGameListResponse();
        videoGameListResponse.set(this.videoGameRepository.findAll());
        return ResponseEntity.ok(videoGameListResponse);
    }

    @PostMapping
    public ResponseEntity<Response<?>> createVideoGame(@RequestBody VideoGame videoGame) {
        VideoGameResponse videoGameResponse = new VideoGameResponse();
        try {
            videoGameResponse.set(this.videoGameRepository.save(videoGame));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(videoGameResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getVideoGameById(@PathVariable int id) {
        VideoGame videoGame = this.videoGameRepository.findById(id).orElse(null);
        if (videoGame == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        VideoGameResponse videoGameResponse = new VideoGameResponse();
        videoGameResponse.set(videoGame);
        return ResponseEntity.ok(videoGameResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> updateVideoGame(@PathVariable int id, @RequestBody VideoGame videogame) {
        VideoGame toUpdate = this.videoGameRepository.findById(id).orElse(null);
        if (toUpdate == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        toUpdate.setName(videogame.getName());
        toUpdate.setPublisher(videogame.getPublisher());
        toUpdate.setYear(videogame.getYear());
        toUpdate.setGenre(videogame.getGenre());

        try {
            toUpdate = this.videoGameRepository.save(toUpdate);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        VideoGameResponse videoGameResponse = new VideoGameResponse();
        videoGameResponse.set(toUpdate);
        return new ResponseEntity<>(videoGameResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteVideoGame(@PathVariable int id) {
        VideoGame toDelete = this.videoGameRepository.findById(id).orElse(null);
        if (toDelete == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        this.videoGameRepository.delete(toDelete);
        VideoGameResponse videoGameResponse = new VideoGameResponse();
        videoGameResponse.set(toDelete);
        return ResponseEntity.ok(videoGameResponse);
    }
}
