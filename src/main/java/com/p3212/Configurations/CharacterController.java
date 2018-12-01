package com.p3212.Configurations;

import com.p3212.EntityClasses.*;
import com.p3212.EntityClasses.Character;
import com.p3212.Services.*;
import java.util.List;
import com.p3212.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    @Autowired
    AppearanceService appearanceServ;

    @Autowired
    CharacterService charServ;

    @Autowired
    RoleRepository roleRep;

    @Autowired
    UserService userServ;

    @Autowired
    StatsService statsServ;

    @RequestMapping("/")
    public String greeting() {
        return "start page";
    }
    
    @GetMapping("/profile")
    public ResponseEntity<String> myAccount() {
        try {
            String response = userServ.getUser(SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()).toString();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.getMessage());
        }
    }

    @PostMapping("/profile/character/appearance")
    public ResponseEntity<String> addAppearance(@RequestBody Appearance appear) {
        try {
            User user = userServ.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            Character ch = user.getCharacter();
            int id = ch.getAppearance().getId();
            appear.setId(id);
            appearanceServ.addAppearance(appear);
            ch.setAppearance(appear);
            charServ.addCharacter(ch);
            return ResponseEntity.status(HttpStatus.OK).body("Appearance is created.");
            
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.getMessage());
        }
    }

    @GetMapping("/admin/characters")
    public ResponseEntity<?> getAllCharacters() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(charServ.getAllCharacters());
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @PostMapping("/profile/character")
    public ResponseEntity<String> updateCharacter(@RequestBody String quality) {
        try { 
            User us = userServ.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (us.getStats().getUpgradePoints() == 0)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User doesn't have upgrade points.");
            Character ch = us.getCharacter();
            switch (quality) {
                case "damage" : {
                    ch.setPhysicalDamage(ch.getPhysicalDamage() + 4);
                    break;
                }
                case "hp" : {
                    ch.setMaxHP(ch.getMaxHp() + 15);
                    break;
                }
                case "resistance" : {
                    ch.setResistance(ch.getResistance() + (1 - ch.getResistance())/4);
                    break;
                }
                case "chakra" : {
                    ch.setMaxChakraAmount(ch.getCurrentChakra() + 7);
                    break;
                }
                default : {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quality "+quality+" doesn't exist.");
                }
            }
            charServ.addCharacter(ch);
            Stats stats = us.getStats();
            stats.setUpgradePoints(stats.getUpgradePoints()-1);
            statsServ.addStats(stats);
            userServ.saveUser(us);
            return ResponseEntity.status(HttpStatus.OK).body("Character is updated.");
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.getMessage());
        }
    }

    @GetMapping("/profile/character")
    public ResponseEntity<?> getCharacter() {
        try {
            User user = userServ.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            return ResponseEntity.status(HttpStatus.OK).body(user.getCharacter());
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }
    
    @GetMapping("/users/{login}/character")
    public ResponseEntity<?> getCharacter(@PathVariable String login) {
        try {
            User user = userServ.getUser(login);
            if (user == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+login+" doesn't exist.");
            return ResponseEntity.status(HttpStatus.OK).body(user.getCharacter());
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }


    @PostMapping("/admin/users/{login}/grantAdmin")
    public ResponseEntity<String> grantAdmin(@PathVariable String login) {
        try {
            User user = userServ.getUser(login);
            if (user == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+login+" doesn't exist.");
            Role admin = roleRep.findById("ADMIN").get();
            if (user.getRoles().contains(admin))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User "+login+" is already an administrator.");
            user.addRole(admin);
            userServ.saveUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("ADMIN role is granted for User "+user.getLogin()+".");
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @GetMapping("/admin/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List users = userServ.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @GetMapping("/users/{login}")
    public ResponseEntity<?> getUser(@PathVariable String login) {
        try {
            User user = userServ.getUser(login);
            if (user == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+login+" doesn't exist.");
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteUser() {
        try {
            String login = SecurityContextHolder.getContext().getAuthentication().getName();
            userServ.removeUser(login);
            return ResponseEntity.status(HttpStatus.OK).body("User is deleted.");
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error.getMessage());
        }
    }

    @GetMapping("/users/{login}/stats")
    public ResponseEntity<?> getStats(@PathVariable String login) {
        try {
            User user = userServ.getUser(login);
            if (user == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+login+" doesn't exist.");
            Stats stats = user.getStats();
        return ResponseEntity.status(HttpStatus.OK).body(stats);
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

}