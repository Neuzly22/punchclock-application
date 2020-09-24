package ch.zli.m223.punchclock.util;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    public static ApplicationUser parseToken(String token) {
        DecodedJWT claim = JWT.decode(token.replace("Bearer", ""));
        ApplicationUser user = new ApplicationUser();
        user.setId(claim.getClaim("id").asLong());
        user.setUsername(claim.getSubject());
        return user;
    }
}
