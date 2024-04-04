package com.lucasramalho.passin.repositories;

import com.lucasramalho.passin.domain.checkin.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin, Integer> {

}
