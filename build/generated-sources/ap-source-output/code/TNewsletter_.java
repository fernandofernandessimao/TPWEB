package code;

import code.TCodigoNewsletter;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T20:32:32")
@StaticMetamodel(TNewsletter.class)
public class TNewsletter_ { 

    public static volatile SingularAttribute<TNewsletter, TCodigoNewsletter> codigo;
    public static volatile SingularAttribute<TNewsletter, Date> data;
    public static volatile SingularAttribute<TNewsletter, String> mensagem;
    public static volatile SingularAttribute<TNewsletter, Integer> id;

}