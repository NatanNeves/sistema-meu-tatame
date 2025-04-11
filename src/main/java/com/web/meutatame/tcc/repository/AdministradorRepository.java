package com.web.meutatame.tcc.repository;

import com.web.meutatame.tcc.domain.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    @Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END  from administradores where id = :id", nativeQuery = true)
    public boolean exist(int id);

    @Query(value="select * from administradores where email = :email and senha = :senha", nativeQuery = true)
    public Administrador Login(String email, String senha);
}
