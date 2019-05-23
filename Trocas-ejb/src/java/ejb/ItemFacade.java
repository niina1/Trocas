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
public class ItemFacade extends AbstractFacade<Item> implements ItemFacadeLocal {
    @PersistenceContext(unitName = "Trocas-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }
    
    @Override
     public Item newItem() {
        int tamanho = 1;
        List<Item> itens = super.findAll();
        if(itens.size() >= 1)
             tamanho = itens.get(itens.size() - 1).getIdItem() + 1;
        Item item = new Item(tamanho );
        return  item;
    }

    @Override
    public List<Item> itensUsuario(Usuario usuario) {
        ArrayList<Item> lista = new ArrayList<Item>();
        for(Item item: this.findAll()){
            if(item.getIdUsuario().getIdUsuario().equals(usuario.getIdUsuario()))
                lista.add(item);
        }
        return lista;
    }

    @Override
    public List<Item> getByDescricao(String descricao) {
        ArrayList<Item> itens = new ArrayList<Item>();
        for(Item item: super.findAll()){
            if(item.getDescricao() != null&& item.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
                itens.add(item);
        }    
        return itens;
    }
    
}
