package JSSJk3003;

public class JssJk3003 {

    public static void main(String[] args) {
        Human human = new Human();

        try{
            human.setHeight(211);
            human.setWeight(100);
            human.disp();
        }catch(HeightTooTallException e){
            System.out.println("不正な身長がセットされました");
        }

    }

}