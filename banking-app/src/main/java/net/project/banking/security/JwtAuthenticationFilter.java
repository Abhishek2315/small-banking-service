package net.project.banking.security;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.project.banking.service.UsersService;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Autowired
	private JwtHelper jwtHelper;

//	@Autowired
//	private UserDetailsService userdetailsService;
	
	@Autowired
	private UsersService usersService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String requestHeader = request.getHeader("Authorization");
		logger.info(" Header :{}", requestHeader);

		String userEmail = null;
		String token = null;

		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {

			// it is used to extract bearer string and store token in variable.
			token = requestHeader.substring(7);

			try {
				userEmail = this.jwtHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				logger.info("Illegal Argument ehile fetching the username !!");
				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				// TODO Auto-generated catch block
				logger.info("Given jwt token is expired");
				e.printStackTrace();
			} catch (MalformedJwtException e) {
				// TODO Auto-generated catch block
				logger.info("Some changed has done in token !! Invalid Token");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			logger.info("Invalid Header Value !!");
		}

		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

//			UserDetails userDetails = this.userdetailsService.loadUserByUsername(userEmail);
			UserDetails userDetails = usersService.userDetailsService().loadUserByUsername(userEmail); 
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);

			if (validateToken) {

				// set the Authentication
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//here or also setContext used in place of getContext
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				logger.info("Validation fails !! ");
			}
		}

		filterChain.doFilter(request, response);
	}

}
