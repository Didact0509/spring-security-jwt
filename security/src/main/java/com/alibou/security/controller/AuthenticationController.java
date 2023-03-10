package com.alibou.security.controller;


import com.alibou.security.auth.AuthenticationRequest;
import com.alibou.security.auth.AuthenticationResponse;
import com.alibou.security.auth.AuthenticationService;
import com.alibou.security.auth.RegisterRequest;
import com.alibou.security.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "帳號註冊", description = " 帳號註冊、認證")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register/user")
    @Operation(summary = " USER 帳號註冊", description = " 輸入帳號資訊, ")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @PostMapping("/register/admin")
    @Operation(summary = " ADMIN 帳號註冊")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }

    @PostMapping("/authenticate")
    @Operation(summary = " 登入取得認證")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
