package covid_Elections;

public class Candidate extends Citizen {
	private Party party;

	public Candidate(String name, int id, int birthYear, BallotBox ballotBox, boolean isIsolate, Party party) {
		super(name, id, birthYear, ballotBox, isIsolate);
		this.party = party;
	}

	public Candidate() {

	}

	public Party getParty() {
		return party;
	}

	public void setBirthYear(int birthYear) throws Exception {
		super.setBirthYear(birthYear);
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public void setId(int id) throws Exception {
		super.setId(id);
	}

	public boolean equals(Candidate other) {
		if (this.equals(other))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + " , a candidate of '" + this.party.getName() + "' party";
	}
}