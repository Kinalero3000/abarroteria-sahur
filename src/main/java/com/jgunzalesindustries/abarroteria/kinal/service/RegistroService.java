package main.java.com.jgunzalesindustries.abarroteria.kinal.service;

 
import main.java.com.jgunzalesindustries.abarroteria.kinal.dto.request.RegistroDTORequest;
import main.java.com.jgunzalesindustries.abarroteria.kinal.dto.response.RegistroDTOResponse;
import main.java.com.jgunzalesindustries.abarroteria.kinal.model.Usuario;
import main.java.com.jgunzalesindustries.abarroteria.kinal.repository.UserRepository;
import main.java.com.jgunzalesindustries.abarroteria.kinal.security.jbcrypt.BCrypt;



public class RegistroService {
     private final UserRepository usuarioRepository;

    public RegistroService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public RegistroDTOResponse registrar(RegistroDTORequest request) {
        validarRequest(request);

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con ese email.");
        }

        String contrasenaHash = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        String idUsuario = usuarioRepository.generarIdUsuario();

        Usuario usuario = new Usuario(idUsuario, request.getNombre(), request.getApellido(),
                request.getEmail(), contrasenaHash, request.getIdRol());

        boolean guardado = usuarioRepository.save(usuario);

        if (!guardado) {
            throw new RuntimeException("No se pudo registrar el usuario.");
        }

        return new RegistroDTOResponse(usuario.getIdUsuario(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
    }

    private void validarRequest(RegistroDTORequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Los datos de registro están vacíos.");
        }
        if (isBlank(request.getNombre())) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (isBlank(request.getApellido())) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (isBlank(request.getEmail()) || !request.getEmail().contains("@")) {
            throw new IllegalArgumentException("El email no es válido.");
        }
        if (isBlank(request.getPassword()) || request.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        if (!request.getPassword().equals(request.getConfirmarPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }
        if (request.getIdRol() <= 0) {
            throw new IllegalArgumentException("Debes seleccionar un rol válido.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

}
