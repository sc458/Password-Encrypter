package controller;

import java.net.MalformedURLException;

import javafx.scene.image.ImageView;
import util.FC;
import util.Language;
import util.Logo;

public class LogoDatabase {

	private Logo[] logoList;

	public LogoDatabase(Language lang, boolean firstCall) throws MalformedURLException {
		if(firstCall) {
			this.logoList = new Logo[] {
					new Logo("A and W", FC.aandwLP),
					new Logo("Absolut Vodka", FC.absolutvodkaLP),
					new Logo("Acer", FC.acerLP),
					new Logo("Aer Lingus", FC.aerlingusLP),
					new Logo("Aerolineas Argentinas", FC.aerolineasLP),
					new Logo("Aetna", FC.aetnaLP),
					new Logo("Ahlstrom", FC.ahlstromLP),
					new Logo("Air Asia", FC.airasiaLP),
					new Logo("Air Canada", FC.aircanadaLP),
					new Logo("Air France", FC.airfranceLP),
					new Logo("Air India", FC.airindiaLP),
					new Logo("Air Malta", FC.airmaltaLP),
					new Logo("Alfresco", FC.alfrescoLP),
					new Logo("Alitalia Airline", FC.alitaliaLP),
					new Logo("Amazon", FC.amazonLP),
					new Logo("Amazon Web", FC.amazonWebLP),
					new Logo("American Express", FC.americanExpressLP),
					new Logo("AMP Investment", FC.ampLP),
					new Logo("Android", FC.androidLP),
					new Logo("AOL", FC.aolLP),
					new Logo("Apple", FC.appleLP),
					new Logo("Apple IPad", FC.appleIpadLP),
					new Logo("Arla", FC.arlaLP),
					new Logo("Arnotts", FC.arnottsLP),
					new Logo("Asia Pay", FC.asiaPayLP),
					new Logo("ASP net", FC.aspNetLP),
					new Logo("Asus", FC.asusLP),
					new Logo("Athenos", FC.athenosLP),
					new Logo("AT&T Communications", FC.attLP),
					new Logo("Austrian Airline", FC.austrianairlineLP),
					new Logo("Authorize Net", FC.authorizeLP),
					new Logo("Auto-Desk", FC.autodeskLP),
					new Logo("Banco do Brasil", FC.bancodobrasilLP),
					new Logo("Bank", FC.bankLP),
					new Logo("Bank of America", FC.bankofamerikaLP),
					new Logo("Bank of China", FC.bankofchinaLP),
					new Logo("Bank Transfer", FC.bankTransferLP),
					new Logo("Bausch + Lomb", FC.bauschlombLP),
					new Logo("BBB", FC.bbbLP),
					new Logo("BBVA", FC.bbvaLP),
					new Logo("Big Commerce", FC.bigcommerceLP),
					new Logo("Blizzard", FC.blizzardLP),
					new Logo("Bank of Montreal", FC.bmoLP),
					new Logo("Boa Corporation", FC.boacorporationLP),
					new Logo("Bosch", FC.boschLP),
					new Logo("BPCE Financials", FC.bpceLP),
					new Logo("Brazzers", FC.brazzersLP),
					new Logo("BRF Food", FC.brfLP),
					new Logo("British Airways", FC.britishairwaysLP),
					new Logo("Bet & Win", FC.bwinLP),
					new Logo("Cable Wireless", FC.cablewirelessLP),
					new Logo("Camel", FC.camelLP),
					new Logo("Care Credit", FC.carecreditLP),
					new Logo("Cathway Pacific", FC.cathwaypacificLP),
					new Logo("CBA Bank", FC.cbafricaLP),
					new Logo("Cell C", FC.cellcLP),
					new Logo("China Unicom", FC.chinaunicomLP),
					new Logo("Christian Aid", FC.christianaidLP),
					new Logo("Chrysler", FC.chryslerLP),
					new Logo("Cirrus", FC.cirrusLP),
					new Logo("Citi Bank", FC.citibankLP),
					new Logo("Click and Buy", FC.clickAndBuyLP),
					new Logo("Click Bank", FC.clickBankLP),
					new Logo("Clicksor", FC.clicksorLP),
					new Logo("Code Igniter", FC.codeIgniterLP),
					new Logo("COM Cast", FC.comcastLP),
					new Logo("Commerzbank", FC.commerzbankLP),
					new Logo("Computer", FC.computerLP),
					new Logo("Copa Libertadores", FC.copalibertadoresLP),
					new Logo("Corel", FC.corelLP),
					new Logo("Cyprus", FC.cyprusLP),
					new Logo("Czech Airlines", FC.czechairlinesLP),
					new Logo("Dairy Queen", FC.dairyqueenLP),
					new Logo("Dallmayr", FC.dallmayerLP),
					new Logo("Danone Group", FC.danonegroupLP),
					new Logo("Delta", FC.deltaLP),
					new Logo("Delta Airline", FC.deltaairlineLP),
					new Logo("Digg", FC.diggLP),
					new Logo("Dinero Mail", FC.dineroMailLP),
					new Logo("Diners Club", FC.dinersClubLP),
					new Logo("Direct Debit", FC.directDebitLP),
					new Logo("Discover Network", FC.discoverNetworkLP),
					new Logo("Dolby", FC.dolbyLP),
					new Logo("Dole", FC.doleLP),
					new Logo("Doosan", FC.doosanLP),
					new Logo("Dragon Air", FC.dragonairLP),
					new Logo("Dropbox", FC.dropboxLP),
					new Logo("Drupal", FC.drupalLP),
					new Logo("Dubai Airports", FC.dubaiairportsLP),
					new Logo("Ducati", FC.ducatiLP),
					new Logo("Dunkin Donuts", FC.dunkindonutsLP),
					new Logo("Dyson", FC.dysonLP),
					new Logo("EA Sports", FC.eaSportsLP),
					new Logo("Ebay", FC.ebayLP),
					new Logo("Eco", FC.ecoLP),
					new Logo("Eco Payz", FC.ecoPayzLP),
					new Logo("Email", FC.emailLP),
					new Logo("EPS", FC.epsLP),
					new Logo("Etisalat", FC.etisalatLP),
					new Logo("Eurasian Bank", FC.eurasianBankLP),
					new Logo("EWay", FC.ewayLP),
					new Logo("Expedia", FC.expediaLP),
					new Logo("Facebook", FC.facebookLP),
					new Logo("FedEx Corporation", FC.fedexcorporationLP),
					new Logo("FedEx Express", FC.fedexexpressLP),
					new Logo("SpA Fiat", FC.fiatLP),
					new Logo("Finnair", FC.finnairLP),
					new Logo("ACF Fiorentina", FC.fiorentinaLP),
					new Logo("Flickr", FC.flickrLP),
					new Logo("Fly Emirates", FC.flyemiratesLP),
					new Logo("France Telecom", FC.francetelecomLP),
					new Logo("FC Fulham", FC.fulhamLP),
					new Logo("Garuda Indonesia", FC.garudaindonesiaLP),
					new Logo("General Electric", FC.geLP),
					new Logo("Generali", FC.generaliLP),
					new Logo("Git", FC.gitLP),
					new Logo("Git Hub", FC.gitHubLP),
					new Logo("G-Mail", FC.gmailLP),
					new Logo("GMX", FC.gmxLP),
					new Logo("Google", FC.googleLP),
					new Logo("Google Chrome", FC.googleChromeLP),
					new Logo("Google Cloud", FC.googleCloudLP),
					new Logo("Google Offers", FC.googleoffersLP),
					new Logo("Google Talk", FC.googleTalkLP),
					new Logo("Google Wallet", FC.googleWalletLP),
					new Logo("Harley Davidson", FC.harleydavidsonLP),
					new Logo("Hawaiian Airline", FC.hawaiianairlineLP),
					new Logo("Heineken", FC.heinekenLP),
					new Logo("Heinz", FC.heinzLP),
					new Logo("Hero", FC.heroLP),
					new Logo("Hershey's", FC.hersheysLP),
					new Logo("Hilton Hotel", FC.hiltonhotelLP),
					new Logo("Hilton Worldwide", FC.hiltonworldwideLP),
					new Logo("HP invent", FC.hpLP),
					new Logo("HSBC", FC.hsbcLP),
					new Logo("Huawei", FC.huaweiLP),
					new Logo("Hulu", FC.huluLP),
					new Logo("Hybris", FC.hybrisLP),
					new Logo("ICA Supermarket", FC.icaLP),
					new Logo("ICQ", FC.icqLP),
					new Logo("ING Group", FC.inggroupLP),
					new Logo("Intelsat", FC.intelsatLP),
					new Logo("IOS", FC.iosLP),
					new Logo("International Rescue", FC.ircLP),
					new Logo("iStock", FC.istockLP),
					new Logo("Iveco", FC.ivecoLP),
					new Logo("Jagran", FC.jagranLP),
					new Logo("JCB", FC.jcbLP),
					new Logo("Jet Airways", FC.jetairwaysLP),
					new Logo("John West", FC.johnwestLP),
					new Logo("Joma", FC.jomaLP),
					new Logo("Joomla", FC.joomlaLP),
					new Logo("JQuery", FC.jQueryLP),
					new Logo("Jung Hans", FC.junghansLP),
					new Logo("Kaust", FC.kaustLP),
					new Logo("Kellogg's", FC.kellogsLP),
					new Logo("KFC", FC.kfcLP),
					new Logo("King", FC.kingLP),
					new Logo("Korean Air", FC.koreanairLP),
					new Logo("Kotex", FC.kotexLP),
					new Logo("Kraft Foods", FC.kraftfoodsLP),
					new Logo("Lada", FC.ladaLP),
					new Logo("Lan Airline", FC.lanairlineLP),
					new Logo("Laptop", FC.laptopLP),
					new Logo("Lauda Air", FC.laudaairLP),
					new Logo("Lenovo", FC.lenovoLP),
					new Logo("Li Ning", FC.liningLP),
					new Logo("Linux", FC.linuxLP),
					new Logo("Long John Silvers", FC.longjohnsilversLP),
					new Logo("Lotto", FC.lottoLP),
					new Logo("Lotus", FC.lotusLP),
					new Logo("Maestro", FC.maestroLP),
					new Logo("Mandarin", FC.mandarinLP),
					new Logo("Map Quest", FC.mapquestLP),
					new Logo("Mastercard", FC.mastercardLP),
					new Logo("Matrix Service Company", FC.matrixservicecompanyLP),
					new Logo("M Bank", FC.mbankLP),
					new Logo("Mc Afee Secure", FC.mcAfeeSecureLP),
					new Logo("Mc Donalds", FC.mcdonaldsLP),
					new Logo("Mc Kinsey", FC.mcKinseyLP),
					new Logo("Mexicana Airline", FC.mexicanaairlineLP),
					new Logo("MFS", FC.mfsLP),
					new Logo("Microsoft", FC.microsoftLP),
					new Logo("Microsoft Office", FC.microsoftOfficeLP),
					new Logo("Mobile Phone", FC.mobilePhoneLP),
					new Logo("Mondex", FC.mondexLP),
					new Logo("Money Bookers", FC.moneyBookersLP),
					new Logo("Money Gram", FC.moneyGramLP),
					new Logo("Monsanto", FC.monsantoLP),
					new Logo("Moodle", FC.moodleLP),
					new Logo("Morrisons", FC.morrisonsLP),
					new Logo("Motorola", FC.motorolaLP),
					new Logo("My SQL", FC.mySqlLP),
					new Logo("NAB Bank", FC.nabLP),
					new Logo("Napoli", FC.napoliLP),
					new Logo("Naver", FC.naverLP),
					new Logo("NBP Bank", FC.nbpLP),
					new Logo("Nescafe", FC.nescafeLP),
					new Logo("Netflix", FC.netflixLP),
					new Logo("NFL", FC.nflLP),
					new Logo("Nintendo", FC.nintendoLP),
					new Logo("Nintendo 3 DS", FC.nintendo3DsLP),
					new Logo("Nintendo E-Shop", FC.nintendoEShopLP),
					new Logo("Node", FC.nodeLP),
					new Logo("Nokia", FC.nokiaLP),
					new Logo("Nordea", FC.nordeaLP),
					new Logo("Nvidia", FC.nvidiaLP),
					new Logo("Odoo", FC.odooLP),
					new Logo("Office Depot", FC.officedepotLP),
					new Logo("Omega", FC.omegaLP),
					new Logo("Opodo", FC.opodoLP),
					new Logo("Oreo", FC.oreoLP),
					new Logo("Panasonic", FC.panasonicLP),
					new Logo("Parmalat", FC.parmalatLP),
					new Logo("Payoneer", FC.payoneerLP),
					new Logo("Pay Pal", FC.paypalLP),
					new Logo("Pay Point", FC.paypointLP),
					new Logo("Pepsi Co", FC.pepsicoLP),
					new Logo("Peugeot", FC.peugeotLP),
					new Logo("Philips", FC.philipsLP),
					new Logo("Pizza Hut", FC.pizzahutLP),
					new Logo("Postepay", FC.postepayLP),
					new Logo("Presta Shop", FC.prestaShopLP),
					new Logo("Przelewy", FC.prezelewyLP),
					new Logo("Pringles", FC.pringlesLP),
					new Logo("Privat Bank", FC.privatBankLP),
					new Logo("Pluse", FC.pulseLP),
					new Logo("Python", FC.pythonLP),
					new Logo("Qantas Airline", FC.qantasairlineLP),
					new Logo("Qatar Airways", FC.qatarairwaysLP),
					new Logo("Quaker", FC.quakerLP),
					new Logo("Quorn", FC.quornLP),
					new Logo("RBS World Pay", FC.RBSWorldPayLP),
					new Logo("Red Hat", FC.redhatLP),
					new Logo("Rediff", FC.rediffLP),
					new Logo("Rolls Royce", FC.rollsroyceLP),
					new Logo("Royal Roads University", FC.royalroaduniversityLP),
					new Logo("RTVE Broadcasting", FC.rtveLP),
					new Logo("Sage", FC.sageLP),
					new Logo("Sage Pay", FC.sagePayLP),
					new Logo("Samsung", FC.samsungLP),
					new Logo("Scandinavian Airlines", FC.scandinavianairlinesLP),
					new Logo("Schibsted", FC.schibstedLP),
					new Logo("Skrill", FC.skrillLP),
					new Logo("Sky", FC.skyLP),
					new Logo("Skype", FC.skypeLP),
					new Logo("Sky Sports", FC.skySportsLP),
					new Logo("Smartphone", FC.smartphoneLP),
					new Logo("Sony", FC.sonyLP),
					new Logo("South Africa Express", FC.southafricaexpressLP),
					new Logo("Scottish Football", FC.spflLP),
					new Logo("Standard Chartered", FC.standardCharteredLP),
					new Logo("Standard Life", FC.standardlifeLP),
					new Logo("Star Alliance Airline", FC.starallianceairlineLP),
					new Logo("Stockholm", FC.stockholmLP),
					new Logo("Subway", FC.subwayLP),
					new Logo("Sumol+Compal", FC.sumolcompalLP),
					new Logo("Switch", FC.switchLP),
					new Logo("Sydbank", FC.sydbankLP),
					new Logo("Symantec", FC.symantecLP),
					new Logo("Tablet", FC.tabletLP),
					new Logo("Taca Airline", FC.tacaairlineLP),
					new Logo("Taco Bell", FC.tacobellLP),
					new Logo("Tata", FC.tataLP),
					new Logo("Tata Docomo", FC.tatadocomoLP),
					new Logo("TD Bank", FC.tdBankLP),
					new Logo("Telecheck", FC.telecheckLP),
					new Logo("Thai Airline", FC.thaiairlineLP),
					new Logo("Ticket Master", FC.ticketmasterLP),
					new Logo("Tiger Air", FC.tigerairLP),
					new Logo("Tim Hortons", FC.timhortonsLP),
					new Logo("Tinder", FC.tinderLP),
					new Logo("Todito Card", FC.toditoCardLP),
					new Logo("Todito Cash", FC.toditoCashLP),
					new Logo("Toronto Dominion", FC.torontodominionLP),
					new Logo("Toshiba", FC.toshibaLP),
					new Logo("Toyota", FC.toyotaLP),
					new Logo("Toys R Us", FC.toysrusLP),
					new Logo("Triumph", FC.triumphLP),
					new Logo("Truvia", FC.truviaLP),
					new Logo("Tubmlr", FC.tumblrLP),
					new Logo("TV", FC.tvLP),
					new Logo("Twitter", FC.twitterLP),
					new Logo("UBS Financials", FC.ubsLP),
					new Logo("Uhlsport", FC.uhlsportLP),
					new Logo("Unilever", FC.unileverLP),
					new Logo("Uni Vision", FC.univisionLP),
					new Logo("UPS", FC.upsLP),
					new Logo("Veri Sign", FC.verisignLP),
					new Logo("Vietnam Airlines", FC.vietnamairlinesLP),
					new Logo("Visa", FC.visaLP),
					new Logo("Visa Electron", FC.visaElectronLP),
					new Logo("Vodafone", FC.vodafoneLP),
					new Logo("Walmart", FC.walmartLP),
					new Logo("Web", FC.webLP),
					new Logo("Web Money", FC.webmoneyLP),
					new Logo("Weight Watchers", FC.weightWatchersLP),
					new Logo("WePay", FC.wepayLP),
					new Logo("Whats App", FC.whatsappLP),
					new Logo("Wii U", FC.wiiULP),
					new Logo("Wirecard", FC.wirecardLP),
					new Logo("WLAN", FC.wlanLP),
					new Logo("Woman NBA", FC.wnbaLP),
					new Logo("Woolworths", FC.woolworthsLP),
					new Logo("Word Press", FC.wordpressLP),
					new Logo("World Pay", FC.RBSWorldPayLP),
					new Logo("XBox", FC.xboxLP),
					new Logo("Xerox", FC.xeroxLP),
					new Logo("Yahoo", FC.yahooLP),
					new Logo("Yammer", FC.yammerLP),
					new Logo("Yeah", FC.yeahLP),
					new Logo("Yelp", FC.yelpLP),
					new Logo("Yes Bank", FC.yesBankLP),
					new Logo("Yoi Go", FC.yoigoLP),
					new Logo("YouTube", FC.youtubeLP),
					new Logo("Yum", FC.yumLP),
					new Logo("Zain", FC.zainLP),
					new Logo("ZD Net", FC.zdnetLP),
					new Logo("Zebra", FC.zebraLP),
					new Logo("Zespri", FC.zespriLP) 
			};
		}
	}


	public String[] getPlatformList() {
		String[] toReturn = new String[this.logoList.length];

		for(int j = 0; j < logoList.length; j++) {
			toReturn[j] = logoList[j].getName();
		}

		return toReturn;
	}
	
	public ImageView checkForExistence(String name) {
		for(int j = 0; j < this.logoList.length; j++) {
			if(logoList[j].getName().equals(name)) {
				return logoList[j].getImg();
			}
		}
		return null;
	}
	


}
