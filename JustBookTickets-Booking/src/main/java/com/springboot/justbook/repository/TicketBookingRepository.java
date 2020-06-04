/**
 * 
 */
package com.springboot.justbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.justbook.domain.BookingDetails;

/**
 * @author M1006601
 *
 */
@Repository
public interface TicketBookingRepository extends JpaRepository<BookingDetails, Long>{

	@Query(nativeQuery=true, value="Select * from booking_details where booking_number=:bookingNumber")
	BookingDetails findByBookingNumber(String bookingNumber);
	
	@Query(nativeQuery=true, value="Select * from booking_details where booked_user=:userName")
	List<BookingDetails> findByUserName(String userName);
	
	@Query(nativeQuery=true, value="Select * from booking_details where booked_user_contactno=:userPhoneNumber")
	List<BookingDetails> findByUserPhoneNumber(String userPhoneNumber);
	
	@Query(nativeQuery=true, value="Select * from booking_details where booked_movie_title=:movieName")
	List<BookingDetails> findByShowMovieName(String movieName);
}
