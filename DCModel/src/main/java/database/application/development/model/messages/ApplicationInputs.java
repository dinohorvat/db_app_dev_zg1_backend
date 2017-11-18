package database.application.development.model.messages;

import database.application.development.model.domain.*;
import database.application.development.model.util.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationInputs {
    private int entityId;

    private Branch branch;
    private Company company;
    private Customer customer;
    private DcsDate date;
    private Employee employee;
    private Location location;
    private Product product;
    private RewardPoints rewardPoints;
    private RewardPolicy rewardPolicy;
    private Transactions transaction;
    private Currency currency;

    /**
     * Object representing an email. This object does NOT represent a database table, as the above.
     */
    private Email email;

    public ApplicationInputs setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public ApplicationInputs setCompany(Company company) {
        this.company = company;
        return this;
    }

    public ApplicationInputs setBranch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public ApplicationInputs setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public ApplicationInputs setDate(DcsDate date) {
        this.date = date;
        return this;
    }

    public ApplicationInputs setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public ApplicationInputs setLocation(Location location) {
        this.location = location;
        return this;
    }

    public ApplicationInputs setProduct(Product product) {
        this.product = product;
        return this;
    }

    public ApplicationInputs setRewardPoints(RewardPoints rewardPoints) {
        this.rewardPoints = rewardPoints;
        return this;
    }

    public ApplicationInputs setRewardPolicy(RewardPolicy rewardPolicy) {
        this.rewardPolicy = rewardPolicy;
        return this;
    }

    public ApplicationInputs setTransaction(Transactions transaction) {
        this.transaction = transaction;
        return this;
    }

    public ApplicationInputs setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public ApplicationInputs setEmail(Email email) {
        this.email = email;
        return this;
    }
}
