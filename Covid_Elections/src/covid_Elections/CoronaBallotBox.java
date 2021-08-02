package covid_Elections;

public class CoronaBallotBox extends BallotBox {

	public CoronaBallotBox() {
		}
	
	public CoronaBallotBox(String adress) {
		super(adress);
	}
	
	public String getThisClass() {
		return "CoronaBallotBox";
	}
	
	public String toString() {
		return "'Corona' "+super.toString();
	}
}
