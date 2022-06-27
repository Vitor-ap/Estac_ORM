package boundary;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Estilos {

	  Background bGround;
	
	// preferencias de Estilos	
	
		private String estiloBotao1 = "-fx-font-size: 25; ";
		private String estiloBotao2 = "-fx-font-size: 15;";
		private String estiloTitulo = "-fx-text-fill: white; -fx-font-size:70;  -fx-font-weight: bold; ";
		private String estiloSub = 	  "-fx-font-size:20;  -fx-font-weight: bold; ";
		private Font fontSubs = Font.font("Arial", FontWeight.EXTRA_BOLD, 40);
		private Font fontTxt = Font.font("Arial", FontWeight.EXTRA_BOLD, 30);
		

		public String getEstiloBotao1() {
			return estiloBotao1;
		}
		
		public String GetEstitloTitulo() {
			return estiloTitulo;
		}
		
		public String GetEstiloSub() {
			return estiloSub;
		}
		
		public Font GetFontSubs() {
			return fontSubs;
		}

		public Font getFontTxt() {
			return fontTxt;
		}
		
		public String getEstiloBotao2() {
			return estiloBotao2;
		}
		
		public Background setarPlanoDeFundo(String a) {
			Image fundo = new Image(getClass().getResourceAsStream(a));
			BackgroundImage bImg = new BackgroundImage(fundo,
					BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.CENTER,
					BackgroundSize.DEFAULT);
			return this.bGround = new Background(bImg);
			
		}
		
}
