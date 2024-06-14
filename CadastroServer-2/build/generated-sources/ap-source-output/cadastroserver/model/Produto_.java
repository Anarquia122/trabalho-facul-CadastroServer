package cadastroserver.model;

import cadastroserver.model.Movimento;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-11T01:10:18", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile SingularAttribute<Produto, BigDecimal> preco;
    public static volatile SingularAttribute<Produto, String> nomeproduto;
    public static volatile SingularAttribute<Produto, Integer> idproduto;
    public static volatile CollectionAttribute<Produto, Movimento> movimentoCollection;
    public static volatile SingularAttribute<Produto, Integer> quantidade;

}