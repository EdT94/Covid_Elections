package covid_Elections;

import java.util.ArrayList;
import java.util.Date;

public class Party {
	private String name;
	private Date establishment;
	private String faction;
	private ArrayList<Candidate> allCandidates;
	private int numOfVotes;
	private int numOfCandidates;

	Party(String name) {
		this.name = name;
		this.establishment = new Date();
		this.allCandidates = new ArrayList<Candidate>();
		this.numOfVotes = 0;
		this.numOfCandidates = 0;
	}

	Party() {
		this.allCandidates = new ArrayList<Candidate>();
		this.establishment = new Date();
		this.numOfVotes = 0;
		this.numOfCandidates = 0;
	}

	public int getNumOfVotes() {
		return numOfVotes;
	}

	public void plusNumOfVotes() {
		this.numOfVotes++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumOfVoted(int number) {
		this.numOfVotes = number;
	}

	public Date getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Date establishment) {
		this.establishment = establishment;
	}

	public boolean equals(Party other) {
		if (this.getName().equals(other.getName()))
			return true;
		return false;
	}

	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public void setCandidate(Candidate can) {
		this.allCandidates.add(can);
		numOfCandidates++;

	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("'" + name + "'" + " established in :" + establishment
				+ ", and the faction of the party is :" + this.getFaction());
		if (numOfCandidates == 0) {
			str.append("");
			return str.toString();
		} else
			str.append("\nThe candidates are : \n");
		for (Candidate can : allCandidates) {
			str.append(can.toString() + "\n");		
		}

		return str.toString();
	}
}
