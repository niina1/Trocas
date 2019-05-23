/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Carol
 */
@Stateless
public class MensagemFacade extends AbstractFacade<Mensagem> implements MensagemFacadeLocal {
    @PersistenceContext(unitName = "Trocas-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensagemFacade() {
        super(Mensagem.class);
    }

    @Override
    public Mensagem newMensagem() {
        int tamanho = 1;
        List<Mensagem> msgs = super.findAll();  
        if(msgs.size() >= 1)
            tamanho = msgs.get(msgs.size() - 1).getIdMensagem() + 1;
        Mensagem msg = new Mensagem(tamanho);
        return  msg;  
    }

    @Override
    public List<Mensagem> getMenssagensChat(Chat chat) {
        List<Mensagem> msgs = super.findAll();
        ArrayList<Mensagem> msgsChat = new ArrayList<Mensagem>();
        if(msgs != null){
            for(Mensagem msg : msgs){
                if(msg.getIdChat().equals(chat))
                    msgsChat.add(msg);
            }
        }
        return msgsChat;
    }
}
