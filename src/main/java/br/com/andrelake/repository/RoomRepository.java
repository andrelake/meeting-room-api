package br.com.andrelake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.andrelake.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

	
}
