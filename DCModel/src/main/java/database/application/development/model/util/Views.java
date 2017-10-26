package database.application.development.model.util;

public class Views {

    //CORE CLASSES
    public interface Response{}

    public interface PrimitiveField extends Response{}

    public interface Company extends PrimitiveField{}

    public interface Location extends PrimitiveField{}

    public interface Customer extends PrimitiveField{}

    public interface Branch extends PrimitiveField{}

    public interface Employee extends PrimitiveField{}

    public interface Transaction extends PrimitiveField{}


    //HST FIELDS
    public interface HstCompany extends Company{}

    public interface HstBranch extends Branch{}

    public interface HstTransaction extends Transaction{}


    //COMPLEX FIELDS
    public interface ComplexFieldBranch {}

    public interface ComplexFieldCompany {}

    public interface ComplexFieldEmployee {}

    public interface ComplexFieldTransaction{}

    public interface ComplexFieldProduct{}

    public interface ComplexFiledCustomer{}


    //REQUEST ENCODING
    public static class RequestToCompany implements Company, Location, HstCompany{}

    public static class RequestToBranch implements Location, Branch, HstBranch{}

    public static class RequestToTransaction implements Transaction, ComplexFieldProduct, ComplexFiledCustomer{}

    public static class RequestToEmployee implements Employee {}

    public static class RequestToCompanyHistory implements PrimitiveField, ComplexFieldCompany{}

}
