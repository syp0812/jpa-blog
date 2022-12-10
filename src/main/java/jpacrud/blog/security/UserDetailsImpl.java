package jpacrud.blog.security;

import jpacrud.blog.entity.Member;
import jpacrud.blog.entity.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final Member member; // 인증 완료된 Member 객체
    private final String username;
    private final String password;

    public UserDetailsImpl(Member member, String username, String password) {
        this.member = member;
        this.username = username;
        this.password = password;
    }

    //  인증 완료된 Member를 가져오는 Getter
    public Member getMember() {
        return member;
    }

    // 사용자 권한을 추상화 및 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        MemberRole role = member.getRole();
        String authority = role.getAuthority();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    // 사용자의 ID, PW Getter
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
