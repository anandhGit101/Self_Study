/**
 * 
 */
package com.springboot.justbook.management.service;

import com.springboot.justbook.management.exception.SeatsMgmtException;
import com.springboot.justbook.vo.MovieMgmtSeatsVOResponseObject;

/**
 * @author M1006601
 *
 */
public interface SeatsService {

	MovieMgmtSeatsVOResponseObject getSeats() throws SeatsMgmtException;

}
