/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carol
 */
@Entity
@Table(name = "MENSAGEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensagem.findAll", query = "SELECT m FROM Mensagem m"),
    @NamedQuery(name = "Mensagem.findByIdMensagem", query = "SELECT m FROM Mensagem m WHERE m.idMensagem = :idMensagem"),
    @NamedQuery(name = "Mensagem.findByTexto", query = "SELECT m FROM Mensagem m WHERE m.texto = :texto")})
public class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENSAGEM")
    private Integer idMensagem;
    @Size(max = 200)
    @Column(name = "TEXTO")
    private String texto;
    @JoinColumn(name = "ID_CHAT", referencedColumnName = "ID_CHAT")
    @ManyToOne
    private Chat idChat;

    public Mensagem() {
    }

    public Mensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Chat getIdChat() {
        return idChat;
    }

    public void setIdChat(Chat idChat) {
        this.idChat = idChat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMensagem != null ? idMensagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensagem)) {
            return false;
        }
        Mensagem other = (Mensagem) object;
        if ((this.idMensagem == null && other.idMensagem != null) || (this.idMensagem != null && !this.idMensagem.equals(other.idMensagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Mensagem[ idMensagem=" + idMensagem + " ]";
    }
    
}
