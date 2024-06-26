package br.com.zellner.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zellner.models.TravelModel;
import br.com.zellner.repositories.TravelRepository;
import jakarta.transaction.Transactional;


@Service
public class TravelService {

    private final TravelRepository viagemRepository;

    public TravelService(TravelRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    public List<TravelModel> findAllTravels() {
        return viagemRepository.findAll();
    }

    @Transactional
    public TravelModel createTravel(Map<String, Object> data) {
        TravelModel viagem = new TravelModel();
        viagem.setDestino((String) data.get("destino"));
        viagem.setLocalizacao((String) data.get("localizacao"));
        viagem.setAvaliacao(0);
        viagem.setQtdAvaliacoes(0);
        return viagemRepository.save(viagem);
    }

    @Transactional
    public TravelModel update(Map<String, Object> data){
        TravelModel viagem = new TravelModel();
        viagem.setDestino((String) data.get("destino"));    
        viagem.setAvaliacao(0);
        viagem.setQtdAvaliacoes(0);
        return viagemRepository.save(viagem);
    }

    @Transactional
    public TravelModel findTravelById(int id){
        return viagemRepository.getById(id);
    }

    @Transactional
    public List<TravelModel> findAllTravelsByFilter(String filtro){
        return viagemRepository.findAllByFilter(filtro);
    }

    @Transactional      
    public TravelModel rateTravel(@RequestBody Map<String, Object> body){
        int id = (int) body.get("id");
        double rating = (double) body.get("rating");
        TravelModel viagem = findTravelById(id);
        int totalRatings = viagem.getQtdAvaliacoes();
        if(totalRatings == 0) {
            viagem.setAvaliacao(rating);
            viagem.setQtdAvaliacoes(1);
        } else {
            double newRating = ((viagem.getAvaliacao() * viagem.getQtdAvaliacoes() + rating) / (viagem.getQtdAvaliacoes()+ 1));
            viagem.setAvaliacao(newRating);
            viagem.setQtdAvaliacoes(viagem.getQtdAvaliacoes() + 1);
        }
        return viagemRepository.save(viagem);
    }

    @Transactional
    public void deleteTravel(int id){
        viagemRepository.deleteById(id);
    }


}