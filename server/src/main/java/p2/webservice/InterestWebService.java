package p2.webservice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import p2.model.Interest;
import p2.model.Product;
import p2.model.User;
import p2.service.impl.InterestService;
import p2.util.Glogger;
import p2.util.InterestStatus;

public class InterestWebService {
	
	private static Logger logger = Glogger.logger;
	
	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("userId").trim());
		int productId = Integer.parseInt(request.getParameter("productId").trim());
		int quantity = Integer.parseInt(request.getParameter("quantity").trim());

		Interest interest = new Interest(new User(userId), new Product(productId),quantity, InterestStatus.DEAL_PENDING.value);
		
		try {
			if (InterestService.insert(interest) != -1) {
				response.getWriter().append("Suceessfully Added").close();
			} else {
				response.getWriter().close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
