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
public class ChatFacade extends AbstractFacade<Chat> implements ChatFacadeLocal {
    @PersistenceContext(unitName = "Trocas-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChatFacade() {
        super(Chat.class);
    }

    @Override
    public List<Chat> getChatsUsuario(Usuario usuario) {
        List<Chat> chats = super.findAll();
        ArrayList<Chat> chatsItem = new ArrayList();
        if(chats.size() > 0){
            for(Chat chat : chats){
                if(chat.getIdUsuario().equals(usuario))
                    chatsItem.add(chat);
            }
        }
        return chatsItem;
       }

    @Override
    public Chat getChatItemUsuario(Item item, Usuario usuario) {
        List<Chat> chats = super.findAll();
        if(chats.size() > 0){
            for(Chat chat : chats){
                if(chat.getIdItem().equals(item))
                    if(chat.getIdUsuario().equals(usuario))
                        return chat;
            }
        }
        return null;
    }


    @Override
    public List<Chat> getChatsItem(Item item) {
        List<Chat> chats = super.findAll();
        ArrayList<Chat> chatsItem = new ArrayList<Chat>();
        if(chats.size() > 0){
            for(Chat chat : chats){
                if(chat.getIdItem().equals(item))
                    chatsItem.add(chat);
            }
        }
        return chatsItem;
    }

    @Override
    public Chat newChat() {
        List<Chat> chats = super.findAll();
        int tamanho;
        if( chats.get(chats.size() - 1).getIdChat() != null){
            tamanho = chats.get(chats.size() - 1).getIdChat() + 1;
        }
        else
           tamanho = 1;
               
        Chat chat = new Chat(tamanho );
        return  chat;    
    }
    
}
