package com.group21.jobTracker.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Class to represent the confirm dialog for UI. 
 */
@SuppressWarnings("serial")
public class ConfirmDialog extends Dialog {

	/**
	 * Constructor
	 * @param caption for the dialog
	 * @param text for the dialog
	 * @param confirmButtonText confirmation text
	 * @param confirmListener action for the dialog
	 */
    public ConfirmDialog(String caption, String text, String confirmButtonText,
            Runnable confirmListener) {

        final VerticalLayout content = new VerticalLayout();
        content.setPadding(false);
        add(content);

        add(new H3(caption));
        add(new Span(text));

        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setPadding(false);
        add(buttons);

        final Button confirm = new Button(confirmButtonText, e -> {
            confirmListener.run();
            close();
        });
        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttons.add(confirm);

        final Button cancel = new Button("Cancel", e -> close());
        buttons.add(cancel);

    }

}
