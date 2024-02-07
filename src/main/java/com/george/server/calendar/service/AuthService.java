package com.george.server.calendar.service;

import com.george.server.calendar.dto.JwtResponse;
import com.george.server.calendar.dto.LoginRequest;
import com.george.server.calendar.dto.MessageResponse;
import com.george.server.calendar.dto.RegisterRequest;
import com.george.server.calendar.model.Role;
import com.george.server.calendar.model.User;
import com.george.server.calendar.repository.RoleRepository;
import com.george.server.calendar.repository.UserRepository;
import com.george.server.calendar.service.user.UserDetailsImpl;
import com.george.server.calendar.util.ERole;
import com.george.server.calendar.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

    public MessageResponse<?> registerUser(RegisterRequest registerRequest) {

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new MessageResponse<>("Error: Username is already taken", null);
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return new MessageResponse<>("Error: Email is already in use!", null);
        }

        User user = new User(registerRequest.getUsername(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword())
        );

        Set<String> listRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (listRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            listRoles.forEach(role -> {
                switch (role) {
                    case "DEVELOPER":
                        Role developerRole = getRole(ERole.DEVELOPER);
                        roles.add(developerRole);
                        break;
                    case "USER":
                        Role userRole = getRole(ERole.USER);
                        roles.add(userRole);
                        break;
                    default:
                        Role userRoleDef = getRole(ERole.USER);
                        roles.add(userRoleDef);
                }
            });
        }

        user.setRoles(roles);
        User _user = userRepository.save(user);

        return new MessageResponse<>("User registered successfully!", _user);
    }

    private Role getRole(ERole eRole) {
        return roleRepository.findByName(eRole)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }

}
