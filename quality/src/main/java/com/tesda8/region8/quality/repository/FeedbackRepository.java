package com.tesda8.region8.quality.repository;

import com.tesda8.region8.quality.model.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>, QuerydslPredicateExecutor<Feedback> {
}
