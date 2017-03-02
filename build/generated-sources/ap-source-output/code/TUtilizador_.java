package code;

import code.TDenunciaUtilizador;
import code.TItem;
import code.TMensagem;
import code.TReactivacao;
import code.TSuspensao;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-02T16:15:27")
@StaticMetamodel(TUtilizador.class)
public class TUtilizador_ { 

    public static volatile CollectionAttribute<TUtilizador, TItem> tItemVendedorCollection;
    public static volatile CollectionAttribute<TUtilizador, TItem> tItemCompradorCollection;
    public static volatile CollectionAttribute<TUtilizador, TDenunciaUtilizador> tDenunciaUtilizadorCollection;
    public static volatile CollectionAttribute<TUtilizador, TMensagem> tMensagemReceptorCollection;
    public static volatile CollectionAttribute<TUtilizador, TReactivacao> tReactivacaoCollection;
    public static volatile SingularAttribute<TUtilizador, String> nome;
    public static volatile SingularAttribute<TUtilizador, Float> saldo;
    public static volatile CollectionAttribute<TUtilizador, TItem> tItemSegueCollection;
    public static volatile SingularAttribute<TUtilizador, Boolean> conectado;
    public static volatile SingularAttribute<TUtilizador, String> password;
    public static volatile CollectionAttribute<TUtilizador, TMensagem> tMensagemSenderCollection;
    public static volatile CollectionAttribute<TUtilizador, TSuspensao> tSuspensaoCollection;
    public static volatile CollectionAttribute<TUtilizador, TItem> tItemLicitacaoCollection;
    public static volatile SingularAttribute<TUtilizador, String> morada;
    public static volatile SingularAttribute<TUtilizador, String> username;
    public static volatile SingularAttribute<TUtilizador, Boolean> activo;

}