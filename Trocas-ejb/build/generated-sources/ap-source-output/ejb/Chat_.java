package ejb;

import ejb.Item;
import ejb.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-23T00:54:37")
@StaticMetamodel(Chat.class)
public class Chat_ { 

    public static volatile SingularAttribute<Chat, Usuario> idUsuario;
    public static volatile SingularAttribute<Chat, Integer> idChat;
    public static volatile SingularAttribute<Chat, Item> idItem;

}