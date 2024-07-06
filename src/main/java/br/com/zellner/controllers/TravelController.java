package br.com.zellner.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zellner.models.TravelModel;
import br.com.zellner.repositories.UserRepository;
import br.com.zellner.services.TravelService;
import br.com.zellner.services.UserDetailServiceImpl;

@RestController
@RequestMapping("/viagens")
public class TravelController {

    private final TravelService viagemService;
    private final UserRepository userRepository;

    public TravelController(TravelService viagemService, UserRepository userRepository) {
        this.viagemService = viagemService;
        this.userRepository = userRepository;
    }

	@PostMapping
	public ResponseEntity<TravelModel> createTravel(@RequestBody Map<String, Object> body) {
		return ResponseEntity.status(HttpStatus.CREATED).body(viagemService.createTravel(body));
	}

    @GetMapping
    public ResponseEntity<List<TravelModel>> findAllTravels() {
        UserDetailsService userDetailsService = new UserDetailServiceImpl(userRepository);
        UserDetails details = userDetailsService.loadUserByUsername("read");
        System.out.println(details.getUsername());
        System.out.println(details.getAuthorities());
        System.out.println(details.getPassword());
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities(); 
        // (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        // System.out.println(authorities.toArray().toString());
        authorities.forEach(authority -> System.out.println(authority.toString()));
        return ResponseEntity.status(HttpStatus.OK).body(viagemService.findAllTravels());
    }

	@GetMapping("/filtro/{filter}")
    public ResponseEntity<List<TravelModel	>> findAllTravelsByFilter(@PathVariable String filter) {
        System.out.println(filter);
        return ResponseEntity.status(HttpStatus.OK).body(viagemService.findAllTravelsByFilter(filter));
    }

	@GetMapping("/id/{id}")	
    public ResponseEntity<TravelModel> findTravelById(@PathVariable int id) {
        UserDetailsService userDetailsService = new UserDetailServiceImpl(userRepository);
        UserDetails details = userDetailsService.loadUserByUsername("mike");
        System.out.println(details.getUsername());
        System.out.println(details.getAuthorities());
        System.out.println(details.getPassword());
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