public class MyClass {

    public static void main(String[] args){
        //Fill in code here 
    }

    public static int factorial(int n){
        if( n < 0){
            throw new java.lang.RuntimeException("Number is negative");
        } else if( n == 0 ){
            return 1;
        } else {
            return n * factorial( n - 1 );
        }
    }
}