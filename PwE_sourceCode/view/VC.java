package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import util.Anch;
import util.FC;
import util.Triple;

public class VC {
	
	public static void setBack(Background bg, TextField...fields) {
		for(TextField t : fields) {
			t.setBackground(bg);
		}
	}	
	
	public static Button getOcStBut(Anch an, String name, double frac, double w, 
			double h, EventHandler<ActionEvent> handler) {
		Button toReturn = new Button();
		toReturn.setText(name);
		toReturn.setFont(getFont(frac));
		setAn(an, w, h, toReturn);
		toReturn.setOnAction(handler);
		return toReturn;
	}
	
	public static ArrayList<String> addStrings(ArrayList<String> list, String...t) {
		for(String string : t) {
			list.add(string);
		}
		return list;
	}
	
	public static ArrayList<TextField> addTexts(ArrayList<TextField> list, TextField...t) {
		for(TextField text : t) {
			list.add(text);
		}
		return list;
	} 
	
	public static void setTextFieldValid(Button b, TextField t1, TextField t2) {
		t1.setBackground(FC.empBa);
		t1.setBorder(FC.encBord);
		t2.setBackground(FC.empBa);
		t2.setBorder(FC.encBord);
		b.setDisable(false);
	}
	
	public static void setTextFieldError(Button b, TextField t1, TextField t2) {
		t1.setBackground(FC.newMPErrBa);
		t1.setBorder(FC.newMPErrBord);
		t2.setBackground(FC.newMPErrBa);
		t2.setBorder(FC.newMPErrBord);
		b.setDisable(true);
	}

	public static void setTextFieldValid(Button b, TextField tf) {
		tf.setBackground(FC.empBa);
		tf.setBorder(FC.encBord);
		b.setDisable(false);
	}
	
	public static void setTextFieldError(Button b, TextField tf) {
		tf.setBackground(FC.newMPErrBa);
		tf.setBorder(FC.newMPErrBord);
		b.setDisable(true);
	}
	
	
	public static TextField getOcTField(String p, String t, int row, int col, double pw, double ph, double f) {
		TextField toReturn = new TextField();
		toReturn.setPromptText(p);
		toReturn.setText(t);
		GridPane.setRowIndex(toReturn, row);
		GridPane.setColumnIndex(toReturn, col);
		
		toReturn.setPrefHeight(ph);
		toReturn.setPrefWidth(pw);
		if(row % 5 != 3) {
			GridPane.setColumnSpan(toReturn, GridPane.REMAINING);
		}
		
		toReturn.setFont(getFont(f));
		toReturn.setBorder(FC.encBord);
		return toReturn;
	}
	
	public static ScrollPane getNewScP(Anch an, double w, double h) {
		ScrollPane toReturn = new ScrollPane() {
			@Override
			public void requestFocus() {
			}
		};
		toReturn.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		toReturn.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		setAn(an, w, h, toReturn);
		return toReturn;
	}
	
	
	public static AnchorPane getNewAnP(Anch an, double w, double h) {
		AnchorPane toReturn = new AnchorPane();
		setAn(an, w, h, toReturn);
		return toReturn;
	}

	public static CheckBox getNewCB(String name, double frac, Anch an, double w, double h) {
		CheckBox toReturn = new CheckBox();
		toReturn.setText(name);
		toReturn.setFont(getFont(frac));
		toReturn.setContentDisplay(ContentDisplay.RIGHT);
		setAn(an, w, h, toReturn);
		toReturn.setBackground(FC.checkBoxBa);
		toReturn.setWrapText(true);
		return toReturn;
	}

	public static Label getNewImLa(ImageView img, Anch an, double width, double height) {
		
		Label toReturn = new Label();
		img.fitWidthProperty().bind(toReturn.widthProperty());
		img.fitHeightProperty().bind(toReturn.heightProperty());
		toReturn.setGraphic(img);
		setAn(an, width, height, toReturn);
		
		return toReturn;
	}
	
	public static Button getNewImBut(ImageView img, double w, double he, EventHandler<ActionEvent> h) {
		Button toReturn = new Button() {
			@Override
			public void requestFocus() {
			}
		};
		toReturn.setPrefWidth(w);
		toReturn.setPrefHeight(he);
		img.fitWidthProperty().bind(toReturn.prefWidthProperty());
		img.fitHeightProperty().bind(toReturn.prefHeightProperty());
		toReturn.setGraphic(img);
		toReturn.setOnAction(h);
		return toReturn;
	}

	public static Button getNewImBut(ImageView img, Anch anch, double w, double he, EventHandler<ActionEvent> h) {
		
		Button toReturn = new Button();
		img.fitWidthProperty().bind(toReturn.widthProperty());
		img.fitHeightProperty().bind(toReturn.heightProperty());
		toReturn.setGraphic(img);
		setAn(anch, w, he, toReturn);
		toReturn.setOnAction(h);
		return toReturn;
	}

	public static Button getNewBut(String name, Anch anch, Triple triple, boolean bool, EventHandler<ActionEvent> h) {
		
		Button toReturn;
		if(bool) {
			toReturn = new Button();
		} else {
			toReturn = new Button() {
				@Override
				public void requestFocus() {
				}
			};
		}
		
		toReturn.setText(name);
		toReturn.setFont(getFont((triple.getF() + triple.getS()) / triple.getT()));
		setAn(anch, triple.getF(), triple.getS(), toReturn);
		toReturn.setOnAction(h);
		return toReturn;
	}
	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static Button getNewSimpleButton(String name, double font, double wi, double he, Anch an, EventHandler<ActionEvent> h) {
		Button toReturn = new Button();
		toReturn.setText(name);
		toReturn.setFont(getFont(font));
		setAn(an, wi, he, toReturn);
		toReturn.setOnAction(h);
		return toReturn;
	}
	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static RadioButton getNewRadioButton(String name, double font) {
		RadioButton toReturn = new RadioButton(name);
		toReturn.setContentDisplay(ContentDisplay.RIGHT);
		toReturn.setFont(getFont(font));
		return toReturn;
	}
	
	
	public static Label getNewLa(String name, Anch anch, Triple triple) {
		
		Label toReturn = new Label();
		toReturn.setText(name);
		setAn(anch, triple.getF(), triple.getS(), toReturn);
		toReturn.setFont(getFont((triple.getF() + triple.getS()) / triple.getT()));
		
		toReturn.setWrapText(true);
		return toReturn;
	}
	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static Label getNewLa(String name, double frac, Anch an, double wi, double he) {
		Label toReturn = new Label();
		toReturn.setText(name);
		toReturn.setFont(getFont(frac));
		setAn(an, wi, he, toReturn);
		toReturn.setWrapText(true);
		return toReturn;
	}

	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static ComboBox<Integer> getCombobox(int min, int max, Anch an, double wi, double he) {
		ComboBox<Integer> toReturn = new ComboBox<Integer>();
		for(int j = min; j <= max; j++) {
			toReturn.getItems().add(j);
		}
		setAn(an, wi, he, toReturn);
		return toReturn;
	}
	
	
	public static void setPwdTextBindings(PasswordField pwd, TextField txt, CheckBox box) {
		pwd.managedProperty().bind(box.selectedProperty().not());
		pwd.visibleProperty().bind(box.selectedProperty().not());
		
		txt.managedProperty().bind(box.selectedProperty());
		txt.visibleProperty().bind(box.selectedProperty());
	}
	
	public static void resetFields(TextField...fields) {
		for(TextField f : fields) {
			f.setText("");
		}
	}
	
	public static TextField getNewTField(String name, Anch anch, Triple triple) {
		
		TextField toReturn = new TextField();
		toReturn.setPromptText(name);
		setAn(anch, triple.getF(), triple.getS(), toReturn);
		toReturn.setFont(getPwdFont((triple.getF() + triple.getS()) / triple.getT()));
		toReturn.setBorder(FC.encBord);
		return toReturn;
	}
	
	public static TextField getNewTField(String name, Anch anch, Triple triple, double w, double h) {
		
		TextField toReturn = new TextField();
		toReturn.setPromptText(name);
		setAn(anch, w, h, toReturn);
		toReturn.setFont(getPwdFont((triple.getF() + triple.getS()) / triple.getT()));
		toReturn.setBorder(FC.encBord);
		return toReturn;
	}
	
	public static PasswordField getNewPwdField(String name, Anch anch, Triple triple) {
		
		PasswordField toReturn = new PasswordField();
		toReturn.setPromptText(name);
		setAn(anch, triple.getF(), triple.getS(), toReturn);
		toReturn.setFont(getPwdFont((triple.getF() + triple.getS()) / triple.getT()));
		toReturn.setBorder(FC.encBord);
		return toReturn;
	}
	
	public static PasswordField getNewPwdField(String name, Anch anch, Triple triple, double w, double h) {
		
		PasswordField toReturn = new PasswordField();
		toReturn.setPromptText(name);
		setAn(anch, w, h, toReturn);
		toReturn.setFont(getPwdFont((triple.getF() + triple.getS()) / triple.getT()));
		toReturn.setBorder(FC.encBord);
		return toReturn;
	}
	
	
	public static void decoratePane(AnchorPane pane, Border border, Background back) {
		pane.setBorder(border);
		pane.setBackground(back);
	}
	
	public static void clearPane(AnchorPane pane) {
		pane.getChildren().clear();
		pane.setBorder(null);
		pane.setBackground(null);
	}
	
	
	public static void setNodesUsable(Node...nodes) {
		setDisSt(false, nodes);
		setVis(true, nodes);
	}
	
	public static void setNodesUnusable(Node...nodes) {
		setDisSt(true, nodes);
		setVis(false, nodes);
	}
	
	
	public static void setDisSt(boolean state, Node...nodes) {
		for(Node n : nodes) {
			n.setDisable(state);
		}
	}
	
	public static void setVis(boolean state, Node...nodes) {
		for(Node n : nodes) {
			n.setVisible(state);
		}
	}
	
	public static void setAn(Anch an, double width, double height, Node node)  {
		AnchorPane.setLeftAnchor(node, an.left() * width);
		AnchorPane.setRightAnchor(node, an.right() * width);
		AnchorPane.setTopAnchor(node, an.top() * height);
		AnchorPane.setBottomAnchor(node, an.bottom() * height);
	}
	
	public static Font getPwdFont(double size) {
		return Font.font("Verdana", size);
	}
	
	public static Font getFont(double size) {
    	return Font.font("Verdana", FontPosture.ITALIC, size);
    }
	
	public static Font getTitleFont(double size) {
		return Font.font("Verdana", FontWeight.BOLD, size);
	}
}






