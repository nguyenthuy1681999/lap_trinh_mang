public class Exp {
	public static void main(String[] args) {
		Double x = Double.parseDouble(args[0]);
		Integer n = Integer.parseInt(args[1]);
		Double emux = 1.0, t=x;
		int m =1;
		while(m<=n){
		    emux +=t;
		    m++;
		    t=t*(x/m);
		}
		Double result;
		result =Math.round(emux*100.0)/100.0;
		System.out.println(result);
    }
}