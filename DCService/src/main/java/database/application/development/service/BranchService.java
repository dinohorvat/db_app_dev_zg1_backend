package database.application.development.service;

import database.application.development.model.domain.Branch;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;

public interface BranchService {

    Response<Branch> getBranchById(Request<ApplicationInputs> request);

    Response<Branch> createBranch(Request<ApplicationInputs> request);

    Response<Branch> updateBranch(Request<ApplicationInputs> request);

    void deleteBranch(Request<ApplicationInputs> request);
}
