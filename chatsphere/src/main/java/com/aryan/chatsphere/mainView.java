package com.aryan.chatsphere;

import java.sql.SQLException;
import java.util.ArrayList;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/")
public class mainView extends VerticalLayout {
    private String name;
    private Grid grid;
    private TextField textField;

    // Constructor
    public mainView() throws SQLException {
        // Name field
        textField = new TextField();
        textField.setLabel("Name");
        textField.setRequiredIndicatorVisible(true);
        textField.setErrorMessage("This field is required");

        // Login button
        Button login = new Button("Login");
        VerticalLayout Layout = new VerticalLayout(textField, login);
       
        add(Layout);

        // Login button click listener
        login.addClickListener(clickEvent -> {
            name = textField.getValue();
            try {
                chatView();
                Layout.remove(textField, login);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    // Chat view method
    public void chatView() throws SQLException {
        // Initialize grid
        grid = new Grid<>(chat.class, true);
        ArrayList<chat> chat = database.get();
        grid.setItems(chat);

        // Chat and submit fields
        TextField chatfield = new TextField();
        chatfield.setLabel("Chat");
        TextField submit = new TextField();
        submit.setLabel("Chat");
        submit.setRequiredIndicatorVisible(true);

        // Submit button
        Button submitButton = new Button("Send");
        add(grid, submit, submitButton);

        // Submit button click listener
        submitButton.addClickListener(clickEvent -> {
            name = textField.getValue();
            try {
                // Commit chat to database
                database.commit(name, dateandtime.getTime(), dateandtime.getDate(), submit.getValue());
                submit.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Update grid periodically
        UI.getCurrent().setPollInterval(1500);
        UI.getCurrent().addPollListener(event -> {
            try{
                gridUpdate();
            } catch (SQLException e){
                e.printStackTrace(); 
            }
        });
    }

    // Update grid method
    public void gridUpdate() throws SQLException {
        ArrayList<chat> people = database.get();
        grid.setItems(people);
    }
}
