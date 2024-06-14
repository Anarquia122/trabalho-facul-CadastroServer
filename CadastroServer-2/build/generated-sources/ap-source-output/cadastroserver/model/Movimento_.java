package cadastroserver.model;

import cadastroserver.model.Pessoa;
import cadastroserver.model.Produto;
import cadastroserver.model.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-11T01:10:18", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Movimento.class)
public class Movimento_ { 

    public static volatile SingularAttribute<Movimento, String> tipo;
    public static volatile SingularAttribute<Movimento, Date> datamovimento;
    public static volatile SingularAttribute<Movimento, BigDecimal> valorunitario;
    public static volatile SingularAttribute<Movimento, Produto> idproduto;
    public static volatile SingularAttribute<Movimento, Pessoa> idpessoa;
    public static volatile SingularAttribute<Movimento, Integer> quantidade;
    public static volatile SingularAttribute<Movimento, Integer> idmovimento;
    public static volatile SingularAttribute<Movimento, Usuario> idusuario;

}