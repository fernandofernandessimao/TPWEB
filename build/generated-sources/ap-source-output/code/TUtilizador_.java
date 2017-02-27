package code;

import code.TSuspensao;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-27T14:46:37")
@StaticMetamodel(TUtilizador.class)
public class TUtilizador_ { 

    public static volatile SingularAttribute<TUtilizador, Boolean> conectado;
    public static volatile SingularAttribute<TUtilizador, String> password;
    public static volatile SingularAttribute<TUtilizador, String> nome;
    public static volatile SingularAttribute<TUtilizador, Float> saldo;
    public static volatile ListAttribute<TUtilizador, TSuspensao> tSuspensaoList;
    public static volatile SingularAttribute<TUtilizador, String> morada;
    public static volatile SingularAttribute<TUtilizador, String> username;
    public static volatile SingularAttribute<TUtilizador, Boolean> activo;

}