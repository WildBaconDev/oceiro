package br.com.davimgoncalves.oceiro.config.details;

import br.com.davimgoncalves.oceiro.model.Authority;
import org.springframework.security.core.GrantedAuthority;

public record AuthorityDetail(Authority authority) implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return authority.nome();
    }
}
