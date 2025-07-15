package com.agstechcloud.birthdayjob.repository;

import com.agstechcloud.birthdayjob.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findBySendDateAndStatus(LocalDate sendDate, Wish.Status status);
}

