package com.ticketbooking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TicketBookingDao {

	private static List<Movie> movies = new ArrayList<Movie>();
	private static long[] seatArray = new long[100];
	public Movie save(Movie movie){
		movies.add(movie);
		return movie;
	}
	public List<Movie> findAll(){
		return movies;
	}
	
	public List<Integer> bookTicket(List<Integer> seats){
		List<Integer> bookedTicket = new ArrayList<Integer>();
		
		synchronized(this){
			for(int i=0;i<seats.size();i++){
				if(seatArray[seats.get(i)-1] !=0){
					long timeElapsedInSecond = (System.currentTimeMillis()-seatArray[seats.get(i)-1])/(1000);
					if(timeElapsedInSecond>120){
						seatArray[seats.get(i)-1] = System.currentTimeMillis();
						bookedTicket.add(seats.get(i));
					}
					
				}else{
					seatArray[seats.get(i)-1] = System.currentTimeMillis();
					bookedTicket.add(seats.get(i));
				}
			}
		}	
		return bookedTicket;
	}
}
