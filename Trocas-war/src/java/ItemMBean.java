/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.Item;
import ejb.ItemFacadeLocal;
import ejb.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carol
 */
@ManagedBean
@ApplicationScoped
public class ItemMBean {
    @EJB
    private ItemFacadeLocal itemFacade;
    public Item item;
    public Usuario usuarioLogado;
    String titulo;
    String descricao;
    @Inject
    UsuarioMBean usuarioMBean;

    public Usuario getUsuario() {
         FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        usuarioLogado = (Usuario) session.getAttribute("user");
        return usuarioLogado;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    /**
     * Creates a new instance of ItemMBean
     */
    public ItemMBean() {
    }
    
  public List<Item> getListaItem(){
        return itemFacade.findAll();
        
    }
 
  
    public List<Item> setListaItem(List<Item> itens){
        return itens;
        
    }
  
  public String criarItem(){
        item = itemFacade.newItem();
        item.setTitulo(titulo);
        item.setDescricao(descricao);
        item.setIdUsuario(usuarioLogado);
        itemFacade.create(item);
        this.item = item;
        return "perfil";
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
    
}
