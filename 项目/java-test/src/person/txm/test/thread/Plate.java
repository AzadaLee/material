package person.txm.test.thread;
import java.util.ArrayList;
import java.util.List;
 
public class Plate {
 
    List<Object> eggs = new ArrayList<Object>();
 
    public synchronized Object getEgg() {
        while(eggs.size() == 0) {
            try {
                wait();
            	//Thread.currentThread().sleep(10000);//sleep()�����ͷ�������ʱ����߳��ò����������˴������ø÷���
            } catch (InterruptedException e) {
            }
        }
 
        Object egg = eggs.get(0);
        eggs.clear();// �������
        notify();// �����������е�ĳ�̵߳���������
        System.out.println("�õ�����");
        return egg;
    }
 
    public synchronized void putEgg(Object egg) {
        while(eggs.size() > 0) {
            try {
                wait();
            	//Thread.currentThread().sleep(10000);//sleep()�����ͷ�������ʱ����߳��ò����������˴������ø÷���
            } catch (InterruptedException e) {
            }
        }
        eggs.add(egg);// ��������ż���
        notify();// �����������е�ĳ�̵߳���������
        System.out.println("���뼦��");
    }
    static class AddThread extends Thread{
        private Plate plate;
        private Object egg=new Object();
        public AddThread(Plate plate){
            this.plate=plate;
        }
       
        public void run(){
            for(int i=0;i<5;i++){
                plate.putEgg(egg);
            }
        }
    }
   
    static class GetThread extends Thread{
        private Plate plate;
        public GetThread(Plate plate){
            this.plate=plate;
        }
       
        public void run(){
            for(int i=0;i<5;i++){
                plate.getEgg();
            }
        }
    }
   
    public static void main(String args[]){
        try {
            Plate plate=new Plate();
            Thread add=new Thread(new AddThread(plate));
            Thread get=new Thread(new GetThread(plate));
            get.start();
            add.start();
           // add.join();
           // get.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("���Խ���");
    }
}