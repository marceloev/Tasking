package br.com.tasking.service;

import br.com.tasking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            User user = service.findByLogin(login);
            List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
            List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(),
                    user.getSenha(),
                    (user.getCodigo() == 0) ? authorityListAdmin : authorityListUser
            );
        } catch (NoResultException ex) {
            ex.printStackTrace();
            throw new UsernameNotFoundException("Usuário não encontrado");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UsernameNotFoundException("Erro ao tentar encontrar usuário");
        }
    }
}
