package samples.riskManagement.coreServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cybersource.authsdk.core.MerchantConfig;

import Api.DecisionManagerApi;
import Data.Configuration;
import Invokers.ApiClient;
import Invokers.ApiException;
import Model.CreateBundledDecisionManagerCaseRequest;
import Model.RiskV1DecisionsPost201Response;
import Model.Riskv1decisionsClientReferenceInformation;
import Model.Riskv1decisionsMerchantDefinedInformation;
import Model.Riskv1decisionsOrderInformation;
import Model.Riskv1decisionsOrderInformationAmountDetails;
import Model.Riskv1decisionsOrderInformationBillTo;
import Model.Riskv1decisionsPaymentInformation;
import Model.Riskv1decisionsPaymentInformationCard;

/**
 * 
 * This is the sample code for Decision Manager Request With Details of Merchant
 * Defined Information
 *
 */
public class DecisionManagerWithMerchantDefinedInformation {
	private static Properties merchantProp;
	private static String responseCode = null;
	private static String status = null;
	private static RiskV1DecisionsPost201Response response;

	/**
	 * Function to create Decision Manager Case Request
	 * 
	 * @return
	 * @throws Exception
	 */
	public static CreateBundledDecisionManagerCaseRequest getRequest(
			CreateBundledDecisionManagerCaseRequest CreateBundledDecisionManagerCaseRequest) throws Exception {
		CreateBundledDecisionManagerCaseRequest = new CreateBundledDecisionManagerCaseRequest();

		// set Client reference information
		Riskv1decisionsClientReferenceInformation riskv1decisionsClientReferenceInformation = new Riskv1decisionsClientReferenceInformation();
		riskv1decisionsClientReferenceInformation.code("54323007");
		CreateBundledDecisionManagerCaseRequest.clientReferenceInformation(riskv1decisionsClientReferenceInformation);

		Riskv1decisionsPaymentInformation riskv1decisionsPaymentInformation = new Riskv1decisionsPaymentInformation();
		Riskv1decisionsPaymentInformationCard riskv1decisionsPaymentInformationCard = new Riskv1decisionsPaymentInformationCard();
		riskv1decisionsPaymentInformationCard.setNumber("4444444444444448");
		riskv1decisionsPaymentInformationCard.expirationMonth("12");
		riskv1decisionsPaymentInformationCard.setExpirationYear("2020");
		riskv1decisionsPaymentInformation.card(riskv1decisionsPaymentInformationCard);
		CreateBundledDecisionManagerCaseRequest.paymentInformation(riskv1decisionsPaymentInformation);

		// set Order information
		Riskv1decisionsOrderInformation riskv1decisionsOrderInformation = new Riskv1decisionsOrderInformation();
		Riskv1decisionsOrderInformationAmountDetails riskv1decisionsOrderInformationAmountDetails = new Riskv1decisionsOrderInformationAmountDetails();
		riskv1decisionsOrderInformationAmountDetails.setTotalAmount("144.14");
		riskv1decisionsOrderInformationAmountDetails.setCurrency("USD");
		riskv1decisionsOrderInformation.amountDetails(riskv1decisionsOrderInformationAmountDetails);

		// set bill to information
		Riskv1decisionsOrderInformationBillTo riskv1decisionsOrderInformationBillTo = new Riskv1decisionsOrderInformationBillTo();
		riskv1decisionsOrderInformationBillTo.address1("96, powers street");
		riskv1decisionsOrderInformationBillTo.country("US");
		riskv1decisionsOrderInformationBillTo.locality("Clearwater milford");
		riskv1decisionsOrderInformationBillTo.firstName("James");
		riskv1decisionsOrderInformationBillTo.lastName("Smith");
		riskv1decisionsOrderInformationBillTo.phoneNumber("7606160717");
		riskv1decisionsOrderInformationBillTo.email("test@visa.com");
		riskv1decisionsOrderInformationBillTo.postalCode("03055");
		riskv1decisionsOrderInformationBillTo.administrativeArea("NH");
		riskv1decisionsOrderInformation.billTo(riskv1decisionsOrderInformationBillTo);
		CreateBundledDecisionManagerCaseRequest.orderInformation(riskv1decisionsOrderInformation);

		// Set Merchant Defined Information Details

		List<Riskv1decisionsMerchantDefinedInformation> riskv1decisionsMerchantDefinedInformationList = new ArrayList<Riskv1decisionsMerchantDefinedInformation>();
		Riskv1decisionsMerchantDefinedInformation riskv1decisionsMerchantDefinedInformation0 = new Riskv1decisionsMerchantDefinedInformation();
		riskv1decisionsMerchantDefinedInformation0.key("1");
		riskv1decisionsMerchantDefinedInformation0.value("Test1");
		riskv1decisionsMerchantDefinedInformationList.add(riskv1decisionsMerchantDefinedInformation0);

		Riskv1decisionsMerchantDefinedInformation riskv1decisionsMerchantDefinedInformation1 = new Riskv1decisionsMerchantDefinedInformation();
		riskv1decisionsMerchantDefinedInformation1.key("2");
		riskv1decisionsMerchantDefinedInformation1.value("Test2");
		riskv1decisionsMerchantDefinedInformationList.add(riskv1decisionsMerchantDefinedInformation1);

		CreateBundledDecisionManagerCaseRequest.setMerchantDefinedInformation(riskv1decisionsMerchantDefinedInformationList);
		return CreateBundledDecisionManagerCaseRequest;
	}

	public static void main(String args[]) throws Exception {
		try {
			CreateBundledDecisionManagerCaseRequest CreateBundledDecisionManagerCaseRequest = null;

			// Create the Create Decision Manager Request
			CreateBundledDecisionManagerCaseRequest = getRequest(CreateBundledDecisionManagerCaseRequest);

			// set Merchant Details
			merchantProp = Configuration.getMerchantDetails();

			MerchantConfig merchantConfig = new MerchantConfig(merchantProp);
			
			ApiClient apiClient = new ApiClient();
			
			apiClient.merchantConfig = merchantConfig;	
			DecisionManagerApi decisionManagerApi = new DecisionManagerApi(apiClient);
			response = decisionManagerApi.createBundledDecisionManagerCase(CreateBundledDecisionManagerCaseRequest);
			responseCode = apiClient.responseCode;
			status = apiClient.status;

			System.out.println("ResponseCode :" + responseCode);
			System.out.println("Status :" + status);
			System.out.println(response);

		} catch (ApiException e) {
			e.printStackTrace();
		}

	}
}