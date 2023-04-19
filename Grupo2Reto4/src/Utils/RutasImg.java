package Utils;

public class RutasImg {
	
	public String randomGifFrontal() {
		
	int rnum = (int) ((Math.random() * 648)+1);
	String nPkDx="";
	if (rnum < 10)
		nPkDx="00"+rnum;
	else if (rnum >= 10 && rnum < 100)
		nPkDx="0"+rnum;
	else
		nPkDx=String.valueOf(rnum);
	
	
	return "img/gifs/"+nPkDx+".gif";
	}
}
