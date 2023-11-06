package JSSJk3003;

public class Human {
    private int height;
    private int weight;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws HeightTooTallException {
        if(height > 210){
            throw new HeightTooTallException();
        }

        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void disp(){
        System.out.println("height:"+height);
        System.out.println("weight:"+weight);
    }

}