package pattern.observer.demo;

public class Runner {
	public static void main(String[] args) {
		AnimalSubject subject = new AnimalSubjectImpl();
		AnimalObserver cat = new AnimalObserverCat(subject);
		AnimalObserver dog = new AnimalObserverOfDog(subject);
		AnimalObserver pig = new AnimalObserverPig(subject);
		subject.removeAnimal(pig);
		subject.noticeAllObservers();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				
//			}
//		}).start();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				
//			}
//		}).start();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				
//			}
//		}).start();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				
//			}
//		}).start();
	}
}
