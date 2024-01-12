package com.example.nexus.SErvices.Implimentations;

import com.example.nexus.Repositories.TeamRepo;
import com.example.nexus.Repositories.UserRepo;
import com.example.nexus.config.MailConfiguration;
import com.example.nexus.entities.Team;
import com.example.nexus.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    TeamRepo teamRepo;



    public Integer AjouterUser(User user) {
        userRepo.save(user);
        return user.getId();
    }

    public boolean signup(User u){
        User user1 = userRepo.findByUsername(u.getUsername()).orElse(null);
        User user2 = userRepo.findUserByEmail(u.getUsername()).orElse(null);
        if (user1 != null || user2 != null){
            return false;
        }else{
            userRepo.save(u);
            return true;
        }
    }
    public User assignuserToTeam(int idTeam, int idUser) {
        User user = userRepo.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
        Team team = teamRepo.findById(idTeam).orElseThrow(() -> new IllegalArgumentException("Invalid team id"));
        user.setTeam(team);
        return userRepo.save(user);
    }
    @Autowired
    MailConfiguration mailConfiguration;

    public void sendEmailToUser(User user) {

        CompletableFuture.runAsync(() -> {

            try {

                MimeMessage message = mailConfiguration.getJavaMailSender().createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setSubject("Affectation à une équipe");
                helper.setText("Bonjour " + user.getUsername() + ",\n\nVous avez été affecté à l'équipe " +
                        user.getTeam().getTeamname() + ".\n\nCordialement,\nL'équipe Nexus");


                if(user.getEmail() !=null){
                    helper.setTo(user.getEmail());
                    mailConfiguration.sendEmail(message);
                }

            } catch (Exception e) {
                log.error(e.getMessage());
            }


        });
}}
