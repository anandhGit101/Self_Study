/**
 * 
 */
package com.springboot.justbook.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.justbook.management.domain.Seats;

/**
 * @author M1006601
 *
 */
public interface SeatsRepository extends JpaRepository<Seats, Long> {

}
