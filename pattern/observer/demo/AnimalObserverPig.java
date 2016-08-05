package pattern.observer.demo;

public class AnimalObserverPig implements AnimalObserver {
	private String name;
	public AnimalObserverPig(AnimalSubject animalSubject){
		this.name = "pig";
		animalSubject.addAnimal(this);
	}
	@Override
	public void display() {
		System.out.println("I am "+this.name);
	}

}
