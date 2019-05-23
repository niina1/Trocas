package ejb;

import ejb.Chat;
import ejb.Item;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-23T00:54:37")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile CollectionAttribute<Usuario, Chat> chatCollection;
    public static volatile SingularAttribute<Usuario, String> nomeUsuario;
    public static volatile SingularAttribute<Usuario, String> uf;
    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, String> cidade;
    public static volatile SingularAttribute<Usuario, String> telefone;
    public static volatile ListAttribute<Usuario, Item> itemCollection;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, String> email;

}