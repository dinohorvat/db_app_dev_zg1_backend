package database.application.development.model.util;

import database.application.development.model.domain.Product;

public class Views {

    //CORE CLASSES
    public interface Response{}

    public interface PrimitiveField extends Response{}

    public interface Company extends PrimitiveField{}

    public interface Location extends PrimitiveField{}

    public interface Customer extends PrimitiveField{}

    public interface Branch extends PrimitiveField{}

    public interface Employee extends PrimitiveField{}

    public interface Product extends PrimitiveField{}

    public interface Transaction extends PrimitiveField{}


    //HST FIELDS
    public interface HstCompany extends Company{}

    public interface HstBranch extends Branch{}

    public interface HstEmployee extends Branch{}

    public interface HstTransaction extends Transaction{}

    public interface HstCustomer extends Transaction{}

    public interface HstProduct extends Transaction{}


    //COMPLEX FIELDS
    public interface ComplexFieldBranch {}

    public interface ComplexFieldCompany {}

    public interface ComplexFieldEmployee {}

    public interface ComplexFieldTransaction{}

    public interface ComplexFieldProduct{}

    public interface ComplexFieldCustomer{}


    //REQUEST ENCODING
    public static class RequestToCompany implements Company, Location, HstCompany{}

    public static class RequestToBranch implements Location, Branch, HstBranch{}

    public static class RequestToCustomer implements Customer, HstCustomer, ComplexFieldProduct, ComplexFieldTransaction{}

    public static class RequestToProduct implements Product, HstProduct{}

    public static class RequestToTransaction implements Transaction, ComplexFieldProduct, ComplexFieldCustomer{}

    public static class RequestToEmployee implements Employee, HstEmployee {}

    public static class RequestToLocation implements Location {}


    //REQUEST TO HST
    public static class RequestToBranchHistory implements PrimitiveField, ComplexFieldBranch{}

    public static class RequestToCompanyHistory implements PrimitiveField, ComplexFieldCompany{}

    public static class RequestToCustomerHistory implements PrimitiveField, ComplexFieldCustomer{}

    public static class RequestToEmployeeHistory implements PrimitiveField, ComplexFieldEmployee{}

    public static class RequestToProductHistory implements PrimitiveField, ComplexFieldProduct {}

    //TODO: implement transaction and add view


}
