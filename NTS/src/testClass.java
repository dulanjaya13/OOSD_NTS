/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pasindu Tennage
 */
public class testClass {
    public static void main(String [] args){
        String a = "Pakaya,Huththa,Kariya,Ponnaya";
        System.out.println(splitString(a));
    }
    public static String splitString(String string){
	if(string.contains(",")){
		String tempArray[] = string.split(",");
		String result = "";
		for(String s : tempArray){
			result = result+s+","+"\n";
		}
                
		result = result.substring(0,result.length()-2);
		return result;
	}else{
		return string;
	}
}
}
