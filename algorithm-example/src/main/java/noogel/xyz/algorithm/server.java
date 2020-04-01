package noogel.xyz.algorithm;

public class server {

    public static void main(String args[]){
        System.out.printf("Hello World!\n");

        final double CM_DEMO = 1.11;  // 常量
        System.out.printf(String.valueOf(CM_DEMO) + "\n");
        System.out.printf(CM_DEMO + "\n");

        double x = 4;
        double y = Math.sqrt(x);
        System.out.println(x + " " + y);

        System.out.println(Math.PI);

        System.out.println(String.valueOf(4 * 2.1123123));

        System.out.println((int) 2.9999);

        System.out.println("abcdefg".substring(0, 3));

        System.out.println("abc".equals("abc"));

        if("" != null && "".length() == 0){
            System.out.println("aaaaa");
        };

        Thread thread = new Thread(){

            @Override
            public void run(){
                System.out.println(this.getClass().toString());
            }
        };
        thread.start();
    }
}
