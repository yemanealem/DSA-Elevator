import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagrams {
     List<Integer> list = new ArrayList<>();
       int pCount[] = new int[26];
       int sCount[] = new int[26];

       for(char c: p.toCharArray()){
        pCount[c-'a']++;
       }
       int windowSize = p.length();
       for(int i=0;i<s.length();i++){
        sCount[s.charAt(i)-'a']++;

        if(i >= windowSize){
            sCount[s.charAt(i-windowSize)-'a']--;
        }
        if(Arrays.equals(pCount,sCount)){
            list.add(i-windowSize+1);
        }
       }
       return list;
}
