package com.booleanuk.library.repository;

import com.booleanuk.library.models.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoGameRepository extends JpaRepository<VideoGame, Integer> {
}
