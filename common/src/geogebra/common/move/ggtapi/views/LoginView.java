package geogebra.common.move.ggtapi.views;

import geogebra.common.move.ggtapi.models.json.JSONObject;
import geogebra.common.move.views.BaseView;
import geogebra.common.move.views.SuccessErrorRenderable;

import java.util.Iterator;

/**
 * @author gabor
 *  View class for Login functionality
 */
public class LoginView extends BaseView<SuccessErrorRenderable> {
	
	/**
	 * Creates a new Login view
	 */
	public LoginView() {
		super();
	}

	/**
	 * @param response from GGT
	 * sets the successfull response 
	 */
	public void loginSuccess(JSONObject response) {
		Iterator<SuccessErrorRenderable> views = this.viewComponents.iterator();
		while (views.hasNext()) {
			views.next().success(response);
		}		
	}

	/**
	 * @param response from GGT
	 * Error happened, must be shown if view
	 */
	public void loginError(JSONObject response) {
		Iterator<SuccessErrorRenderable> views = this.viewComponents.iterator();
		while (views.hasNext()) {
			views.next().fail(response);
		}		
	}

}
