package model;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.controlsfx.control.textfield.TextFields;

import controller.Controller;
import controller.LogoDatabase;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
//import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
//import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
//import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import util.Anch;
import util.FC;
import util.Language;
import util.Names;
import util.Triple;
//import util.Triple;
import view.VC;

/** @author tmf
 *  
 *  @date 18-02-28 import of Password class.
 *  					-> Rather avoid importing the Password class in Main.
 *  					-> Only controller should have access to Password. 
 * 
 */
import crypt.Password;
import crypt.PasswordSecurity;
import crypt.RunOutOfHashException;

public class MainApplication extends Application {

	public static Controller controller;
	public static LogoDatabase logos;
	public static Names names;
	public static Names Usernames;

	public static double wM = 0.11;
	public static double hM = 0.16;
	public static double mainFrac;
	public static int decButSel = 0;
	public static ArrayList<Boolean> decSel;
	public static Random rand = new Random();

	public static boolean decShSel = false;
	public static boolean decChSel = false;
	public static boolean decDelSel = false;
	
	public static boolean resetConfirmBool = false;
	public static boolean resetCancelled = false;
	
	

	/**
	 * View Control.
	 */
	private static boolean startFrameSelected = true;
	private static boolean newuserFrameSelected = false;
	private static boolean testpwdFrameSelected = false;
	private static boolean menuClVSel = false;
	private static boolean menuOpVSel = false;
	private static boolean encVSel = false;
	private static boolean decVSel = false;
	private static boolean newMPwdVSel = false;
	private static boolean encErrOcc = false;
	private static boolean decErrOccNoMP = false;
	private static boolean decErrOccNoPw = false;
	private static boolean userManOp = false;
	private static boolean userManComeFromLogin = false;
	
	
	/**
	 * Menu Closed View.
	 */
	private Stage stage;
	private Scene scene;
	private AnchorPane mainFrame;
	
	/**
	 * Start Screen View.
	 */
	private AnchorPane startFrame;
	private TextField logintext;
	private PasswordField loginpwd;
	private TextField loginpwdT;
	private Button loginOK;
	private Label loginhead;
	private Label loginnewuserlabel;
	private Button loginnewuser;
	private CheckBox loginshow;
	private MenuButton loginmenu;
	private Label loginnewhead;
	private AnchorPane startframeMsg;
	private Button startframeclMsg;
	private Label startframeNoUN;
	private Label startframeNoPwd;
	private Label startframeWrongPwd;
	private Label startframeWrongUN;
	// Menu elements
	private MenuItem loginmenuhelp;
	private MenuItem loginenglishitem;
	private MenuItem logingermanitem;
	private Menu loginmenulanguage;
	
	
	/**
	 * New user View.
	 */
	private AnchorPane newuserPane;
	private Button newuserback;
	private Button newuserdone;
	private TextField newusername;
	private RadioButton newuserchoosepwd;
	private RadioButton newusergenpwd;
	private ToggleGroup newusertoggle;
	private VBox newuserVBox;
	private CheckBox newuserincludespecial;
	private CheckBox newuserfixedlength;
	private ComboBox<Integer> newuserlengthchoices;
	private Label newuserUNtitle;
	private Label newuserPwdtitle;
	private PasswordField newuserPwd;
	private TextField newuserPwdT;
	private PasswordField newuserPwdRep;
	private TextField newuserPwdRepT;
	private CheckBox newusershow;
	private Label newuserlengthlabel;
	private AnchorPane newuserMsgPane;
	private Button newuserMsgCl;
	private Label newuserNoUN;
	private Label newuserExUN;
	private Label newuserMissPwd;
	private Label newuserMissRepPwd;
	private Label newuserWrongPwd;
	
	/**
	 * Menu Open View.
	 */
	private AnchorPane menuopenPane;
	private Button menuopenLogout;
	private Button menuopenencrypt;
	private Button menuopendecrypt;
	private Button menuopenTestpwd;
	private MenuButton menuopenmenu;
	private MenuItem menuopenhelp;
	private Menu menuopenlanguage;
	private MenuItem menuopenenglishitem;
	private MenuItem menuopengermanitem;
	private MenuItem menuopendeleteaccount;
	private MenuItem menuopenresetpwds;
	private MenuItem menuopenchangeMPwd;
	private Separator menuopenseparator;
	private AnchorPane menuopenMsg;
	private Button menuopenMsgCl;
	private Label menuopenWrongReset;
	private Label menuopenRightReset;
	private Label menuopenNoDecrypt;
	private Label menuopenNewMPwd;
	
	private AnchorPane menuopenNewPwdPane;
	private AnchorPane moMsg;
	private Button moMsgCl;
	private Button modone;
	private Label moMissOldPwd;
	private Label moMissPwd;
	private Label moMissRepPwd;
	private Label moNonEqualPwd;
	private Label moWrongPwd;	
	private Stage menuopenNewPwdStage;
	private Scene menuopenNewPwdScene;
	private PasswordField menuopenOldPwdP;
	private PasswordField menuopenNewPwdP;
	private PasswordField menuopenNewPwdRepP;
	private TextField menuopenOldPwdT;
	private TextField menuopenNewPwdT;
	private TextField menuopenNewPwdRepT;
	private RadioButton mochoosepwd;
	private RadioButton mogenpwd;
	private CheckBox moshow;
	private ComboBox<Integer> molengthchoices;
	private Label molengthlabel;
	private CheckBox mospecial;
	private CheckBox mofixed;
	private ToggleGroup motoggle;
	private VBox movbox;

	/**
	 * Settings.
	 */
	private AnchorPane userMan;
	private Button clUserMan;
	private Label userManTitle;
	private ScrollPane userManCon;
	private WebView browser;
	private WebEngine webEngine;
	private Label manI;

	/**
	 * Test-Passwords View.
	 */
	private AnchorPane testpwdPane;
	private Button testpwdBack;
	private RadioButton testpwdestimateOwn;
	private RadioButton testpwdestimateGen;
	private ToggleGroup testpwdtoggle;
	private VBox testpwdvbox;
	private CheckBox testpwdspecial;
	private CheckBox testpwdspace;
	private CheckBox testpwdnumbers;
	private CheckBox testpwdfixedlength;
	private ComboBox<Integer> testpwdchooselength;
	private Label testpwdlengthlabel;
	private TextField testpwdownfield;
	private Button testpwdEstimate;
	private Label testpwdHeader;
	private Label testpwdMsgLabel1;
	private Label testpwdMsgLabel2;
	private Label testpwdMsgLabel3;
	private Label testpwdMsgLabel4;
	private AnchorPane testpwdMsg;
	private Button testpwdMsgCl;
	
	/**
	 * Encrypt View.
	 */
	private AnchorPane encryptPane;
	private Button encryptBackButton;
	private ScrollPane encryptScroll;
	private ArrayList<PasswordField[]> encryptPwds;
	private ArrayList<TextField[]> encryptPwdsT;
	private ArrayList<TextField[]> encryptText;
	private ArrayList<AnchorPane> encryptBlocks;
	private CheckBox encryptShowPwds;
	private VBox encryptVbox;
	private AnchorPane encryptPlusMinusPane;
	private Button encryptPlus;
	private Button encryptMinus;
	
	private Button encryptSave;
	private AnchorPane encryptMsgPane;
	private Button encryptMsgCl;
	private Button encryptAgain;
	private Button encryptDone;
	private Label encryptErrorIcon;
	private Label encryptDoneIcon;
	private Label encryptDoneLabel;
	private Label encryptMuError;
	private Label encryptExError;
	private Label encryptMissError;
	private Label encryptNoEntry;
	private Label encryptDiff;
	private Button encryptDismiss;
	private Button encryptChange;
	private Button encryptOK;
	private Label encryptOverwriteQuest;
	
	
	/**
	 * Decrypt View.
	 */
	private AnchorPane decryptPane;
	private Button decryptBack;
	private Button decryptDelete;
	private Button decryptChange;
	private Button decryptShow;
	private ArrayList<Button> decryptPwds;
	private ScrollPane decryptScroll;
	private GridPane decryptContent;
	private AnchorPane decryptMsgPane;
	private Button decryptClI;
	private Label decryptDoneIcon;
	private Label decryptSuccessDel;
	private Label decryptSuccessOv;
	
	private Stage decryptShowStage;
	private Scene decryptShowScene;
	private Button decryptShowClose;
	private Button decryptShowChange;
	private ArrayList<TextField> decryptShowTexts;
	private ArrayList<String> decryptShowOldVals;
	private Button decryptShowOK;
	private ScrollPane decryptShowScroll;
	private GridPane decryptShowContent;
	private AnchorPane decryptShowButtonPane;
	private AnchorPane decryptShowMainFrame;
	

	public static void main(String[] args) throws MalformedURLException {

		logos = new LogoDatabase(FC.defaultLan, true);
		controller = new Controller();
		controller.initialIOSituation();
		names = new Names(FC.defaultLan);

		launch(args);
	}

	/**
	 * Initialization of all panels, called when application starts
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		mainFrame = new AnchorPane();

		mainFrac = (FC.inWi + FC.inHe) / FC.butDiv;		
		
		/**
		 * Start Screen View.
		 */
		startFrame = new AnchorPane();
		VC.setAn(FC.empty, 0.0, 0.0, startFrame);
		
		
		loginhead = new Label(names.loginTitle);
		loginhead.setFont(VC.getTitleFont((FC.inWi + FC.inHe) / (FC.titDiv + 0.5 * FC.titDiv)));
		VC.setAn(new Anch(0.15,0.2,0.05,0.78), FC.inWi, FC.inHe, loginhead);

		logintext = VC.getNewTField(names.loginTextfield, new Anch(0.15,0.5,0.2,0.68), FC.inMT);
		TextFields.bindAutoCompletion(logintext, Controller.accountnames);
		
		loginpwd = VC.getNewPwdField(names.loginPwdfield, new Anch(0.15,0.5,0.28,0.60), FC.inMT);
		loginpwdT = VC.getNewTField(names.loginPwdfield, new Anch(0.15,0.5,0.28,0.60), FC.inMT);
		VC.setNodesUnusable(new Node[] {loginpwdT});
		loginOK = VC.getNewBut(names.loginLoginButton, new Anch(0.518,0.35,0.225,0.625), FC.inMT, false, loginAttemptHandler);
		VC.setBack(FC.empBa, logintext, loginpwdT);
		
		loginshow = VC.getNewCB(names.logincheckbox, mainFrac, new Anch(0.165,0.65,0.37,0.53), FC.inWi, FC.inHe);
		loginshow.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(false, new Node[] {loginpwdT});
					VC.setDisSt(true, new Node[] {loginpwd});
					loginpwdT.setText(loginpwd.getText());
					
				} else {
					VC.setDisSt(true, new Node[] {loginpwdT});
					VC.setDisSt(false, new Node[] {loginpwd});
					loginpwd.setText(loginpwdT.getText());
				}
			}
		});
		VC.setPwdTextBindings(loginpwd, loginpwdT, loginshow);
		
		loginnewuserlabel = VC.getNewLa(names.loginNewlabel, new Anch(0.15,0.3,0.53,0.35), FC.inMT);
		loginnewhead = new Label(names.loginSecondTitle);
		loginnewhead.setFont(VC.getTitleFont((FC.inWi + FC.inHe) / (FC.titDiv + 0.5 * FC.titDiv)));
		VC.setAn(new Anch(0.15,0.2,0.62,0.23), FC.inWi, FC.inHe, loginnewhead);
		loginnewuser = VC.getNewBut(names.loginNewbutton, new Anch(0.518,0.35,0.62,0.23), FC.inMT, false, loginNewUserHandler);
		
		startframeMsg = VC.getNewAnP(new Anch(0.7,0.04,0.3,0.45), FC.inWi, FC.inHe);
		startframeclMsg = VC.getNewImBut(controller.startClI, new Anch(0.0, 0.75, 0.0, 0.6), 
				FC.inWi*0.3, FC.inHe*0.22, startScreenCloseMsg);
		startframeclMsg.setBackground(null);
		
		startframeNoUN = VC.getNewLa(names.startframenoname, new Anch(0.055,0.03,0.01,0.0), FC.inMT);
		startframeNoPwd = VC.getNewLa(names.startframenopwd, new Anch(0.055,0.03,0.01,0.0), FC.inMT);
		startframeWrongUN = VC.getNewLa(names.startframewrname, new Anch(0.055,0.03,0.01,0.0), FC.inMT);
		startframeWrongPwd = VC.getNewLa(names.startframewrpwd, new Anch(0.055,0.03,0.01,0.0), FC.inMT);
				
		
		loginmenuhelp = new MenuItem(names.loginHelp);
		loginmenuhelp.setOnAction(loginHelpHandler);
		
		loginenglishitem = new MenuItem(names.loginEnglish);
		loginenglishitem.setOnAction(toggleLanguage);
		
		logingermanitem = new MenuItem(names.loginGerman);
		logingermanitem.setOnAction(toggleLanguage);
		
		loginmenulanguage = new Menu(names.loginLanguage);
		loginmenulanguage.getItems().addAll(loginenglishitem, logingermanitem);
		
		loginmenu = new MenuButton(names.loginMenu, controller.startI, loginmenuhelp, loginmenulanguage);
		loginmenu.setPopupSide(Side.TOP);
		loginmenu.setFont(VC.getFont(mainFrac));
		VC.setAn(new Anch(0.0,0.82,0.86,0.0), FC.inWi, FC.inHe, loginmenu);
		
		startFrame.getChildren().addAll(loginhead, logintext, loginpwd, loginnewuserlabel, loginshow, loginOK,
				loginnewhead, loginnewuser, loginmenu, loginpwdT);
		
		
		/**
		 * Settings and options.
		 */
		userMan = VC.getNewAnP(new Anch(0.0,0.0,0.0,0.0), FC.inWi, FC.inHe);
		clUserMan = VC.getNewImBut(controller.userManClI, new Anch(0.01,0.94,0.08,0.81), FC.inWi, FC.inHe, userManualBackHandler);

		userManTitle = new Label(names.usMTit);
		userManTitle.setFont(VC.getTitleFont((FC.inWi + FC.inHe) / FC.titDiv));
		VC.setAn(new Anch(0.36, 0.3, 0.076, 0.824), FC.inWi, FC.inHe, userManTitle);

		userManCon = new ScrollPane() {
			@Override
			public void requestFocus() {
			}
		};

		browser = new WebView();
		webEngine = browser.getEngine();	
		userManCon.setContent(browser);		
		webEngine.loadContent(names.usMCon);
		
		VC.setAn(new Anch(0.15,0.15,0.2,0.02), FC.inWi, FC.inHe, userManCon);	
		manI = VC.getNewImLa(controller.manI, new Anch(0.22,0.65,0.05,0.78), FC.inWi, FC.inHe);
		
		userManCon.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		userManCon.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		userManCon.setFitToWidth(true);
		userManCon.setFitToHeight(true);

		userMan.getChildren().addAll(clUserMan,userManTitle,userManCon,manI);
		
		/**
		 * New user View.
		 */
		newuserPane = VC.getNewAnP(new Anch(0.0,0.0,0.0,0.0), FC.inWi, FC.inHe);
		newuserback = VC.getNewImBut(controller.newUserCl, new Anch(0.01,0.94,0.08,0.81), FC.inWi, FC.inHe, newuserBackHandler);
		
		newusername = VC.getNewTField(names.newuserchoose, new Anch(0.26,0.4,0.16,0.725), FC.inMT);

		newuserchoosepwd = VC.getNewRadioButton(names.newuserpwd, 1.08*mainFrac);
		newusergenpwd = VC.getNewRadioButton(names.newusergenpwd, 1.08*mainFrac);
		newusergenpwd.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(true, new Node[] {newuserPwd,newuserPwdRep,newuserPwdT,newuserPwdRepT,newusershow});
					VC.setDisSt(false, new Node[] {newuserincludespecial,newuserfixedlength});
					if(newuserfixedlength.isSelected()) {
						VC.setDisSt(false, new Node[] {newuserlengthchoices,newuserlengthlabel});
					}
				} else {
					VC.setDisSt(false, new Node[] {newuserPwd,newuserPwdRep,newuserPwdT,newuserPwdRepT,newusershow});
					VC.setDisSt(true, new Node[] {newuserlengthchoices,newuserincludespecial,newuserfixedlength,newuserlengthlabel});
				}
			}
		});		
		
		newusertoggle = new ToggleGroup();
		newusertoggle.getToggles().addAll(newuserchoosepwd,newusergenpwd);
		newusertoggle.selectToggle(newuserchoosepwd);
		
		newuserVBox = new VBox();
		newuserVBox.getChildren().add(newuserchoosepwd);
		newuserVBox.getChildren().add(newusergenpwd);
		newuserVBox.setSpacing(0.8 * FC.vbspac * FC.inHe);
		VC.setAn(new Anch(0.26,0.1,0.335,0.0), FC.inWi, FC.inHe, newuserVBox);
		
		newuserUNtitle = new Label(names.newuseruntitle);
		newuserUNtitle.setFont(VC.getTitleFont((FC.inWi+FC.inHe) / (FC.titDiv + 0.5 * FC.titDiv)));
		VC.setAn(new Anch(0.18,0.1,0.08,0.82),  FC.inWi, FC.inHe, newuserUNtitle);
		
		newuserPwdtitle = new Label(names.newuserpwdtitle);
		newuserPwdtitle.setFont(VC.getTitleFont((FC.inWi+FC.inHe) / (FC.titDiv + 0.5 * FC.titDiv)));
		VC.setAn(new Anch(0.18,0.1,0.25,0.65),  FC.inWi, FC.inHe, newuserPwdtitle);
		
		newuserPwd = VC.getNewPwdField(names.newuserpwdfield, new Anch(0.32,0.34,0.397,0.488), FC.inMT);
		newuserPwdRep = VC.getNewPwdField(names.newuserpwdfieldr, new Anch(0.32,0.34,0.469,0.416), FC.inMT);
		
		newuserPwdT = VC.getNewTField(names.newuserpwdfield, new Anch(0.32,0.34,0.397,0.488), FC.inMT);
		newuserPwdRepT = VC.getNewTField(names.newuserpwdfieldr, new Anch(0.32,0.34,0.469,0.416), FC.inMT);
		VC.setNodesUnusable(new Node[] {newuserPwdT, newuserPwdRepT});
		
		newusershow = VC.getNewCB(names.newusershow, mainFrac, new Anch(0.335,0.48,0.54,0.36), FC.inWi, FC.inHe);
		newusershow.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(false, new Node[] {newuserPwdT,newuserPwdRepT});
					VC.setDisSt(true,  new Node[] {newuserPwd,newuserPwdRep});
					newuserPwdT.setText(newuserPwd.getText());
					newuserPwdRepT.setText(newuserPwdRep.getText());
				} else {
					VC.setDisSt(true,  new Node[] {newuserPwdT,newuserPwdRepT});
					VC.setDisSt(false,  new Node[] {newuserPwd,newuserPwdRep});
					newuserPwd.setText(newuserPwdT.getText());
					newuserPwdRep.setText(newuserPwdRepT.getText());
				}
			}
		});
		VC.setPwdTextBindings(newuserPwd, newuserPwdT, newusershow);
		VC.setPwdTextBindings(newuserPwdRep, newuserPwdRepT, newusershow);
		
		newuserincludespecial = VC.getNewCB(names.newuserspecial, mainFrac, new Anch(0.335,0.39,0.7,0.2), FC.inWi, FC.inHe);
		newuserfixedlength = VC.getNewCB(names.newuserfixed, mainFrac, new Anch(0.335,0.39,0.76,0.14), FC.inWi, FC.inHe);
		newuserfixedlength.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(false, new Node[] {newuserlengthchoices,newuserlengthlabel});
				} else {
					VC.setDisSt(true, new Node[] {newuserlengthchoices,newuserlengthlabel});
				}
			}
		});		
	
		newuserlengthchoices = VC.getCombobox(4, 10, new Anch(0.53,0.39,0.83,0.08), FC.inWi, FC.inHe);		
		newuserlengthlabel = VC.getNewLa(names.newuserlength, new Anch(0.362,0.45,0.83,0.08), FC.inMT);
		newuserdone = VC.getNewBut(names.newuserdone, new Anch(0.8,0.1,0.65,0.2), FC.inMT, false, newAccountHandler);
		
		VC.setDisSt(true, new Node[] {newuserlengthchoices,newuserincludespecial,newuserfixedlength,newuserlengthlabel});
		
		
		newuserMsgPane = VC.getNewAnP(new Anch(0.7,0.05,0.225,0.416), FC.inWi, FC.inHe);
		newuserMsgCl = VC.getNewImBut(controller.newuCl, new Anch(0.0, 0.65, 0.0, 0.6), FC.inWi*0.3, FC.inHe*0.4, newuserCloseMsg);
		newuserMsgCl.setBackground(null);
		
		
		newuserNoUN = VC.getNewLa(names.newuserlabelnoun, new Anch(0.05,0.05,0.1,0.1), FC.inMT);
		newuserExUN = VC.getNewLa(names.newuserlabelexun, new Anch(0.05,0.05,0.1,0.1), FC.inMT);
		newuserMissPwd = VC.getNewLa(names.newuserlabelmiss, new Anch(0.05,0.05,0.1,0.1), FC.inMT);
		newuserMissRepPwd = VC.getNewLa(names.newuserlabelmissr, new Anch(0.05,0.05,0.1,0.1), FC.inMT);
		newuserWrongPwd = VC.getNewLa(names.newuserlabelwrong, new Anch(0.05,0.05,0.1,0.1), FC.inMT);
		
		newuserPane.getChildren().addAll(newuserback,newusername,newuserVBox,newuserUNtitle, 
				newuserPwdtitle,newuserincludespecial,newuserfixedlength,newuserPwd,newuserPwdRep,
				newuserPwdT,newuserPwdRepT,newusershow,newuserlengthchoices,newuserlengthlabel,newuserdone);

		/**
		 * Menu Open View.
		 */
		menuopenPane = VC.getNewAnP(new Anch(0.0,0.0,0.0,0.0), FC.inWi, FC.inHe);
		
		menuopenLogout = new Button(names.menuopenlogout,controller.menuopenback);
		menuopenLogout.setFont(VC.getFont(mainFrac));
		menuopenLogout.setContentDisplay(ContentDisplay.LEFT);
		VC.setAn(new Anch(0.0,0.82,0.0,0.86), FC.inWi, FC.inHe, menuopenLogout);
		menuopenLogout.setOnAction(menuopenlogoutHandler);
			
		menuopenencrypt = VC.getNewBut(names.menuopenenc, new Anch(0.0, 0.75, 0.2, 0.67), FC.inMT, false, menuopenencryptHandler);
		menuopendecrypt = VC.getNewBut(names.menuopendec, new Anch(0.0, 0.75, 0.35, 0.52), FC.inMT, false, menuopendecryptHandler);
		menuopenTestpwd = VC.getNewBut(names.menuopentest, new Anch(0.0, 0.75, 0.5, 0.37), FC.inMT, false, menuopentestpwdHandler);
		
		menuopenhelp = new MenuItem(names.menuopenhelp);
		menuopenhelp.setOnAction(menuopenHelpHandler);
		
		menuopenenglishitem = new MenuItem(names.menuopenenglish);
		menuopenenglishitem.setOnAction(toggleLanguage);
		
		menuopengermanitem = new MenuItem(names.menuopengerman);
		menuopengermanitem.setOnAction(toggleLanguage);
		
		menuopenlanguage = new Menu(names.menuopenlang);
		menuopenlanguage.getItems().addAll(menuopenenglishitem,menuopengermanitem);
		
		menuopenchangeMPwd = new MenuItem(names.menuopenchange);
		menuopenchangeMPwd.setOnAction(menuopennewPwdHandler);
		
		menuopendeleteaccount = new MenuItem(names.menuopendelete);
		menuopendeleteaccount.setOnAction(resetAccountHandler);
		
		menuopenresetpwds = new MenuItem(names.menuopenreset);
		menuopenresetpwds.setOnAction(resetPasswordHandler);
		
		menuopenmenu = new MenuButton(names.menuopenopt, controller.menuopenI,menuopenhelp,menuopenlanguage,menuopenchangeMPwd,
				menuopendeleteaccount,menuopenresetpwds);
		menuopenmenu.setPopupSide(Side.TOP);
		menuopenmenu.setFont(VC.getFont(mainFrac));
		VC.setAn(new Anch(0.0,0.82,0.86,0.0), FC.inWi, FC.inHe, menuopenmenu);

		menuopenMsg = new AnchorPane();
		VC.setAn(new Anch(0.4,0.2,0.2,0.45), FC.inWi, FC.inHe, menuopenMsg);
		VC.decoratePane(menuopenMsg, FC.newMPErrBord, FC.newMPErrBa);
		
		menuopenMsgCl = VC.getNewImBut(controller.menuopenMsgCl, 0.06*FC.inWi, 0.07*FC.inHe, menuopenCloseMsgHandler);
		VC.setAn(new Anch(0.28,0.62,0.33,0.57), FC.inWi, FC.inHe, menuopenMsgCl);
		
		menuopenWrongReset = VC.getNewLa(names.menuopenwrongres, mainFrac, new Anch(0.05,0.05,0.0,0.0), FC.inWi, FC.inHe);
		menuopenRightReset = VC.getNewLa(names.menuopenrightres, mainFrac, new Anch(0.05,0.05,0.0,0.0), FC.inWi, FC.inHe);
		menuopenNoDecrypt = VC.getNewLa(names.movDecNoPwsMsg, mainFrac, new Anch(0.05,0.05,0.0,0.0), FC.inWi,FC.inHe);
		menuopenNewMPwd = VC.getNewLa(names.menuopennewmpwd, mainFrac, new Anch(0.05,0.05,0.0,0.0), FC.inWi, FC.inHe);
		
		menuopenseparator = new Separator(Orientation.VERTICAL);
		VC.setAn(FC.movSep, FC.inWi, FC.inHe, menuopenseparator);
			
		menuopenPane.getChildren().addAll(menuopenLogout, menuopenseparator, menuopenencrypt,
				menuopendecrypt, menuopenTestpwd, menuopenmenu);
		
		/**
		 * Test Passwords View.
		 */
		testpwdPane = VC.getNewAnP(new Anch(0.0,0.0,0.0,0.0), FC.inWi, FC.inHe);
		testpwdBack = VC.getNewImBut(controller.testpwdClI, new Anch(0.01,0.94,0.08,0.81), FC.inWi, FC.inHe, testpwdBackHandler);
		
		testpwdestimateOwn = VC.getNewRadioButton(names.testpwdowninp, 1.08*mainFrac);
		testpwdestimateGen = VC.getNewRadioButton(names.testpwdgeninp, 1.08*mainFrac);
		testpwdestimateGen.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(true, new Node[] {testpwdownfield});
					VC.setDisSt(false, new Node[] {testpwdspecial, testpwdspace, testpwdnumbers, testpwdfixedlength});
					if(testpwdfixedlength.isSelected()) {
						VC.setDisSt(false, new Node[] {testpwdchooselength,testpwdlengthlabel});
					}
				} else {
					VC.setDisSt(false, new Node[] {testpwdownfield});
					VC.setDisSt(true, new Node[] {testpwdspecial, testpwdspace, testpwdnumbers, testpwdfixedlength,
							testpwdchooselength, testpwdlengthlabel});
				}
			}
		});
		
		testpwdownfield = VC.getNewTField(names.testpwdfield, new Anch(0.2,0.4,0.24,0.66), FC.inMT);
		
		testpwdtoggle = new ToggleGroup();
		testpwdtoggle.getToggles().addAll(testpwdestimateOwn,testpwdestimateGen);
		testpwdtoggle.selectToggle(testpwdestimateOwn);
		
		testpwdvbox = new VBox();
		testpwdvbox.getChildren().add(testpwdestimateOwn);
		testpwdvbox.getChildren().add(testpwdestimateGen);
		testpwdvbox.setSpacing(0.4 * FC.vbspac * FC.inHe);
		VC.setAn(new Anch(0.14,0.1,0.18,0.7), FC.inWi, FC.inHe, testpwdvbox);
		
		testpwdspecial = VC.getNewCB(names.testpwdspecial, mainFrac, new Anch(0.215,0.54,0.41,0.49), FC.inWi, FC.inHe);
		testpwdspace = VC.getNewCB(names.testpwdspace, mainFrac, new Anch(0.215,0.54,0.48,0.42), FC.inWi, FC.inHe);
		testpwdnumbers = VC.getNewCB(names.testpwdnumb, mainFrac, new Anch(0.215,0.54,0.55,0.35), FC.inWi, FC.inHe);
		
		testpwdfixedlength = VC.getNewCB(names.testpwdfixed, mainFrac, new Anch(0.215,0.54,0.62,0.28), FC.inWi, FC.inHe);
		testpwdfixedlength.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(false, new Node[] {testpwdchooselength, testpwdlengthlabel});
				} else {
					VC.setDisSt(true, new Node[] {testpwdchooselength, testpwdlengthlabel});
				}
			}
		});
		
		testpwdchooselength = VC.getCombobox(2, 15, new Anch(0.4,0.52,0.678,0.23), FC.inWi, FC.inHe);		
		testpwdlengthlabel = VC.getNewLa(names.testpwdchlen, new Anch(0.242,0.6,0.674,0.226), FC.inMT);
		VC.setDisSt(true, new Node[] {testpwdspecial, testpwdspace, testpwdnumbers, testpwdfixedlength,
				testpwdchooselength, testpwdlengthlabel});
		
		testpwdEstimate = VC.getNewBut(names.testpwdstrength, new Anch(0.65,0.1,0.72,0.08), FC.inMT, false, testpwdEstimateHandler);
		testpwdHeader = VC.getNewLa(names.testpwdheader, new Anch(0.14,0.1,0.08,0.8), FC.inMT);
		testpwdHeader.setFont(VC.getTitleFont((FC.inWi + FC.inHe) / (FC.titDiv + 0.5 * FC.titDiv)));
		
		
		testpwdMsg = VC.getNewAnP(new Anch(0.62,0.08,0.25,0.3), FC.inWi, FC.inHe);
		testpwdMsgCl = VC.getNewImBut(controller.testpwdMsgCl, 0.06*FC.inWi, 0.07*FC.inHe, testpwdCloseMsg);
		testpwdMsgCl.setBackground(null);
		VC.setAn(new Anch(0.45,0.7,0.36,0.7), FC.inWi, FC.inHe, testpwdMsgCl);
		
		testpwdMsgLabel1 = new Label();
		testpwdMsgLabel1.setText(names.testpwdlabel1);
		VC.setAn(new Anch(0.1,0.1,0.07,0.6), 0.5*FC.inWi, 0.45*FC.inHe, testpwdMsgLabel1);
		testpwdMsgLabel1.setFont(VC.getFont(mainFrac));
		testpwdMsgLabel1.setWrapText(true);
		
		testpwdMsgLabel3 = new Label();
		testpwdMsgLabel3.setText("");
		VC.setAn(new Anch(0.15,0.1,0.25,0.5), 0.5*FC.inWi, 0.45*FC.inHe, testpwdMsgLabel3);
		testpwdMsgLabel3.setFont(VC.getFont(mainFrac));
		testpwdMsgLabel3.underlineProperty().set(true);
		testpwdMsgLabel3.setWrapText(true);
		
		testpwdMsgLabel2 = new Label();
		testpwdMsgLabel2.setText(names.testpwdlabel2);
		VC.setAn(new Anch(0.1,0.1,0.45,0.3), 0.5*FC.inWi, 0.45*FC.inHe, testpwdMsgLabel2);
		testpwdMsgLabel2.setFont(VC.getFont(mainFrac));
		testpwdMsgLabel2.setWrapText(true);
		
		testpwdMsgLabel4 = new Label();
		testpwdMsgLabel4.setText("");
		VC.setAn(new Anch(0.15,0.1,0.63,0.1), 0.5*FC.inWi, 0.45*FC.inHe, testpwdMsgLabel4);
		testpwdMsgLabel4.setFont(VC.getFont(mainFrac));
		testpwdMsgLabel4.setWrapText(true);
		
		testpwdPane.getChildren().addAll(testpwdBack, testpwdvbox, testpwdspecial, testpwdspace, testpwdnumbers,
				testpwdfixedlength, testpwdownfield, testpwdchooselength, testpwdlengthlabel, testpwdEstimate, testpwdHeader);
		
		/**
		 * Encrypt Pane.
		 */
		encryptPane = VC.getNewAnP(new Anch(0.0,0.0,0.0,0.0), FC.inWi, FC.inHe);
		encryptBackButton = VC.getNewImBut(controller.encryptClI, new Anch(0.01,0.94,0.08,0.81), FC.inWi, FC.inHe, encryptBackHandler);
		
		encryptScroll = VC.getNewScP(new Anch(0.5,0.0,0.0,0.074), FC.inWi, FC.inHe);
		encryptVbox = new VBox();
		encryptScroll.setContent(encryptVbox);
		encryptScroll.vvalueProperty().bind(encryptVbox.heightProperty());
		
		encryptPwds = new ArrayList<PasswordField[]>();
		encryptPwdsT = new ArrayList<TextField[]>();
		encryptText = new ArrayList<TextField[]>();
		encryptBlocks = new ArrayList<AnchorPane>();
		addNewPwdBlock(FC.inWi, FC.inHe);
		
		VC.setDisSt(true, new Node[] {encryptPwdsT.get(0)[0], encryptPwdsT.get(0)[1]});
		VC.setVis(false, new Node[] {encryptPwdsT.get(0)[0], encryptPwdsT.get(0)[1]});
		
		encryptShowPwds = VC.getNewCB(names.resetpwdshow, mainFrac, FC.encBoxAn, FC.inWi, FC.inHe);
		encryptShowPwds.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				for(int j = 0; j < encryptPwds.size(); j++) {
					if(newVal) {
						encryptPwdsT.get(j)[0].setText(encryptPwds.get(j)[0].getText());
						encryptPwdsT.get(j)[1].setText(encryptPwds.get(j)[1].getText());
						VC.setNodesUnusable(new Node [] {encryptPwds.get(j)[0], encryptPwds.get(j)[1]});
						VC.setNodesUsable(new Node[] {encryptPwdsT.get(j)[0], encryptPwdsT.get(j)[1]});
					} else {
						encryptPwds.get(j)[0].setText(encryptPwdsT.get(j)[0].getText());
						encryptPwds.get(j)[1].setText(encryptPwdsT.get(j)[1].getText());
						VC.setNodesUsable(new Node[] {encryptPwds.get(j)[0], encryptPwds.get(j)[1]});
						VC.setNodesUnusable(new Node[] {encryptPwdsT.get(j)[0], encryptPwdsT.get(j)[1]});
					}
				}
			}
		});
		
		encryptPlusMinusPane = new AnchorPane();
		encryptPlusMinusPane.setPrefSize(FC.pmWi * FC.inWi, FC.pmHe * FC.inHe);

		encryptPlus = VC.getNewImBut(controller.newPI, FC.pAn, FC.inWi, FC.inHe, encryptPlusHandler);
		encryptMinus = VC.getNewImBut(controller.newMI, FC.mAn, FC.inWi, FC.inHe, encryptMinusHandler);
		
		encryptSave = VC.getNewImBut(controller.encSI, FC.saButAn, FC.inWi, FC.inHe, encryptSaveHandler);
		encryptSave.setBackground(null);

		encryptAgain = VC.getNewBut(names.encAgN, FC.agButAn, FC.inMT, true, encryptAgainHandler);
		encryptDone = VC.getNewBut(names.encDoN, FC.doButAn, FC.inMT, true, encryptDoneHandler);

		
		encryptMsgPane = VC.getNewAnP(FC.encMsgAn, FC.inWi, FC.inHe);
		encryptErrorIcon = VC.getNewImLa(controller.encErrI, new Anch(0.09,0.71,0.32,0.53), FC.inWi, FC.inHe);
		encryptMsgCl = VC.getNewImBut(controller.encClI, new Anch(0.3,0.5,0.3,0.55), FC.inWi, FC.inHe, encryptCloseMsgHandler);
		encryptMsgCl.setBackground(null);
		
		encryptDoneIcon = VC.getNewImLa(controller.encDoI, new Anch(0.09,0.71,0.32,0.53), FC.inWi, FC.inHe);
		encryptOverwriteQuest = VC.getNewImLa(controller.encOvQuI, new Anch(0.09,0.71,0.32,0.53), FC.inWi, FC.inHe);
		
		encryptDoneLabel = VC.getNewLa(names.encLabDo, new Anch(0.035,0.035,0.03,0.0), FC.inMT);
		encryptMuError = VC.getNewLa(names.encLabMErr, new Anch(0.035,0.035,0.03,0.0), FC.inMT);
		encryptExError = VC.getNewLa(names.encLabExErr, new Anch(0.035,0.035,0.03,0.0), FC.inMT);
		encryptNoEntry = VC.getNewLa(names.encNoEnt, new Anch(0.035,0.035,0.03,0.0), FC.inMT);
		encryptDiff = VC.getNewLa(names.encDiffP, new Anch(0.035,0.035,0.03,0.0), FC.inMT);
		encryptMissError = VC.getNewLa(names.encMissErr, new Anch(0.035,0.035,0.03,0.0), FC.inMT);
		
		encryptDismiss = VC.getNewBut(names.encDisN, new Anch(0.11, 0.755, 0.84, 0.06), FC.inMT, true, encryptDismissHandler);
		encryptChange = VC.getNewBut(names.encChN, new Anch(0.255, 0.61, 0.84, 0.06), FC.inMT, true, encryptChangeHandler);
		encryptOK = VC.getNewBut(names.encOKN, new Anch(0.1775, 0.6675, 0.76, 0.14), FC.inMT, true, encryptOKHandler);
		
		encryptPane.getChildren().addAll(encryptBackButton, encryptScroll, encryptShowPwds, encryptPlus, 
				encryptMinus, encryptSave);
		
		/**
		 * Decrypt View.
		 */
		decryptPane = VC.getNewAnP(FC.empty, FC.inWi, FC.inHe);	
		decryptBack = VC.getNewImBut(controller.decryptClI, new Anch(0.01,0.94,0.08,0.81), FC.inWi, FC.inHe, decryptBackHandler);

		decryptDelete = VC.getNewImBut(controller.newDelI, new Anch(0.4, 0.5, 0.85, 0.0), FC.inWi, FC.inHe, decryptDeleteHandler);
		decryptDelete.setDisable(true);

		decryptChange = VC.getNewBut(names.decChN, FC.decChAn, FC.inMT, true, decryptChangeHandler);
		decryptChange.setDisable(true);
		decryptShow = VC.getNewBut(names.decSN, FC.decShAn, FC.inMT, true, decryptShowHandler);
		decryptShow.setDisable(true);

		decryptScroll = VC.getNewScP(FC.decScAn, FC.inWi, FC.inHe);
		decryptContent = new GridPane();
		decryptScroll.setContent(decryptContent);
		decryptScroll.vvalueProperty().bind(decryptContent.heightProperty());

		decryptMsgPane = VC.getNewAnP(FC.decMsgAn, FC.inWi, FC.inHe);
		decryptDoneIcon = VC.getNewImLa(controller.decDoI, new Anch(0.09,0.71,0.32,0.53), FC.inWi, FC.inHe);
		decryptClI = VC.getNewImBut(controller.decClI, new Anch(0.3,0.5,0.3,0.55), FC.inWi, FC.inHe, decryptCloseMsgHandler);
		decryptClI.setBackground(null);
		
		decryptSuccessDel = VC.getNewLa(names.decryptSucDel, new Anch(0.035,0.035,0.03,0.0), FC.inMT);
		decryptSuccessOv = VC.getNewLa(names.decryptSucCh, new Anch(0.035,0.035,0.03,0.0), FC.inMT);
		decryptPane.getChildren().addAll(decryptBack, decryptChange, decryptShow, decryptScroll, decryptDelete);
		
		mainFrame.getChildren().add(startFrame);
		scene = new Scene(mainFrame, FC.inWi, FC.inHe);
		stage.initStyle(StageStyle.UTILITY);

		stage.setScene(scene);
		stage.setTitle(names.frameTitle);
		stage.setOnCloseRequest(confClEvHa);
		stage.setMaxHeight(FC.inHe);
		stage.setMaxWidth(FC.inWi);
		stage.setMinHeight(FC.inHe);
		stage.setMinWidth(FC.inWi);
		stage.setResizable(false);
		stage.show();
	}

	
    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Global Handling
	 */	
	
	
	/**
	 * event handler in case of pressing the back-button in the user manual
	 */
	private EventHandler<ActionEvent> userManualBackHandler = userManualBackEvent -> {
		if(userManComeFromLogin) {
			togglePane(startFrame, userManOp, startFrameSelected);
		} else {
			togglePane(menuopenPane, userManOp, menuOpVSel);
		}
		userManComeFromLogin = false;
	};
	
	/**
	 * event handler for switching the language (either german or english)
	 */
	private EventHandler<ActionEvent> toggleLanguage = toggleLanguageEvent -> {
		if(toggleLanguageEvent.getSource().equals(logingermanitem) ||
				toggleLanguageEvent.getSource().equals(menuopengermanitem)) {
			if(names.language.equals(Language.GERMAN)) {
				toggleLanguageEvent.consume();
			} else {
				names = new Names(Language.GERMAN);
				changeItemLanguage();
			}
		} else {
			if(names.language.equals(Language.ENGLISH)) {
				toggleLanguageEvent.consume();
			} else {
				names = new Names(Language.ENGLISH);
				changeItemLanguage();
			}
		}
	};
	
	/**
	 * update item descriptions shown in the view with current language choice
	 */
	private void changeItemLanguage() {
		if(startFrameSelected) {
			stage.setTitle(names.loginHeader);
		} else {
			stage.setTitle(names.menuframetitle);
		}
		
		loginhead.setText(names.loginTitle);
		logintext.setPromptText(names.loginTextfield);
		loginpwd.setPromptText(names.loginPwdfield);
		loginpwdT.setPromptText(names.loginPwdfield);
		loginOK.setText(names.loginLoginButton);
		loginshow.setText(names.logincheckbox);
		loginnewuserlabel.setText(names.loginNewlabel);
		loginnewhead.setText(names.loginSecondTitle);
		loginnewuser.setText(names.loginNewbutton);
		
		startframeNoUN.setText(names.startframenoname);
		startframeNoPwd.setText(names.startframenopwd);
		startframeWrongUN.setText(names.startframewrname);
		startframeWrongPwd.setText(names.startframewrpwd);
		
		loginmenuhelp.setText(names.loginHelp);
		loginenglishitem.setText(names.loginEnglish);
		logingermanitem.setText(names.loginGerman);
		loginmenulanguage.setText(names.loginLanguage);
		loginmenu.setText(names.loginMenu);
		
		userManTitle.setText(names.usMTit);
		webEngine.loadContent(names.usMCon);
		
		newusername.setText(names.newuserchoose);
		newuserchoosepwd.setText(names.newuserpwd);
		newusergenpwd.setText(names.newusergenpwd);
		newuserUNtitle.setText(names.newuseruntitle);
		newuserPwdtitle.setText(names.newuserpwdtitle);
		newuserPwd.setPromptText(names.newuserpwdfield);
		newuserPwdRep.setPromptText(names.newuserpwdfieldr);
		newuserPwdT.setPromptText(names.newuserpwdfield);
		newuserPwdRepT.setPromptText(names.newuserpwdfieldr);
		newusershow.setText(names.newusershow);
		newuserincludespecial.setText(names.newuserspecial);
		newuserfixedlength.setText(names.newuserfixed);
		newuserlengthlabel.setText(names.newuserlength);
		newuserdone.setText(names.newuserdone);
		newuserNoUN.setText(names.newuserlabelnoun);
		newuserExUN.setText(names.newuserlabelexun);
		newuserMissPwd.setText(names.newuserlabelmiss);
		newuserMissRepPwd.setText(names.newuserlabelmissr);
		newuserWrongPwd.setText(names.newuserlabelwrong);
		
		menuopenLogout.setText(names.menuopenlogout);
		menuopenencrypt.setText(names.menuopenenc);
		menuopendecrypt.setText(names.menuopendec);
		menuopenTestpwd.setText(names.menuopentest);
		menuopenhelp.setText(names.menuopenhelp);
		menuopenenglishitem.setText(names.menuopenenglish);
		menuopengermanitem.setText(names.menuopengerman);
		menuopenlanguage.setText(names.menuopenlang);
		menuopenchangeMPwd.setText(names.menuopenchange);
		menuopendeleteaccount.setText(names.menuopendelete);
		menuopenresetpwds.setText(names.menuopenreset);
		menuopenmenu.setText(names.menuopenopt);
		menuopenWrongReset.setText(names.menuopenwrongres);
		menuopenRightReset.setText(names.menuopenrightres);
		menuopenNoDecrypt.setText(names.movDecNoPwsMsg);
		menuopenNewMPwd.setText(names.menuopennewmpwd);
		
		testpwdestimateOwn.setText(names.testpwdowninp);
		testpwdestimateGen.setText(names.testpwdgeninp);
		testpwdownfield.setPromptText(names.testpwdfield);
		testpwdspecial.setText(names.testpwdspecial);
		testpwdspace.setText(names.testpwdspace);
		testpwdnumbers.setText(names.testpwdnumb);
		testpwdfixedlength.setText(names.testpwdfixed);
		testpwdlengthlabel.setText(names.testpwdchlen);
		testpwdEstimate.setText(names.testpwdstrength);
		testpwdHeader.setText(names.testpwdheader);
		testpwdMsgLabel1.setText(names.testpwdlabel1);
		testpwdMsgLabel3.setText(names.testpwdlabel2);
		
		encryptShowPwds.setText(names.resetpwdshow);
		encryptAgain.setText(names.encAgN);
		encryptDone.setText(names.encDoN);
		encryptMuError.setText(names.encLabMErr);
		encryptDoneLabel.setText(names.encLabDo);
		encryptExError.setText(names.encLabExErr);
		encryptDiff.setText(names.encDiffP);
		encryptNoEntry.setText(names.encNoEnt);
		encryptMissError.setText(names.encMissErr);
		encryptDismiss.setText(names.encDisN);
		encryptChange.setText(names.encChN);
		encryptOK.setText(names.encOKN);

		decryptChange.setText(names.decChN);
		decryptShow.setText(names.decSN);
		decryptSuccessDel.setText(names.decryptSucDel);
		decryptSuccessOv.setText(names.decryptSucCh);
		
		if(encryptPwds != null) {
			if(encryptPwds.size() > 0) {
				encryptText.get(0)[0].setPromptText(names.encNN);
				encryptText.get(0)[1].setPromptText(names.encUNN);
				encryptPwds.get(0)[0].setPromptText(names.encPwdN);
				encryptPwdsT.get(0)[0].setPromptText(names.encPwdN);
				encryptPwds.get(0)[1].setPromptText(names.encRepPwdN);
				encryptPwdsT.get(0)[1].setPromptText(names.encRepPwdN);
				encryptText.get(0)[2].setPromptText(names.encComN);
			}
		}	
	}
	
	/**
	 * occupy the current view with a new pane
	 */
	private void togglePane(AnchorPane np, boolean ob, boolean nb) {
		ob = false;
		nb = true;
		VC.clearPane(mainFrame);
		mainFrame.getChildren().add(np);
	}
	
	/**
	 * event handler for clicking the exit button
	 */
	private EventHandler<WindowEvent> confClEvHa = event -> {

		Alert exit = new Alert(AlertType.CONFIRMATION);

		exit.initOwner(stage);
		exit.getButtonTypes().clear();
		exit.setTitle(names.conExitTitle);
		exit.setHeaderText(names.conExitQuest);
		exit.setContentText(names.conExitMsg);
		ButtonType buttonTypeYes = new ButtonType(names.conExitYN, ButtonBar.ButtonData.YES);
		ButtonType buttonTypeNo = new ButtonType(names.conExitNN, ButtonBar.ButtonData.CANCEL_CLOSE);
		exit.getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
		Button exitYesButton = (Button) exit.getDialogPane().lookupButton(buttonTypeYes);
		Button exitNoButton = (Button) exit.getDialogPane().lookupButton(buttonTypeNo);
		exitNoButton.setDefaultButton(false);
		exitYesButton.setDefaultButton(true);

		Optional<ButtonType> result = exit.showAndWait();
		if(result.get() == buttonTypeYes) {	
			if(!controller.saveChanges()) {
				event.consume();
			}			
		} else {
			event.consume();
		}
	};
	
    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Login-View Handling
	 */	
	
	
	/**
	 * event handler when the login-button is pressed
	 */
	private EventHandler<ActionEvent> loginAttemptHandler = loginAttemptEvent -> {
		startframeclMsg.fire();
		// no textfield input
		if(logintext.getText().equals("")) {
			startframeMsg.getChildren().addAll(startframeclMsg,startframeNoUN);
			mainFrame.getChildren().add(startframeMsg);
			VC.decoratePane(startframeMsg, FC.newMPErrBord, FC.newMPErrBa);
		// no password input
		} else if(loginpwd.getText().equals("") && loginpwdT.getText().equals("")) {
			startframeMsg.getChildren().addAll(startframeclMsg,startframeNoPwd);
			mainFrame.getChildren().add(startframeMsg);
			VC.decoratePane(startframeMsg, FC.newMPErrBord, FC.newMPErrBa);
		// specified account does not exist
		} else if(!Controller.accountnames.contains(logintext.getText())) {
			startframeMsg.getChildren().addAll(startframeclMsg,startframeWrongUN);
			mainFrame.getChildren().add(startframeMsg);
			VC.decoratePane(startframeMsg, FC.newMPErrBord, FC.newMPErrBa);
		} else {
			boolean allok = false;

			if(loginshow.isSelected()) {
				try {
					allok = Password.initialiseMaster(loginpwdT.getText(), Controller.chooseMPwdFromUsers(logintext.getText()));
				} catch (RunOutOfHashException e) {
				}
			} else {
				try {
					allok = Password.initialiseMaster(loginpwd.getText(), Controller.chooseMPwdFromUsers(logintext.getText()));
				} catch (RunOutOfHashException e) {
				}
			}

			// if password correct: go to menu-view
			if(allok) {
				Controller.selectCurrentUser(logintext.getText());
				startFrameSelected = false;
				menuOpVSel = true;
				VC.clearPane(mainFrame);
				mainFrame.getChildren().add(menuopenPane);
				stage.setTitle(names.menuframetitle);
				
			} else {
				startframeMsg.getChildren().addAll(startframeclMsg,startframeWrongPwd);
				mainFrame.getChildren().add(startframeMsg);
				startframeMsg.setBackground(FC.newMPErrBa);
				startframeMsg.setBorder(FC.newMPErrBord);
			}
		}
	};
	
	/**
	 * event handler when the usermanual-button is pressed
	 */
	private EventHandler<ActionEvent> loginHelpHandler = loginHelpEvent -> {
		togglePane(userMan, startFrameSelected, userManOp);
		userManComeFromLogin = true;
	};
	
	/**
	 * event handler when new-user-button is pressed
	 */
	private EventHandler<ActionEvent> loginNewUserHandler = loginnewuserEvent -> {
		togglePane(newuserPane, startFrameSelected, newuserFrameSelected);
		startframeclMsg.fire();
		stage.setTitle(names.newuserTitle);
	};
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	* NewAccount-View Handling
	*/	


	
	/**
	 * event handler when new account data is confirmed
	 */
	private EventHandler<ActionEvent> newAccountHandler = newAccountEvent -> {
		newuserMsgCl.fire();		
		// no account name
		if(newusername.getText().equals("")) {
			newuserMsgPane.getChildren().addAll(newuserMsgCl,newuserNoUN);
			VC.decoratePane(newuserMsgPane, FC.newMPErrBord, FC.newMPErrBa);
			mainFrame.getChildren().add(newuserMsgPane);
		// account name already exists
		} else if(Controller.accountnames.contains(newusername.getText())) {
			newuserMsgPane.getChildren().addAll(newuserMsgCl,newuserExUN);
			VC.decoratePane(newuserMsgPane, FC.newMPErrBord, FC.newMPErrBa);
			mainFrame.getChildren().add(newuserMsgPane);
			
		// Master-Password missing
		} else if(newuserchoosepwd.isSelected() && (newusershow.isSelected() && newuserPwdT.getText().equals("") ||
				!newusershow.isSelected() && newuserPwd.getText().equals(""))) {
			newuserMsgPane.getChildren().addAll(newuserMsgCl,newuserMissPwd);
			VC.decoratePane(newuserMsgPane, FC.newMPErrBord, FC.newMPErrBa);
			mainFrame.getChildren().add(newuserMsgPane);
			
		// Master-Password repetition missing
		} else if(newuserchoosepwd.isSelected() && (newusershow.isSelected() && newuserPwdRepT.getText().equals("") ||
				!newusershow.isSelected() && newuserPwdRep.getText().equals(""))) {
			newuserMsgPane.getChildren().addAll(newuserMsgCl,newuserMissRepPwd);
			VC.decoratePane(newuserMsgPane, FC.newMPErrBord, FC.newMPErrBa);
			mainFrame.getChildren().add(newuserMsgPane);
			
		// password not equal to repetition
		} else if(newuserchoosepwd.isSelected() && (newusershow.isSelected() && 
				!newuserPwdT.getText().equals(newuserPwdRepT.getText()) ||
				!newusershow.isSelected() && !newuserPwd.getText().equals(newuserPwdRep.getText()))) {
			newuserMsgPane.getChildren().addAll(newuserMsgCl,newuserWrongPwd);
			VC.decoratePane(newuserMsgPane, FC.newMPErrBord, FC.newMPErrBa);
			mainFrame.getChildren().add(newuserMsgPane);
			
		// everything fine	
		} else {
			
			String temppwd = "";
			boolean cont = false;
			if(newusergenpwd.isSelected()) {
				
				int genpwdsize;
				if(!newuserfixedlength.isSelected()) {
					// random length between 4 and 10
					genpwdsize = rand.nextInt(10-4) + 4;					
				} else if(newuserlengthchoices.getSelectionModel().getSelectedItem().equals(null)) {
					genpwdsize = 6;
				} else {
					genpwdsize = newuserlengthchoices.getSelectionModel().getSelectedItem();
				}
				boolean usesymbol = newuserincludespecial.isSelected();
				
				// per default, do not use space, use numbers
				temppwd = PasswordSecurity.getRandomPassword(genpwdsize, false, true, usesymbol);
				
				cont = confirmnewuserpwd(temppwd,true);
			} else {
				if(newusershow.isSelected()) {
					cont = confirmnewuserpwd(newuserPwdT.getText(),false);
				} else {
					cont = confirmnewuserpwd(newuserPwd.getText(),false);
				}
			}
			
			if(!cont) {
				newAccountEvent.consume();
			} else {
				// create empty JsonObject -> current user
				// then set the current hash
				if(newusergenpwd.isSelected()) {
					try {
						Password.initialiseMaster(temppwd);
					} catch (RunOutOfHashException e) {
					}
					Controller.createNewUser(newusername.getText(), Password.getMasterHash());
				} else if (newusershow.isSelected()) {
					try {
						Password.initialiseMaster(newuserPwdT.getText());
					} catch (RunOutOfHashException e) {
					}
					Controller.createNewUser(newusername.getText(), Password.getMasterHash());
				} else {
					try {
						Password.initialiseMaster(newuserPwd.getText());
					} catch (RunOutOfHashException e) {
					}
					Controller.createNewUser(newusername.getText(), Password.getMasterHash());
				}
				TextFields.bindAutoCompletion(logintext, Controller.accountnames);
				
				// clear current pane and change to menu view
				VC.resetFields(newusername,newuserPwd,newuserPwdT,newuserPwdRep,newuserPwdRepT);
				newuserMsgCl.fire();
				newusershow.setSelected(false);
				newuserlengthchoices.getSelectionModel().clearSelection();
				newuserincludespecial.setSelected(false);
				newuserfixedlength.setSelected(false);
				newusertoggle.selectToggle(newuserchoosepwd);
			
				newuserFrameSelected = false;
				VC.clearPane(mainFrame);
				menuOpVSel = true;
				mainFrame.getChildren().add(menuopenPane);
				stage.setTitle(names.menuframetitle);
			}
		}
	};
	
	
	/**
	 * generate a dialog, asking the user to confirm a new choice for the password
	 */
	private boolean confirmnewuserpwd(String pwd, boolean generated) {
		
		Alert conf = new Alert(AlertType.CONFIRMATION);
		conf.initOwner(stage);
		conf.getButtonTypes().clear();
		conf.setTitle(names.confirmnewusertitle);
		
		// get an estimate for the security of the password
		double score = Math.min(PasswordSecurity.estimatePwStrength(pwd), 100.0);
		String scoreStr = String.format("%.2f", score);
		
		if(!generated) {
			conf.setHeaderText(names.confirmnewuserquest1);
			conf.setContentText(names.confirmnewusercont1 +	scoreStr + "/100");
		} else {
			conf.setHeaderText(names.confirmnewuserquest2);
			Label label = new Label(names.confirmnewusercont2a + pwd + names.confirmnewusercont2b + scoreStr + "/100");
			label.setWrapText(true);
			conf.getDialogPane().setContent(label);
		}
		ButtonType buttonTypeYes = new ButtonType(names.confirmnewuseryes, ButtonBar.ButtonData.CANCEL_CLOSE);
		ButtonType buttonTypeNo = new ButtonType(names.confirmnewuserno, ButtonBar.ButtonData.OK_DONE);
		conf.getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
		Button confYesButton = (Button) conf.getDialogPane().lookupButton(buttonTypeYes);
		Button confNoButton = (Button) conf.getDialogPane().lookupButton(buttonTypeNo);
		confNoButton.setDefaultButton(false);
		confYesButton.setDefaultButton(true);
		
		Optional<ButtonType> result = conf.showAndWait();
		if(result.get() == buttonTypeYes) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * event handler for pressing the back-button in the new-user frame
	 */
	private EventHandler<ActionEvent> newuserBackHandler = newuserBackEvent -> {
		// reset single components of newuser-view
		VC.resetFields(newusername,newuserPwd,newuserPwdT,newuserPwdRep,newuserPwdRepT);
		newuserMsgCl.fire();
		newusershow.setSelected(false);
		newuserlengthchoices.getSelectionModel().clearSelection();
		newuserincludespecial.setSelected(false);
		newuserfixedlength.setSelected(false);
		newusertoggle.selectToggle(newuserchoosepwd);
		
		// reset components of start screen
		VC.resetFields(logintext,loginpwd,loginpwdT);
		loginshow.setSelected(false);
		VC.clearPane(startframeMsg);;
		mainFrame.getChildren().remove(startframeMsg);
		
		newuserFrameSelected = false;
		VC.clearPane(mainFrame);
		startFrameSelected = true;
		mainFrame.getChildren().add(startFrame);
		stage.setTitle(names.frameTitle);		
	};

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Menu-View Handling
	 */	

	
	/**
	 * event handler for pressing the logout button in the menu view
	 */
	private EventHandler<ActionEvent> menuopenlogoutHandler = menuopenlogoutEvent -> {
		menuopenMsgCl.fire();
		Alert conf = new Alert(AlertType.CONFIRMATION);
		conf.initOwner(stage);
		conf.getButtonTypes().clear();
		conf.setTitle(names.confirmlogouttitle);
		conf.setHeaderText(names.confirmlogouthead);
		conf.setContentText(names.confirmlogoutcont);
		
		ButtonType buttonTypeYes = new ButtonType(names.confirmlogoutyes,ButtonBar.ButtonData.CANCEL_CLOSE);
		ButtonType buttonTypeNo = new ButtonType(names.confirmlogoutno,ButtonBar.ButtonData.OK_DONE);
		conf.getButtonTypes().addAll(buttonTypeYes,buttonTypeNo);
		Button confYesButton = (Button) conf.getDialogPane().lookupButton(buttonTypeYes);
		Button confNoButton = (Button) conf.getDialogPane().lookupButton(buttonTypeNo);
		confNoButton.setDefaultButton(false);
		confYesButton.setDefaultButton(true);
		
		Optional<ButtonType> result = conf.showAndWait();
		if(result.get() == buttonTypeYes) {
			Controller.saveChangesOnLogout();
			menuopenMsgCl.fire();
			VC.clearPane(mainFrame);
			menuOpVSel = false;
			mainFrame.getChildren().add(startFrame);
			startFrameSelected = true;
		} else {
			menuopenlogoutEvent.consume();
		}
		logintext.setText("");
		loginpwd.setText("");
		loginpwdT.setText("");
		loginshow.setSelected(false);
	};
	
	
	/**
	 * event handler for pressing the help-button in the menu view
	 */
	private EventHandler<ActionEvent> menuopenHelpHandler = menuopenHelpEvent -> {
		menuopenMsgCl.fire();
		togglePane(userMan, menuOpVSel, userManOp);
		userManComeFromLogin = false;
	};
	
	
	/**
	 * event handler for pressing the test-passwords-button in the menu view
	 */
	private EventHandler<ActionEvent> menuopentestpwdHandler = menuopentestpwdEvent -> {
		togglePane(testpwdPane, menuOpVSel, testpwdFrameSelected);
	};
	
	
	/**
	 * event handler for pressing the encrypt-button in the menu view
	 */
	private EventHandler<ActionEvent> menuopenencryptHandler = menuopenencryptEvent -> {
		togglePane(encryptPane, menuOpVSel, encVSel);
	};
	
	
	/**
	 * event handler for pressing the decrypt-button in the menu view
	 */
	private EventHandler<ActionEvent> menuopendecryptHandler = menuopendecryptEvent -> {
		menuopenMsgCl.fire();
		if(Controller.getNumOfPwds() == 0) {
			VC.decoratePane(menuopenMsg, FC.newMPErrBord, FC.newMPErrBa);
			menuopenMsg.getChildren().addAll(menuopenMsgCl,menuopenNoDecrypt);
			mainFrame.getChildren().add(menuopenMsg);
			return;
		}
		setDecryptButtons();
		togglePane(decryptPane, menuOpVSel, decVSel);
	};
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Menu-Reset/Delete/NewMPwd Handling
	 */

	
	/**
	 * event handler for pressing the close-button of the error message in the menu view
	 */
	private EventHandler<ActionEvent> moCloseHandler = moCloseEvent -> {
		closeMoMsgPane(moMsg);
	};
	
	
	/**
	 * event handler for pressing the done-button in case of a new password choice
	 */
	private EventHandler<ActionEvent> moDoneHandler = moDoneEvent -> {
		boolean tocontinue = true;
		if(moshow.isSelected() && menuopenOldPwdT.getText().equals("") ||
				!moshow.isSelected() && menuopenOldPwdP.getText().equals("")) {
			setMoErrorMsg(moMissOldPwd);
			tocontinue = false;
		} else if(mochoosepwd.isSelected()) {
			if(moshow.isSelected() && menuopenNewPwdT.getText().equals("") ||
					!moshow.isSelected() && menuopenNewPwdP.getText().equals("")) {
				setMoErrorMsg(moMissPwd);
				tocontinue = false;
			} else if(moshow.isSelected() && menuopenNewPwdRepT.getText().equals("") ||
					!moshow.isSelected() && menuopenNewPwdRepP.getText().equals("")) {
				setMoErrorMsg(moMissRepPwd);
				tocontinue = false;
			} else if(moshow.isSelected() && !menuopenNewPwdT.getText().equals(menuopenNewPwdRepT.getText()) ||
					!moshow.isSelected() && !menuopenNewPwdP.getText().equals(menuopenNewPwdRepP.getText())) {
				setMoErrorMsg(moNonEqualPwd);
				tocontinue = false;
			}
		}

		if(tocontinue) {
			boolean validate;
			if(moshow.isSelected()) {
				validate = Password.validate(menuopenOldPwdT.getText());
			} else {
				validate = Password.validate(menuopenOldPwdP.getText());
			}
			if(!validate) {
				setMoErrorMsg(moWrongPwd);
				tocontinue = false;
			}
		}
		
		if(tocontinue) {
			moMsgCl.fire();
			menuopenNewPwdStage.close();
			
			VC.decoratePane(menuopenMsg, FC.newMPDoBord, FC.newMPDoBa);
			menuopenMsg.getChildren().addAll(menuopenMsgCl,menuopenNewMPwd);
			mainFrame.getChildren().add(menuopenMsg);

			if(moshow.isSelected()) {
				Controller.changeMasterPwd(menuopenNewPwdT.getText());
			} else {
				Controller.changeMasterPwd(menuopenNewPwdP.getText());
			}			
		}
	};
	
	/**
	 * put an error message to the new-account view
	 */
	private void setMoErrorMsg(Label msg) {
		moMsgCl.fire();
		moMsg.getChildren().addAll(moMsgCl,msg);
		VC.decoratePane(moMsg, FC.newMPErrBord, FC.newMPErrBa);
		menuopenNewPwdPane.getChildren().add(moMsg);
	}
	
	/**
	 * event handler for pressing the reset-button for an existing account
	 */
	private EventHandler<ActionEvent> resetAccountHandler = resetAccountEvent -> {
		menuopenMsgCl.fire();
		confirmReset(names.resetaccounthead,names.resetaccountlabel);
		if(resetConfirmBool && !resetCancelled) {
			// logout, then delete account entry
			VC.clearPane(mainFrame);
			menuOpVSel = false;
			startFrameSelected = true;
			mainFrame.getChildren().add(startFrame);
			
			Controller.deleteCurrentUser();
		} else if(!resetCancelled) {
			// error message
			VC.decoratePane(menuopenMsg, FC.newMPErrBord, FC.newMPErrBa);			
			menuopenMsg.getChildren().addAll(menuopenMsgCl,menuopenWrongReset);
			mainFrame.getChildren().add(menuopenMsg);
		}
		resetConfirmBool = false;
		resetCancelled = false;
	};
	
	/**
	 * event handler for the reset-button for the master-password
	 */
	private EventHandler<ActionEvent> resetPasswordHandler = resetPasswordEvent -> {
		menuopenMsgCl.fire();
		confirmReset(names.resetpwdhead,names.resetpwdlabel);
		if(resetConfirmBool && !resetCancelled) {
			// message about password deletion
			VC.decoratePane(menuopenMsg, FC.newMPDoBord, FC.newMPDoBa);
			menuopenMsg.getChildren().addAll(menuopenMsgCl,menuopenRightReset);
			mainFrame.getChildren().add(menuopenMsg);
			Controller.deleteCurrentPasswords();
		} else if(!resetCancelled) {
			// error message
			VC.decoratePane(menuopenMsg, FC.newMPErrBord, FC.newMPErrBa);
			menuopenMsg.getChildren().addAll(menuopenMsgCl,menuopenWrongReset);
			mainFrame.getChildren().add(menuopenMsg);
		}
		resetConfirmBool = false;
		resetCancelled = false;
	};
	
	
	/**
	 * opens a confirmations dialog for resetting an account
	 */
	private void confirmReset(String title,String msg) {
		Stage resetstage = new Stage();
		AnchorPane resetpane = new AnchorPane();
		double ninwi = 0.6 * scene.getWidth();
		double ninhe = 0.5 * scene.getHeight();
		
		resetstage.initOwner(stage);
		resetstage.centerOnScreen();
		resetstage.initModality(Modality.WINDOW_MODAL);
		
		Scene resetscene = new Scene(resetpane,ninwi,ninhe);
		resetstage.initStyle(StageStyle.UTILITY);
		
		resetstage.setScene(resetscene);
		resetstage.setTitle(title);
		resetstage.setMinHeight(ninhe);
		resetstage.setMaxHeight(ninhe);
		resetstage.setMinWidth(ninwi);
		resetstage.setMaxWidth(ninwi);
		
		PasswordField resetpwd = new PasswordField();
		resetpwd.setPromptText(names.resetpwdfield);
		TextField resetpwdt = new TextField();
		resetpwdt.setPromptText(names.resetpwdfield);
		
		CheckBox resetshow = VC.getNewCB(names.resetpwdshow, mainFrac, new Anch(0.23,0.47,0.56,0.24), ninwi, ninhe);
		resetshow.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(false, new Node[] {resetpwdt});
					VC.setDisSt(true, new Node[] {resetpwd});
					resetpwdt.setText(resetpwd.getText());
				} else {
					VC.setDisSt(true,  new Node[] {resetpwdt});
					VC.setDisSt(false, new Node[] {resetpwd});
					resetpwd.setText(resetpwdt.getText());
				}
			}
		});
		VC.setPwdTextBindings(resetpwd, resetpwdt, resetshow);		
		
		VC.setAn(new Anch(0.2,0.2,0.37,0.38), ninwi, ninhe, resetpwd);
		VC.setAn(new Anch(0.2,0.2,0.37,0.38), ninwi, ninhe, resetpwdt);
		
		Label resetlabel = new Label();
		resetlabel.setText(msg);
		VC.setAn(new Anch(0.1,0.1,0.02,0.5), ninwi, ninhe, resetlabel);
		resetlabel.setFont(VC.getFont(mainFrac));
		resetlabel.setWrapText(true);
		
		EventHandler<ActionEvent> confirmHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean correctpwd;
				if(resetshow.isSelected()) {
					correctpwd = Password.validate(resetpwdt.getText());
				} else {
					correctpwd = Password.validate(resetpwd.getText());
				}
				if(correctpwd) {
					resetConfirmBool = true;
				} else {
					resetConfirmBool = false;
				}
				resetstage.close();
			}
		};
		
		/**
		 * event handler for pressing exit-button in the reset-view
		 */
		EventHandler<ActionEvent> cancelHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				resetCancelled = true;
				resetstage.close();
			}
		};
		
		Button resetconfirm = VC.getNewSimpleButton(names.resetconfirm, mainFrac, 
				ninwi, ninhe, new Anch(0.6,0.1,0.72,0.02), confirmHandler);
		
		Button resetcancel = VC.getNewSimpleButton(names.resetcancel, mainFrac, 
				ninwi, ninhe, new Anch(0.1,0.6,0.72,0.02), cancelHandler);
		
		EventHandler<WindowEvent> exitHandler = new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				if(!event.getSource().equals(resetconfirm) && !event.getSource().equals(resetcancel)) {
					resetCancelled = true;
				}
				resetstage.close();
			}
			
		};
		resetstage.setOnCloseRequest(exitHandler);
		
		resetpane.getChildren().addAll(resetlabel,resetpwd,resetpwdt,resetshow,resetconfirm,resetcancel);
		resetstage.showAndWait();
	}
	
	
	/**
	 * event handler for createing the new-password view
	 */
	private EventHandler<ActionEvent> menuopennewPwdHandler = menuopennewPwdEvent -> {
		menuopenMsgCl.fire();
		menuopenNewPwdStage = new Stage();
		menuopenNewPwdPane = new AnchorPane();
		double ninwi = 0.6 * scene.getWidth();
		double ninhe = 0.9 * scene.getHeight();
		
		menuopenNewPwdStage.initOwner(stage);
		menuopenNewPwdStage.centerOnScreen();
		menuopenNewPwdStage.initModality(Modality.WINDOW_MODAL);
		
		menuopenNewPwdScene = new Scene(menuopenNewPwdPane, ninwi, ninhe);
		menuopenNewPwdStage.initStyle(StageStyle.UTILITY);
		
		menuopenNewPwdStage.setScene(menuopenNewPwdScene);
		menuopenNewPwdStage.setTitle(names.motitle);
		
		menuopenNewPwdStage.setMinHeight(ninhe);
		menuopenNewPwdStage.setMaxHeight(ninhe);
		menuopenNewPwdStage.setMinWidth(ninwi);
		menuopenNewPwdStage.setMaxWidth(ninwi);				
		
		menuopenOldPwdP = new PasswordField();
		menuopenOldPwdP.setPromptText(names.menuopenOldpwd);
		menuopenNewPwdP = new PasswordField();
		menuopenNewPwdP.setPromptText(names.motext);
		menuopenNewPwdRepP = new PasswordField();
		menuopenNewPwdRepP.setPromptText(names.motextR);
		
		menuopenOldPwdT = new TextField();
		menuopenOldPwdT.setPromptText(names.menuopenOldpwd);
		menuopenNewPwdT = new TextField();
		menuopenNewPwdT.setPromptText(names.motext);
		menuopenNewPwdRepT = new TextField();
		menuopenNewPwdRepT.setPromptText(names.motextR);
		
		mochoosepwd = VC.getNewRadioButton(names.mochoose, 1.08*mainFrac);
		mogenpwd = VC.getNewRadioButton(names.mogen, 1.08*mainFrac);
		
		moshow = VC.getNewCB(names.moshow, mainFrac, new Anch(0.05,0.624,0.13,0.75), ninwi, ninhe);
		moshow.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(false, new Node[] {menuopenOldPwdT,menuopenNewPwdT,menuopenNewPwdRepT});
					VC.setDisSt(true,  new Node[] {menuopenOldPwdP,menuopenNewPwdP,menuopenNewPwdRepP});
					menuopenOldPwdT.setText(menuopenOldPwdP.getText());
					menuopenNewPwdT.setText(menuopenNewPwdP.getText());
					menuopenNewPwdRepT.setText(menuopenNewPwdRepP.getText());
					if(!mochoosepwd.isSelected()) {
						VC.setDisSt(true, new Node[] {menuopenNewPwdRepT,menuopenNewPwdT});
					}
				} else {
					VC.setDisSt(true,  new Node[] {menuopenOldPwdT,menuopenNewPwdT,menuopenNewPwdRepT});
					VC.setDisSt(false,  new Node[] {menuopenOldPwdP,menuopenNewPwdP,menuopenNewPwdRepP});
					menuopenOldPwdP.setText(menuopenOldPwdT.getText());
					menuopenNewPwdP.setText(menuopenNewPwdT.getText());
					menuopenNewPwdRepP.setText(menuopenNewPwdRepP.getText());
					if(!mochoosepwd.isSelected()) {
						VC.setDisSt(true, new Node[] {menuopenNewPwdP,menuopenNewPwdRepP});
					}
				}
			}
		});
		VC.setPwdTextBindings(menuopenOldPwdP, menuopenOldPwdT, moshow);
		VC.setPwdTextBindings(menuopenNewPwdP, menuopenNewPwdT, moshow);
		VC.setPwdTextBindings(menuopenNewPwdRepP, menuopenNewPwdRepT, moshow);
		
		// place items at appropriate positions
		VC.setAn(new Anch(0.03,0.38,0.05,0.82), ninwi, ninhe, menuopenOldPwdP);
		VC.setAn(new Anch(0.1,0.45,0.35,0.52), ninwi, ninhe, menuopenNewPwdP);
		VC.setAn(new Anch(0.1,0.45,0.43,0.44), ninwi, ninhe, menuopenNewPwdRepP);
		VC.setAn(new Anch(0.03,0.38,0.05,0.82), ninwi, ninhe, menuopenOldPwdT);
		VC.setAn(new Anch(0.1,0.45,0.35,0.52), ninwi, ninhe, menuopenNewPwdT);
		VC.setAn(new Anch(0.1,0.45,0.43,0.44), ninwi, ninhe, menuopenNewPwdRepT);
		
		molengthchoices = VC.getCombobox(4, 10, new Anch(0.46,0.45,0.818,0.042), ninwi, ninhe);		
		molengthlabel = new Label();
		molengthlabel.setText(names.molengthlabel);
		VC.setAn(new Anch(0.17,0.55,0.8,0.05), ninwi, ninhe, molengthlabel);
		molengthlabel.setFont(VC.getFont(mainFrac));
		molengthlabel.setWrapText(true);
		
		mospecial = VC.getNewCB(names.mospecial, mainFrac, new Anch(0.124,0.45,0.6,0.25), ninwi, ninhe);
		mofixed = VC.getNewCB(names.mofixed, mainFrac, new Anch(0.124,0.45,0.7,0.15), ninwi, ninhe);
		mofixed.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(false, new Node[] {molengthchoices,molengthlabel});
				} else {
					VC.setDisSt(true, new Node[] {molengthchoices,molengthlabel});
				}
			}
		});
		VC.setDisSt(true, new Node[] {molengthchoices,mospecial,mofixed,molengthlabel});
		
		mogenpwd.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVal, Boolean newVal) {
				if(newVal) {
					VC.setDisSt(true, new Node[] {menuopenNewPwdP,menuopenNewPwdRepP,menuopenNewPwdT,
							menuopenNewPwdRepT});
					VC.setDisSt(false, new Node[] {mospecial,mofixed});
					if(mofixed.isSelected()) {
						VC.setDisSt(false, new Node[] {molengthchoices,molengthlabel});
					}
				} else {
					VC.setDisSt(false, new Node[] {menuopenNewPwdP,menuopenNewPwdRepP,menuopenNewPwdT,
							menuopenNewPwdRepT});
					VC.setDisSt(true, new Node[] {molengthchoices,mospecial,mofixed,molengthlabel});
				}
			}
		});		
		
		// add new items to current view
		motoggle = new ToggleGroup();
		motoggle.getToggles().addAll(mochoosepwd,mogenpwd);
		motoggle.selectToggle(mochoosepwd);
		
		movbox = new VBox();
		movbox.getChildren().add(mochoosepwd);
		movbox.getChildren().add(mogenpwd);
		movbox.setSpacing(0.65*FC.vbspac * ninhe);
		VC.setAn(new Anch(0.02,0.1,0.28,0.0), ninwi, ninhe, movbox);
			
		modone = new Button();
		modone.setText(names.menuopenDone);
		modone.setFont(VC.getFont(mainFrac));
		VC.setAn(new Anch(0.76,0.08,0.68,0.15), ninwi, ninhe, modone);
		modone.setOnAction(moDoneHandler);
	
		moMsg = new AnchorPane();
		VC.setAn(new Anch(0.6,0.03,0.2,0.3), ninwi, ninhe, moMsg);
		moMsgCl = VC.getNewImBut(controller.newuCl, new Anch(0.0, 0.65, 0.0, 0.6), FC.inWi*0.28, FC.inHe*0.5, moCloseHandler);
		moMsgCl.setBackground(null);
	
		moMissOldPwd = VC.getNewLa(names.moMissOldPwd, mainFrac, new Anch(0.05,0.05,0.0,0.0), ninwi, ninhe);
		moMissPwd = VC.getNewLa(names.moMissPwd, mainFrac, new Anch(0.05,0.05,0.0,0.0), ninwi, ninhe);
		moMissRepPwd = VC.getNewLa(names.moMissRepPwd, mainFrac, new Anch(0.05,0.05,0.0,0.0), ninwi, ninhe);
		moNonEqualPwd = VC.getNewLa(names.moNonEqualPwd, mainFrac, new Anch(0.05,0.05,0.0,0.0), ninwi, ninhe);
		moWrongPwd = VC.getNewLa(names.moWrongPwd, mainFrac, new Anch(0.05,0.05,0.0,0.0), ninwi, ninhe);
		
		menuopenNewPwdPane.getChildren().addAll(movbox,mospecial,mofixed,moshow,molengthchoices,
				molengthlabel,menuopenNewPwdP,menuopenNewPwdRepP,menuopenNewPwdT,menuopenNewPwdRepT,
				modone,menuopenOldPwdP,menuopenOldPwdT);		
		
		menuopenNewPwdStage.show();
	};		
	
    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test-Pwds-View Handling
	 */	
	
	
	/**
	 * event handler for the back-button in test-pwd view
	 */
	private EventHandler<ActionEvent> testpwdBackHandler = testpwdBackEvent -> {
		// reset Test-Password pane
		testpwdMsgCl.fire();
		testpwdownfield.setText("");
		testpwdchooselength.setValue(null);
		
		testpwdfixedlength.setSelected(false);
		testpwdnumbers.setSelected(false);
		testpwdspecial.setSelected(false);
		testpwdspace.setSelected(false);
		testpwdestimateOwn.setSelected(true);
		
		togglePane(menuopenPane, testpwdFrameSelected, menuOpVSel);
	};
	
	
	/**
	 * event handler for evaluating a password from the user
	 */
	private EventHandler<ActionEvent> testpwdEstimateHandler = testpwdEstimateEvent -> {
		testpwdMsgCl.fire();
		String toTest;
		if(testpwdestimateOwn.isSelected()) {
			toTest = testpwdownfield.getText();
		} else {
			boolean special = testpwdspecial.isSelected();
			boolean space = testpwdspace.isSelected();
			boolean numb = testpwdnumbers.isSelected();
			int length;
			if(testpwdfixedlength.isSelected()) {
				try {
					length = testpwdchooselength.getSelectionModel().getSelectedItem();
				} catch (Exception e) {
					length = 6;
				}
			} else {
				length = rand.nextInt(15-2) + 2;
			}
			toTest = PasswordSecurity.getRandomPassword(length, space, numb, special);
		}
		
		testpwdMsgLabel3.setText(toTest);
		double score = Math.min(PasswordSecurity.estimatePwStrength(toTest), 100.0);
		String scoreStr = String.format("%.2f", score);
		testpwdMsgLabel4.setText(scoreStr + "/100");
		
		testpwdMsg.getChildren().addAll(testpwdMsgCl,testpwdMsgLabel1,testpwdMsgLabel2,testpwdMsgLabel3,testpwdMsgLabel4);
		VC.decoratePane(testpwdMsg, FC.blackBord, FC.grayBa);
		mainFrame.getChildren().add(testpwdMsg);
	};
	
    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Decrypt-View Handling
	 */
	
	
	/**
	 * event handler for the back-button in decrypt view
	 */
	private EventHandler<ActionEvent> decryptBackHandler = decryptBackEvent -> {
		VC.setDisSt(true, new Node[] {decryptShow, decryptChange, decryptDelete});
		decryptClI.fire();
		togglePane(menuopenPane, decVSel, menuOpVSel);
	};
	
	/**
	 * event handler for the change-button in the decrypt view
	 */
	private EventHandler<ActionEvent> decryptChangeHandler = decryptChangeEvent -> {
		decChSel = true;
		decShSel = false;
		decryptClI.fire();
		decryptChangeAndShow();
	};
	
	/**
	 * event handler for the show-button in the decrypt view
	 */
	private EventHandler<ActionEvent> decryptShowHandler = decryptShowEvent -> {
		decChSel = false;
		decShSel = true;
		decryptClI.fire();
		decryptChangeAndShow();
	};
	
	/**
	 * event handler for the delete-button in the decrypt view
	 */
	private EventHandler<ActionEvent> decryptDeleteHandler = decryptDeleteEvent -> {
		
		decryptClI.fire();
		Alert exit = new Alert(AlertType.CONFIRMATION);

		exit.initOwner(stage);
		exit.getButtonTypes().clear();
		exit.setTitle(names.decryptDeleteTitle);
		exit.setHeaderText(names.decryptDeleteQuest);
		exit.setContentText(names.decryptDeleteMsg);
		ButtonType buttonTypeYes = new ButtonType(names.conExitYN, ButtonBar.ButtonData.YES);
		ButtonType buttonTypeNo = new ButtonType(names.conExitNN, ButtonBar.ButtonData.CANCEL_CLOSE);
		exit.getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
		Button exitYesButton = (Button) exit.getDialogPane().lookupButton(buttonTypeYes);
		Button exitNoButton = (Button) exit.getDialogPane().lookupButton(buttonTypeNo);
		exitNoButton.setDefaultButton(false);
		exitYesButton.setDefaultButton(true);

		Optional<ButtonType> result = exit.showAndWait();
		if(result.get() == buttonTypeYes) {	
			
			// identify the entries to be deleted
			// indices are collected and afterwards deleted via Controller.deleteListEntries
			ArrayList<Integer> deleteIndices = new ArrayList<Integer>();
			for(int j=0; j<decryptPwds.size(); j++) {
				if(decSel.get(j)) {
					deleteIndices.add(j);
				}
			}
			Controller.deleteListEntries(deleteIndices);	
			
			decryptMsgPane.getChildren().addAll(decryptDoneIcon, decryptClI, decryptSuccessDel);
			VC.decoratePane(decryptMsgPane, FC.newMPDoBord, FC.newMPDoBa);
			mainFrame.getChildren().addAll(decryptMsgPane);
			
			// update the button pane
			setDecryptButtons();
			decButSel = 0;
			VC.setDisSt(true, new Node[] {decryptShow, decryptChange, decryptDelete});
			decryptDeleteEvent.consume();
		} else {
			decryptDeleteEvent.consume();
		}
	};
	
	
	/**
	 * event handler for addressing a single entry in the decrypt view
	 */
	private EventHandler<ActionEvent> decryptSingleButtonHandler = decryptSingleButtonEvent -> {
		int index = -1;
		for(int j = 0; j < decryptPwds.size(); j++) {
			if(decryptPwds.get(j).equals(decryptSingleButtonEvent.getSource())) {
				index = j;
				break;
			}
		}

		if(decSel.get(index)) {
			decSel.set(index, false);
			decButSel--;
			decryptPwds.get(index).setBorder(new Button().getBorder());
		} else {
			decSel.set(index, true);
			decButSel++;
			decryptPwds.get(index).setBorder(FC.decButSelBord);
		}

		if(decButSel == 0) {
			VC.setDisSt(true, new Node[] {decryptShow, decryptChange, decryptDelete});
		} else {
			VC.setDisSt(false, new Node[] {decryptShow, decryptChange, decryptDelete});
		}
	};
	
	
	/**
	 * method for putting the stored entries in their associated place in the decrypt view
	 */
	private void setDecryptButtons() {
		decryptContent.getChildren().clear();
		decryptPwds = new ArrayList<Button>();
		decSel = new ArrayList<Boolean>();

		String[] existingNames = Controller.getExistingNameList();
		
		double nM = (0.5-0.5*0.01)/4.0;
		double gM = 1.2*nM;

		for(int j = 0; j < existingNames.length; j++) {
			ImageView img = logos.checkForExistence(existingNames[j]);

			Button toAdd;
			if(img == null) {
				toAdd = VC.getNewBut(existingNames[j], FC.empty, FC.inMT, false, decryptSingleButtonHandler);
			} else {
				toAdd = VC.getNewImBut(img, nM * FC.inWi, gM * FC.inHe, decryptSingleButtonHandler);
			}

			decryptPwds.add(toAdd);
			decSel.add(false);
		}

		try {
			for(int j = 0; j < decryptPwds.size(); j++) {
				decryptPwds.get(j).setPrefHeight(gM * scene.getHeight());
				decryptPwds.get(j).setPrefWidth(nM * scene.getWidth());
				decryptPwds.get(j).setMinHeight(gM * scene.getHeight());
				decryptPwds.get(j).setMinWidth(nM * scene.getWidth());
				decryptPwds.get(j).setMaxHeight(gM * scene.getHeight());
				decryptPwds.get(j).setMaxWidth(nM * scene.getWidth());
				GridPane.setRowIndex(decryptPwds.get(j), 1 + j / 4);
				GridPane.setColumnIndex(decryptPwds.get(j), 1 + j % 4);
				decryptContent.getChildren().add(decryptPwds.get(j));
			}
		} catch (Exception e) {
			for(int j = 0; j < decryptPwds.size(); j++) {
				decryptPwds.get(j).setPrefHeight(gM * FC.inHe);
				decryptPwds.get(j).setPrefWidth(nM * FC.inWi);
				decryptPwds.get(j).setMinHeight(gM * FC.inHe);
				decryptPwds.get(j).setMinWidth(nM * FC.inWi);
				decryptPwds.get(j).setMaxHeight(gM * FC.inHe);
				decryptPwds.get(j).setMaxWidth(nM * FC.inWi);
				GridPane.setRowIndex(decryptPwds.get(j), 1 + j / 4);
				GridPane.setColumnIndex(decryptPwds.get(j), 1 + j % 4);
				decryptContent.getChildren().add(decryptPwds.get(j));
			}
		}

		if(decryptPwds.size() == 0) {
			decryptShow.setDisable(true);
			decryptChange.setDisable(true);
			decryptDelete.setDisable(true);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Decrypt-Stage Handling (Shows selected entries)
	 */
	
	/**
	 * create a new view as pop-up, containing the requested entries
	 */
	private void decryptChangeAndShow() {

		decryptShowStage = new Stage();
		decryptShowMainFrame = new AnchorPane();

		double ocInWi = 0.4 * scene.getWidth();
		double ocInHe = 0.9 * scene.getHeight();

		decryptShowScroll = VC.getNewScP(FC.ocScAn, ocInWi, ocInHe);

		decryptShowContent = new GridPane();
		decryptShowContent.setMaxHeight(ocInHe);
		decryptShowContent.setMaxWidth(0.95 * ocInWi);

		decryptShowScroll.setContent(decryptShowContent);

		double font = (scene.getWidth() + scene.getHeight()) / FC.butDiv;

		ArrayList<String[]> toShow = Controller.getPwds(decSel);
		decryptShowTexts = new ArrayList<TextField>();
		decryptShowOldVals = new ArrayList<String>();

		for(int j = 0; j < toShow.size(); j++) {
			String[] cinfo = toShow.get(j);
			
			TextField n = VC.getOcTField(names.encNN, cinfo[0], 5*j+1, 1, ocInWi, 0.11*ocInHe, font);

			n.textProperty().addListener((observable, oldVal, newVal) -> {
				if(newVal.equals("")) {
					VC.setTextFieldError(decryptShowOK, n);
				} else {
					n.setBackground(FC.empBa);
					n.setBorder(FC.encBord);
				}
			});

			TextField un = VC.getOcTField(names.encUNN, cinfo[1], 5*j+2, 1, ocInWi, 0.11*ocInHe, font);
			TextField pwd = VC.getOcTField(names.encPwdN, cinfo[2], 5*j+3, 1, 0.5*ocInWi, 0.11*ocInHe, font);		
			TextField pwdRep = VC.getOcTField(names.encRepPwdN, cinfo[2], 5*j+3, 2, 0.5*ocInWi, 0.11*ocInHe, font);

			pwd.textProperty().addListener((observable, oldVal, newVal) -> {
				if(newVal.equals("")) {
					VC.setTextFieldError(decryptShowOK, pwd);
				} else if(!newVal.equals(pwdRep.getText())) {
					VC.setTextFieldError(decryptShowOK, pwd, pwdRep);
				} else if(pwdRep.getText().equals("")) {
					VC.setTextFieldValid(decryptShowOK, pwd);
				} else {
					VC.setTextFieldValid(decryptShowOK, pwd, pwdRep);
				}
			});

			pwdRep.textProperty().addListener((observable, oldVal, newVal) -> {
				if(newVal.equals("")) {
					VC.setTextFieldError(decryptShowOK, pwdRep);
					pwdRep.setBackground(FC.newMPErrBa);
					pwdRep.setBorder(FC.newMPErrBord);
				} else if(!newVal.equals(pwd.getText())) {
					VC.setTextFieldError(decryptShowOK, pwd, pwdRep);
				} else if(pwd.getText().equals("")) {
					VC.setTextFieldValid(decryptShowOK, pwdRep);
				} else {
					VC.setTextFieldValid(decryptShowOK, pwd, pwdRep);
				}
			});	

			TextField com = VC.getOcTField(names.encComN, cinfo[3], 5*j+4, 1, ocInWi, 0.11*ocInHe, font);
			TextField emp = VC.getOcTField("", "", 5*j+5, 1, ocInWi, 0.11*ocInHe, font);
			
			VC.addTexts(decryptShowTexts, new TextField[] {n, un, pwd, pwdRep, com});

			VC.addStrings(decryptShowOldVals, new String[] {cinfo[0], cinfo[1],	cinfo[2], cinfo[2], cinfo[3]});
			if(j != toShow.size() - 1) {
				decryptShowOldVals.add("");
			}
			
			decryptShowContent.getChildren().addAll(n, un, pwd, pwdRep, com);

			n.setDisable(true);
			emp.setDisable(true);
			
			if(j != toShow.size() - 1) {
				decryptShowContent.getChildren().add(emp);
				VC.addTexts(decryptShowTexts, new TextField[] {emp});
			}
				
			if(decShSel) {
				VC.setDisSt(true, new Node[] {un, pwd, pwdRep, com});
			}
		}

		decryptShowButtonPane = VC.getNewAnP(FC.ocButPAn, ocInWi, ocInHe);

		decryptShowClose = VC.getOcStBut(FC.ocCaAn, names.ocStDo, font, ocInWi, 
				decryptShowButtonPane.getHeight(), decryptStageCloseHandler);
		decryptShowOK = VC.getOcStBut(FC.ocOKAn, names.ocStOv, font, ocInWi, 
				decryptShowButtonPane.getHeight(), decryptStageOKHandler);
		decryptShowChange = VC.getOcStBut(FC.ocChAn, names.ocStCh, font, ocInWi, 
				decryptShowButtonPane.getHeight(), decryptStageChangeHandler);

		decryptShowButtonPane.getChildren().addAll(decryptShowClose, decryptShowOK, decryptShowChange);

		if(decShSel) {
			VC.setNodesUnusable(new Node[] {decryptShowOK});
		} else if(decChSel) {
			VC.setNodesUnusable(new Node[] {decryptShowChange});
		}

		decryptShowMainFrame.getChildren().addAll(decryptShowScroll, decryptShowButtonPane);

		decryptShowStage.initOwner(stage);
		decryptShowStage.centerOnScreen();
		decryptShowStage.initModality(Modality.WINDOW_MODAL);

		decryptShowScene = new Scene(decryptShowMainFrame, ocInWi, ocInHe);
		decryptShowStage.initStyle(StageStyle.UTILITY);

		decryptShowStage.setScene(decryptShowScene);
		decryptShowStage.setTitle(names.ocStTit);
		decryptShowStage.setOnCloseRequest(decryptStageExitHandler);

		decryptShowStage.setMinHeight(ocInHe);
		decryptShowStage.setMaxHeight(ocInHe);
		decryptShowStage.setMinWidth(ocInWi);
		decryptShowStage.setMaxHeight(ocInWi);
		
		decryptShowStage.show();
		decryptShowStage.setResizable(false);
		
		decryptShowClose.requestFocus();
	}
	
	
	/**
	 * event handler for pressing the close-button on the second-decrypt-stage
	 */
	private EventHandler<WindowEvent> decryptStageExitHandler = decryptStageExitEvent -> {
		if(!decShSel) {

			boolean hasChanged = false;
			for(int j = 0; j < decryptShowOldVals.size(); j++) {
				if(!decryptShowOldVals.get(j).equals(decryptShowTexts.get(j).getText())) {
					hasChanged = true;
					break;
				}
			}

			if(hasChanged) {
				Alert exit = new Alert(AlertType.CONFIRMATION);

				exit.initOwner(decryptShowStage);
				exit.getButtonTypes().clear();
				exit.setTitle(names.ocTit);
				exit.setHeaderText(names.ocHeadT);
				exit.setContentText(names.ocConT);
				ButtonType buttonTypeYes = new ButtonType(names.conExitYN, ButtonBar.ButtonData.YES);
				ButtonType buttonTypeNo = new ButtonType(names.conExitNN, ButtonBar.ButtonData.CANCEL_CLOSE);
				exit.getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);

				Button exitYesButton = (Button) exit.getDialogPane().lookupButton(buttonTypeYes);
				Button exitNoButton = (Button) exit.getDialogPane().lookupButton(buttonTypeNo);
				exitNoButton.setDefaultButton(false);
				exitYesButton.setDefaultButton(true);

				Optional<ButtonType> result = exit.showAndWait();
				if(result.get() == buttonTypeYes) {			
				} else {
					decryptStageExitEvent.consume();
					return;
				}
			}
		}
		
		for(int j = 0; j < decryptPwds.size(); j++) {
			if(decSel.get(j)) {
				decryptPwds.get(j).fire();
			}
		}
	};
	
	
	/**
	 * event handler for the close-button in the second-decrypt-stage
	 */
	private EventHandler<ActionEvent> decryptStageCloseHandler = decryptStageCloseEvent -> {
		decryptShowStage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	};
	
	
	/**
	 * event handler for the change-button in the second-decrypt-stage
	 */
	private EventHandler<ActionEvent> decryptStageChangeHandler = decryptStageChangeEvent -> {
		VC.setNodesUnusable(new Node[] {decryptShowChange});
		VC.setNodesUsable(new Node[] {decryptShowOK});
		for(int j = 0; j < decryptShowTexts.size(); j++) {
			if(j % 6 != 0 && j % 6 != 5) {
				decryptShowTexts.get(j).setDisable(false);
			}
		}
		decShSel = false;
		decChSel = true;
	};
	
	
	/**
	 * event handler for the OK-button in the second-decrypt-stage
	 */
	private EventHandler<ActionEvent> decryptStageOKHandler = decryptStageOKEvent -> {
		
		ArrayList<String> toStore = new ArrayList<String>();		
		// loop over number of blocks		
		for(int j=0; j < (decryptShowTexts.size()+1)/6; j++) {
			toStore.add(decryptShowTexts.get(j*6).getText());
			toStore.add(decryptShowTexts.get(j*6+1).getText());
			toStore.add(decryptShowTexts.get(j*6+2).getText());
			toStore.add(decryptShowTexts.get(j*6+4).getText());		
		}
		
		Controller.storeEntryChanges(toStore);
		decryptShowStage.close();
		
		for(int j = 0; j < decryptPwds.size(); j++) {
			if(decSel.get(j)) {
				decryptPwds.get(j).fire();
			}
		}	
		decryptMsgPane.getChildren().addAll(decryptDoneIcon, decryptClI, decryptSuccessOv);
		VC.decoratePane(decryptMsgPane, FC.newMPDoBord, FC.newMPDoBa);
		mainFrame.getChildren().addAll(decryptMsgPane);
	};
	
	/////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Encrypt-View Handling
	 */

	
	/**
	 * event handler for the back button in the encrypt view
	 */
	private EventHandler<ActionEvent> encryptBackHandler = encryptBackEvent -> {
		VC.setDisSt(false, new Node[] {encryptPwds.get(0)[0], encryptPwds.get(0)[1], encryptPwdsT.get(0)[0],
				encryptPwdsT.get(0)[1], encryptText.get(0)[0], encryptText.get(0)[1], encryptText.get(0)[2], 
				encryptShowPwds, encryptSave, encryptPlus, encryptMinus, encryptBackButton});
		while(encryptPwds.size() > 1) {
			encryptMinus.fire();
		}	
		encryptMsgCl.fire();
		
		encryptText.get(0)[0].setBackground(FC.empBa);
		encryptText.get(0)[1].setBackground(FC.empBa);
		encryptText.get(0)[2].setBackground(FC.empBa);
		encryptPwds.get(0)[0].setBackground(FC.empBa);
		encryptPwds.get(0)[1].setBackground(FC.empBa);
		encryptPwdsT.get(0)[0].setBackground(FC.empBa);
		encryptPwdsT.get(0)[1].setBackground(FC.empBa);
		
		VC.resetFields(new TextField[] {encryptPwds.get(0)[0], encryptPwds.get(0)[1], encryptPwdsT.get(0)[0],
				encryptPwdsT.get(0)[1], encryptText.get(0)[0], encryptText.get(0)[1], encryptText.get(0)[2]});
		VC.setNodesUnusable(new Node[] {encryptAgain, encryptDone});
		encryptShowPwds.setSelected(false);
		encryptPane.getChildren().remove(encryptAgain);
		encryptPane.getChildren().remove(encryptDone);
				
		togglePane(menuopenPane, encVSel, menuOpVSel);
	};
	
	
	/**
	 * event handler for the again-button in the encrypt view
	 */
	private EventHandler<ActionEvent> encryptAgainHandler = encryptAgainEvent -> {
		VC.setDisSt(false, new Node[] {encryptPwds.get(0)[0], encryptPwds.get(0)[1], encryptPwdsT.get(0)[0],
				encryptPwdsT.get(0)[1], encryptText.get(0)[0], encryptText.get(0)[1], encryptText.get(0)[2], 
				encryptShowPwds, encryptSave, encryptPlus, encryptMinus, encryptBackButton});
		encryptMsgCl.fire();
		while(encryptPwds.size() > 1) {
			encryptMinus.fire();
		}	
		VC.resetFields(new TextField[] {encryptPwds.get(0)[0], encryptPwds.get(0)[1], encryptPwdsT.get(0)[0],
				encryptPwdsT.get(0)[1], encryptText.get(0)[0], encryptText.get(0)[1], encryptText.get(0)[2]});
		VC.setNodesUnusable(new Node[] {encryptAgain, encryptDone});
		encryptShowPwds.setSelected(false);
		encryptPane.getChildren().remove(encryptAgain);
		encryptPane.getChildren().remove(encryptDone);
	};
	
	
	/**
	 * event handler for the done-button in the encrypt view
	 */
	private EventHandler<ActionEvent> encryptDoneHandler = encryptDoneEvent -> {
		encryptAgain.fire();
		encryptBackButton.fire();
		encryptShowPwds.setSelected(false);
	};
	
	
	/**
	 * event handler for the OK-button in the encrypt view
	 */
	private EventHandler<ActionEvent> encryptOKHandler = encryptOKEvent -> {
		encryptMsgCl.fire();

		encryptMsgPane.getChildren().addAll(encryptDoneIcon, encryptMsgCl, encryptDoneLabel);
		VC.decoratePane(encryptMsgPane, FC.newMPDoBord, FC.newMPDoBa);
		mainFrame.getChildren().addAll(encryptMsgPane);
		
		encryptText.get(0)[0].setBackground(FC.empBa);

		VC.setNodesUnusable(new Node[] {encryptDismiss, encryptOK, encryptChange});
		encryptPane.getChildren().removeAll(encryptDismiss, encryptChange, encryptOK);
		VC.setNodesUsable(new Node[] {encryptAgain, encryptDone});
		encryptPane.getChildren().addAll(encryptAgain, encryptDone);
		
		ArrayList<String[]> toStore = new ArrayList<String[]>();
		for(int j = 0; j < encryptPwds.size(); j++) {

			String[] toAdd = new String[4];
			toAdd[0] = encryptText.get(j)[0].getText();
			toAdd[1] = encryptText.get(j)[1].getText();
			toAdd[2] = encryptText.get(j)[2].getText();
			if(encryptShowPwds.isSelected()) {
				toAdd[3] = encryptPwdsT.get(j)[0].getText();
			} else {
				toAdd[3] = encryptPwds.get(j)[0].getText();
			}
			toStore.add(toAdd);

			VC.setDisSt(true, new Node[] {encryptPwds.get(j)[0], encryptPwds.get(j)[1], encryptPwdsT.get(j)[0],
					encryptPwdsT.get(j)[1], encryptText.get(j)[0], encryptText.get(j)[1], encryptText.get(j)[2], 
					encryptShowPwds, encryptSave, encryptPlus, encryptMinus});
		}		
		Controller.storeAndOverwrite(toStore);
	};
	
	
	/**
	 * event handler for the change-button in the encrypt view
	 */
	private EventHandler<ActionEvent> encryptChangeHandler = encryptChangeEvent -> {
		encryptMsgCl.fire();
		VC.decoratePane(encryptMsgPane, null, null);
		VC.setNodesUnusable(new Node[] {encryptDismiss, encryptChange, encryptOK});
		encryptPane.getChildren().removeAll(encryptDismiss, encryptChange, encryptOK);
		for(int j = 0; j < encryptPwds.size(); j++) {
			VC.setDisSt(false, new Node[] {encryptPwds.get(j)[0], encryptPwds.get(j)[1], encryptPwdsT.get(j)[0],
					encryptPwdsT.get(j)[1], encryptText.get(j)[0], encryptText.get(j)[1], encryptText.get(j)[2], 
					encryptShowPwds, encryptSave, encryptPlus, encryptMinus});
		}
	};
	
	
	/**
	 * event handler for the dismiss-button in the encrypt view
	 */
	private EventHandler<ActionEvent> encryptDismissHandler = encryptDismissEvent -> {
		encryptMsgCl.fire();

		VC.setDisSt(false, new Node[] {encryptPwds.get(0)[0], encryptPwds.get(0)[1], encryptPwdsT.get(0)[0],
				encryptPwdsT.get(0)[1], encryptText.get(0)[0], encryptText.get(0)[1], encryptText.get(0)[2], 
				encryptShowPwds, encryptSave, encryptPlus, encryptMinus, encryptBackButton});
		VC.resetFields(new TextField[] {encryptPwds.get(0)[0], encryptPwds.get(0)[1], encryptPwdsT.get(0)[0],
				encryptPwdsT.get(0)[1], encryptText.get(0)[0], encryptText.get(0)[1], encryptText.get(0)[2]});
		VC.setNodesUnusable(new Node[] {encryptDismiss, encryptChange, encryptOK});

		while(encryptPwds.size() > 1) {
			encryptMinus.fire();
		}
		encryptPane.getChildren().removeAll(encryptDismiss, encryptChange, encryptOK);
		encryptText.get(0)[0].setBackground(FC.empBa);
		encryptShowPwds.setSelected(false);
	};
	
	
	/**
	 * event handler for the save-button in the encrypt view
	 */
	private EventHandler<ActionEvent> encryptSaveHandler = encryptSaveEvent -> {
		encryptMsgCl.fire();

		for(int j = 0; j < encryptPwds.size(); j++) {
			VC.setBack(FC.empBa, new TextField[] {encryptPwds.get(j)[0], encryptPwds.get(j)[1], 
					encryptPwdsT.get(j)[0], encryptPwdsT.get(j)[1], encryptText.get(j)[0]});
		}

		boolean missing = false;
		ArrayList<Integer> toCheck = new ArrayList<Integer>();

		for(int j = 0; j < encryptPwds.size(); j++) {
			if(encryptPwds.get(j)[0].getText().equals("") && encryptPwds.get(j)[1].getText().equals("")
					&& encryptPwdsT.get(j)[0].getText().equals("") && encryptPwdsT.get(j)[1].getText().equals("")
					&& encryptText.get(j)[0].getText().equals("") && encryptText.get(j)[1].getText().equals("") 
					&& encryptText.get(j)[2].getText().equals("")) {
				continue;
			}	
			toCheck.add(j);

			String first = encryptText.get(j)[0].getText();
			String second = "";
			String third = "";
			if(encryptShowPwds.isSelected()) {
				second = encryptPwdsT.get(j)[0].getText();
				third = encryptPwdsT.get(j)[1].getText();
			} else {
				second = encryptPwds.get(j)[0].getText();
				third = encryptPwds.get(j)[1].getText();
			}

			if(first.equals("")) {
				encryptText.get(j)[0].setBackground(FC.errMsgBa);
				missing = true;
			}
			if(second.equals("")) {
				VC.setBack(FC.errMsgBa, new TextField[] {encryptPwdsT.get(j)[0], encryptPwds.get(j)[0]});
				missing = true;
			}
			if(third.equals("")) {
				VC.setBack(FC.errMsgBa, new TextField[] {encryptPwdsT.get(j)[1], encryptPwds.get(j)[1]});
				missing = true;
			}
		}

		if(missing) {
			encryptMsgPane.getChildren().addAll(encryptErrorIcon, encryptMsgCl, encryptMissError);
			VC.decoratePane(encryptMsgPane, FC.errMsgBord, FC.errMsgBa);
			mainFrame.getChildren().addAll(encryptMsgPane);
			return;
		}

		boolean hasDuplicates = false;
		for(int j = 0; j < toCheck.size(); j++) {
			for(int k = 0; k < toCheck.size(); k++) {
				if(k == j) {
					continue;
				}
				if(encryptText.get(toCheck.get(j))[0].getText().equals(encryptText.get(toCheck.get(k))[0].getText())) {
					hasDuplicates = true;
					VC.setBack(FC.errMsgBa, new TextField[] {encryptText.get(toCheck.get(j))[0], encryptText.get(toCheck.get(k))[0]});
				}
			}
		}
	
		if(hasDuplicates) {
			encryptMsgPane.getChildren().addAll(encryptErrorIcon, encryptMsgCl, encryptMuError);
			VC.decoratePane(encryptMsgPane, FC.errMsgBord, FC.errMsgBa);
			mainFrame.getChildren().addAll(encryptMsgPane);
			return;
		}

		boolean differentPwds = false;
		for(int j = 0; j < encryptPwds.size(); j++) {
			if(toCheck.size() == 0) {
				continue;
			}
			String first = "";
			String second = "";
			if(encryptShowPwds.isSelected()) {
				first = encryptPwdsT.get(j)[0].getText();
				second = encryptPwdsT.get(j)[1].getText();
			} else {
				first = encryptPwds.get(j)[0].getText();
				second = encryptPwds.get(j)[1].getText();
			}

			if(!first.equals(second)) {
				differentPwds = true;
				VC.setBack(FC.errMsgBa, new TextField[] {encryptPwdsT.get(j)[0], encryptPwdsT.get(j)[1], 
						encryptPwds.get(j)[0], encryptPwds.get(j)[1]});
			}
		}

		if(differentPwds) {
			encryptMsgPane.getChildren().addAll(encryptErrorIcon, encryptMsgCl, encryptDiff);
			VC.decoratePane(encryptMsgPane, FC.errMsgBord, FC.errMsgBa);
			mainFrame.getChildren().addAll(encryptMsgPane);
			return;
		}

		if(toCheck.size() == 0) {
			encryptMsgPane.getChildren().addAll(encryptErrorIcon, encryptMsgCl, encryptNoEntry);
			VC.decoratePane(encryptMsgPane, FC.errMsgBord, FC.errMsgBa);
			mainFrame.getChildren().addAll(encryptMsgPane);
			return;
		}

		boolean alreadyExists = false;
		for(int j = 0; j < toCheck.size(); j++) {
			boolean temp = Controller.checkForPwdDuplicates(encryptText.get(toCheck.get(j))[0].getText());
			if(temp) {
				alreadyExists = true;
				encryptText.get(toCheck.get(j))[0].setBackground(FC.errMsgBa);
			}
		}
		if(alreadyExists) {
			encryptMsgPane.getChildren().addAll(encryptOverwriteQuest, encryptMsgCl, encryptExError);
			VC.decoratePane(encryptMsgPane, FC.errMsgBord, FC.errMsgBa);
			mainFrame.getChildren().addAll(encryptMsgPane);
			
			VC.setNodesUsable(new Node[] {encryptDismiss, encryptChange, encryptOK});
			encryptPane.getChildren().addAll(encryptDismiss, encryptChange, encryptOK);
			
			for(int j = 0; j < encryptPwds.size(); j++) {
				VC.setDisSt(true, new Node[] {encryptPwds.get(j)[0], encryptPwds.get(j)[1], encryptPwdsT.get(j)[0],
						encryptPwdsT.get(j)[1], encryptText.get(j)[0], encryptText.get(j)[1], encryptText.get(j)[2],
						encryptShowPwds, encryptSave, encryptPlus, encryptMinus, encryptBackButton});
			}	
			return;
		}

		for(int j = 0; j < toCheck.size(); j++) {
			String name = encryptText.get(toCheck.get(j))[0].getText();
			String uname = encryptText.get(toCheck.get(j))[1].getText();
			String comment = encryptText.get(toCheck.get(j))[2].getText();
			String p = "";
			if(encryptShowPwds.isSelected()) {
				p = encryptPwdsT.get(toCheck.get(j))[0].getText();
			} else {
				p = encryptPwds.get(toCheck.get(j))[0].getText();
			}
			Controller.appendNewEntry(name, uname, comment, p);
		}

		encryptMsgPane.getChildren().addAll(encryptDoneIcon, encryptMsgCl, encryptDoneLabel);
		VC.decoratePane(encryptMsgPane, FC.newMPDoBord, FC.newMPDoBa);
		mainFrame.getChildren().addAll(encryptMsgPane);
		VC.setNodesUsable(new Node[] {encryptAgain, encryptDone});

		for(int j = 0; j < encryptPwds.size(); j++) {
			VC.setDisSt(true, new Node[] {encryptPwds.get(j)[0], encryptPwds.get(j)[1], encryptPwdsT.get(j)[0],
					encryptPwdsT.get(j)[1], encryptText.get(j)[0], encryptText.get(j)[1], encryptText.get(j)[2], 
					encryptShowPwds, encryptSave, encryptPlus, encryptMinus, encryptBackButton});
		}
		
		encryptPane.getChildren().addAll(encryptAgain, encryptDone);
	};	

	
	/**
	 * event handler for the plus-button in the encrypt view
	 */
	private EventHandler<ActionEvent> encryptPlusHandler = encryptPlusEvent -> {
		addNewPwdBlock(FC.inWi,FC.inHe);
	};
	
	
	/**
	 * event handler for the minus-button in the encrypt view
	 */
	private EventHandler<ActionEvent> encryptMinusHandler = encryptMinusEvent -> {
		if(encryptPwds.size() > 1) {
			encryptBlocks.get(encryptBlocks.size()-1).getChildren().clear();
			encryptVbox.getChildren().remove(encryptBlocks.get(encryptBlocks.size()-1));
			encryptPwds.remove(encryptPwds.size()-1);
			encryptPwdsT.remove(encryptPwdsT.size()-1);
			encryptText.remove(encryptText.size()-1);
			encryptBlocks.remove(encryptBlocks.size()-1);
		}
	};
	
	
	/**
	 * method for adding a single block in the encrypt view (for storing an additional entry)
	 */
	private void addNewPwdBlock(double width, double height) {

		AnchorPane ancPaneToAdd = new AnchorPane();
		ancPaneToAdd.setPrefSize(0.5 * width, 0.35 * height);

		Triple temT = new Triple(width, height, FC.butDiv);
		double wi = ancPaneToAdd.getPrefWidth();
		double hi = ancPaneToAdd.getPrefHeight();

		PasswordField[] tPwds = new PasswordField[] {VC.getNewPwdField(names.encPwdN, FC.pwdAn, temT, wi, hi),
				VC.getNewPwdField(names.encRepPwdN, FC.repPwdAn, temT, wi, hi)};
		TextField[] tPwdTexts = new TextField[] {VC.getNewTField(names.encPwdN, FC.pwdTAn, temT, wi, hi),
				VC.getNewTField(names.encRepPwdN, FC.repPwdTAn, temT, wi, hi)};
		TextField[] tTexts = new TextField[] {VC.getNewTField(names.encNN, FC.nAn, temT, wi, hi),
				VC.getNewTField(names.encUNN, FC.unAn, temT, wi, hi), VC.getNewTField(names.encComN, FC.comAn, temT, wi, hi)};

		TextFields.bindAutoCompletion(tTexts[0], logos.getPlatformList());

		encryptPwds.add(tPwds);
		encryptPwdsT.add(tPwdTexts);
		encryptText.add(tTexts);
		encryptBlocks.add(ancPaneToAdd);

		if(encryptPwds.size() > 1) {
			if(encryptShowPwds.isSelected()) {
				VC.setNodesUnusable(tPwds);
			} else {
				VC.setNodesUnusable(tPwdTexts);
			}
		}
		ancPaneToAdd.getChildren().addAll(tTexts[0], tTexts[1], tPwds[0], tPwdTexts[0], tPwds[1], tPwdTexts[1], tTexts[2]);
		encryptVbox.getChildren().add(ancPaneToAdd);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Close-Message Handling
	 */
	private EventHandler<ActionEvent> startScreenCloseMsg = closeMsgEvent -> {
		closeMsgPane(startframeMsg);
	};
	private EventHandler<ActionEvent> newuserCloseMsg = closeNewUserMsgEvent -> {
		closeMsgPane(newuserMsgPane);
	};
	private EventHandler<ActionEvent> menuopenCloseMsgHandler = menuopenCloseMsgEvent -> {
		closeMsgPane(menuopenMsg);
	};
	private EventHandler<ActionEvent> testpwdCloseMsg = testpwdCloseMsgEvent -> {
		closeMsgPane(testpwdMsg);
	};
	private EventHandler<ActionEvent> encryptCloseMsgHandler = encryptCloseMsgEvent -> {
		closeMsgPane(encryptMsgPane);
	};
	private EventHandler<ActionEvent> decryptCloseMsgHandler = decryptCloseMsgEvent -> {
		closeMsgPane(decryptMsgPane);
	};
	private void closeMsgPane(AnchorPane pane) {
		VC.clearPane(pane);
		mainFrame.getChildren().remove(pane);
	}
	private void closeMoMsgPane(AnchorPane pane) {
		VC.clearPane(pane);
		menuopenNewPwdPane.getChildren().remove(pane);
	}
}

