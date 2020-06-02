package util;

import io.jsonwebtoken.impl.DefaultClock;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.logging.Logger;

public final class JwtUtils {

    private static final JwtUtils INSTANCE = new JwtUtils();

    private final String jwtSecret = "fuhXp?#kfDq556!#PZ2-F&=&?U@3dNYBRA-9$KHmMC%d6B=mmbNVNV$99khK6@Y!*yh+epSfp2w$S3@Zh9&_@RB_6vDh?DU3HD&LRLygu&zde--BQnK8y7Uudwn-Cp6&aNXgjW_fqpUW2$-9E%^PQ94B24WL3?ZR$@GnT*as4WG!7-+8Z=-7EqrBXeZ+LwydKtkvTZE3L769XvMMRZJFaM?$h_*EhhfUAsU8*7zS?5QSXAXd2n*4vw-*JT%X7KJU";
    //30 min
    private final int jwtExpirationMs = 1800000;

    private final Clock clock = new DefaultClock();

    private final Logger logger = Logger.getLogger("util.JwtUtils");

    private JwtUtils() {}

    public static JwtUtils getInstance() {
        return INSTANCE;
    }

    public String getUserNameFromJwtToken(String token) throws Exception {
        return Jwts.parser().setClock(clock).setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        if (authToken == null) return false;
        try {
            Jwts.parser().setClock(clock).setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.severe("Invalid JWT token: {" + e.getMessage() + "}");
        } catch (ExpiredJwtException e) {
            logger.severe("JWT token is expired: {" + e.getMessage() + "}");
        } catch (UnsupportedJwtException e) {
            logger.severe("JWT token is unsupported: {" + e.getMessage() + "}");
        } catch (IllegalArgumentException e) {
            logger.severe("JWT claims string is empty: {" + e.getMessage() + "}");
        }
        return false;
    }

    public String generateJwtToken(String userName) {
        Date now = clock.now();
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

}
