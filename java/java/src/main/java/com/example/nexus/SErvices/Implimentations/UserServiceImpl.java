package com.example.nexus.SErvices.Implimentations;

import com.example.nexus.Repositories.BanRepository;
import com.example.nexus.Repositories.UserRepo;
import com.example.nexus.entities.User;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl {
    @Autowired
    UserRepo userRepository;
    @Autowired
    private BanRepository banRepository;


    public void deleteUser(Integer id) {
        userRepository.deleteById(id);


    }


    public List<User> getAll() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }


    public Optional<User> findById(Integer id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id);
    }


	public User addUser(User u) {
		// TODO Auto-generated method stub
		return userRepository.save(u);
	}


	public User updateUser(User u, Integer id) {
		// TODO Auto-generated method stub
		u.setId(id);
		return userRepository.save(u);
	}




    public String checkBan(User user) {


        if (user != null && user.getBan() != null && user.getBan().getExpiryTime() != null &&
                LocalDateTime.now().isBefore(user.getBan().getExpiryTime())) {
            // User is banned, return error response
            Duration remainingTime = Duration.between(LocalDateTime.now(), user.getBan().getExpiryTime());
            String timeLeft = String.format("%d minutes, %d seconds", remainingTime.toMinutes(), remainingTime.getSeconds() % 60);
            return timeLeft;

        }
        return "a";
    }

}