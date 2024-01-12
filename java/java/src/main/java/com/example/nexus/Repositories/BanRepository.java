package com.example.nexus.Repositories;


import com.example.nexus.entities.Ban;
import com.example.nexus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanRepository extends JpaRepository<Ban, Long> {
    Ban findByUser(User u);
}
