package pattern.observer.demo;

public interface AnimalSubject {
	public void addAnimal(AnimalObserver observer);
	public void removeAnimal(AnimalObserver observer);
	public void noticeAllObservers();
}
