package org.proyecto.primerproyecto.controllers;


import org.proyecto.primerproyecto.models.Usuario;
import org.proyecto.primerproyecto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public List<Usuario> findAll() {
        return this.usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserByID(@PathVariable long id) {
        Optional<Usuario> usuario = this.usuarioService.getUserById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/create")
    public void createUser(@RequestBody Usuario usuario) {
        
        this.usuarioService.createUser(usuario);

    }

    @GetMapping("/delete/{id}")
        public void deleteUser(@PathVariable Long id) {
            this.usuarioService.delete(id);
        }

    @GetMapping("/status")
    public Map<String, String> checkStatus(){
        Map<String, String> response = new HashMap<>();
        response.put("message", "conexión establecida");
        return response;
    }

    /*
    *credenciales = {"username": antonio, "password":1234}
     */

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credenciales) {

        String username = credenciales.get("username");
        String password = credenciales.get("password");

        boolean isAuth = this.usuarioService.authenticate(username, password);
        Map<String, Object> response = new HashMap<>();

        if (isAuth) {
            response.put("message", "Login correcto");
            response.put("login", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Credenciales no válidas.");
            response.put("login", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

        @PostMapping("/login/v2")
                public ResponseEntity<Map<String, Object>> loginV2(@RequestBody Map<String, String> credenciales){
                    String username = credenciales.get("username");
                    String password = credenciales.get("password");

                    String result = this.usuarioService.authWithPassword(username, password);
                    Map<String, Object> response = new HashMap<>();

                    if (result.equals("El usuario existe")) {
                        response.put("message", result);
                        response.put("login", true);
                        return ResponseEntity.ok(response); // Código de estado 200 -> Entra en el next.
                    }
                    else if (result.equals("Contraseña incorrecta")){
                        response.put("message", result);
                        response.put("logged", false);
                        // Código de estado 401 -> Esto entra en el error.
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    }
                    else {
                        response.put("message", result);
                        response.put("logged", false);
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                    }
                }
}


