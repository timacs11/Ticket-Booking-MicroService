package com.ticketbooking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketBookingController {

	@Autowired
	private TicketBookingDao service;
	
	@PostMapping("/movies")
	public ResponseEntity<Object> addMovie(@RequestBody Movie movie){
		Movie addedMovie = service.save(movie);
		return new ResponseEntity<>(addedMovie,HttpStatus.CREATED);
	}
	
	@GetMapping("/movies")
	public List<Movie> getMovies(){
		return service.findAll();
	}
	
	@PostMapping("/ticket/book")
	public ResponseEntity<Object> bookTicket(@RequestBody List<Integer> seats){
		seats.forEach(s->{
			if(s<1||s>100) throw new InvalidSeatException("One of the input seats is not valid");
		});
		
		List<Integer> bookedSeat = service.bookTicket(seats);
		String message = "";
		if(bookedSeat.size() == 0){
			message = "Seat number "+seats+" already occupied, Please book another seat!";
		}else{
			message = bookedSeat.size()+" ticket booked successfully out of "+ seats.size()+", booked seatNo :"+bookedSeat;
		}
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
}
