package org.example.backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.request.LoginRequest;
import org.example.backend.DTO.request.SignupRequest;
import org.example.backend.DTO.response.JwtResponse;
import org.example.backend.DTO.response.ResponseMessage;
import org.example.backend.Util.IdGenerationUtil;
import org.example.backend.models.Role;
import org.example.backend.models.User;
import org.example.backend.models.enums.ERole;
import org.example.backend.repository.RoleRepository;
import org.example.backend.repository.UserRepository;
import org.example.backend.security.jwt.JwtUtils;
import org.example.backend.security.services.UserDetailsImpl;
import org.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/socialApp/auth")
@RequiredArgsConstructor
public class AuthController {
    final AuthenticationManager authenticationManager;

    final UserRepository userRepository;
    final RoleRepository roleRepository;

    final PasswordEncoder encoder;

    final JwtUtils jwtUtils;

    final IdGenerationUtil idGenerationUtil;

    final UserService userService;

    private static final Date currentDate = new Date();

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        if (!authentication.isAuthenticated()){
            return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "error", "Username is already taken!"), HttpStatus.OK);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String userCode = null;
        Optional<User> byId = userRepository.findById(userDetails.getId());
        if (byId.isPresent()) {
            userCode = byId.get().getUserCode();
        }

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getRole(), userCode));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws ParseException {
        System.out.println(signUpRequest.getUsername());
        System.out.println(signUpRequest.getEmail());
        System.out.println(signUpRequest.getPassword());
        System.out.println(signUpRequest.getRole());

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "error", "Username is already taken!"), HttpStatus.OK);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "error", "Email is already in use!"), HttpStatus.OK);
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role adminRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            user.setRole("ROLE_USER");
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        System.out.println("admin");
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        user.setRole("ROLE_ADMIN");
                        break;
                    case "user":
                        System.out.println("user");
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        user.setRole("ROLE_USER");
                        break;
                }
            });
        }


        String dateString = String.valueOf(currentDate);
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = inputFormat.parse(dateString);

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = outputFormat.format(date);

        user.setCreateDateTime(formattedDate);
        user.setRoles(roles);
        user.setUserCode(idGenerationUtil.userCodeGenerator());
        user.setIsActive(1);
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "success", "successfully registered"), HttpStatus.OK);

    }
}

