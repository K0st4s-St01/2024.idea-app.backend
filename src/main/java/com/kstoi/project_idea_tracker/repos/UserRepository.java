package com.kstoi.project_idea_tracker.repos;

import com.kstoi.project_idea_tracker.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}