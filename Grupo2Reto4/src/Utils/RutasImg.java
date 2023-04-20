package Utils;

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
}
