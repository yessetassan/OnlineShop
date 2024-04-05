package yesko.project.OnlineShop.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yesko.project.OnlineShop.service.EmailSenderService;

@RestController
@RequestMapping("/open-api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;
    private final EmailSenderService emailSenderService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Validated RegisterRequest request
    ) {
        return ResponseEntity.ok().body(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("Authentication process...");
        return ResponseEntity.ok().body(service.authenticate(request));
    }
    @PostMapping("/email")
    public ResponseEntity<String> authenticate(
            @RequestBody EmailRequest request
    ) {
        log.info("EmailSender process...");
        return ResponseEntity.ok().body(emailSenderService.sendMail(request));
    }
}
