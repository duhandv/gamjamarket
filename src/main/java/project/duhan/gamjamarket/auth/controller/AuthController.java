package project.duhan.gamjamarket.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.duhan.gamjamarket.auth.application.AuthService;
import project.duhan.gamjamarket.auth.controller.dto.MemberLoginRequest;
import project.duhan.gamjamarket.auth.controller.dto.MemberRegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public static final String AUTHENTICATION = "Authentication";

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody MemberRegisterRequest request) {
        authService.register(request.loginId(), request.password(), request.phone());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody MemberLoginRequest request, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Long memberId = authService.login(request.loginId(), request.password());
        session.setAttribute(AUTHENTICATION, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        return ResponseEntity.ok().build();
    }

}
