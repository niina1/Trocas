/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import ejb.Chat;
import ejb.ChatFacadeLocal;
import ejb.Item;
import ejb.ItemFacadeLocal;
import ejb.Mensagem;
import ejb.MensagemFacadeLocal;
import ejb.UsuarioFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ejb.Usuario;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carol
 */
@ManagedBean
@RequestScoped
public class UsuarioMBean {
    @EJB
    private ItemFacadeLocal itemFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @Inject
    public Usuario usuario;
    String email = "";
    String senha = "";
    String nomeUsuario = "";
    String cidade = "";
    String uf = "";
    String telefone = "";
    Item item;
    String titulo;
    String descricao;
    String mensagem;
    @Inject
    ItemMBean itemMBean = new ItemMBean();
    Item itemSelecionado;
    @EJB 
    private ChatFacadeLocal chatFacade;
    @EJB
    private MensagemFacadeLocal mensagemFacade;
    Chat chatSelecionado;

        /**
     * Creates a new instance of UsuarioMBean
     */
    public UsuarioMBean() {
    }

    public ItemMBean getItemMBean() {
        return itemMBean;
    }

    public void setItemMBean(ItemMBean itemMBean) {
        this.itemMBean = itemMBean;
    }
    
    public List<Usuario> getListaUsuario(){
        return usuarioFacade.findAll();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public String criarUsuario(){
         if(emailExiste(email)){
             FacesMessage fm = new FacesMessage("E-mail já cadastrado");
             FacesContext.getCurrentInstance().addMessage("msgCd", fm);
            return null;
        }
         if(email == ""){
             FacesMessage fm = new FacesMessage("E-mail Obrigatorio");
             FacesContext.getCurrentInstance().addMessage("msgEm", fm);
            return null;
         }
         if(senha == ""){
             FacesMessage fm = new FacesMessage("Senha Obrigatoria");
             FacesContext.getCurrentInstance().addMessage("msgSn", fm);
            return null;
         }
        usuario = usuarioFacade.newUsuario();
        usuario.setEmail(email); 
        usuario.setCidade(cidade);
        usuario.setNomeUsuario(nomeUsuario);
        usuario.setTelefone(telefone);
        usuario.setSenha(senha);
        usuarioFacade.create(usuario);
        email = "";
        cidade = "";
        nomeUsuario = "";
        telefone = "";
        senha = "";
         FacesContext context = FacesContext.getCurrentInstance();
         HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
         session.setAttribute("user", this.usuario);
         ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();  
         return "perfil";
    }
    
     private boolean emailExiste(String email) {
        for (Usuario user : this.getListaUsuario()) {
          if (user.getEmail().equals(email)) {
              
                  return true;
              
          }
        }           
        
        return false;
    }
    
     public String realizarLogin() throws IOException {
         if(email != ""&& senha != ""){
            for (Usuario user : this.getListaUsuario()) {
                if (user.getEmail().equals(email)) {
                    if (user.getSenha().equals(senha)) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
                        this.usuario = user;
                        session.setAttribute("user", this.usuario);
                        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();  
                        return "perfil";
                        }
                }
            }
         }
        FacesMessage fm = new FacesMessage("Login ou senha inválidos");
        FacesContext.getCurrentInstance().addMessage("msgLg", fm);
        return null;
    }
                
     
     public String Login(){
         return "login";
     }
     
      public String Cadastro(){
         return "cadastro";
     }

    public Usuario getUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        this.usuario = (Usuario) session.getAttribute("user");
        return this.usuario;
        
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Integer getIdUsuario() {
        return usuario.getIdUsuario();
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
    
     public String getEmail() {
        return email;
    }
     
     public void setEmail(String email) {
        this.email = email;
    }
     
      
    
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
        
  public List<Item> getListaItemUsuario(){
      getUsuario();
      return itemFacade.itensUsuario(this.getUsuario());
    }
  
  public void criarItem(){
        item = itemFacade.newItem();
        item.setTitulo(titulo);
        item.setDescricao(descricao);
        getUsuario();
        item.setIdUsuario(usuario);
        itemFacade.create(item);
        titulo = "";
        descricao = "";
    }
  
  public void enviarEmail(){
      getUsuario();
      getItemSelecionado();
      String msg = this.usuario.getNomeUsuario() + " - " + this.usuario.getEmail() + 
              " enviou uma mensagem sobre o item " + this.itemSelecionado.getTitulo() + ": " +  this.mensagem;
      usuarioFacade.enviarEmail(usuario, itemSelecionado, msg);
  }
  
   public boolean enviarMensagem(){
      getUsuario();
      getItemSelecionado();
      this.mensagem = usuario.getNomeUsuario() + ": " + mensagem;
      for(Chat chat: chatFacade.findAll()){
            if(chat.getIdItem().equals(itemSelecionado)){
                if(chat.getIdUsuario().equals(usuario)){
                        Mensagem msg = mensagemFacade.newMensagem();
                        msg.setTexto(mensagem);
                        msg.setIdChat(chat);
                        mensagemFacade.create(msg);
                        chatFacade.edit(chat);
                        return true;
                    }
                }
      }
      
                Chat chat = chatFacade.newChat();
                chat.setIdItem(itemSelecionado);
                chat.setIdUsuario(usuario);
                Mensagem msg = mensagemFacade.newMensagem();
                msg.setTexto(mensagem);
                msg.setIdChat(chat);   
                this.chatFacade.create(chat);
                this.mensagemFacade.create(msg);
                return true;
            
   }
  
  public String setItemSelecionado(Item item){
      getUsuario();
      FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.setAttribute("item", item);
        this.itemSelecionado = (Item) session.getAttribute("item"); 
        if(itemSelecionado.getIdUsuario().equals(usuario))
            return "meuitem";
        return "visualizaritem";
  }
  
  
  public Item getItemSelecionado(){
      FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        this.itemSelecionado = (Item) session.getAttribute("item"); 
        return this.itemSelecionado;
  }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Mensagem> getMensagemItem(){
        getUsuario();
        getItemSelecionado();
        Chat chat = chatFacade.getChatItemUsuario(itemSelecionado, usuario);
        List<Mensagem> msgs = null;
        if(chat != null)
            msgs = mensagemFacade.getMenssagensChat(chat);
        return msgs;
    }
    
     public List<Chat> getChatItem(){
        getItemSelecionado();
        List<Chat> chats = chatFacade.getChatsItem(itemSelecionado);
        return chats;
    }
     
     public List<Mensagem> getMensagemChat(){
         getChatSelecionado();
         return mensagemFacade.getMenssagensChat(chatSelecionado);
    }
     
    public String setChatSelecionado(Chat chat){
          FacesContext context = FacesContext.getCurrentInstance();
          HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
          session.setAttribute("chat", chat);
          this.chatSelecionado = (Chat) session.getAttribute("chat"); 
          return "mensagemchat";
  }
       public Chat getChatSelecionado(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        this.chatSelecionado = (Chat) session.getAttribute("chat"); 
        setItemSelecionado(chatSelecionado.getIdItem());
        return this.chatSelecionado;
  }
       
       public void enviarMensagemChat(){
        getUsuario();
        getItemSelecionado();
        getChatSelecionado();
        Mensagem msg = mensagemFacade.newMensagem();
        mensagem = usuario.getNomeUsuario() + ": " + mensagem;
        msg.setTexto(mensagem);
        msg.setIdChat(chatSelecionado);   
        this.chatFacade.edit(chatSelecionado);
        this.mensagemFacade.create(msg);
            
   }
       
       public List<Chat> getChatsUsuario(){
           getUsuario();
           return chatFacade.getChatsUsuario(usuario);
       }
       
       public String visualizarChats(){
           
           return "usuariochats";
       }
       
       public String Sair(){
           FacesContext context = FacesContext.getCurrentInstance();
          HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
          session.setAttribute("chat", null);
          session.setAttribute("user", null);
          session.setAttribute("item", null);
          return "index";
       }
    
}
