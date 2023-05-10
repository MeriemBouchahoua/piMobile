package com.mycompany.myapp.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-05-10T15:53:23")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, String> date;
    public static volatile SingularAttribute<Consultation, String> cause;
    public static volatile SingularAttribute<Consultation, String> description;
    public static volatile SingularAttribute<Consultation, Integer> id;
    public static volatile SingularAttribute<Consultation, String> nom;
    public static volatile SingularAttribute<Consultation, String> prenom;
    public static volatile SingularAttribute<Consultation, String> cabinet;
    public static volatile SingularAttribute<Consultation, String> medecin;

}