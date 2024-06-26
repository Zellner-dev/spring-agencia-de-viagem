package br.com.zellner.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.zellner.models.TravelModel;

public interface  TravelRepository extends JpaRepository<TravelModel, UUID> {

    @Query(value = "SELECT * FROM VIAGENS WHERE DESTINO LIKE %:filtro% OR LOCALIZACAO LIKE %:filtro% ", nativeQuery = true)
    List<TravelModel> findAllByFilter(@Param("filtro") String filter);

    @Query(value = "SELECT * FROM VIAGENS WHERE ID = :id", nativeQuery = true)
    TravelModel getById(@Param("id") int id);
    
    @Modifying
    @Query(value = "DELETE FROM VIAGENS WHERE ID = :id", nativeQuery = true)
    void deleteById(@Param("id")int id); 
}

