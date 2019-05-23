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
public interface MensagemFacadeLocal {

    void create(Mensagem mensagem);

    void edit(Mensagem mensagem);

    void remove(Mensagem mensagem);

    Mensagem find(Object id);

    List<Mensagem> findAll();

    List<Mensagem> findRange(int[] range);

    int count();
    
    Mensagem newMensagem();
    
    List<Mensagem> getMenssagensChat(Chat chat);
    
}
