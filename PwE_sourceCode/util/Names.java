package util;

/**
 * class used for toggling languages; for this version we offer english and german;
 * every string displayed in the program has its english and german translation here;
 */

public class Names {

	public Language language;
	public String frameTitle;
	public String conExitTitle;
	public String conExitQuest;
	public String conExitMsg;
	public String conExitYN;
	public String conExitNN;
	public String movDecNoPwsMsg;
	public String usMCon;
	public String usMTit;

	public String encPwdN;
	public String encRepPwdN;
	public String encNN;
	public String encUNN;
	public String encComN;
	public String encAgN;
	public String encDoN;
	public String encLabDo;
	public String encLabWMP;
	public String encLabMErr;
	public String encLabExErr;
	public String encNoEnt;
	public String encDiffP;
	public String encMissErr;
	public String encOvMiss;
	public String encDisN;
	public String encChN;
	public String encOKN;
	public String encPwdField;
	
	public String decPwdBox;
	public String decChN;
	public String decSN;
	public String decPwdN;
	public String decOKN;
	public String decCaN;
	public String decNoMPN;
	public String decWrMPN;
	public String decDelN;
	public String decSuc;
	
	public String ocStTit;
	public String ocStDo;
	public String ocStOv;
	public String ocStCh;
	public String ocTit;
	public String ocHeadT;
	public String ocConT;
	

	
	public String loginHeader;
	public String loginTitle;
	public String loginSecondTitle;
	public String loginTextfield;
	public String loginPwdfield;
	public String loginLoginButton;
	public String loginNewlabel;
	public String loginNewbutton;
	public String logincheckbox;
	public String loginMenu;
	public String loginHelp;
	public String loginLanguage;
	public String loginGerman;
	public String loginEnglish;
	
	public String startframenoname;
	public String startframenopwd;
	public String startframewrname;
	public String startframewrpwd;
	
	public String newuserTitle;
	public String newuserchoose;
	public String newuserpwd;
	public String newusergenpwd;
	public String newuseruntitle;
	public String newuserpwdtitle;
	public String newuserpwdfield;
	public String newuserpwdfieldr;
	public String newusershow;
	public String newuserspecial;
	public String newuserfixed;
	public String newuserlength;
	public String newuserdone;
	public String newuserlabelnoun;
	public String newuserlabelexun;
	public String newuserlabelmiss;
	public String newuserlabelmissr;
	public String newuserlabelwrong;
	
	public String confirmnewusertitle;
	public String confirmnewuserquest1;
	public String confirmnewuserquest2;
	public String confirmnewusercont1;
	public String confirmnewusercont2a;
	public String confirmnewusercont2b;
	public String confirmnewuseryes;
	public String confirmnewuserno;
	

	
	public String menuframetitle;	
	public String menuopenlogout;
	public String menuopenenc;
	public String menuopendec;
	public String menuopentest;
	public String menuopenhelp;
	public String menuopenenglish;
	public String menuopengerman;
	public String menuopenlang;
	public String menuopenchange;
	public String menuopendelete;
	public String menuopenreset;
	public String menuopenopt;
	public String menuopenwrongres;
	public String menuopenrightres;
	public String menuopennewmpwd;
	
	public String confirmlogouttitle;
	public String confirmlogouthead;
	public String confirmlogoutcont;
	public String confirmlogoutyes;
	public String confirmlogoutno;
	
	public String resetaccounthead;
	public String resetaccountlabel;
	public String resetpwdhead;
	public String resetpwdlabel;
	public String resetpwdfield;
	public String resetpwdshow;
	public String resetconfirm;
	public String resetcancel;
	
	public String motext;
	public String motextR;
	public String moshow;
	public String molengthlabel;
	public String mospecial;
	public String mofixed;
	public String mochoose;
	public String mogen;
	public String motitle;
	public String menuopenOldpwd;
	public String menuopenDone;
	public String moMissOldPwd;
	public String moMissPwd;
	public String moMissRepPwd;
	public String moNonEqualPwd;
	public String moWrongPwd;
	
	public String testpwdowninp;
	public String testpwdgeninp;
	public String testpwdfield;
	public String testpwdspecial;
	public String testpwdspace;
	public String testpwdnumb;
	public String testpwdfixed;
	public String testpwdchlen;
	public String testpwdstrength;
	public String testpwdlabel1;
	public String testpwdlabel2;
	public String testpwdheader;

	public String decryptDeleteTitle;
	public String decryptDeleteMsg;
	public String decryptDeleteQuest;
	public String decryptSucDel;
	public String decryptSucCh;
	
	
	public Names(Language lang) {
		
		this.language = lang;
		
		
		/*
		 * Language independent Strings - Start screen
		 */
		loginGerman = "Deutsch";
		loginEnglish = "English";		
		
		
		if(lang.equals(Language.GERMAN)) {
			
			/*
			 * Start screen
			 */
			loginHeader = "PwE - Passwort-Sicherung";
			loginTitle = "Einloggen in Nutzer-Account";
			loginSecondTitle = "Nutzer-Account erstellen";
			loginTextfield = "Nutzername";
			loginPwdfield = "Master-Passwort";
			loginLoginButton = "Einloggen";
			loginNewlabel = "... noch kein bestehenden Account?";
			loginNewbutton = "Neuer Nutzer";
			logincheckbox = "Eingabe anzeigen";
			loginMenu = "Optionen";
			loginHelp = "Hilfe";
			loginLanguage = "Sprache";
			startframenoname = "Bitte geben Sie den Nutzername ein.";
			startframenopwd = "Bitte geben Sie das Master-Passwort ein.";
			startframewrname = "Der eingegebene Nutzername existiert nicht.";
			startframewrpwd = "Das eingegebene Passwort war nicht korrekt.";
			
			/**
			 * New user view
			 */
			newuserTitle = "PwE - Neuer Nutzer-Account";
			newuserchoose = "Nutzername wählen";
			newuserpwd = "Eigenes Passwort wählen";
			newusergenpwd = "Passwort generieren lassen";
			newuseruntitle = "Nutzername";
			newuserpwdtitle = "Passwort";
			newuserpwdfield = "Master-Passwort";
			newuserpwdfieldr = "Master-Passwort wiederholen";
			newusershow = "Eingabe anzeigen";
			newuserspecial = "Sonderzeichen verwenden";
			newuserfixed = "Feste Passwort Länge";
			newuserlength = "Gewünschte Länge:";
			newuserdone = "Fertig";
			
			newuserlabelnoun = "Bitte geben Sie einen Nutzernamen ein.";
			newuserlabelexun = "Der Nutzername existiert bereits, bitte treffen Sie eine andere Wahl.";
			newuserlabelmiss = "Bitte geben Sie ein Master-Passwort ein.";
			newuserlabelmissr = "Bitte wiederholen Sie das Master-Passwort.";
			newuserlabelwrong = "Die Master-Passwörter stimmen nicht überein.";
			 
			confirmnewusertitle = "Master-Passwort bestätigen";
			confirmnewuserquest1 = "Wollen Sie das gewählte Passwort behalten?";
			confirmnewusercont1 = "Ihr Passwort hat eine Sicherheit von ";
			confirmnewuserquest2 = "Wollen Sie dieses Passwort behalten?";
			confirmnewusercont2a = "Generiertes Passwort: ";
			confirmnewusercont2b = "\nEs hat eine Sicherheit von ";
			confirmnewuseryes = "Ja";
			confirmnewuserno = "Nein";
			
			/**
			 * Menu View
			 */
			menuframetitle = "PwE - Menü";
			menuopenlogout = "Ausloggen";
			menuopenenc = "Verschlüsseln";
			menuopendec = "Entschlüsseln";
			menuopentest = "Passwörter testen";
			menuopenhelp = "Hilfe";
			menuopenenglish = "English";
			menuopengerman = "Deutsch";
			menuopenlang = "Sprache";
			menuopenchange = "Master-Passwort ändern";
			menuopendelete = "Account löschen";
			menuopenreset = "Passwörter zurücksetzen";
			menuopenopt = "Optionen";
			menuopenwrongres = "Das eingegeben Master-Passwort ist nicht korrekt.";
			menuopenrightres = "Alle Passwörter in diesem Account wurden erfolgreich gelöscht.";
			menuopennewmpwd = "Das Master-Passwort wurde erfolgreich geändert.";
			
			
			confirmlogouttitle = "Logout bestätigen";
			confirmlogouthead = "Zurück zum Start-Fenster?";
			confirmlogoutcont = "Nicht-gespeicherte Änderungen werden verworfen.";
			confirmlogoutyes = "Ja";
			confirmlogoutno = "Nein";

			resetaccounthead = "Account löschen";
			resetaccountlabel = "Sie sind dabei Ihren Account und alle darin gespeicherten " + 
				"Passwörter zu löschen. Bitte geben Sie das Master-Passwort ein, falls Sie fortfahren möchten.";
			resetpwdhead = "Passwörter löschen";
			resetpwdlabel = "Sie sind dabei alle gespeicherten Passwörter in Ihrem " +
					"Account zu löschen. Bitte geben Sie das Master-Passwort ein, falls Sie fortfahren möchten.";
			resetpwdfield = "Master-Passwort";
			resetpwdshow = "Input anzeigen";
			resetconfirm = "Bestätigen";
			resetcancel = "Abbrechen";
			
			motext = "Neues Master-Passwort";
			motextR = "Master-Passwort wiederholen";
			moshow = "Eingabe anzeigen";
			molengthlabel = "Gewünschte Länge:";
			mospecial = "Sonderzeichen verwenden";
			mofixed = "Feste Passwort Länge";
			mochoose = "Eigenes Passwort wählen";
			mogen = "Passwort generieren lassen";
			motitle = "Master-Passwort ändern";
			menuopenOldpwd = "Altes Master-Passwort";
			menuopenDone = "Fertig";
			moMissOldPwd = "Bitte geben Sie das alte Master-Passwort ein.";
			moMissPwd = "Bitte geben Sie das neue Master-Passwort ein.";
			moMissRepPwd = "Bitte wiederholen Sie das neue Master-Passwort.";
			moNonEqualPwd = "Die beiden Eingaben für das neue Master-Passwort müssen übereinstimmen.";
			moWrongPwd = "Das eingegebene Master-Passwort ist nicht korrekt.";

			
			testpwdowninp = "Eigene Eingabe testen";
			testpwdgeninp = "Zufälliges Passwort testen";
			testpwdfield = "Zu testendes Passwort";
			testpwdspecial = "Sonderzeichen verwenden";
			testpwdspace = "Leerzeichen verwenden";
			testpwdnumb = "Nummern verwenden";
			testpwdfixed = "Feste Länge";
			testpwdchlen = "Gewünschte Länge:";
			testpwdstrength = "Passwort-Stärke anzeigen";
			testpwdlabel1 = "Zu bewertendens Passwort:";
			testpwdlabel2 = "Stärke:";
			testpwdheader = "Bewertung der Passwort-Sicherheit";
			
			decryptDeleteTitle = "Löschen Bestätigen";
			decryptDeleteQuest = "Sollen die ausgewählten Einträge wirklich gelöscht werden?";
			decryptDeleteMsg = "Die Einträge können nicht wiederhergestellt werden.";
			decryptSucDel = "Die Einträge wurden erfolgreich gelöscht.";
			decryptSucCh = "Die Änderungen wurden erfolgreich gespeichert.";
			
		} else {
			
			/**
			 * Start screen
			 */
			loginHeader = "PwE - Protect your passwords";
			loginTitle = "Login to your user account";
			loginSecondTitle = "Create user account";
			loginTextfield = "Username";
			loginPwdfield = "Master-Password";
			loginLoginButton = "Login";
			loginNewlabel = "... you don't have a username and password yet?";
			loginNewbutton = "New User";
			logincheckbox = "Show input";
			loginMenu = "Options";
			loginHelp = "Help";
			loginLanguage = "Language";
			startframenoname = "Please specify your account name.";
			startframenopwd = "Please type your Master-Password.";
			startframewrname = "The username you specified does not exist.";
			startframewrpwd = "The password you entered is not correct.";
			
			
			/**
			 * New User view
			 */
			newuserTitle = "PwE - New User-Account";
			newuserchoose = "Choose Username";
			newuserpwd = "Choose your own password";
			newusergenpwd = "Generate your password";
			newuseruntitle = "Username";
			newuserpwdtitle = "Password";
			newuserpwdfield = "Master-Password";
			newuserpwdfieldr = "Repeat Master-Password";
			newusershow = "Show input";
			newuserspecial = "Include special characters";
			newuserfixed = "Fixed password length";
			newuserlength = "Choose Length:";
			newuserdone = "Done";
			
			newuserlabelnoun = "Please specifiy an account name.";
			newuserlabelexun = "The account name already exists, please make another choice.";
			newuserlabelmiss = "Please type a Master-Password";
			newuserlabelmissr = "Please repeat the Master-Password.";
			newuserlabelwrong = "The Master-Password and the repitition do not coincide.";
					
			confirmnewusertitle = "Confirm Master-Password";
			confirmnewuserquest1 = "Are you sure about the password choice?";
			confirmnewusercont1 = "Your password has a security of ";
			confirmnewuserquest2 = "Do you want to keep this password?";
			confirmnewusercont2a = "The password we generated: ";
			confirmnewusercont2b = "\nIt has a security of ";
			confirmnewuseryes = "Yes";
			confirmnewuserno = "No";
			
			/**
			 * Menu View		
			 */
			menuframetitle = "PwE - Menu";
			menuopenlogout = "Logout";
			menuopenenc = "Encrypt";
			menuopendec = "Decrypt";
			menuopentest = "Test Passwords";
			menuopenhelp = "Help";
			menuopenenglish = "English";
			menuopengerman = "Deutsch";
			menuopenlang = "Language";
			menuopenchange = "Change Master-Password";
			menuopendelete = "Delete account";
			menuopenreset = "Reset Passwords";
			menuopenopt = "Options";
			menuopenwrongres = "The Master-Password you entered is not correct.";
			menuopenrightres = "All passwords in your account were successfully deleted.";
			menuopennewmpwd = "The Master-Password has been successfully changed.";
			
			
			confirmlogouttitle = "Confirm Logout";
			confirmlogouthead = "Go back to the start screen?";
			confirmlogoutcont = "Unsaved changes will be lost.";
			confirmlogoutyes = "Yes";
			confirmlogoutno = "No";
			
			resetaccounthead = "Delete Account";
			resetaccountlabel = "You are about to delete your account and " + 
				"all passwords stored therein. Please enter your Master-Password if you want to continue.";
			resetpwdhead = "Delete Passwords";
			resetpwdlabel = "You are about to delete all passwords in " +
					"your account. Please enter your Master-Password if you want to continue.";
			resetpwdfield = "Master-Password";
			resetpwdshow = "Show input";
			resetconfirm = "Confirm";
			resetcancel = "Cancel";
			
			motext = "New Master-Password";
			motextR = "Repeat Master-Password";
			moshow = "Show input";
			molengthlabel = "Choose length:";
			mospecial = "Include special characters";
			mofixed = "Fixed password length";
			mochoose = "Choose your new password";
			mogen = "Generate your new password";
			motitle = "Change Master-Password";
			menuopenOldpwd = "Old Master-Password";
			menuopenDone = "Done";
			moMissOldPwd = "Please enter the old Master-Password.";
			moMissPwd = "Please enter the new Master-Password.";
			moMissRepPwd = "Please repeat the new Master-Password.";
			moNonEqualPwd = "The two inputs for the new Master-Password must coincide.";
			moWrongPwd = "The Master-Password entered is not correct.";

			
			testpwdowninp = "Choose your own input";
			testpwdgeninp = "Test a random password";
			testpwdfield = "Enter password to test";
			testpwdspecial = "Include special characters";
			testpwdspace = "Include empty space";
			testpwdnumb = "Include numbers";
			testpwdfixed = "Fixed length";
			testpwdchlen = "Choose length:";
			testpwdstrength = "Show password strength";
			testpwdlabel1 = "Password to evaluate:";
			testpwdlabel2 = "Strength:";
			testpwdheader = "Evaluation of Password-Strengths";
			
			decryptDeleteTitle = "Confirm Deletion";
			decryptDeleteQuest = "Are you sure you want to delete the selected entries?";
			decryptDeleteMsg = "There is no way of recovering the entries afterwards.";
			decryptSucDel = "The entries were successfully deleted.";
			decryptSucCh = "The changes have been successfully saved.";
		}
				
		
		
		if(lang.equals(Language.GERMAN)) {
			this.frameTitle = "PwE - Password-Sicherung";
			this.conExitTitle = "Beenden Bestätigen";
			this.conExitQuest = "Wollen Sie die Anwendung wirklich beenden?";
			this.conExitMsg = "Alle nicht gespeicherten Änderungen werden verworfen.";
			this.conExitYN = "Ja";
			this.conExitNN = "Nein";
			this.movDecNoPwsMsg = "Um Informationen über abgespeicherte Passwörter einzusehen oder zu ändern, muss mindestens"
					+ " ein gültiger Eintrag vorliegen. Wählen Sie zunächst den Button '<something>' mit dessen Hilfe"
					+ " Sie ein neues Passwort abspeichern können.";
			this.usMTit = "Nutzerhilfe";
			
			
			/**
			 * Encrypt View.
			 */
			this.encPwdN = "Passwort Eingeben (*)";
			this.encRepPwdN = "Passwort Wiederholen (*)";
			this.encNN = "Name des Passwort Eintrages (*)";
			this.encUNN = "Nutzername";
			this.encComN = "Weitere Kommentare";
			this.encAgN = "Nochmal";
			this.encDoN = "Fertig";
			this.encLabDo = "Ihre Eingabe wurde erfolgreich gespeichert.";
			this.encLabWMP = "Das eingegebene Master-Passwort ist nicht korrekt, versuchen Sie es erneut oder verwerfen Sie die Änderungen.";
			this.encLabMErr = "Es gibt Namen in Ihrer Eingabe, die mehrmals auftreten. Bitte verwenden Sie jeden Bezeichner nur einmal.";
			this.encLabExErr = "Mindestens einer der Namen existiert bereits. Wollen Sie den bestehenden Eintrag Überschreiben?";
			this.encNoEnt = "Sie haben nichts eingeben. Bitte füllen Sie mindestens einen Block aus um die Änderungen speichern zu können.";
			this.encDiffP = "In mindestens einem Block stimmen die Passwörter nicht überein. Bitte überprüfen Sie Ihre Eingaben.";
			this.encMissErr = "In mindestens einem Block sind nicht alle Eingaben vorhanden. Bitte füllen Sie alle mit (*) markierten Felder aus.";
			this.encOvMiss = "Geben Sie bitte das Master-Password ein um die Einträge zu überschreiben.";
			this.encDisN = "Löschen";
			this.encChN = "Ändern";
			this.encOKN = "Überschreiben";
			this.encPwdField = "Master-Passwort (*)";
			
			/**
			 * Decrypt View.
			 */
			this.decPwdBox = "Inhalt des Feldes anzeigen lassen";
			this.decChN = "Ändern";
			this.decSN = "Öffnen";
			this.decPwdN = "Master-Passwort (*)";
			this.decOKN = "OK";
			this.decCaN = "Abbrechen";
			this.decNoMPN = "Sie haben kein Master-Passwort eingegeben. Bitte füllen Sie das mit (*) markierte Feld aus.";
			this.decWrMPN = "Das eingegebene Master-Passwort ist nicht korrekt. Bitte versuchen Sie es erneut.";
			this.decDelN = "Die ausgewählten Einträge wurden erfolgreich gelöscht.";
			this.decSuc = "Die Änderungen wurden erfolgreich gespeichert.";
		
			this.ocStTit = "Passwörter Anzeigen und Ändern";
			this.ocStDo = "Schließen";
			this.ocStOv = "Überschreiben";
			this.ocStCh = "Ändern";
			
			this.ocTit = "Schließen Bestätigen";
			this.ocHeadT = "Wollen Sie dieses Fenster wirklich schließen?";
			this.ocConT = "Alle Änderungen werden verworfen.";
			
			this.usMCon = "<html>"
					 + "<b><font color=\"#404040\">TEST-VERSION<br>"
					 + "</font></b><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">PwE - Passwort-Sicherung</font></b><br>"
					 + "<small>Version: " + FC.programVersion + " (beta)<br>"
					 + "<br>"
					 + "Willkommen zu PwE, einem Programm dass das Leben einfacher "
					 + "macht. Heutzutage bietet jedes Unternehmen, jede Website, etc. "
					 + "eigene Nutzer-Accounts an, was es zu einer Herausforderung "
					 + "werden lässt, den Überblick über alle Passwörter und Nutzernamen zu behalten. "
					 + "Dieses Programm hilft Ihnen dabei diese Daten "
					 + "zu organisieren; in Zukunft werden Sie sich nur noch ein Passwort, "
					 + "das Master-Passwort, merken müssen. Sie können sämtliche Passwörter mit diesem "
					 + "abspeichern und später wieder abrufen; das"
					 + "Master-Passwort kann individuell gewählt werden und ermöglicht"
					 + "dann den Zugriff auf alle anderen Passwort-Einträge."
					 + "</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">General Information</font></b><br>"
					 + "<small>Diese Software wurde von Timo Felser und Fabian Schrodi entwickelt. Sie ist "
					 + "ausschließlich für nicht-kommerzielle Zwecke zu verwenden, und die Entwickler geben "
					 + "keinerlei Garantie bezüglich Korrektheit oder Sicherheit. Das Programm verwendet "
					 + "klassische Hashing-Verfahren für den Umgang mit den Passwörtern; obwohl dieses "
					 + "Verschlüsselungsverfahren als sehr schwer zu 'knacken' gilt, besteht die theoretische Möglichkeit.<br>"
					 + "Das Program erzeugt die Datei PwEsaveIO.json, die vom Nutzer nicht verändert oder verschoben werden darf. "
					 + "Da sich die komplette Logik des Programms auf die Nutzung des Master-Passwortes stützt, weisen wir "
					 + "auf die Wichtigkeit hin, das Passwort nicht zu vergessen, da die abgespeicherten Informationen "
					 + "ohne das Master-Passwort NICHT wiederhergestellt werden können, auch nicht von den Entwicklern.</small><br>"
					 + "<br>"					 
					 + "<b><font color=\"#404040\">1. Login Fenster</font></b><br>"
					 + "<small>Nach dem Start des Programms öffnet sich das Login-Menü, von dem aus ein neuer Nutzer-Account "
					 + "erstellt werden kann (Button 'Neuer Nutzer'). Wenn Sie bereits ein Konto angelegt haben, können "
					 + "Nutzername und Master-Passwort in die dafür vorgesehenen Felder eingegeben werden, das Einloggen erfolgt "
					 + "dann durch einen Klick auf 'Einloggen'. Der Button 'Optionen' in der linken unteren Ecke "
					 + "ermöglicht es dieses Benutzerhandbuch zu öffnen, oder die Sprache zu verändern (Deutsch oder Englisch)."
					 + "</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">2. Nutzeraccount Erstellen</font></b><br>"
					 + "<small>In diesem Fenster können Sie ihren eigenen Nutzer-Account erstellen. Im Feld direkt unter "
					 + "'Nutzername' muss der Name des Account eingegeben werden. Bitte beachten Sie, dass Account-Namen "
					 + "nicht doppelt vorkommen dürfen. Bezüglich des Master-Passwortes haben Sie zwei Optionen: Entweder "
					 + "Sie wählen Ihr eigenes Passwort (Standard-Einstellung), oder Sie überlassen die Wahl dem "
					 + "Zufallsgenerator von PwE (klicken Sie hierfür auf den Button 'Passwort generieren lassen'). In "
					 + "beiden Fällen werden Sie gefragt, basierend auf der resultierenden Passwort-Stärke, ob Sie sich "
					 + "Ihrer Wahl sicher sind. Falls nicht, können Sie Ihre Eingabe entsprechend abändern; sind Sie sich"
					 + " jedoch sicher, wird der Account erstellt. Beachten Sie: Im Falle einer Passwort-Generierung ist "
					 + "dies der einzige Zeitpunkt, an dem das Master-Passwort explizit gezeigt wird, es ist also "
					 + "wichtig, sich dieses gut einzuprägen.</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">3. Hauptmenü</font></b><br>"
					 + "<small>Hier gibt es die Möglichkeit, Passwörter abzuspeichern, klicken Sie dazu den 'Verschlüsseln' "
					 + "Button. Bereits existierende Einträge können durch einen Klick auf 'Entschlüsseln' geöffnet oder "
					 + "geändert werden. Der dritte Menü Button 'Passwörter Testen' öffnet eine Ansicht, in der Sie die "
					 + "Stärke Ihrer Passwörter testen können. Benutzen Sie den 'Ausloggen' Button, falls Sie den "
					 + "Nutzer-Account wechseln wollen. Sofern Sie das Programm nach abgeschlossenen Handlungen schließen "
					 + "wollen, klicken Sie den X Button. Das Feld 'Optionen' bietet auch hier die Möglichkeit die Sprache "
					 + "zu ändern, oder das Nutzer-Handbuch zu öffnen. Zusätzlich können Sie hier auch Ihren Account löschen, "
					 + "alle existierenden Einträge löschen, oder Ihr Master-Passwort ändern. Für diese drei Optionenen "
					 + "müssen Sie jeweils Ihr altes Master-Passwort eingeben.</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">4. Passwörter Verschlüsseln</font></b><br>"
					 + "<small>Jeder neue Passwort-Eintrag wird dargestellt durch einen Block mit fünf Textfeldern, siehe"
					 + " rechte Hälfte des Fensters. Neue Blöcke können erzeugt/entfernt werden durch einen Klick auf "
					 + "den '+'/'-' Button. Ein gültiger Eintrag besteht aus einem Namen und einem zugehörigen Passwort ("
					 + "muss wiederholt werden). Optional kann auch ein Nutzername und ein zugehöriger Kommentar eingegeben "
					 + "werden. Beachten Sie, dass die Namen der Passwort-Blöcke eindeutig sein müssen. Nachdem Sie alle "
					 + "Informationen in die dafür vorgesehenen Felder eingegeben haben, drücken Sie auf das Speichern-Symbol "
					 + "in der Mitte der linken Seite des Fensters. Sofern alle Einträge gültig sind, wird Ihre Eingabe "
					 + "abgespeichert. Sie können dann weitere Einträge speichern ('Nochmal') oder zurück zum Hauptmenü "
					 + "('Fertig') wechseln.</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">5. Passwörter Entschlüsseln</font></b><br>"
					 + "<small>Sofern mindestens ein Passwort-Eintrag im aktuellen Account existiert, können die "
					 + "zugehörigen Informationen hier abgerufen werden. Die Namen der Einträge, bzw. die zugehörigen "
					 + "Bilder erscheinen in der rechten Hälfte des Fensters. Sie können einen oder mehrere Einträge "
					 + "auswählen, und haben dann die Wahl zwischen den Optionen 'Ändern', 'Öffnen' oder Löschen (Symbol "
					 + "mit Mülltonne). Im Falle der ersten beiden Optionen öffnet sich ein Pop-Up Fenster mit den "
					 + "angeforderten Informationen.</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">6. Passwörter Testen</font></b><br>"
					 + "<small>Hier können Sie die Stärke von Passwörtern testen, entweder von solchen, die Sie bereits"
					 + " benutzen, oder die noch gewählt werden müssen. Sie können ein beliebiges Passwort in das dafür "
					 + "vorgesehene Feld eingeben und dessen Stärke durch Klick auf 'Passwort-Stärke anzeigen' ausgeben lassen. "
					 + "Alternativ dazu können auch verschiedene Arten von Zufalls-Passwörtern getestet werden."
					 + "</small><br><br></html>";		
		} else {
			this.frameTitle = "PwE - Protect your passwords";
			this.conExitTitle = "Confirm Exit";
			this.conExitQuest = "Do you really want to exit the program?";
			this.conExitMsg = "All unsaved changes will be lost.";
			this.conExitYN = "Yes";
			this.conExitNN = "No";
			this.movDecNoPwsMsg = "For looking up information about, or changing the content of your stored password entries you "
					+ "need to have at least one password saved. Please first select the button '" + menuopenenc + "',"
							+ " where you can store new entries.";
			this.usMTit = "User Manual";
			
			/**
			 * Encrypt View.
			 */
			this.encPwdN = "Enter Password (*)";
			this.encRepPwdN = "Repeat Password (*)";
			this.encNN = "Name of the Password Entry (*)";
			this.encUNN = "Username";
			this.encComN = "Additional Comments";
			this.encAgN = "Again";
			this.encDoN = "Finish";
			this.encLabDo = "Your Input was saved successfully.";
			this.encLabWMP = "You entered a wrong Master-Password, please try again or dismiss your entries.";
			this.encLabMErr = "Some of the names you entered appear more than once. Please make the entry names unique.";
			this.encLabExErr = "One or more of the new entries already exist. Do you want to overwrite?";
			this.encNoEnt = "You did not enter anything. Please fill at least one block to save your changes.";
			this.encDiffP = "In at least one of the blocks the input of the passwords do not coincide. Please check the entries.";
			this.encMissErr = "In at least one of the blocks some input is missing. Please fill all fields, that are marked with (*)";
			this.encOvMiss = "To overwrite the entries, please enter the Master-Password.";
			this.encDisN = "Dismiss";
			this.encChN = "Change";
			this.encOKN = "Overwrite";
			this.encPwdField = "Master-Password (*)";
			
			/**
			 * Decrypt View.
			 */
			this.decPwdBox = "Show input of password field";
			this.decChN = "Change";
			this.decSN = "Open";
			this.decPwdN = "Master-Password (*)";
			this.decOKN = "OK";
			this.decCaN = "Cancel";
			this.decNoMPN = "You did not enter a Master-Password. Please fill all fields marked by (*)";
			this.decWrMPN = "The Master-Password you entered is not correct. Please try again.";
			this.decDelN = "The selected entries were deleted successfully.";
			this.decSuc = "Your changes have been successfully saved.";
					
			this.ocStTit = "Show and Change your Passwords";
			this.ocStDo = "Close";
			this.ocStOv = "Overwrite";
			this.ocStCh = "Change";
			
			this.ocTit = "Confirm Close";
			this.ocHeadT = "Do you really want to close this frame?";
			this.ocConT = "All changes will be lost.";			
			
			this.usMCon = "<html>"
					 + "<b><font color=\"#404040\">TEST-VERSION<br>"
					 + "</font></b><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">PwE - Encrypt your Passwords</font></b><br>"
					 + "<small>Version: " + FC.programVersion + " (beta)<br>"
					 + "<br>"
					 + "Welcome to PwE, a program which will make your life easier. Since "
					 + "nowadays it is common for each company, website, etc. to offer "
					 + "user accounts, it is quite a challenge to keep track of all your "
					 + "different passwords and usernames. This program helps you to "
					 + "organize those, requiring to remember one password only, the so "
					 + "called Master-password. You can store and later open all of your "
					 + "passwords in this program and create a single password, with "
					 + "which all others can be looked at again."
					 + "</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">General Information</font></b><br>"
					 + "<small>This software was developed by Timo Felser and Fabian Schrodi. It is not "
					 + "meant for commercial use and the developers do not give any guarantee on correctness "
					 + "or security. The program uses classical Hashing-algorithms for the password handling; "
					 + "although this encryption is very hard to break, it is not impossible.<br>"
					 + "The program creates a file PwEsaveIO.json, which is not to be changed or moved by the user. "
					 + "Since the whole program relies on the usage of a Master-Password, we strongly recommend to not "
					 + "forget this password, since the stored information can NOT be recovered, not even by the developers."
					 + "</small><br>"
					 + "<br>"					 
					 + "<b><font color=\"#404040\">1. Login View</font></b><br>"
					 + "<small>After the program is started the user is in the login menu, where "
					 + "you can create a new user account by clicking on the button 'New User'. "
					 + "If you already have an account you can type your username and password "
					 + "in the corresponding fields and click on 'Login'. The 'Options' button "
					 + "in the lower left corner enables you to access this user manual, or change "
					 + "the language (german or english).</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">2. Create User Account</font></b><br>"
					 + "<small>In this panel you can create your own account. In the field below "
					 + "'Username' you can choose the name of your account. Please note, that double-occurrences"
					 + " are not allowed. For the Master-Password you have two options: Either choose your "
					 + "own password (default) or let the program generate a random password for you (click on "
					 + "button 'Generate your password'). In both cases the strength of your Master-Password will "
					 + "be shown, and you are asked whether you want to keep that choice. If not, you can modify "
					 + "your input accordingly; if so the account is created. Please note, that in the case"
					 + " of a generated Master-Password, it is shown only once at this specific step, so make sure "
					 + "to remember it.</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">3. Main Menu</font></b><br>"
					 + "<small>Here you have the possibility of storing passwords by means of the 'Encrypt' button. "
					 + "Any existing entries can be opened by clicking on 'Decrypt'. The third menu button, 'Test Passwords' "
					 + "brings you to a panel, where you can estimate the strength of given passwords. Use the 'Logout'"
					 + " button if you want to switch the user account. If you want to close the program after you finished "
					 + "your tasks, you can also use the X button. Clicking on 'Options' gives you again the possibility "
					 + "of changing the language or opening the user manual. In addition you can also delete your account, "
					 + "delete all existing entries in your account, or change your Master-Password. For these options "
					 + "you have to enter your Master-Password.</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">4. Encrypt Panel</font></b><br>"
					 + "<small>Each new password entry is represented by a block of five text fields in the right half "
					 + "of the panel. New blocks can be added/removed by the '+'/'-' button. A valid entry consists "
					 + "of a name and the corresponding password (which must be repeated). Optionally you can specify "
					 + "a username and additional comments. Please note, that the names of password blocks must be "
					 + "unique. After typing the information into the corresponding fields, press the save-symbol in "
					 + "the center of the left half of the panel. If all entries are valid, your results are stored and"
					 + " you can choose to enter additional blocks by 'Again', or go back to the main menu by 'Finish'.</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">5. Decrypt Panel</font></b><br>"
					 + "<small>If there are any existing password entries in your account, you can open them here. "
					 + "The names of your passwords or the corresponding pictures appear in the right side of the "
					 + "panel. You can select one or several of these buttons and choose between the options 'Open', "
					 + "'Change' and delete (garbage bin). For the first two options, there is a pop-up opening with "
					 + "the information you requested.</small><br>"
					 + "<br>"
					 + "<b><font color=\"#404040\">6. Test Passwords Panel</font></b><br>"
					 + "<small>Here you can play around with the passwords you already possess or are about to choose. "
					 + "You can enter any password in the corresponding field and get an estimate about its strength by "
					 + "clicking on the 'Show Password Strength' button. Alternatively you can test different kinds of "
					 + "random passwords in the same way.</small><br><br></html>";
		}
	}
}
