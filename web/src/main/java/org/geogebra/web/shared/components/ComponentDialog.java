package org.geogebra.web.shared.components;

import org.geogebra.web.html5.gui.FastClickHandler;
import org.geogebra.web.html5.gui.GPopupPanel;
import org.geogebra.web.html5.gui.view.button.StandardButton;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.util.Persistable;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base dialog material design component
 */
public class ComponentDialog extends GPopupPanel implements Persistable, ResizeHandler {

	private Label title;
	private FlowPanel dialogContent;

	/**
	 * based dialog constructor
	 * @param app - see {@link AppW}
	 * @param dialogData - contains trans keys for title and buttons
	 * @param autoHide - if the dialog should be closed on click outside
	 * @param hasScrim - background should be greyed out
	 */
	public ComponentDialog(AppW app, DialogData dialogData, boolean autoHide,
						   boolean hasScrim) {
		super(autoHide, app.getPanel(), app);
		setGlassEnabled(hasScrim);
		this.setStyleName("dialogComponent");
		buildDialog(dialogData);
		Window.addResizeHandler(this);
	}

	private void  buildDialog(DialogData dialogData) {
		FlowPanel dialogMainPanel = new FlowPanel();
		dialogMainPanel.addStyleName("dialogMainPanel");

		addTitleOfDialog(dialogMainPanel, dialogData.getTitleTransKey());
		createEmptyDialogContent(dialogMainPanel);
		if (dialogData.getNegativeBtnTransKey() != null ||
				dialogData.getPositiveBtnTransKey() != null) {
			addButtonsOfDialog(dialogMainPanel, dialogData);
		}

		this.add(dialogMainPanel);
	}

	private void addTitleOfDialog(FlowPanel dialogMainPanel, String titleTransKey) {
		if (titleTransKey == null) {
			return;
		}

		title = new Label(getApplication().getLocalization().getMenu(titleTransKey));
		title.setStyleName("dialogTitle");
		dialogMainPanel.add(title);
	}

	private void createEmptyDialogContent(FlowPanel dialogMainPanel) {
		dialogContent = new FlowPanel();
		dialogContent.addStyleName("dialogContent");
		dialogMainPanel.add(dialogContent);
	}

	private void addButtonsOfDialog(FlowPanel dialogMainPanel, DialogData dialogData) {
		FlowPanel dialogButtonPanel = new FlowPanel();
		dialogButtonPanel.setStyleName("dialogBtnPanel");

		addNegativeButton(dialogButtonPanel, dialogData.getNegativeBtnTransKey());
		addPositiveButton(dialogButtonPanel, dialogData.getPositiveBtnTransKey());

		dialogMainPanel.add(dialogButtonPanel);
	}

	private void addNegativeButton(FlowPanel dialogButtonPanel, String negTransKey) {
		if (negTransKey == null) {
			return;
		}

		StandardButton negButton = new StandardButton(negTransKey, getApplication());
		negButton.setStyleName("materialTextButton");

		FastClickHandler negBtnClickHandler = new FastClickHandler() {
			@Override
			public void onClick(Widget source) {
				ComponentDialog.super.hide();
			}
		};

		negButton.addFastClickHandler(negBtnClickHandler);
		dialogButtonPanel.add(negButton);
	}

	private void addPositiveButton(FlowPanel dialogButtonPanel, String posTransKey) {
		if (posTransKey == null) {
			return;
		}

		StandardButton posButton = new StandardButton(posTransKey, getApplication());
		posButton.setStyleName("materialContainedButton");

		FastClickHandler posBtnClickHandler = new FastClickHandler() {
			@Override
			public void onClick(Widget source) {
				onPositiveAction();
			}
		};

		posButton.addFastClickHandler(posBtnClickHandler);
		dialogButtonPanel.add(posButton);
	}

	/**
	 * fills the dialog with content
	 * @param content - content of the dialog
	 */
	public void addDialogContent(IsWidget content) {
		dialogContent.add(content);
	}

	/**
	 * only hides the dialog, this should be overwritten in the dialog
	 * to define what should happen on positive action, if any
	 */
	public void onPositiveAction() {
		hide();
	}

	@Override
	public void show() {
		super.show();
		super.center();
	}

	@Override
	public void onResize(ResizeEvent resizeEvent) {
		if (isShowing()) {
			super.center();
		}
	}

	private boolean isEnter(int key) {
		return key == KeyCodes.KEY_ENTER;
	}

	@Override
	protected void onPreviewNativeEvent(Event.NativePreviewEvent event) {
		Event nativeEvent = Event.as(event.getNativeEvent());
		switch (event.getTypeInt()) {
			case Event.ONKEYPRESS:
				if (isEnter(nativeEvent.getCharCode())) {
					onPositiveAction();
				}
		}
	}
}
