package jpacrud.blog.controller;

import jpacrud.blog.dto.MemberRequestDto;
import jpacrud.blog.dto.ResponseDto;
import jpacrud.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody MemberRequestDto requestDto) {
        memberService.signup(requestDto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK,"회원가입 완료"));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody MemberRequestDto requestDto, HttpServletResponse response) {
        memberService.login(requestDto, response);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK,"로그인 완료"));
    }
}
