package org.reflection.model.com;

import org.reflection.model.com.enums.CrudType;

public interface IDetail extends IAbstract {

    public CrudType getCrudType();

    public void setCrudType(CrudType crudType);

}
