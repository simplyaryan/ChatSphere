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
    public mainView() throws SQLException {
        //name feild
       textField = new TextField();
        textField.setLabel("Name");
        textField.setRequiredIndicatorVisible(true);
        textField.setErrorMessage("This field is required");

        //login button
        Button login = new Button("Button");
        VerticalLayout Layout = new VerticalLayout(textField,login);
        add(Layout);
        login.addClickListener(clickEvent -> {
            name = textField.getValue();
            try {
                chatView();
                Layout.remove(textField,login);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

    }

    public void chatView() throws SQLException {
  
        grid = new Grid<>(chat.class, true);
        ArrayList<chat> chat = database.get();
        grid.setItems(chat);
        TextField chatfield = new TextField();
        chatfield.setLabel("Chat");
        TextField submit = new TextField();
        submit.setLabel("Submit");
        submit.setRequiredIndicatorVisible(true);
        Button submitButton = new Button("Button");
        add(grid,submit,submitButton);
        submitButton.addClickListener(clickEvent -> {
            name = textField.getValue();
            try {
                database.commit(name, dateandtime.getTime(),  dateandtime.getDate(), submit.getValue());
                submit.clear();
            } catch (SQLException e) {
               
                e.printStackTrace();
            }
        });
        UI.getCurrent().setPollInterval(1500);
        UI.getCurrent().addPollListener(event -> {
            try{
                gridUpdate();
            }
            catch (SQLException e){
                e.printStackTrace(); 
            }
        });
    }
    public void gridUpdate() throws SQLException {
        ArrayList<chat> people = database.get();
        grid.setItems(people);
    }

}
    

