package br.com.zellner.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zellner.models.TravelModel;
import br.com.zellner.services.TravelService;

@RestController
@RequestMapping("/viagens")
public class TravelController {

 private final TravelService viagemService;

    public TravelController(TravelService viagemService) {
        this.viagemService = viagemService;
    }

	@PostMapping
	public ResponseEntity<TravelModel> createTravel(@RequestBody Map<String, Object> body) {
		return ResponseEntity.status(HttpStatus.CREATED).body(viagemService.createTravel(body));
	}

    @GetMapping
    public ResponseEntity<List<TravelModel>> findAllTravels() {
        return ResponseEntity.status(HttpStatus.OK).body(viagemService.findAllTravels());
    }

	@GetMapping("/filtro/{filter}")
    public ResponseEntity<List<TravelModel	>> findAllTravelsByFilter(@PathVariable String filter) {
        System.out.println(filter);
        return ResponseEntity.status(HttpStatus.OK).body(viagemService.findAllTravelsByFilter(filter));
    }

	@GetMapping("/id/{id}")	
    public ResponseEntity<TravelModel> findTravelById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(viagemService.findTravelById(id));
    }

	@PostMapping(path = "/rate")
    public ResponseEntity<TravelModel> rateTravel(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.OK).body(viagemService.rateTravel(body));
    }

	@DeleteMapping("/id/{id}")	
    public ResponseEntity<String> deleteTravel(@PathVariable int id) {
		viagemService.deleteTravel(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
    }
}