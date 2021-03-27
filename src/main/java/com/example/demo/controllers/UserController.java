package com.example.demo.controllers;

import com.example.demo.adapters.UserService;
import com.example.demo.adapters.model.Address;
import com.example.demo.adapters.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("defaultS")
    public ResponseEntity<String> defaultS() {
        return ResponseEntity.ok("DefaultUser");
    }

    @PutMapping("createUser")
    public ResponseEntity createUser(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "height") Integer height,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @RequestParam(value = "dob") Date dob,
                                     @RequestParam(value = "city") String city,
                                     @RequestParam(value = "street") String street,
                                     @RequestParam(value = "housenumber") Integer houseNumber,
                                     @RequestParam(value = "country") String country) {

        var address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setCountry(country);

        var newUser = new User();
        newUser.setName(name);
        newUser.setHeight(height);
        newUser.setDateOfBirth(dob);
        newUser.setAddress(address);

        userService.createNewUser(newUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("listUsers")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.getAllUsers().toJavaList());
    }
}
