package person.txm.test.oncurrent;

public class FinalExample {
    int i;                            //��ͨ����
     int j;                      //final����
    static FinalExample obj;

    public void FinalExample () {     //���캯��
        i = 1;                        //д��ͨ��
        j = 2;                        //дfinal��
    }

    public static void writer () {    //д�߳�Aִ��
        obj = new FinalExample ();
    }

    public static void reader () {       //���߳�Bִ��
        FinalExample object = obj;       //����������
        int a = object.i;                //����ͨ��
        int b = object.j;                //��final��
    }
}
