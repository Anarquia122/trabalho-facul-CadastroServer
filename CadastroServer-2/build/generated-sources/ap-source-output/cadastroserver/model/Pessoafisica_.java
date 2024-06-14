package cadastroserver.model;

import cadastroserver.model.Pessoa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-11T01:10:18", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pessoafisica.class)
public class Pessoafisica_ { 

    public static volatile SingularAttribute<Pessoafisica, Integer> idpessoafisica;
    public static volatile SingularAttribute<Pessoafisica, String> cpf;
    public static volatile SingularAttribute<Pessoafisica, Pessoa> pessoaIdpessoa;

}