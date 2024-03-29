package util;

import java.awt.Toolkit;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * collection of constants of all types, contains:
 * 	- file paths for logos
 *  - file paths for icons
 *  - json-identifiers
 *  - backgrounds and borders
 *  - anchors for each view
 *  - global size attributs
 */

public class FC {

	public final static String mainFileName = "/PwEsaveIO.json";
	public final static String mainFileBackupN = "/PwEsaveIO_BACKUP.json";
	public final static String menuIPath = "/icons/icmenu.png";
	public final static String settingsIPath = "/icons/icsettings.png";
	public final static String closeIPath = "/icons/icclose.png";
	public final static String closeMsgIPath = "/icons/icclose.png";
	public final static String backIPath = "/icons/icback.png";
	public final static String colInfoIPath = "/icons/infoIcon.png";
	public final static String manualIPath = "/icons/manualIcon.png";
	public final static String newLockIPath = "/icons/lockIcon.png";
	public final static String newErrorIPath = "/icons/errorIcon.png";
	public final static String newDoneIPath = "/icons/doneIcon.png";
	public final static String newPlusIPath = "/icons/newPlusIcon.png";
	public final static String newMinusIPath = "/icons/newMinusIcon.png";
	public final static String newSaveIPath = "/icons/newSaveIcon.png";
	public final static String newQuestIPath = "/icons/newQuestIcon.png";
	public final static String newDelIPath = "/icons/newDeleteIcon.png";

	public final static String aandwLP = "/logos/aandw.png";
	public final static String absolutvodkaLP = "/logos/absolutvodka.png";
	public final static String acerLP = "/logos/acer.png";
	public final static String aerlingusLP = "/logos/aerlingus.png";
	public final static String aerolineasLP = "/logos/aerolineas.png";
	public final static String aetnaLP = "/logos/aetna.png";
	public final static String ahlstromLP = "/logos/ahlstrom.png";
	public final static String airasiaLP = "/logos/airasia.png";
	public final static String aircanadaLP = "/logos/aircanada.png";
	public final static String airfranceLP = "/logos/airfrance.png";
	public final static String airindiaLP = "/logos/airindia.png";
	public final static String airmaltaLP = "/logos/airmalta.png";
	public final static String alfrescoLP = "/logos/alfresco.png";
	public final static String alitaliaLP = "/logos/alitaliaairline.png";
	public final static String amazonLP = "/logos/amazon.png";
	public final static String amazonWebLP = "/logos/amazon_web.png";
	public final static String americanExpressLP = "/logos/american_express.png";
	public final static String ampLP = "/logos/amp.png";
	public final static String androidLP = "/logos/android.png";
	public final static String aolLP = "/logos/aol.png";
	public final static String appleLP = "/logos/apple.png";
	public final static String appleIpadLP = "/logos/apple_ipad.png";
	public final static String arlaLP = "/logos/arla.png";
	public final static String arnottsLP = "/logos/arnotts.png";
	public final static String asiaPayLP = "/logos/asia_pay.png";
	public final static String aspNetLP = "/logos/asp_net.png";
	public final static String asusLP = "/logos/asus.png";
	public final static String athenosLP = "/logos/athenos.png";
	public final static String attLP = "/logos/att.png";
	public final static String austrianairlineLP = "/logos/austrianairline.png";
	public final static String authorizeLP = "/logos/authorize.png";
	public final static String autodeskLP = "/logos/autodesk.png";
	public final static String bancodobrasilLP = "/logos/bancodobrasil.png";
	public final static String bankLP = "/logos/bank.png";
	public final static String bankofamerikaLP = "/logos/bankofamerika.png";
	public final static String bankofchinaLP = "/logos/bankofchina.png";
	public final static String bankTransferLP = "/logos/bank_transfer.png";
	public final static String bauschlombLP = "/logos/bauschlomb.png";
	public final static String bbbLP = "/logos/bbb.png";
	public final static String bbvaLP = "/logos/bbva.png";
	public final static String bigcommerceLP = "/logos/bigcommerce.png";
	public final static String blizzardLP = "/logos/blizzard.png";
	public final static String bmoLP = "/logos/bmo.png";
	public final static String boacorporationLP = "/logos/boacorporation.png";
	public final static String boschLP = "/logos/bosch.png";
	public final static String bpceLP = "/logos/bpce.png";
	public final static String brazzersLP = "/logos/brazzers.png";
	public final static String brfLP = "/logos/brf.png";
	public final static String britishairwaysLP = "/logos/britishairways.png";
	public final static String bwinLP = "/logos/bwin.png";
	public final static String cablewirelessLP = "/logos/cablewireless.png";
	public final static String camelLP = "/logos/camel.png";
	public final static String carecreditLP = "/logos/carecredit.png";
	public final static String cathwaypacificLP = "/logos/cathwaypacific.png";
	public final static String cbafricaLP = "/logos/cbafrica.png";
	public final static String cellcLP = "/logos/cellc.png";
	public final static String chinaunicomLP = "/logos/chinaunicom.png";
	public final static String christianaidLP = "/logos/christianaid.png";
	public final static String chryslerLP = "/logos/chrysler.png";
	public final static String cirrusLP = "/logos/cirrus.png";
	public final static String citibankLP = "/logos/citibank.png";
	public final static String clickAndBuyLP = "/logos/click_and_buy.png";
	public final static String clickBankLP = "/logos/click_bank.png";
	public final static String clicksorLP = "/logos/clicksor.png";
	public final static String codeIgniterLP = "/logos/code_igniter.png";
	public final static String comcastLP = "/logos/comcast.png";
	public final static String commerzbankLP = "/logos/commerzbank.png";
	public final static String computerLP = "/logos/computer.png";
	public final static String copalibertadoresLP = "/logos/copalibertadores.png";
	public final static String corelLP = "/logos/corel.png";
	public final static String cyprusLP = "/logos/cyprus.png";
	public final static String czechairlinesLP = "/logos/czechairlines.png";
	public final static String dairyqueenLP = "/logos/dairyqueen.png";
	public final static String dallmayerLP = "/logos/dallmayer.png";
	public final static String danonegroupLP = "/logos/danonegroup.png";
	public final static String deltaLP = "/logos/delta.png";
	public final static String deltaairlineLP = "/logos/deltaairline.png";
	public final static String diggLP = "/logos/digg.png";
	public final static String dineroMailLP = "/logos/dinero_mail.png";
	public final static String dinersClubLP = "/logos/diners_club.png";
	public final static String directDebitLP = "/logos/direct_debit.png";
	public final static String discoverNetworkLP = "/logos/discover_network.png";
	public final static String dolbyLP = "/logos/dolby.png";
	public final static String doleLP = "/logos/dole.png";
	public final static String doosanLP = "/logos/doosan.png";
	public final static String dragonairLP = "/logos/dragonair.png";
	public final static String dropboxLP = "/logos/dropbox.png";
	public final static String drupalLP = "/logos/drupal.png";
	public final static String dubaiairportsLP = "/logos/dubaiairports.png";
	public final static String ducatiLP = "/logos/ducati.png";
	public final static String dunkindonutsLP = "/logos/dunkindonuts.png";
	public final static String dysonLP = "/logos/dyson.png";
	public final static String eaSportsLP = "/logos/ea_sports.png";
	public final static String ebayLP = "/logos/ebay.png";
	public final static String ecoLP = "/logos/eco.png";
	public final static String ecoPayzLP = "/logos/eco_payz.png";
	public final static String emailLP = "/logos/email.png";
	public final static String epsLP = "/logos/eps.png";
	public final static String etisalatLP = "/logos/etisalat.png";
	public final static String eurasianBankLP = "/logos/eurasian_bank.png";
	public final static String ewayLP = "/logos/eway.png";
	public final static String expediaLP = "/logos/expedia.png";
	public final static String facebookLP = "/logos/facebook.png";
	public final static String fedexcorporationLP = "/logos/fedexcorporation.png";
	public final static String fedexexpressLP = "/logos/fedexexpress.png";
	public final static String fiatLP = "/logos/fiatspa.png";
	public final static String finnairLP = "/logos/finnair.png";
	public final static String fiorentinaLP = "/logos/fiorentina.png";
	public final static String flickrLP = "/logos/flickr.png";
	public final static String flyemiratesLP = "/logos/flyemirates.png";
	public final static String francetelecomLP = "/logos/francetelecom.png";
	public final static String fulhamLP = "/logos/fulham.png";
	public final static String garudaindonesiaLP = "/logos/garudaindonesiaairline.png";
	public final static String geLP = "/logos/ge.png";
	public final static String generaliLP = "/logos/generali.png";
	public final static String gitLP = "/logos/git.png";
	public final static String gitHubLP = "/logos/github.png";
	public final static String gmailLP = "/logos/gmail.png";
	public final static String gmxLP = "/logos/gmx.png";
	public final static String googleLP = "/logos/google.png";
	public final static String googleChromeLP = "/logos/google_chrome.png";
	public final static String googleCloudLP = "/logos/google_cloud.png";
	public final static String googleoffersLP = "/logos/googleoffers.png";
	public final static String googleTalkLP = "/logos/google_talk.png";
	public final static String googleWalletLP = "/logos/google_wallet.png";
	public final static String harleydavidsonLP = "/logos/harleydavidson.png";
	public final static String hawaiianairlineLP = "/logos/hawaiianairline.png";
	public final static String heinekenLP = "/logos/heineken.png";
	public final static String heinzLP = "/logos/heinz.png";
	public final static String heroLP = "/logos/hero.png";
	public final static String hersheysLP = "/logos/hersheys.png";
	public final static String hiltonhotelLP = "/logos/hiltonhotel.png";
	public final static String hiltonworldwideLP = "/logos/hiltonworldwide.png";
	public final static String hpLP = "/logos/hp.png";
	public final static String hsbcLP = "/logos/hsbc.png";
	public final static String huaweiLP = "/logos/huawei.png";
	public final static String huluLP = "/logos/hulu.png";
	public final static String hybrisLP = "/logos/hybris.png";
	public final static String icaLP = "/logos/ica.png";
	public final static String icqLP = "/logos/icq.png";
	public final static String inggroupLP = "/logos/inggroup.png";
	public final static String intelsatLP = "/logos/intelsat.png";
	public final static String iosLP = "/logos/ios.png";
	public final static String ircLP = "/logos/irc.png";
	public final static String istockLP = "/logos/istock.png";
	public final static String ivecoLP = "/logos/iveco.png";
	public final static String jagranLP = "/logos/jagran.png";
	public final static String jcbLP = "/logos/jcb.png";
	public final static String jetairwaysLP = "/logos/jetairways.png";
	public final static String johnwestLP = "/logos/johnwest.png";
	public final static String jomaLP = "/logos/joma.png";
	public final static String joomlaLP = "/logos/joomla.png";
	public final static String jQueryLP = "/logos/jQuery.png";
	public final static String junghansLP = "/logos/junghans.png";
	public final static String kaustLP = "/logos/kaust.png";
	public final static String kellogsLP = "/logos/kellogs.png";
	public final static String kfcLP = "/logos/kfc.png";
	public final static String kingLP = "/logos/king.png";
	public final static String koreanairLP = "/logos/koreanair.png";
	public final static String kotexLP = "/logos/kotex.png";
	public final static String kraftfoodsLP = "/logos/kraftfoods.png";
	public final static String ladaLP = "/logos/lada.png";
	public final static String lanairlineLP = "/logos/lanairline.png";
	public final static String laptopLP = "/logos/laptop.png";
	public final static String laudaairLP = "/logos/laudaair.png";
	public final static String lenovoLP = "/logos/lenovo.png";
	public final static String liningLP = "/logos/lining.png";
	public final static String linuxLP = "/logos/linux.png";
	public final static String longjohnsilversLP = "/logos/longjohnsilvers.png";
	public final static String lottoLP = "/logos/lotto.png";
	public final static String lotusLP = "/logos/lotus.png";
	public final static String maestroLP = "/logos/maestro.png";
	public final static String mandarinLP = "/logos/mandarin.png";
	public final static String mapquestLP = "/logos/mapquest.png";
	public final static String mastercardLP = "/logos/mastercard.png";
	public final static String matrixservicecompanyLP = "/logos/matrixservicecompany.png";
	public final static String mbankLP = "/logos/mbank.png";
	public final static String mcAfeeSecureLP = "/logos/mc_afee_secure.png";
	public final static String mcdonaldsLP = "/logos/mcdonalds.png";
	public final static String mcKinseyLP = "/logos/mc_kinsey.png";
	public final static String mexicanaairlineLP = "/logos/mexicanaairline.png";
	public final static String mfsLP = "/logos/mfs.png";
	public final static String microsoftLP = "/logos/microsoft.png";
	public final static String microsoftOfficeLP = "/logos/microsoft_office.png";
	public final static String mobilePhoneLP = "/logos/mobile_phone.png";
	public final static String mondexLP = "/logos/mondex.png";
	public final static String moneyBookersLP = "/logos/money_bookers.png";
	public final static String moneyGramLP = "/logos/money_gram.png";
	public final static String monsantoLP = "/logos/monsanto.png";
	public final static String moodleLP = "/logos/moodle.png";
	public final static String morrisonsLP = "/logos/morrisons.png";
	public final static String motorolaLP = "/logos/motorola.png";
	public final static String mySqlLP = "/logos/my_sql.png";
	public final static String nabLP = "/logos/nab.png";
	public final static String napoliLP = "/logos/napoli.png";
	public final static String naverLP = "/logos/naver.png";
	public final static String nbpLP = "/logos/nbp.png";
	public final static String nescafeLP = "/logos/nescafe.png";
	public final static String netflixLP = "/logos/netflix.png";
	public final static String nflLP = "/logos/nfl.png";
	public final static String nintendoLP = "/logos/nintendo.png";
	public final static String nintendo3DsLP = "/logos/nintendo_3ds.png";
	public final static String nintendoEShopLP = "/logos/nintendo_eshop.png";
	public final static String nodeLP = "/logos/node.png";
	public final static String nokiaLP = "/logos/nokia.png";
	public final static String nordeaLP = "/logos/nordea.png";
	public final static String nvidiaLP = "/logos/nvidia.png";
	public final static String odooLP = "/logos/odoo.png";
	public final static String officedepotLP = "/logos/officedepot.png";
	public final static String omegaLP = "/logos/omegasa.png";
	public final static String opodoLP = "/logos/opodo.png";
	public final static String oreoLP = "/logos/oreo.png";
	public final static String panasonicLP = "/logos/panasonic.png";
	public final static String parmalatLP = "/logos/parmalat.png";
	public final static String payoneerLP = "/logos/payoneer.png";
	public final static String paypalLP = "/logos/paypal.png";
	public final static String paypointLP = "/logos/paypoint.png";
	public final static String pepsicoLP = "/logos/pepsico.png";
	public final static String peugeotLP = "/logos/peugeot.png";
	public final static String philipsLP = "/logos/philips.png";
	public final static String pizzahutLP = "/logos/pizzahut.png";
	public final static String postepayLP = "/logos/postepay.png";
	public final static String prestaShopLP = "/logos/presta_shop.png";
	public final static String prezelewyLP = "/logos/prezelewy.png";
	public final static String pringlesLP = "/logos/pringles.png";
	public final static String privatBankLP = "/logos/privat_bank.png";
	public final static String pulseLP = "/logos/pulse.png";
	public final static String pythonLP = "/logos/python.png";
	public final static String qantasairlineLP = "/logos/qantasairline.png";
	public final static String qatarairwaysLP = "/logos/qatarairways.png";
	public final static String quakerLP = "/logos/quaker.png";
	public final static String quornLP = "/logos/quorn.png";
	public final static String RBSWorldPayLP = "/logos/RBS_worldpay.png";
	public final static String redhatLP = "/logos/redhat.png";
	public final static String rediffLP = "/logos/rediff.png";
	public final static String rollsroyceLP = "/logos/rollsroyce.png";
	public final static String royalroaduniversityLP = "/logos/royalroadsuniversity.png";
	public final static String rtveLP = "/logos/rtve.png";
	public final static String sageLP = "/logos/sage.png";
	public final static String sagePayLP = "/logos/sage_pay.png";
	public final static String samsungLP = "/logos/samsung.png";
	public final static String scandinavianairlinesLP = "/logos/scandinavianairlines.png";
	public final static String schibstedLP = "/logos/schibsted.png";
	public final static String skrillLP = "/logos/skrill.png";
	public final static String skyLP = "/logos/sky.png";
	public final static String skypeLP = "/logos/skype.png";
	public final static String skySportsLP = "/logos/sky_sports.png";
	public final static String smartphoneLP = "/logos/smartphone.png";
	public final static String sonyLP = "/logos/sony.png";
	public final static String southafricaexpressLP = "/logos/southafricaexpress.png";
	public final static String spflLP = "/logos/spfl.png";
	public final static String standardCharteredLP = "/logos/standard_chartered.png";
	public final static String standardlifeLP = "/logos/standardlife.png";
	public final static String starallianceairlineLP = "/logos/starallianceairline.png";
	public final static String stockholmLP = "/logos/stockholm.png";
	public final static String subwayLP = "/logos/subway.png";
	public final static String sumolcompalLP = "/logos/sumolcompal.png";
	public final static String switchLP = "/logos/switch.png";
	public final static String sydbankLP = "/logos/sydbank.png";
	public final static String symantecLP = "/logos/symantec.png";
	public final static String tabletLP = "/logos/tablet.png";
	public final static String tacaairlineLP = "/logos/tacaairline.png";
	public final static String tacobellLP = "/logos/tacobell.png";
	public final static String tataLP = "/logos/tata.png";
	public final static String tatadocomoLP = "/logos/tatadocomo.png";
	public final static String tdBankLP = "/logos/td_bank.png";
	public final static String telecheckLP = "/logos/telecheck.png";
	public final static String thaiairlineLP = "/logos/thaiairline.png";
	public final static String ticketmasterLP = "/logos/ticketmaster.png";
	public final static String tigerairLP = "/logos/tigerair.png";
	public final static String timhortonsLP = "/logos/timhortons.png";
	public final static String tinderLP = "/logos/tinder.png";
	public final static String toditoCardLP = "/logos/todito_card.png";
	public final static String toditoCashLP = "/logos/todito_cash.png";
	public final static String torontodominionLP = "/logos/torontodominion.png";
	public final static String toshibaLP = "/logos/toshiba.png";
	public final static String toyotaLP = "/logos/toyota.png";
	public final static String toysrusLP = "/logos/toysrus.png";
	public final static String triumphLP = "/logos/triumph.png";
	public final static String truviaLP = "/logos/truvia.png";
	public final static String tumblrLP = "/logos/tumblr.png";
	public final static String tvLP = "/logos/tv.png";
	public final static String twitterLP = "/logos/twitter.png";
	public final static String ubsLP = "/logos/ubsag.png";
	public final static String uhlsportLP = "/logos/uhlsport.png";
	public final static String unileverLP = "/logos/unilever.png";
	public final static String univisionLP = "/logos/univision.png";
	public final static String upsLP = "/logos/ups.png";
	public final static String verisignLP = "/logos/verisign.png";
	public final static String vietnamairlinesLP = "/logos/vietnamairlines.png";
	public final static String visaLP = "/logos/visa.png";
	public final static String visaElectronLP = "/logos/visa_electron.png";
	public final static String vodafoneLP = "/logos/vodafone.png";
	public final static String walmartLP = "/logos/walmart.png";
	public final static String webLP = "/logos/web.png";
	public final static String webmoneyLP = "/logos/webmoney.png";
	public final static String weightWatchersLP = "/logos/weight_watchers.png";
	public final static String wepayLP = "/logos/wepay.png";
	public final static String whatsappLP = "/logos/whatsapp.png";
	public final static String wiiULP = "/logos/wii_u.png";
	public final static String wirecardLP = "/logos/wirecard.png";
	public final static String wlanLP = "/logos/wlan.png";
	public final static String wnbaLP = "/logos/wnba.png";
	public final static String woolworthsLP = "/logos/woolworths.png";
	public final static String wordpressLP = "/logos/wordpress.png";
	public final static String wolrdpayLP = "/logos/worldpay.png";
	public final static String xboxLP = "/logos/xbox.png";
	public final static String xeroxLP = "/logos/xerox.png";
	public final static String yahooLP = "/logos/yahoo.png";
	public final static String yammerLP = "/logos/yammer.png";
	public final static String yeahLP = "/logos/yeah.png";
	public final static String yelpLP = "/logos/yelp.png";
	public final static String yesBankLP = "/logos/yes_bank.png";
	public final static String yoigoLP = "/logos/yoigo.png";
	public final static String youtubeLP = "/logos/youtube.png";
	public final static String yumLP = "/logos/yum.png";
	public final static String zainLP = "/logos/zain.png";
	public final static String zdnetLP = "/logos/zdnet.png";
	public final static String zebraLP = "/logos/zebra.png";
	public final static String zespriLP = "/logos/zespri.png";
	
	/**
	 * Password attributes
	 */
	public final static String user = "user";
	public final static String name = "name";
	public final static String masterPwd = "masterPwd";
	public final static String masterSalt = "masterSalt";
	public final static String passwords = "passwords";
	public final static String associate = "associate";
	public final static String encryptUsername = "encryptUsername";
	public final static String username = "username";
	public final static String pwd = "pwd";
	public final static String lastSeed = "lastSeed";
	public final static String comment = "comment";
	public final static String programVersion = "1.1";
	public static final String head = "head";
	public static final String created = "created";
	public static final String version = "version";
	public static final String numberOfPwds = "numberOfPwds";

	
	/**
	 * Menu closed view.
	 */
	public final static double inWi = 0.45 * Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public final static double inHe = 0.55 * Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public final static double maxFrWi = 0.9 * Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public final static double maxFrHe = 0.9 * Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public final static double minFrWi = 0.2 * Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public final static double minFrHe = 0.22 * Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public final static Anch mcvBut = new Anch(0.02, 0.93, 0.02, 0.915);
	public final static Anch empty = new Anch(0.0, 0.0, 0.0, 0.0);
	
	/**
	 * Menu open view.
	 */
	public final static Anch movCl = new Anch(0.2, 0.75, 0.01, 0.93);
	public final static Anch movEnc = new Anch(0.0, 0.75, 0.2, 0.7);
	public final static Anch movDec = new Anch(0.0, 0.75, 0.35, 0.55);
	public final static Anch movNew = new Anch(0.0, 0.75, 0.65, 0.25);
	public final static Anch movSet = new Anch(0.045, 0.75, 0.905, 0.01);
	public final static Anch movSep = new Anch(0.25, 0.74535, 0.0, 0.0);
	public final static Anch movErrPAn = new Anch(0.405, 0.185, 0.3, 0.33);
	public final static Anch movErrClAn = new Anch(0.0, 0.876, 0.02, 0.82);
	public final static Anch movErrIAn = new Anch(0.43, 0.44, 0.1, 0.73);
	public final static Anch movErrLaAn = new Anch(0.02, 0.0, 0.2, 0.0);
	
	/**
	 * Encrypt view.
	 */
	public final static Anch back = new Anch(0.02, 0.93, 0.02, 0.915);
	public final static Anch pAn = new Anch(0.635, 0.305, 0.88, 0.0);
	public final static Anch mAn = new Anch(0.805, 0.135, 0.88, 0.0);
	public final static Anch encBoxAn = new Anch(0.305, 0.505, 0.1, 0.81);
	public final static double pmWi = 0.4;
	public final static double pmHe = 0.2;
	public final static double encBlWi = 0.5;
	public final static double encBlHe = 0.35;
	
	public final static Anch pwdAn = new Anch(0.07, 0.5, 0.41, 0.4);
	public final static Anch pwdTAn = new Anch(0.07, 0.5, 0.41, 0.4);
	public final static Anch repPwdAn = new Anch(0.5, 0.07, 0.41, 0.4);
	public final static Anch repPwdTAn = new Anch(0.5, 0.07, 0.41, 0.4);
	public final static Anch nAn = new Anch(0.07, 0.07, 0.01, 0.8);
	public final static Anch unAn = new Anch(0.07, 0.07, 0.21, 0.6);
	public final static Anch comAn = new Anch(0.07, 0.07, 0.61, 0.2);
	
	public final static Anch saButAn = new Anch(0.22, 0.7, 0.23, 0.63);
	public final static Anch agButAn = new Anch(0.125, 0.755, 0.82, 0.08);
	public final static Anch doButAn = new Anch(0.255, 0.625, 0.82, 0.08);
	public final static Anch encMsgAn = new Anch(0.125, 0.625, 0.4, 0.23);
	
	/**
	 * Decrypt view.
	 */
	public final static Anch decDelAn = new Anch(0.4, 0.5, 0.9, 0.0);
	public final static Anch decChAn = new Anch(0.29, 0.6, 0.2, 0.7);
	public final static Anch decShAn = new Anch(0.1, 0.79, 0.2, 0.7);
	public final static Anch decScAn = new Anch(0.5, 0.0, 0.0, 0.0);
	public final static Anch decPwdAn = new Anch(0.1, 0.6, 0.35, 0.57);
	public final static Anch decMsgAn = new Anch(0.12, 0.63, 0.59, 0.03);
	
	public final static Anch ocScAn = new Anch(0.0, 0.0, 0.02, 0.2);
	public final static Anch ocButPAn = new Anch(0.0, 0.0, 0.83, 0.0);
	public final static Anch ocCaAn = new Anch(0.0, 0.5, 0.05, 0.0);
	public final static Anch ocOKAn = new Anch(0.5, 0.0, 0.05, 0.0);
	public final static Anch ocChAn = new Anch(0.5, 0.0, 0.05, 0.0);
	
	
	
	public final static Language defaultLan = Language.ENGLISH;
	public final static Border errMsgBord = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, 
			CornerRadii.EMPTY, BorderWidths.DEFAULT));
	public final static Border usManBord = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, 
			CornerRadii.EMPTY, BorderWidths.DEFAULT));
	public final static Background errMsgBa = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
	public final static Background checkBoxBa = new Background(new BackgroundFill(Color.LIGHTGRAY, 
			CornerRadii.EMPTY, new Insets(0.0, 0.0, 0.0, -10.0)));
	public final static Border newMPDoBord = new Border(new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, 
			CornerRadii.EMPTY, BorderWidths.DEFAULT));
	public final static Border newMPErrBord = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, 
			CornerRadii.EMPTY, BorderWidths.DEFAULT));
	public final static Background newMPDoBa = new Background(new BackgroundFill(Color.LIGHTGREEN, null, null));
	public final static Background newMPErrBa = new Background(new BackgroundFill(Color.rgb(250, 128, 114), null, null));
	public final static int minPwdLen = 4;
	public final static double setIDiv = 3.5;
	public final static double butDiv = 110.0;
	public final static double errDiv = 40.0;
	public final static double titDiv = 45.0;
	public final static double conDiv = 1500.0;
	public final static double vbspac = 0.34;
	public final static Triple inMT = new Triple(inWi, inHe, butDiv);
	public final static Border encBord = new Border(new BorderStroke(Color.rgb(54, 54, 54), BorderStrokeStyle.SOLID, 
			CornerRadii.EMPTY, BorderWidths.DEFAULT));
	public final static Background empBa = new Background(new BackgroundFill(Color.WHITE, null, null));
	public final static Border decButSelBord = new Border(new BorderStroke(Color.rgb(210,105,30), BorderStrokeStyle.SOLID, 
			CornerRadii.EMPTY, new BorderWidths(6)));
	
	public final static Background grayBa = new Background(new BackgroundFill(Color.LIGHTGRAY, 
			CornerRadii.EMPTY, new Insets(0.0, 0.0, 0.0, 0.0)));
	public final static Border blackBord = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, 
			CornerRadii.EMPTY, new BorderWidths(2)));
	
}
