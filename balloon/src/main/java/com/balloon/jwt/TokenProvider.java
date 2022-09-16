package com.balloon.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.balloon.dto.TokenDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {

	private static final String AUTHORITIES_KEY = "auth";
	private static final String BEARER_TYPE = "bearer";
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;
	private final Key key;

	// 주의점: 여기서 @Value는 `springframework.beans.factory.annotation.Value`소속이다!
	// lombok의 @Value와 착각하지 말것!
	// * @param secretKey
	/* JwtAuthTokenProvider */
	public TokenProvider(@Value("${jwt.secret}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	// 토큰 생성
	public TokenDTO generateTokenDTO(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		long now = (new Date()).getTime();

		Date tokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

		System.out.println(tokenExpiresIn);
		System.out.println(tokenExpiresIn);

		String accessToken = Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
				.setExpiration(tokenExpiresIn).signWith(key, SignatureAlgorithm.HS512).compact();

		return TokenDTO.builder().grantType(BEARER_TYPE).accessToken(accessToken)
				.tokenExpiresIn(tokenExpiresIn.getTime()).partitionKey(authentication.getName()).build();
	}

	public Authentication getAuthentication(String accessToken) {
		Claims claims = parseClaims(accessToken);

		if (claims.get(AUTHORITIES_KEY) == null) {
			throw new RuntimeException("권한 정보가 없는 토큰입니다.");
		}

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		UserDetails principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, "", authorities);
	}

	public boolean validateToken(String token, HttpServletResponse response) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			System.out.println(
					"\nlogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglog");
			System.out.println("잘못된 JWT 서명입니다.");
			Cookie kc = new Cookie("choiceCookieName", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정

			kc.setMaxAge(0); // 유효시간을 0으로 설정

			response.addCookie(kc); // 응답 헤더에 추가해서 없어지도록 함
		} catch (ExpiredJwtException e) {
			System.out.println(
					"\\nlogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglog");
			System.out.println("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			System.out.println(
					"\\nlogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglog");
			System.out.println("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			System.out.println(
					"\\nlogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglogloglog");
			System.out.println("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}

	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}