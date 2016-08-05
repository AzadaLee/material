package pattern.observer.demo;

public class AnimalObserverCat implements AnimalObserver {
	
	private String name;
	public AnimalObserverCat(AnimalSubject animalSubject){
		this.name = "cat";
		animalSubject.addAnimal(this);
	}
	@Override
	public void display() {
		System.out.println("I am "+this.name);
	}
}
