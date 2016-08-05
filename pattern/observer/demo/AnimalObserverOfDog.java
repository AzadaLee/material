package pattern.observer.demo;

public class AnimalObserverOfDog implements AnimalObserver{

	private String name;
	public AnimalObserverOfDog(AnimalSubject animalSubject){
		this.name = "dog";
		animalSubject.addAnimal(this);
	}
	@Override
	public void display() {
		System.out.println("I am "+this.name);
	}

}
