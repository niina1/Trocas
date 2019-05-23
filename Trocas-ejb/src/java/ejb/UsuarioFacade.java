/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Carol
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "Trocas-ejbPU")
    private EntityManager em;
    public Usuario  usuario;
    ChatFacade chatFacade;
    MensagemFacade mensagemFacade; 

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario newUsuario() {
        List<Usuario> usuarios = super.findAll();
        int tamanho = 1;
        if(usuarios.size() >= 1)
            tamanho = usuarios.get(usuarios.size() - 1).getIdUsuario() + 1;
        usuario = new Usuario(tamanho );
        return  usuario;
    }
        @Override
     public String getNomeUsuario() {
        return this.usuario.getNomeUsuario();
    }

    @Override
    public void setNomeUsuario(String nomeUsuario) {
        this.usuario.setNomeUsuario(nomeUsuario);
    }

    @Override
    public String getEmail() {
        return this.usuario.getEmail();
    }

    @Override
    public void setEmail(String email) {
        this.usuario.setEmail(email);
    }

    @Override
    public void enviarEmail(Usuario usuario, Item item, String msg) {
        ejb.SendMail email = new ejb.SendMail();
         try {
                email.sendMail("sistemaTrocasDsoo2@gmail.com", "carolina_alves.s@hotmail.com", item.getTitulo() , msg);
            } catch (Exception e) {
               e.printStackTrace();
            }
    
    }

    @Override
    public void enviarMensagem(Usuario usuario, Item item, String msg) {   
        for(Chat chat: chatFacade.findAll()){
            if(chat.getIdItem().equals(item)){
                if(chat.getIdChat().equals(chat)){
                        Mensagem mensagem = mensagemFacade.newMensagem();
                        mensagem.setTexto(msg);
                        mensagem.setIdChat(chat);
                        mensagemFacade.create(mensagem);
                        chatFacade.edit(chat);
                    }
                }
            else{
                chat = chatFacade.newChat();
                chat.setIdItem(item);
                chat.setIdUsuario(usuario);
                Mensagem mensagem = mensagemFacade.newMensagem();
                mensagem.setTexto(msg);
                mensagem.setIdChat(chat);   
                chatFacade.create(chat);
                mensagemFacade.create(mensagem);
            }
        }   
 
            

    }
    
    
    
    
  
    
    
    
}
