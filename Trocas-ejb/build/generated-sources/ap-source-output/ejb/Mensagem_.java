package ejb;

import ejb.Chat;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-23T00:54:37")
@StaticMetamodel(Mensagem.class)
public class Mensagem_ { 

    public static volatile SingularAttribute<Mensagem, String> texto;
    public static volatile SingularAttribute<Mensagem, Integer> idMensagem;
    public static volatile SingularAttribute<Mensagem, Chat> idChat;

}