package br.com.belval.api.geraacao.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.belval.api.geraacao.model.TipoUser;
import br.com.belval.api.geraacao.model.Usuario;
import br.com.belval.api.geraacao.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    // === Versão de produção ===
    @Bean
    @Profile("prod")
    CommandLineRunner initProd(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            boolean exists = usuarioRepository.findAll().stream()
                    .anyMatch(u -> u.getTipoUser() == TipoUser.GERENCIADOR);

            if (!exists) {
                String email = System.getenv("ADMIN_EMAIL");
                String senha = System.getenv("ADMIN_PASSWORD");

                if (email == null || senha == null) {
                    throw new RuntimeException(
                            "Variáveis ADMIN_EMAIL e ADMIN_PASSWORD não configuradas para criar GERENCIADOR!"
                    );
                }

                Usuario admin = new Usuario();
                admin.setNome("Gerenciador Inicial");
                admin.setEmail(email);
                admin.setSenha(passwordEncoder.encode(senha));
                admin.setTipoUser(TipoUser.GERENCIADOR);
                admin.setAtivo(true);

                usuarioRepository.save(admin);
                System.out.println("Usuário GERENCIADOR inicial criado (PROD).");
            }
        };
    }

    // === Versão de desenvolvimento ===
    @Bean
    @Profile("dev")
    CommandLineRunner initDev(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            boolean exists = usuarioRepository.findAll().stream()
                    .anyMatch(u -> u.getTipoUser() == TipoUser.GERENCIADOR);

            if (!exists) {
                Usuario admin = new Usuario();
                admin.setNome("Gerenciador Dev");
                admin.setEmail("admin@teste.com");
                admin.setCpf("38186847820");
                admin.setSenha(passwordEncoder.encode("adm12345678"));
                admin.setTipoUser(TipoUser.GERENCIADOR);
                admin.setAtivo(true);

                usuarioRepository.save(admin);
                System.out.println("Usuário GERENCIADOR inicial criado (DEV) -> admin@teste.com / adm12345678");
            }
        };
    }
}
