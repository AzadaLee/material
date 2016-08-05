package pattern.observer.demo;

import java.util.AbstractQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class AnimalSubjectImpl implements AnimalSubject {

	private AbstractQueue<AnimalObserver> observers;
	public AnimalSubjectImpl(){
		this.observers = new ArrayBlockingQueue<AnimalObserver>(4);
	}
	@Override
	public void addAnimal(AnimalObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeAnimal(AnimalObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void noticeAllObservers() {
		for(AnimalObserver o : observers){
			o.display();
		}
	}

}
