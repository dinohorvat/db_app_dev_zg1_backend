package database.application.development.model.util;

public class Views {

    public interface Response{}

    public interface PrimitiveField extends Response{}

    public interface Company extends PrimitiveField{}

    public interface Location extends PrimitiveField{}

    public interface Customer extends PrimitiveField{}

    public interface HstCompany extends Company{}

    public interface ComplexFieldCompany {}


    public static class RequestToCompany implements Company, Location, HstCompany{}

    public static class RequestToCompanyHistory implements PrimitiveField, ComplexFieldCompany{}

}
