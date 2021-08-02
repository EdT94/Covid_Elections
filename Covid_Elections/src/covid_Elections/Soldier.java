package covid_Elections;

public class Soldier extends Citizen implements CarryWeapon {
	public Soldier(String name, int id, int birthYear, BallotBox ballotBox, boolean isIsolate) {
		super(name, id, birthYear, ballotBox, isIsolate);
	}

	public Soldier() {
	}
	
	public void carryWeapon() {
		System.out.println("I'm carrying  a weapon");
	}
}
