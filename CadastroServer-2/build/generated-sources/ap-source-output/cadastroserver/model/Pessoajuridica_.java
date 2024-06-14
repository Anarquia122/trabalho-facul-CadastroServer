package cadastroserver.model;

import cadastroserver.model.Pessoa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-11T01:10:18", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pessoajuridica.class)
public class Pessoajuridica_ { 

    public static volatile SingularAttribute<Pessoajuridica, Integer> idpessoajuridica;
    public static volatile SingularAttribute<Pessoajuridica, String> cnpj;
    public static volatile SingularAttribute<Pessoajuridica, Pessoa> pessoaIdpessoa;

}