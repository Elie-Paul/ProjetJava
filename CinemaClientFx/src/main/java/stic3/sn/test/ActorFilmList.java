package stic3.sn.test;

import cours.java.stic3.model.Actor;
import cours.java.stic3.model.Film;


public class ActorFilmList {
	
	private Actor actorName;
	private Film filmName;

	public ActorFilmList() {
		super();
	}
	public ActorFilmList(Actor actorName, Film filmName) {
		super();
		this.actorName = actorName;
		this.filmName = filmName;
	}
	public Actor getActorName() {
		return actorName;
	}
	public void setActorName(Actor actorName) {
		this.actorName = actorName;
	}
	public Film getFilmName() {
		return filmName;
	}
	public void setFilmName(Film filmName) {
		this.filmName = filmName;
	}
	
}
