/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import ejb.Item;
import ejb.ItemFacadeLocal;
import ejb.UsuarioFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ejb.Usuario;
import java.io.IOException;
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
    @Inject
    ItemMBean itemMBean = new ItemMBean();
    

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
        usuario = getUsuario();
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
      return "indexantigo";
    }
    
     public String realizarLogin() throws IOException {
        for (Usuario user : this.getListaUsuario()) {
            if (user.getEmail().equals(email)) {
                if (user.getSenha().equals(this.getSenha())) {
{
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
        return "index";
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

    
}
