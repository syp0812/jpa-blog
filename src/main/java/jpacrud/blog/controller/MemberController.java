package jpacrud.blog.controller;

import jpacrud.blog.dto.MemberRequestDto;
import jpacrud.blog.dto.ResponseDto;
import jpacrud.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseDto signup(@Valid @RequestBody MemberRequestDto requestDto) {
        memberService.signup(requestDto);
        return new ResponseDto(HttpStatus.OK.value(),"success");
    }

    @PostMapping("/auth/login")
    public ResponseDto login(@Valid @RequestBody MemberRequestDto requestDto, HttpServletResponse response) {
        memberService.login(requestDto, response);
        return new ResponseDto(HttpStatus.OK.value(),"success");
    }
}
