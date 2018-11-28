package hu.unideb.inf.notebookservice.commons.table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TableName {

    public static final String TABLE_NAME_USER = "user";

    public static final String TABLE_NAME_SERVICE = "interfaces";

    public static final String TABLE_NAME_MODIFICATION = "modification";

    public static final String TABLE_NAME_PRODUCT = "product";

    public static final String TABLE_NAME_CLIENT = "client";

    public static final String TABLE_NAME_BRAND = "brand";

    public static final String TABLE_NAME_SERVICE_HAS_MODIFICATION = "service_has_modification";
}
