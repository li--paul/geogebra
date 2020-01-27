package org.geogebra.web.full.gui.dialog;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import org.geogebra.web.full.gui.view.algebra.InputPanelW;
import org.geogebra.web.html5.gui.util.FormLabel;
import org.geogebra.web.html5.main.AppW;

public class MediaInputPanel extends FlowPanel {

	private AppW app;
	private OptionDialog parentDialog;

	protected InputPanelW inputField;
	private FormLabel inputLabel;
	private Label errorLabel;
	private Label infoLabel;

	public MediaInputPanel(AppW app, OptionDialog parentDialog) {
		this.app = app;
		this.parentDialog = parentDialog;

		setStyleName("mowMediaDialogContent");
		addStyleName("emptyState");

		inputField = new InputPanelW("", app, 1, 25, false);
		inputLabel = new FormLabel().setFor(inputField.getTextComponent());
		inputLabel.addStyleName("inputLabel");
		inputField.getTextComponent().getTextBox().getElement().setAttribute(
				"placeholder", app.getLocalization().getMenu("pasteLink"));
		inputField.addStyleName("inputText");
		add(inputLabel);
		add(inputField);
		errorLabel = new Label();
		errorLabel.addStyleName("msgLabel errorLabel");
		add(errorLabel);

		infoLabel = new Label();
		infoLabel.addStyleName("msgLabel");
		add(infoLabel);

		addHoverHandlers();
		addFocusBlurHandlers();
		addInputHandler();

		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			@Override
			public void execute() {
				inputField.getTextComponent().setFocus(true);
			}
		});
	}

	public void setLabels() {
		inputLabel.setText(app.getLocalization().getMenu("Link"));
		infoLabel.setText("");
		errorLabel.setText(""); // actual error set in showError
	}

	/**
	 * @return trimmed input text
	 */
	public String getInput() {
		return inputField.getText().trim();
	}

	public void showError(String msg) {
		setStyleName("mowMediaDialogContent");
		addStyleName("errorState");
		errorLabel.setText(app.getLocalization().getMenu("Error") + ": "
				+ app.getLocalization().getError(msg));
		parentDialog.setPrimaryButtonEnabled(false);
	}

	/**
	 * @param info
	 *            permanent information message
	 */
	public void showInfo(String info) {
		infoLabel.setText(info);
	}

	public void resetError() {
		setStyleName("mowMediaDialogContent");
		addStyleName("emptyState");
		removeStyleName("errorState");
		parentDialog.setPrimaryButtonEnabled(!"".equals(inputField.getText()));
	}

	/**
	 * Input changed (paste or key event happened)
	 */
	protected void onInput() {
		resetError();
		addStyleName("focusState");
		removeStyleName("emptyState");
	}

	/**
	 * Add handler for input event
	 */
	private void addInputHandler() {
		inputField.getTextComponent().getTextBox().addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeEvent()
						.getKeyCode() == KeyCodes.KEY_ENTER) {
					parentDialog.processInput();
				} else {
					onInput();
				}
			}
		});

		nativeOn(inputField.getTextComponent().getInputElement());
	}

	private native void nativeOn(Element img) /*-{
        var that = this;
        img.addEventListener("input", function () {
            that.@org.geogebra.web.full.gui.dialog.MediaInputPanel::onInput()();
        });
    }-*/;

	/**
	 * Add mouse over/ out handlers
	 */
	private void addHoverHandlers() {
		inputField.getTextComponent().getTextBox().addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				MediaInputPanel.this.addStyleName("hoverState");
			}
		});

		inputField.getTextComponent().getTextBox().addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				MediaInputPanel.this.removeStyleName("hoverState");
			}
		});
	}

	private void addFocusBlurHandlers() {
		inputField.getTextComponent().getTextBox().addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(FocusEvent event) {
				setFocusState();
			}
		});

		inputField.getTextComponent().getTextBox().addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				resetInputField();
			}
		});
	}

	/**
	 * sets the style of InputPanel to focus state
	 */
	private void setFocusState() {
		setStyleName("mowMediaDialogContent");
		addStyleName("focusState");
	}

	/**
	 * Resets input style on blur
	 */
	private void resetInputField() {
		removeStyleName("focusState");
		addStyleName("emptyState");
	}
}
