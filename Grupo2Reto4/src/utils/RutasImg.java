package utils;

public class RutasImg {

	public String randomPNGFrontal() {
		int rnum = (int) ((Math.random() * 648) + 1);
		String nPkDx = "";
		nPkDx = String.valueOf(rnum);
		return "img/grande/" + nPkDx + ".png";
	}

	public String rutaImgProfOak() {
		return "img/grande/oak.jpg";
	}
	
	public String rutaPC() {
		return "img/pc.jpg";
	}
	
	public String PNGfrontalPKMN(int num) {
		String nPkDx = String.valueOf(num);
		return "img/grande/" + nPkDx + ".png";
	}
	
	public String PNGpequenyo(int num) {
		String nPkDx = String.valueOf(num);
		return "img/mini/" + nPkDx + ".png";
	}
	
	public String jpgPoekdex() {
		return "img/pokedex.jpg";
	}
}
