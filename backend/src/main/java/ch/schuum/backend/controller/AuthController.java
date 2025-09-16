package ch.schuum.backend.controller;

import ch.schuum.backend.auth.JwtUtils;
import ch.schuum.backend.auth.UserDetailsImpl;
import ch.schuum.backend.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Der BeerTypeController nimmt alle REST-Abfragen für /auth entgegen, validiert und verarbeitet sie weiter.
 * Dabei ist er zuständig für alle Abfragen betreffend Login und Authentisierung.
 *
 * @author Miriam Streit
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * <p>authentisiert einen Benutzer</p>
     * @param userDto Beim Login eingegebene Daten (E-Mail und Passwort)
     * @return ein Benutzerobjekt befüllt mit UserId, der E-Mail-Adresse und dem JSON-Web-Token
     * @author Miriam Streit
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> authenticateUser(@Valid @RequestBody UserDto userDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new UserDto(
                Math.toIntExact(userDetails.getId()),
                userDetails.getEmail(),
                "",
                jwt));
    }
}