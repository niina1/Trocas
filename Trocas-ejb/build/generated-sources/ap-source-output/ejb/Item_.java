package ejb;

import ejb.Chat;
import ejb.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-23T00:54:37")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile CollectionAttribute<Item, Chat> chatCollection;
    public static volatile SingularAttribute<Item, Usuario> idUsuario;
    public static volatile SingularAttribute<Item, String> titulo;
    public static volatile SingularAttribute<Item, Integer> idItem;
    public static volatile SingularAttribute<Item, String> descricao;

}