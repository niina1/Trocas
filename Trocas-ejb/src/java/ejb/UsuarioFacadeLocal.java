/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Carol
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario newUsuario();
    
    String getNomeUsuario();
    
    String getEmail();
    
    void setNomeUsuario(String nomeUsuario);
    
    void setEmail(String email);
    
    void enviarEmail(Usuario usuario, Item item, String msg);
           
}
