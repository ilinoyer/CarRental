package sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Brand {
    private int id;
    private String brandName;
    private Set<Model> modelsList = new HashSet<Model>();

    public Brand(String brandName)
    {
        this.brandName = brandName;
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

    public Set<Model> getModelsList() {
        return modelsList;
    }

    public void setModelsList(Set<Model> modelsList) {
        this.modelsList = modelsList;
    }

    public void addModel(Model newModel)
    {
        newModel.setBrand(this);
        this.modelsList.add(newModel);
    }

    public int getModelsNumber()
    {
        return modelsList.size() +1;
    }

    @Override
    public String toString() {
        return brandName;
    }
}
