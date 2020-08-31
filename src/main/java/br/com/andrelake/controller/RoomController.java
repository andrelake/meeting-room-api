package br.com.andrelake.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrelake.exception.ResourceNotFoundException;
import br.com.andrelake.model.Room;
import br.com.andrelake.repository.RoomRepository;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

	@Autowired
	private RoomRepository repository;
	
	@GetMapping("/rooms")
	public List<Room> getAllRooms() {
		
		return repository.findAll();
	}
	
	@GetMapping("/rooms/{roomId}")
	public ResponseEntity<Room> getRoomById(@PathVariable long roomId) throws ResourceNotFoundException {
		
		Room room = repository.findById(roomId)
						.orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
		
		return ResponseEntity.ok(room);
	}
	
	@PostMapping("/rooms")
	public Room createRoom(@Valid @RequestBody Room room) {
		
		return repository.save(room);
	}
	
	@PutMapping("/rooms/{roomId}")
	public ResponseEntity<Room> updateRoom(@PathVariable Long roomId, @Valid @RequestBody Room room) throws ResourceNotFoundException {
		
		Room originRoom = repository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found. Id: " + roomId));
		
		originRoom.setName(room.getName());
		originRoom.setDate(room.getDate());
		originRoom.setStartHour(room.getStartHour());
		originRoom.setEndHour(room.getEndHour());
		
		Room updatedRoom = repository.save(originRoom);
		return ResponseEntity.ok(updatedRoom);
	}
	
	@DeleteMapping("/rooms/{roomId}")
	public Map<String, Boolean> deleteRoom(@PathVariable Long roomId) throws ResourceNotFoundException {
		
		Room room = repository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found. Id: " + roomId));
		
		repository.delete(room);
		
		Map<String, Boolean> res = new HashMap<>();
		res.put("deleted", Boolean.TRUE);
		return res;
	}
}
