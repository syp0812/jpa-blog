package jpacrud.blog.service;

import jpacrud.blog.dto.MemberRequestDto;
import jpacrud.blog.entity.Member;
import jpacrud.blog.entity.MemberRole;
import jpacrud.blog.exception.BlogException;
import jpacrud.blog.exception.BlogExceptionType;
import jpacrud.blog.jwt.JwtUtil;
import jpacrud.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    /**
     * 회원 가입
     * DB에 중복된 username이 없다면 회원을 저장
     * 회원 권한 부여 (ADMIN, USER)
     */
    @Transactional
    public void signup(MemberRequestDto requestDto) {
        String username = requestDto.getUsername();

        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<Member> found = memberRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new BlogException(BlogExceptionType.ALREADY_EXISTS_USERNAME);
        }

        // 사용자 ROLE 확인
        MemberRole role = MemberRole.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new BlogException(BlogExceptionType.WRONG_PASSWORD);
            }
            role = MemberRole.ADMIN;
        }

        Member member = new Member(username, password, role);
        memberRepository.save(member);
    }

    /**
     * 로그인
     * username을 사용하여 저장된 사용자의 유무를 확인하고 있다면 password 비교
     * 로그인 성공 시 토큰을 발급하고 발급한 토큰을 Header에 추가
     */
    @Transactional(readOnly = true)
    public void login(MemberRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 확인
        Member member = memberRepository.findByUsername(username)
//                .filter(m -> m.getPassword().equals(password))
                .orElseThrow(() -> new BlogException(BlogExceptionType.MEMBER_NOT_FOUND));

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new BlogException(BlogExceptionType.WRONG_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getUsername(), member.getRole()));
    }
}
