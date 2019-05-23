/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carol
 */
@Entity
@Table(name = "ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByTitulo", query = "SELECT i FROM Item i WHERE i.titulo = :titulo"),
    @NamedQuery(name = "Item.findByDescricao", query = "SELECT i FROM Item i WHERE i.descricao = :descricao"),
    @NamedQuery(name = "Item.findByIdItem", query = "SELECT i FROM Item i WHERE i.idItem = :idItem")})
public class Item implements Serializable {
    @OneToMany(mappedBy = "idItem")
    private Collection<Chat> chatCollection;
    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 200)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ITEM")
    private Integer idItem;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public Item() {
    }

    public Item(Integer idItem) {
        this.idItem = idItem;
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

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Item[ idItem=" + idItem + " ]";
    }

    @XmlTransient
    public Collection<Chat> getChatCollection() {
        return chatCollection;
    }

    public void setChatCollection(Collection<Chat> chatCollection) {
        this.chatCollection = chatCollection;
    }
    
}
