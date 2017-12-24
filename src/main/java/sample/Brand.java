package sample;

import java.util.ArrayList;
import java.util.List;

public class Brand {
    private int id;
    private String brandName;
    private List<Model> modelsList;

    public Brand(String brandName)
    {
        this.brandName = brandName;
        this.modelsList = new ArrayList<>();
    }

    public Brand(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Model> getModelsList() {
        return modelsList;
    }

    public void setModelsList(List<Model> modelsList) {
        this.modelsList = modelsList;
    }

    public void addModel(Model newModel)
    {
        newModel.setBrand(this);
        this.modelsList.add(newModel);
    }

}
