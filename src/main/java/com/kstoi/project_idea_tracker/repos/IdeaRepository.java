package com.kstoi.project_idea_tracker.repos;

import com.kstoi.project_idea_tracker.models.entities.Idea;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
    List<Idea> findByTitle(String title, Pageable pageable);

    long countByTitle(String title);

    Idea findByTitleLike(String title);

    List<Idea> findByTitleLikeIgnoreCase(String title, Pageable pageable);

    @Query("select i from Idea i where upper(i.title) like upper(?1)")
    List<Idea> search(String title, Pageable pageable);
}