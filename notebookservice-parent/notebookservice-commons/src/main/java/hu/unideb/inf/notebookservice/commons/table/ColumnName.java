package hu.unideb.inf.notebookservice.commons.table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColumnName {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UserColumnName {

        public static final String COLUMN_NAME_USER_ID = "user_id";

        public static final String COLUMN_NAME_USERNAME = "username";

        public static final String COLUMN_NAME_PASSWORD = "password";

        public static final String COLUMN_NAME_ROLE = "role";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ServiceColumnName {

        public static final String COLUMN_NAME_SERVICE_ID = "service_id";

        public static final String COLUMN_NAME_STATUS = "status";

        public static final String COLUMN_NAME_START_DATE = "start_date";

        public static final String COLUMN_NAME_END_DATE = "end_date";

        public static final String COLUMN_NAME_FAULT = "fault";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ModificationColumnName {

        public static final String COLUMN_NAME_MODIFICATION_ID = "modification_id";

        public static final String COLUMN_NAME_NAME = "name";

        public static final String COLUMN_NAME_PRICE = "price";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ProductColumnName {

        public static final String COLUMN_NAME_PRODUCT_ID = "product_id";

        public static final String COLUMN_NAME_DESCRIPTION = "description";

        public static final String COLUMN_NAME_TYPE = "type";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ClientColumnName {

        public static final String COLUMN_NAME_CLIENT_ID = "client_id";

        public static final String COLUMN_NAME_FIRST_NAME = "first_name";

        public static final String COLUMN_NAME_LAST_NAME = "last_name";

        public static final String COLUMN_NAME_EMAIL = "email";

        public static final String COLUMN_NAME_PHONE = "phone";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BrandColumName {

        public static final String COLUMN_NAME_BRAND_ID = "brand_id";

        public static final String COLUMN_NAME_NAME = "name";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ReferencedColumName{

        public static final String REFERENCED_COLUM_NAME_ID = "id";

    }
}
